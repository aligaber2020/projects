package com.app.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.app.datatransferobject.CategoryDTO;
import com.app.domainobject.CategoryDO;
import com.app.domainobject.FarmDO;

public class CategeoryMapper {
	
	public static CategoryDO makeCategeoryDO (CategoryDTO categoryDTO) {
		
		return new CategoryDO (categoryDTO.getId(),categoryDTO.getCategoryName(),categoryDTO.getDescription(),categoryDTO.getType());
	}

	public static CategoryDTO makeCategeoryDTO (CategoryDO categoryDO) {
		CategoryDTO.CategeoryDTOBuilder categeoryDTOBuilder = CategoryDTO.newBuilder().setId(categoryDO.getId()).setCategoryName(categoryDO.getCategoryName())
				.setDescription(categoryDO.getDescription()).setType(categoryDO.getType());
		
		return categeoryDTOBuilder.createCategeoryDTO();
		
	}
	public static List<CategoryDTO> makeCategoryDTOList(Collection<CategoryDO> CategoryDOs){
		return CategoryDOs.stream().map(CategeoryMapper::makeCategeoryDTO).collect(Collectors.toList());

		
	}
}
