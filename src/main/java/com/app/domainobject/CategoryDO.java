package com.app.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "app_Categories", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDO {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String categoryName;
	@Column
	private String description;
	@Column 
	private int type;

	public CategoryDO() {}


	public CategoryDO(Long id, String categoryName, String description, int type) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.description = description;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


}
