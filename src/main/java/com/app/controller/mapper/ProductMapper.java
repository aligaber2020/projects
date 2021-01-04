package com.app.controller.mapper;

import java.util.List;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.ProductDO;

public class ProductMapper {
	


	public static ProductDO makeProductDO(ProductDTO ProductDTO) {
		 return new ProductDO(ProductDTO.getId(),ProductDTO.getProductName(),
		 ProductDTO.getUserID(),ProductDTO.getCode(), 
		 ProductDTO.getDescription(),ProductDTO.getCategoryID(),
		 ProductDTO.getPath(),ProductDTO.getPhoneNo(),ProductDTO.getPrice(),ProductDTO.getQuantity(),
		 ProductDTO.getLongitude(),ProductDTO.getLatitude(),ProductDTO.getPostalCode(),ProductDTO.getItemImage(),
		 ProductDTO.isType(),ProductDTO.getUnit(),ProductDTO.getUserName(),ProductDTO.getUserPhone(),ProductDTO.getCategoryname(),
		 ProductDTO.isIs_deleted(),ProductDTO.isIs_favorit(),ProductDTO.getMeasurement(),ProductDTO.getFiles());
		 		 
		
	}

   

	public static ProductDTO makeProductDTO(ProductDO ProductDO) {
		
		ProductDTO.ProductDTOBuilder ProductDTOBuilder=ProductDTO.newBuilder().setId(ProductDO.getId()).setProductName(ProductDO.getProductName())
				.setUserID(ProductDO.getUserID()).setCode(ProductDO.getCode()).setDescription(ProductDO.getDescription()).setPath(ProductDO.getPath())
				.setQuantity(ProductDO.getQuantity()).setPrice(ProductDO.getPrice()).setPhoneNo(ProductDO.getPhoneNo()).setItemImage(ProductDO.getItemImage()).setType(ProductDO.isType())
				.setLongitude(ProductDO.getLongitude()).setLatitude(ProductDO.getLatitude()).setPostalCode(ProductDO.getPostalCode()).setDateCreated(ProductDO.getDateCreated())
				.setCategoryID(ProductDO.getCategoryID()).setCategoryname(ProductDO.getCategoryname()).setPathes(ProductDO.getPathes()).setUnit(ProductDO.getUnit()).
				setUserName(ProductDO.getUserName()).setUserPhone(ProductDO.getUserPhone()).setIs_favorit(ProductDO.isIs_favorit())
				.setIs_deleted(ProductDO.isIs_deleted()).setMeasurement(ProductDO.getMeasurement()).setFiles(ProductDO.getFiles());
		
				
		 return ProductDTOBuilder.createProductDTO();
	}


	public static List<ProductDTO> makeProductDTOList(Collection<ProductDO> items) {
		return items.stream().map(ProductMapper::makeProductDTO).collect(Collectors.toList());
	}


}
