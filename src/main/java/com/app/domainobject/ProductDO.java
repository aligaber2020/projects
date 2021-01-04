package com.app.domainobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "app_product", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = {
		"id" }))

public class ProductDO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String productName;
	@Column
	private Long userID;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column
	private String code;
	@Column
	private String description;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date dateCreated=new Date();
	private Long categoryID;
	@Transient
	public Double distance;
	@Transient
	private List<byte[]>   itemImage;
	@Column(length = 500)
	private String path;
	@Column
	private String phoneNo;
	@Column
	private BigDecimal price;
	@Column
	private Double quantity;
	@Column
	private Long postalCode;
	@Column
	private boolean type; //0 local , 1 external ..
	private String userName;
	private String userPhone;
	@Column
	private String categoryname;
	private String[] pathes; 
	@Column
	private String unit;
	@Column
	private boolean is_deleted;
	@ManyToOne
	private FavoritesDO favoritesDO;
	
	@Column
	private boolean is_favorit;
	@Column
	private String measurement;
	
	@Transient
	private MultipartFile [] files;

	public MultipartFile [] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile [] files) {
		this.files = files;
	}

	public ProductDO() {}
	
	public ProductDO(Long id, String productName, Long userID, String code, String description,Long
			categoryID,String path,String phoneNo,BigDecimal price,Double quantity,Double longitude,Double latitude,
			Long postalCode,List<byte[]>   itemImage ,boolean type,String unit,String userName,String userPhone,
			String categoryname,boolean is_deleted,boolean is_favorite,String measurement,MultipartFile[] files) {
		this.id = id;
		this.productName = productName;
		this.userID = userID;
		this.code = code;
		this.description = description;
		this.categoryID=categoryID;
		this.path=path;
		this.phoneNo=phoneNo;
		this.price=price;
		this.quantity=quantity;
		this.longitude=longitude;
		this.latitude=latitude;
		this.postalCode=postalCode;
		this.itemImage=itemImage;
		this.type=type;
		this.unit=unit;
		this.userName=userName;
		this.userPhone=userPhone;
		this.categoryname=categoryname;
		this.is_deleted=is_deleted;
		this.is_favorit=is_favorite;
        this.measurement=measurement;
        this.files = files;
	}


	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
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
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}
	public List<byte[]>  getItemImage() {
		return itemImage;
	}

	public void setItemImage(List<byte[]>  itemImage) {
		this.itemImage = itemImage;
	}
	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	public String[]getPathes() {
		return pathes;
	}

	public void setPathes(String[]pathes) {
		this.pathes = pathes;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public boolean isIs_favorit() {
		return is_favorit;
	}

	public void setIs_favorit(boolean is_favorit) {
		this.is_favorit = is_favorit;
	}
	
	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

}
