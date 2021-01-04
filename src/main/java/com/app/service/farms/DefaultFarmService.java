package com.app.service.farms;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.StringResponse;
import com.app.controller.mapper.FarmMapper;
import com.app.dataaccessobject.FarmRepository;
import com.app.datatransferobject.FarmDTO;
import com.app.datatransferobject.GetNearestDTO;
import com.app.domainobject.CategoryDO;
import com.app.domainobject.LocationDO;
import com.app.domainobject.ProductDO;
import com.app.domainobject.FarmDO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.Location.LocationService;
import com.app.service.Loggin.DefaultLogginService;
import com.app.service.favorites.FavoritesService;
import com.app.service.user.userService;
import com.app.util.FileService;
import com.sun.javafx.collections.MappingChange.Map;
@Service
public class DefaultFarmService implements FarmService{
    @Value("${spring.api.baseUrl}")
    private String baseUrl;
	@Autowired
	DataSource dataSource;
	@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    FileService  fileService;
    
	 final static int EarthRadius = 6371;
	 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultLogginService.class);

    @Autowired
    private final FarmRepository farmRepository;

    @Autowired
    private userService userService;
    
    
    @Autowired
    private FavoritesService favoriteService;
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
    ServletContext context;

    @Autowired
    public DefaultFarmService(final FarmRepository farmRepository)
    {
        this.farmRepository = farmRepository;
    }


    /**
     * Creates a new login session.
     *
     * @param FarmDO
     * @return
     * @throws ConstraintsViolationException 
     */
    @Override
    public StringResponse create(FarmDO FarmDO) throws ConstraintsViolationException
    {
        FarmDO login;
        String imagePath = "";
        try
        {
        	int counter = 0;
        	if(FarmDO.getFiles().length > 0) {
 			for (MultipartFile file : FarmDO.getFiles()) {
 				counter++;
 				imagePath = fileService.uploadFile(file,counter) + imagePath;
			}
			imagePath= imagePath.substring(0, imagePath.length()-1);
        	if(imagePath!=null&&!imagePath.equals("")&&!imagePath.equals("Error")) {
        		FarmDO.setPath(imagePath);
        	}
        	}
        	UserDO user=findUserChecked(FarmDO.getUserID());
        	if(user==null) {
        		return new StringResponse(400, "أنت غير مسجل");
        	}
        	FarmDO.setDateCreation(new Date());
        	login = farmRepository.save(FarmDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to item creation", e);
            return new StringResponse(400, "حدث خطأ ما");
        }
         return new StringResponse(0,"تم التسجيل بنجاح");
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
	public FarmDO find(Long productId) throws EntityNotFoundException {
		FarmDO FarmDO =  farmRepository.findOne(productId);
		return FarmDO;
	}

	@Override
	public void delete( Long farmId) throws EntityNotFoundException {
		
		FarmDO farmDO = farmRepository.findOne(farmId);
		if(farmDO != null) {
			farmDO.setIs_deleted(true);
		farmRepository.save(farmDO);
		}
		
	}


	@Override
	public List<FarmDO> findAll(long userid) {
		List<FarmDO> allItems = null;
		try {

			String sql="select p.* ,u.firstname userName ,u.mobile_number userPhone from app_farm p , app_users u where "
					+ " p.userid=u.id and p.is_deleted=false order by id desc";
			JdbcTemplate obj=new JdbcTemplate(dataSource);
			 allItems=obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<FarmDO>(FarmDO.class));
			 if(allItems!=null) {
				 for(FarmDO item:allItems) {
					 List<byte[]>  img=new ArrayList<byte[]>();
						item.setIs_favorit(favoriteService.findByUserIdAndItemIdAndTypeId(userid,item.getID(),2L));
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
	public List<FarmDO> findAllFavorites(Long userId) {
		
		List<FarmDO> favoriteItems = new ArrayList<FarmDO>();
		List<FarmDO> allItems = null;
		try {			
			String sql="select p.*,u.firstname userName ,u.mobile_number userPhone from app_farm p ,app_users u ,favorites f where f.item_id = p.id and f.user_id = u.id and type_id=2 " + 
					" and p.is_deleted=false and u.id="+userId+"";
			
			JdbcTemplate obj=new JdbcTemplate(dataSource);
			 allItems=obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<FarmDO>(FarmDO.class));
			 if(allItems!=null) {
				 for(FarmDO item:allItems) {
					 
					 
					 List<byte[]>  img=new ArrayList<byte[]>();
						item.setIs_favorit(true);
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
	public List<FarmDO> findAllAdv(Long userId) {
		
		List<FarmDO> favoriteItems = new ArrayList<FarmDO>();
		List<FarmDO> allItems = null;
		try {
			String sql="select p.* ,u.firstname userName ,u.mobile_number userPhone from app_farm p , app_users u where  p.userid=u.id and p.is_deleted=false and u.id="+userId+"";
			JdbcTemplate obj=new JdbcTemplate(dataSource);
			 allItems=obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<FarmDO>(FarmDO.class));
			 if(allItems!=null) {
				 for(FarmDO item:allItems) {
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
	public List<FarmDO> findNearest(double  longitude,double latitude) throws EntityNotFoundException {
		try {
			List<FarmDO> list = null;
			String sql="select p.* ,u.firstname userName ,u.mobile_number userPhone from app_farm p , app_users u where "
					+ " p.userid=u.id and p.is_deleted=false";
			JdbcTemplate obj=new JdbcTemplate(dataSource);
			List<FarmDO> farmPoints = obj.query(sql, new Object[] {}, new BeanPropertyRowMapper<FarmDO>(FarmDO.class));
			 
			TreeMap<Double , FarmDO> farmsList = new TreeMap<Double, FarmDO>();
			if(farmPoints != null) {
				
			for(FarmDO point : farmPoints) {
				double distance = CalculateDistance(longitude,latitude,point.getLongitude(),point.getLatitude());
				farmsList.put(distance, point);
			}
			
			 list = new ArrayList<FarmDO>(farmsList.values());

			 for(FarmDO item:list) {
				 List<byte[]>  img=new ArrayList<byte[]>();
				 if(item.getPath()!=null) {
					 item.setPathes(item.getPath().split(","));
				 }
			 }
			}
			return list ;
		}catch(Exception ex) {
			System.out.println(""+ex);
			
		}
		return null;
	}


	@Override
	public StringResponse updateInfo(FarmDO farmDO) throws EntityNotFoundException {
	        String imagePath = "";
	        try
	        {
	        	int counter = 0;
	        	if(farmDO.getFiles().length > 0) {
	 			for (MultipartFile file : farmDO.getFiles()) {
	 				counter ++;
	 				imagePath = fileService.uploadFile(file,counter) + imagePath;
				}
				imagePath= imagePath.substring(0, imagePath.length()-1);
	        	if(imagePath!=null&&!imagePath.equals("")&&!imagePath.equals("Error")) {
	        		farmDO.setPath(imagePath);
	        	}
	        	}
	        	UserDO user=findUserChecked(farmDO.getUserID());
	        	if(user==null) {
	        		return new StringResponse(400, "أنت غير مسجل");
	        	}
	        	farmDO.setDateCreation(new Date());
	        	farmRepository.save(farmDO);
	        
        }
	        catch (DataIntegrityViolationException e)
	        {
	            LOG.warn("Some constraints are thrown due to item creation", e);
	            return new StringResponse(400, "حدث خطأ ما");
	        }
	         return new StringResponse(0,"تم التعديل بنجاح");
		
	}
	
    private FarmDO findProductChecked(Long itemid) throws EntityNotFoundException
    {
    	FarmDO item = farmRepository.findOne(itemid);
        if (item == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + item);
        }
        return item;
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
    		String sql="select * from app_Categories where id=?";
    		JdbcTemplate obj=new JdbcTemplate(dataSource);
			CategoryDO type=(CategoryDO) obj.queryForObject(sql, new Object[] { catID }, new BeanPropertyRowMapper(CategoryDO.class));

//    		CategoryDO type = farmRepository.findCategoryByid(catID);
    	return type;
    	}catch(Exception ex) {
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
