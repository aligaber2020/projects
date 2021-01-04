package com.app.datatransferobject;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.domainobject.CityDO;
import com.app.domainobject.CountryDO;
import com.app.domainobject.LocationDO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ID;
	private String latitude;
	private String longitude;
	private Long postalCode;
	
	public LocationDTO() {}
	public LocationDTO(Long id,String latitude,String longitud,Long postalCode) {
		this.ID=id;
		this.latitude=latitude;
		this.longitude=longitude;
		this.postalCode=postalCode;
		
	}
	public static LocationDTOBuilder newBuilder() {
		return new  LocationDTOBuilder();
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
	public Long getPostalCode() {
		return postalCode;
	}

	  public static class LocationDTOBuilder
	{
		private String latitude;
		private String longitude;
		private Long id;
		private Long postalCode;

		
		public LocationDTOBuilder() {
		}

		public LocationDTOBuilder(Long id) {
			this.id = id;
		}

		public static LocationDTOBuilder newBuilder() {
			return new LocationDTOBuilder();
		}


	public LocationDTOBuilder setID(Long iD) {
			id = iD;
			return this;
		}

		public LocationDTOBuilder setLatitude(String latitude) {
			this.latitude = latitude;
			return this;
		}

		public LocationDTOBuilder setLongitude(String longitude) {
			this.longitude = longitude;
			return this;
		}
		public LocationDTOBuilder setPostalCode(Long postalCode) {
			this.postalCode = postalCode;
			return this;
		}

	       public LocationDTO createLocationDTO()
	        {
	            return new LocationDTO(id,latitude, longitude,postalCode);
	        }

	}


}