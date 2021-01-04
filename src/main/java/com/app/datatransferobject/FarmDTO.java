package com.app.datatransferobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FarmDTO implements Serializable {
	
	private Long id;
	private String farmName;
	private Double farmSize;
	private  String farmType;
	private Double latitude;
	private Double longitude;
	private String farmShape;
	private Long postalCode;
	private BigDecimal price;
	private List<byte[]>   farmImage;
	@JsonIgnore
	private String path;
	@JsonIgnore
	private Date dateCreation;
	private Long userID;
	private String phoneNo;
	private String[] pathes;
	private String typeName;
	private String userName;
	private String userPhone;
	private boolean is_deleted;
	private boolean is_favorit;
	private String measurement;
	private MultipartFile [] files;

	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public static FarmDTOBuilder newBuilder() {
		return new FarmDTOBuilder();
	}
	private FarmDTO() {}
	public FarmDTO(Long ID,String farmName,Double farmSize, String farmType,Double latitude,Double longitude,String farmShape,
			Long postalCode,BigDecimal price,List<byte[]>   farmImage,String path,Date dateCreation,Long userID,String phoneNo,
			String[] pathes,String typeName,String userName,String userPhone,boolean is_deleted,boolean is_favorite,String measurement, MultipartFile[] files) {	
		this.dateCreation=dateCreation;
		this.farmImage=farmImage;
		this.farmName=farmName;
		this.farmShape=farmShape;
		this.farmSize=farmSize;
		this.farmType=farmType;
		this.id=ID;
		this.longitude=longitude;
		this.latitude=latitude;
		this.postalCode=postalCode;
		this.path=path;
		this.price=price;
		this.userID=userID;
		this.phoneNo=phoneNo;
		this.pathes=pathes;
		this.typeName=typeName;
		this.userName=userName;
		this.userPhone=userPhone;
		this.is_deleted=is_deleted;
		this.is_favorit=is_favorite;	
		this.measurement=measurement;
		this.files = files;

	}
	
	public Long getID() {
		return id;
	}
	public String getFarmName() {
		return farmName;
	}
	public Double getFarmSize() {
		return farmSize;
	}
	public  String getFarmType() {
		return farmType;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public String getFarmShape() {
		return farmShape;
	}
	public Long getPostalCode() {
		return postalCode;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public List<byte[]>  getFarmImage() {
		return farmImage;
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
	public Long getUserID() {
		return userID;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public String[] getPathes() {
		return pathes;
	}
	public String getTypeName() {
		return typeName;
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

public static class FarmDTOBuilder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long ID;
	private String farmName;
	private Double farmSize;
	private  String farmType;
	private Double latitude;
	private Double longitude;
	private String farmShape;
	private Long postalCode;
	private BigDecimal price;
	private List<byte[]>  farmImage;
	private String path;
	private Date dateCreation;
	private Long userID;
	private String phoneNo;
	private String[] pathes;
	private String typeName;
	private String userName;
	private String userPhone;
	private boolean is_deleted;
	private boolean is_favorit;
	private String measurement;
	private MultipartFile[] files;

	
	public FarmDTOBuilder setID(Long iD) {
		ID = iD;
		return this;
	}
	public FarmDTOBuilder setFarmName(String farmName) {
		this.farmName = farmName;
		return this;
	}
	public FarmDTOBuilder setFarmSize(Double farmSize) {
		this.farmSize = farmSize;
		return this;
	}
	public FarmDTOBuilder setFarmType( String farmType) {
		this.farmType = farmType;
		return this;
	}
	public FarmDTOBuilder setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	public FarmDTOBuilder setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	public FarmDTOBuilder setFarmShape(String farmShape) {
		this.farmShape = farmShape;
		return this;
	}
	public FarmDTOBuilder setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	public FarmDTOBuilder setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	public FarmDTOBuilder setFarmImage(List<byte[]>  farmImage) {
		this.farmImage = farmImage;
		return this;
	}
	public FarmDTOBuilder setPath(String path) {
		this.path = path;
		return this;
	}
	public FarmDTOBuilder setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
		return this;	
		}
	public FarmDTOBuilder setUserID(Long userID) {
		this.userID = userID;
		return this;
	}
	public FarmDTOBuilder setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
		return this;
	}
	public FarmDTOBuilder setPathes(String[] pathes) {
		this.pathes = pathes;
		return this;
	}
	public FarmDTOBuilder setTypeName(String typeName) {
		this.typeName = typeName;
		return this;
	}
	public FarmDTOBuilder setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}
	public FarmDTOBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public FarmDTOBuilder setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
		return this;
	}
	public FarmDTOBuilder setIs_favorit(boolean is_favorit) {
		this.is_favorit = is_favorit;
		return this;
	}
	public FarmDTOBuilder setMeasurement(String measurement) {
		this.measurement = measurement;
		return this;
	}

	public FarmDTOBuilder setFiles(MultipartFile[] files) {
		this.files = files;
		return this;
	}

	
	public FarmDTO createFarmDTO() {
		return new FarmDTO(ID, farmName, farmSize, farmType,latitude,longitude, farmShape,postalCode,price,
				farmImage,path,dateCreation,userID,phoneNo,pathes,typeName,userName,userPhone,is_deleted,is_favorit,measurement,files);
	}


}


}
