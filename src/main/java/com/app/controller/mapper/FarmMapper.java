package com.app.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.app.datatransferobject.FarmDTO;
import com.app.domainobject.FarmDO;

public class FarmMapper {
	
	public static FarmDO makeFarmDO(FarmDTO farmDTO) {
		 return new FarmDO(farmDTO.getID(),
		farmDTO.getFarmName(),
		 farmDTO.getFarmSize(),farmDTO.getFarmType(),farmDTO.getLatitude(),farmDTO.getLongitude(),farmDTO.getFarmShape(),
		 farmDTO.getPostalCode(),farmDTO.getPrice(),farmDTO.getFarmImage(),farmDTO.getPath(),
		 farmDTO.getDateCreation(),farmDTO.getUserID(),farmDTO.getPhoneNo(),farmDTO.getTypeName(),farmDTO.getUserName(),
		 farmDTO.getUserPhone(),farmDTO.isIs_deleted(),farmDTO.isIs_favorit(),farmDTO.getMeasurement(),farmDTO.getFiles());
	}
	public static FarmDTO makeFarmDTO(FarmDO farmDO) {
	
		FarmDTO.FarmDTOBuilder FarmDTOBuilder=FarmDTO.newBuilder().setID(farmDO.getID()).setFarmName(farmDO.getFarmName()).setFarmSize(farmDO.getFarmSize()).setFarmType(farmDO.getFarmType()).setLatitude(farmDO.getLatitude())
				.setLongitude(farmDO.getLongitude()).setFarmShape(farmDO.getFarmShape()).setPostalCode(farmDO.getPostalCode()).setPrice(farmDO.getPrice()).setFarmImage(farmDO.getFarmImage())
				.setDateCreation(farmDO.getDateCreation()).setUserID(farmDO.getUserID()).setPath(farmDO.getPath()).setPhoneNo(farmDO.getPhoneNo()).
				setPathes(farmDO.getPathes()).setTypeName(farmDO.getTypeName()).setUserName(farmDO.getUserName()).setUserPhone(farmDO.getUserPhone()).
				setIs_favorit(farmDO.isIs_favorit()).setIs_deleted(farmDO.isIs_deleted()).setIs_favorit(farmDO.isIs_favorit()).setMeasurement(farmDO.getMeasurement()).setFiles(farmDO.getFiles());
		
		 return FarmDTOBuilder.createFarmDTO();
	}

	public static List<FarmDTO> makeFarmDTOList(Collection<FarmDO> farms) {
		return farms.stream().map(FarmMapper::makeFarmDTO).collect(Collectors.toList());
	}

}
