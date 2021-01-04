package com.app.datatransferobject;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
	private Long id;
	private String categoryName;
	private String description;
	private int type;
	
	
	public static CategeoryDTOBuilder newBuilder() {
		return new CategeoryDTOBuilder();
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

	public CategoryDTO() {}
	public CategoryDTO(Long id, String categoryName, String description,int type) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.description = description;
		this.type=type;
	}
	
	public static class CategeoryDTOBuilder {
		
		private Long id;
		private String categoryName;
		private String description;
		private int type;
		
		public CategeoryDTOBuilder setId(Long id) {
			this.id = id;
			return this;

		}
		public CategeoryDTOBuilder setCategoryName(String categoryName) {
			this.categoryName = categoryName;
			return this;

		}
		public CategeoryDTOBuilder setDescription(String description) {
			this.description = description;
			return this;

		}
		public CategeoryDTOBuilder setType(int type) {
			this.type = type;
			return this;
		}
		
		public CategoryDTO createCategeoryDTO() {
			return new CategoryDTO(id, categoryName, description, type);
		}

		
		
	}


}
