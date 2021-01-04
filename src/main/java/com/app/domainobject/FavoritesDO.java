package com.app.domainobject;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "favorites", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = {"id" }))

public class FavoritesDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private Long userId;
	@Column
	private Long itemId;
	@Column 
	private Long typeId;
	@OneToMany
	private List<ProductDO> productDOs;
	@OneToMany
	private List<FarmDO> farmDOs;
	
	
	public FavoritesDO() {}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long type) {
		this.typeId = type;
	}

	public List<ProductDO> getProductDOs() {
		return productDOs;
	}
	public void setProductDOs(List<ProductDO> productDOs) {
		this.productDOs = productDOs;
	}
	public List<FarmDO> getFarmDOs() {
		return farmDOs;
	}
	public void setFarmDOs(List<FarmDO> farmDOs) {
		this.farmDOs = farmDOs;
	}
	
	public FavoritesDO(Long userId,Long itemId,Long typeId) {
		this.userId=userId;
		this.itemId=itemId;
		this.typeId=typeId;

	}
	
}