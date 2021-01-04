package com.app.datatransferobject;

import java.io.Serializable;
import java.util.List;

import com.app.datatransferobject.FarmDTO.FarmDTOBuilder;
import com.app.domainobject.FarmDO;
import com.app.domainobject.ProductDO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoritesDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private Long itemId;
	private Long type;
	private List<ProductDTO> productDTOs;
	private List<FarmDTO> farmDTOs;
	
	public List<ProductDTO> getProductDTOs() {
		return productDTOs;
	}
	public void setProductDTOs(List<ProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}
	public List<FarmDTO> getFarmDTOs() {
		return farmDTOs;
	}
	public void setFarmDTOs(List<FarmDTO> farmDTOs) {
		this.farmDTOs = farmDTOs;
	}

	
	public FavoritesDTO(Long userId,Long itemId,Long type) {
		this.userId=userId;
		this.itemId=itemId;
		this.type=type;
	}
	public static FavoritesDTOBuilder newBuilder() {
		return new FavoritesDTOBuilder();
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}

	public FavoritesDTO() {}
	

public static class FavoritesDTOBuilder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long itemId;
	private Long type;
	
	public FavoritesDTOBuilder setUserId(Long userid) {
		this.userId = userid;
		return this;
	}
	public FavoritesDTOBuilder setItemId(Long  itemId) {
		this.itemId = itemId;
		return this;
	}
	public FavoritesDTOBuilder setType( Long  type) {
		this.type = type;
		return this;
	}
	public FavoritesDTO createFavoritesDTO() {
		return new FavoritesDTO(userId, itemId, type);
	}
	
}
}