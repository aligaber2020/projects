	package com.app.datatransferobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.SessionInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO extends SessionInfo implements Serializable{
	private Long id;
	private String productName;
	private Long userID;
	private String code;
	private String description;
	private Long categoryID;
	@JsonIgnore
	private String path;
	private String phoneNo;
	private BigDecimal price;
	private Double quantity;
	private Double longitude;
	private Double latitude;
	private Date dateCreated;
	private Long postalCode;
	private List<byte[]>  itemImage;
	private boolean type;
	private String categoryname;
	private String[] pathes; 
	private String unit;
	private String userName;
	private String userPhone;
	private boolean is_deleted;
	private boolean is_favorit;
	private String measurement;
	public MultipartFile []getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}


	private MultipartFile[] files;
	public static ProductDTOBuilder newBuilder() {
		return new ProductDTOBuilder();
	}
	private ProductDTO() {
	}

	public ProductDTO(Long id, String productName, Long userID,String code, String description,Long 
			categoryId,String path,String phoneNo,BigDecimal price ,Double quantity,Double longitude,Double latitude,Long postalCode,List<byte[]> 
	itemImage,boolean type,String categoryname,String[] pathes,String unit,String userName,String userPhone,boolean is_deleted,
	boolean is_favorite, String measurement,MultipartFile [] files
){
		this.id = id;
		this.productName = productName;
		this.userID = userID;
		this.code = code;
		this.description = description;
		this.categoryID=categoryId;
		this.path=path;
		this.phoneNo=phoneNo;
		this.price=price;
		this.quantity=quantity;
		this.longitude=longitude;
		this.latitude=latitude;
		this.postalCode=postalCode;
		this.itemImage=itemImage;
		this.type=type;
		this.categoryname=categoryname;
		this.pathes=pathes;
		this.unit=unit;
		this.userName=userName;
		this.userPhone=userPhone;
		this.is_deleted=is_deleted;
		this.is_favorit=is_favorite;
		this.measurement=measurement;
		this.files= files;	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public Long getUserID() {
		return userID;
	}

	public String getCode() {
		return code;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Long getCategoryID() {
		return categoryID;
	}


	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public Long getPostalCode() {
		return postalCode;
	}
	public List<byte[]>  getItemImage() {
		return itemImage;
	}
	public boolean isType() {
		return type;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public String[] getPathes() {
		return pathes;
	}
	public String getUnit() {
		return unit;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}
	
	public boolean isIs_favorit() {
		return is_favorit;
	}

	public String getMeasurement() {
		return measurement;
	}


	public static class ProductDTOBuilder implements Serializable{
		private Long id;
		private String productName;
		private Long userID;
		private String code;
		private String description;
		private Long categoryID;
		private String path;
		private String phoneNo;
		private BigDecimal price;
		private Double quantity;
		private Double longitude;
		private Double latitude;
		private Date dateCreated;
		private Long postalCode;
		private List<byte[]>   itemImage;
		private boolean type;
		private String categoryname;
		private String[] pathes;
		private String unit;
		private String userName;
		private String userPhone;
		private boolean is_deleted;
		private boolean is_favorit;
		private String measurement;
		private MultipartFile [] files;
		
		public ProductDTOBuilder setFiles(MultipartFile [] files) {
			this.files = files;
			return this;
		}
		public ProductDTOBuilder setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
			return this;
		}
		public ProductDTOBuilder setCategoryID(Long categoryID) {
			this.categoryID=categoryID;
			return this;
		}
		public ProductDTOBuilder setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
			return this;
		}
		public ProductDTOBuilder setPrice(BigDecimal price) {
			this.price = price;
			return this;
		}
		public ProductDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		public ProductDTOBuilder setLongitude(Double longitude) {
			this.longitude = longitude;
			return this;
		}
		public ProductDTOBuilder setLatitude(Double latitude) {
			this.latitude = latitude;
			return this;
		}
		public ProductDTOBuilder setPath(String  path) {
			this.path = path;
			return this;
		}
		public ProductDTOBuilder setQuantity(Double quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public ProductDTOBuilder setProductName(String productName) {
			this.productName = productName;
			return this;
		}

		public ProductDTOBuilder setUserID(Long userID) {
			this.userID = userID;
			return this;
		}


		public ProductDTOBuilder setCode(String code) {
			this.code = code;
			return this;
		}

		public ProductDTOBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public ProductDTOBuilder setPostalCode(Long postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		public ProductDTOBuilder setItemImage(List<byte[]>  itemImage) {
			this.itemImage = itemImage;
			return this;
		}
		public ProductDTOBuilder setType(boolean type) {
			this.type = type;
			return this;
		}

		public ProductDTOBuilder setCategoryname(String categoryname) {
			this.categoryname = categoryname;
			return this;
		}
		public ProductDTOBuilder setPathes(String[] pathes) {
			this.pathes = pathes;
			return this;
		}
		public ProductDTOBuilder setUnit(String unit) {
			this.unit = unit;
			return this;
		}
		public ProductDTOBuilder setUserPhone(String userPhone) {
			this.userPhone = userPhone;
			return this;
		}
		public ProductDTOBuilder setUserName(String userName) {
			this.userName = userName;
			return this;
		}
		public ProductDTOBuilder setIs_deleted(boolean is_deleted) {
			this.is_deleted = is_deleted;
			return this;
		} 
		
		public ProductDTOBuilder setIs_favorit(boolean is_favorit) {
			this.is_favorit = is_favorit;
			return this;
		}
		public ProductDTOBuilder setMeasurement(String measurement) {
			this.measurement = measurement;
			return this;
		}
		
		public ProductDTO createProductDTO() {
			return new ProductDTO(id, productName, userID, code, description,categoryID,path,phoneNo,
					price,quantity,longitude,latitude,postalCode,itemImage,type,categoryname,pathes,unit,userName,
					userPhone,is_deleted,is_favorit,measurement,files);
		}

	}

}
