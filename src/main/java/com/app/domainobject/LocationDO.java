package com.app.domainobject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_locations", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
public class LocationDO {

	@Id
	@GeneratedValue
	private Long ID;;
	@NotNull(message = "latitude can not be null!")
	private String latitude;
	@Column(nullable = false)
	@NotNull(message = "longitudecan not be null!")
	private String longitude;

	

	@SuppressWarnings("unused")
	public LocationDO(Long id,String latitude, String longitude) {
		this.ID=id;
		this.latitude = latitude;
		this.longitude = longitude;

	}

	public LocationDO() {
	}

	public LocationDO(Long id) {
		this.ID = id;
	}

	public static LocationDO newBuilder() {
		return new LocationDO();
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}


	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


}
