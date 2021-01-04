package com.app.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "app_validationcode", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
public class ValidationCode {
	@Id
	@GeneratedValue
	private Long ID;
	@NotNull
	@Column
	private long userId;
	@NotNull
	@Column
	private String validationCode;
	@Column
	private boolean active;
	@Column
	private boolean tested;
	
	
	  public ValidationCode() { }
	    public ValidationCode(Long  userId, String validationCode, boolean active) {
	        this.userId = userId;
	        this.validationCode = validationCode;
	        this.active = active;
	    }
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isTested() {
		return tested;
	}
	public void setTested(boolean tested) {
		this.tested = tested;
	}

}
