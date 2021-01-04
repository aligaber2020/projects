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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "App_Farm", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
public class FarmDO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	@Column
	@NotNull(message = "farm name can not be null!")
	private String farmName;
	@Column
	private Double farmSize;
	@Column
	private  String farmType;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column
	private String farmShape;
	@Column
	private Long postalCode;
	@Column
	private BigDecimal price;
	@Transient
	private List<byte[]>   farmImage;
	@Column(length = 500)
	private String path;
	@Column
	private Date dateCreation;
	@Column
	private Long userID;
	@Column
	private String phoneNo;
	private String[] pathes; 
	@Column
	private String TypeName;
	private String userName;
	private String userPhone;
	private boolean is_deleted;
	@ManyToOne
	private FavoritesDO favoritesDO;
	@Column
	private boolean is_favorit;
	@Column
	private String measurement;
	@Transient
	private MultipartFile [] files;

	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public FarmDO() {}
	public FarmDO(Long ID,String farmName,Double farmSize, String farmType,Double latitude,Double longitude,String farmShape,
			Long postalCode,BigDecimal price,List<byte[]>   farmImage,String path,Date dateCreation,Long userID,String phoneNo,
			String TypeName,String userName,String userPhone,boolean is_deleted,boolean is_favorite,String measurement, MultipartFile [] files) {	
		this.dateCreation=dateCreation;
		this.farmImage=farmImage;
		this.farmName=farmName;
		this.farmShape=farmShape;
		this.farmSize=farmSize;
		this.farmType=farmType;
		this.ID=ID;
		this.longitude=longitude;
		this.latitude=latitude;
		this.postalCode=postalCode;
		this.path=path;
		this.price=price;
		this.userID=userID;
		this.phoneNo=phoneNo;
		this.TypeName=TypeName;
		this.userName=userName;
		this.userPhone=userPhone;
		this.is_deleted=is_deleted;
		this.is_favorit=is_favorite;
		this.measurement=measurement;
		this.files = files;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getFarmName() {
		return farmName;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	public Double getFarmSize() {
		return farmSize;
	}
	public void setFarmSize(Double farmSize) {
		this.farmSize = farmSize;
	}
	public  String getFarmType() {
		return farmType;
	}
	public void setFarmType( String farmType) {
		this.farmType = farmType;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getFarmShape() {
		return farmShape;
	}
	public void setFarmShape(String farmShape) {
		this.farmShape = farmShape;
	}
	public Long getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<byte[]>  getFarmImage() {
		return farmImage;
	}
	public void setFarmImage(List<byte[]>  farmImage) {
		this.farmImage = farmImage;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String[] getPathes() {
		return pathes;
	}
	public void setPathes(String[] pathes) {
		this.pathes = pathes;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
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

