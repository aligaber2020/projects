package com.app.service.product;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.StringResponse;
import com.app.controller.mapper.ProductMapper;
import com.app.dataaccessobject.ProductRepository;
import com.app.datatransferobject.GetNearestDTO;
import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.CategoryDO;
import com.app.domainobject.FarmDO;
import com.app.domainobject.LocationDO;
import com.app.domainobject.ProductDO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.Location.LocationService;
import com.app.service.Loggin.DefaultLogginService;
import com.app.service.favorites.FavoritesService;
import com.app.service.user.userService;
import com.app.util.FileService;

@Service
public class DefaultProductService implements ProductService{
    @Value("${spring.api.baseUrl}")
    private String baseUrl;
	@Autowired
	DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
	 final static int EarthRadius = 6371;

	 
    @Autowired
    FileService  fileService;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultLogginService.class);

    private final ProductRepository productRepository;

    @Autowired
    private userService userService;
    
    @Autowired
    private FavoritesService favoriteService;
    
    @Autowired
    private LocationService locationService;
    @Autowired
    ServletContext context;

    public DefaultProductService(final ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    

    /**
     * Creates a new login session.
     *
     * @param ProductDO
     * @return
     * @throws ConstraintsViolationException 
     */
    @Override
    public StringResponse create(ProductDO ProductDO) throws ConstraintsViolationException
    {
        ProductDO product;
        try
        {
        	int counter = 0;
        	String imagePath="";
            if(ProductDO.getFiles().length > 0) {
			for (MultipartFile file : ProductDO.getFiles()) {
				counter ++;
				imagePath = fileService.uploadFile(file,counter) + imagePath;
			}
			imagePath= imagePath.substring(0, imagePath.length()-1);
        	if(imagePath!=null&&!imagePath.equals("")&&!imagePath.equals("Error")) {
        		ProductDO.setPath(imagePath);
        	}
            }
        	UserDO user=findUserChecked(ProductDO.getUserID());
        	if(user==null) {
        		 return new StringResponse(400, "أنت غير مسجل ");
        	}
        	product = productRepository.save(ProductDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to item creation", e);
            return new StringResponse(400, "حدث خطأ ما !!!");
        }
        return new StringResponse(0, "تم التسجيل بنجاح");
    }

	private String createimage(String name ,List<byte[]>  img) {
    	try {
            Path path = null;
            int counter=0;
            String allPath="";
             String prefix=baseUrl;

      for(byte[] images:img) {
    	  counter++;
    	  Date tim=new Date();
    	  String imageName=tim.getTime()+counter+".jpg";
          path = Paths.get(context.getRealPath("")+imageName) ;

        File upl = new File(""+path);
        upl.createNewFile();
        FileOutputStream fout = new FileOutputStream(upl);
        fout.write(images);
        fout.close();
        
        allPath=prefix+imageName+","+allPath;
        }
        
        return allPath.substring(0, allPath.length()-1);
    	}catch(Exception ex) {
    		System.out.print(""+ex);
    		return "Error";
    	}

	}

	@Override
	public ProductDO find(Long productId) throws EntityNotFoundException {
		ProductDO productDO = productRepository.findOne(productId);
		return productDO;
	}

	@Override
	public void delete( Long productId) throws EntityNotFoundException {
	    ProductDO productDO = productRepository.findOne(productId);
		productDO.setIs_deleted(true);
		productRepository.save(productDO);
		
	}
	@Override
	public List<ProductDO> findAll(long userid,String name) {
		List<ProductDO> allItems = null;
		String str = " ";
	      String filter = "";
          if(name != null &&!name.equalsIgnoreCase(str) && !name.isEmpty()) {
          filter = "and p.product_name = '"+name+"'";
          }
		try {
		String sql="select p.*,u.firstname userName ,u.mobile_number userPhone from app_product p ,app_users u "
				+ " where  p.userid=u.id and p.is_deleted=false " + filter +" order by id desc";
		JdbcTemplate obj=new JdbcTemplate(dataSource);
		 allItems=obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<ProductDO>(ProductDO.class));
		 if(allItems!=null) {
			 for(ProductDO item:allItems) {
					boolean check= favoriteService.findByUserIdAndItemIdAndTypeId(userid,item.getId(),1L);
					
					item.setIs_favorit(check);
					List<byte[]>  img=new ArrayList<byte[]>();
				 if(item.getPath()!=null) {
					 item.setPathes(item.getPath().split(","));
					 
				 }

			 }
		 }
		 
		 
		return allItems;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return allItems;
		
		
	}
	
	@Override
	public List<ProductDO> findAllFavorites(Long userId) {
		List<ProductDO> allItems = null;
		List<ProductDO> favoriteItems = new ArrayList<ProductDO>();

		try {
		String sql="select p.*,u.firstname userName ,u.mobile_number userPhone from app_product p ,app_users u ,favorites f where f.item_id = p.id and f.user_id = u.id and type_id=1 " + 
				" and p.is_deleted=false and u.id="+userId+"";
		JdbcTemplate obj=new JdbcTemplate(dataSource);
		 allItems=obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<ProductDO>(ProductDO.class));
		 if(allItems!=null) {
			 for(ProductDO item:allItems) {

					 item.setIs_favorit(true);
					List<byte[]>  img=new ArrayList<byte[]>();
				 if(item.getPath()!=null) {
					 item.setPathes(item.getPath().split(","));
					 
				 }
				 
					 favoriteItems.add(item);
				 
			 }
		 }
		 		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return favoriteItems;
		
		
	}
	
	
	@Override
	public List<ProductDO> findAllAdv(Long userId) {
		List<ProductDO> allItems = null;
		List<ProductDO> favoriteItems = new ArrayList<ProductDO>();

		try {
		String sql="select p.*,u.firstname userName ,u.mobile_number userPhone from app_product p ,app_users u where  p.userid=u.id and p.is_deleted=false and u.id="+userId+"";
		JdbcTemplate obj=new JdbcTemplate(dataSource);
		 allItems=obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<ProductDO>(ProductDO.class));
		 if(allItems!=null) {
			 for(ProductDO item:allItems) {
					
					List<byte[]>  img=new ArrayList<byte[]>();
				 if(item.getPath()!=null) {
					 item.setPathes(item.getPath().split(","));
					 
				 }
					 favoriteItems.add(item);
				 
			 }
		 }
		 		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return favoriteItems;
		
		
	}
	
	@Override
	public List<ProductDO> findNearest(GetNearestDTO nearestReq) throws EntityNotFoundException {
		try {
			List<ProductDO> list = null;
			
		      MapSqlParameterSource parameters = new MapSqlParameterSource();
		      String filter = "";
             if(nearestReq.getName() !=null && !nearestReq.getName().equalsIgnoreCase("") && !nearestReq.getName().equalsIgnoreCase("null")) 
             filter = "and product_name like '%"+nearestReq.getName()+"%'";
             
            if(nearestReq.getCategoryName() !=null && !nearestReq.getCategoryName().equalsIgnoreCase("") && !nearestReq.getCategoryName().equalsIgnoreCase("null")) 
            	filter += " and categoryname like '%"+nearestReq.getCategoryName()+"%' ";
            	 
     		String sql="select p.*,u.firstname userName ,u.mobile_number userPhone from app_product p ,app_users u "
    				+ " where  p.userid=u.id and p.is_deleted=false " + filter +" order by id desc";
    		JdbcTemplate obj=new JdbcTemplate(dataSource);
    		List<ProductDO> itemsPoints = obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<ProductDO>(ProductDO.class));
    		 
			TreeMap<Double , ProductDO> itemsList = new TreeMap<Double, ProductDO>();
			if(itemsPoints != null) {
				
			for(ProductDO point : itemsPoints) {
				double distance = CalculateDistance(nearestReq.getLongitude(),nearestReq.getLatitude(),point.getLongitude(),point.getLatitude());
				itemsList.put(distance, point);
			}
			
			 list = new ArrayList<ProductDO>(itemsList.values());
			 
			 for(ProductDO item:list) {
				List<byte[]>  img=new ArrayList<byte[]>();
			 if(item.getPath()!=null) {
				 item.setPathes(item.getPath().split(","));
				 
			 }
		 }
			 
			 }
			return list;
		}catch(Exception ex) {
			System.out.println(""+ex);
			
		}
		return null;
	}


	@Override
	public StringResponse updateInfo(ProductDO ProductDO) throws EntityNotFoundException {
        try
        {
        	int counter = 0;
        	String imagePath="";
            if(ProductDO.getFiles().length > 0) {
			for (MultipartFile file : ProductDO.getFiles()) {
				counter ++;
				imagePath = fileService.uploadFile(file,counter) + imagePath;
			}
			imagePath= imagePath.substring(0, imagePath.length()-1);
        	if(imagePath!=null&&!imagePath.equals("")&&!imagePath.equals("Error")) {
        		ProductDO.setPath(imagePath);
        	}
            }
            UserDO user=findUserChecked(ProductDO.getUserID());
        	if(user==null) {
        		 return new StringResponse(400, "أنت غير مسجل ");
        	}
			productRepository.save(ProductDO);
	        
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to item creation", e);
            return new StringResponse(400, "حدث خطأ ما !!!");
        }
        return new StringResponse(0, "تم التعديل بنجاح");
		
	}
    
    private UserDO findUserChecked(Long userID) 
    {
    	try {
    	UserDO item = userService.findOne(userID);
    	return item;
    	}catch(EntityNotFoundException ex) {
    		System.out.println("");
    		return null;
    	}
        
    }
    
    private CategoryDO findCategoryChecked(Long catID) 
    {
    	try {
    		CategoryDO type = productRepository.findCategoryByid(catID);
    	return type;
    	}catch(EntityNotFoundException ex) {
    		return null;
    	}
        
    }


	@Override
	public LocationDO createLocation(LocationDO locationDO) throws ConstraintsViolationException {
		 LocationDO newLocation;
	        try
	        {
	        	newLocation=locationService.create(locationDO);
	        }
	        catch (DataIntegrityViolationException e)
	        {
	            LOG.warn("Some constraints are thrown due to user creation", e);
	            throw new ConstraintsViolationException(e.getMessage());
	        }
	        return newLocation;
	}


	private static Double CalculateDistance(double lon1,double lat1, double lon2,double lat2) {
	    Double latDistance = Math.toRadians(lat2 - lat1);
	    Double lonDistance = Math.toRadians(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    Double distance = EarthRadius * c;
	    return distance;
	}
 

}
