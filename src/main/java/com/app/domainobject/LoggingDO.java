package com.app.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "app_Loggin",uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = {"id"}))
public class LoggingDO {
	
	@Id
    @GeneratedValue
    private Long id;
	@OneToOne
	  @JoinColumn(name = "userId", updatable = false)
	  @NotNull(message = "user ID can not be null!")
		private UserDO userId;
		@Column(nullable = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		private ZonedDateTime loginTime = ZonedDateTime.now();
		@Column(nullable = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		private ZonedDateTime lastActivationTime = ZonedDateTime.now();
		@Column(nullable = false)
		private String sessionId;
		@Column
		private String mobile_number;
		private String password;
		
		public LoggingDO(Long id,UserDO userId,ZonedDateTime loginTime ,ZonedDateTime lastActivationTime,String mobile_number,String sessionId) {
			this.id=id;
			this.userId=userId;
			this.loginTime=loginTime;
			this.lastActivationTime=lastActivationTime;
			this.mobile_number=mobile_number;
			this.sessionId=sessionId;
		}
	
  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDO getUserId() {
		return userId;
	}
	public void setUserId(UserDO userId) {
		this.userId = userId;
	}
	public ZonedDateTime getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(ZonedDateTime loginTime) {
		this.loginTime = loginTime;
	}
	public ZonedDateTime getLastActivationTime() {
		return lastActivationTime;
	}
	public void setLastActivationTime(ZonedDateTime lastActivationTime) {
		this.lastActivationTime = lastActivationTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String username) {
		this.mobile_number = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
