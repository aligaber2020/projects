package com.app.controller.mapper;

import java.time.ZonedDateTime;

import com.app.datatransferobject.LocationDTO;
import com.app.datatransferobject.LocationDTO.LocationDTOBuilder;
import com.app.domainobject.LocationDO;


public class LocationMapper {
	public static LocationDO makeLocationDO(LocationDTO LocationDTO) {
		 return new LocationDO(LocationDTO.getID(), LocationDTO.getLatitude(),LocationDTO.getLongitude());
	}
	
	public static LocationDTO makelocationDTO(LocationDO LocationDO) {
		LocationDTOBuilder locationBuilder=LocationDTO.newBuilder().setID(LocationDO.getID()).setLatitude(LocationDO.getLatitude())
				.setLongitude(LocationDO.getLongitude());
		
		 return locationBuilder.createLocationDTO();
	}


}
