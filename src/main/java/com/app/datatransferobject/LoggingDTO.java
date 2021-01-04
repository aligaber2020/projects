package com.app.datatransferobject;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.datatransferobject.UserDTO.UserDTOBuilder;
import com.app.domainobject.UserDO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoggingDTO {

	private Long id;
	private UserDO userId;
	private ZonedDateTime loginTime = ZonedDateTime.now();
	private ZonedDateTime lastActivationTime = ZonedDateTime.now();
	private String sessionId;
	private String mobile_number;
	private String Password;
	
	public LoggingDTO() {}
	
	public LoggingDTO(Long id,UserDO userId,ZonedDateTime loginTime,ZonedDateTime lastActivationTime,String sessionId,String mobile_number,String  Password) {
		this.id=id;
		this.userId = userId;
		this.loginTime = loginTime;
		this.lastActivationTime = lastActivationTime;
		this.sessionId = sessionId;
		this.mobile_number = mobile_number;
		this.Password = Password;
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
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}


	public static LoginDTOBuilder newBuilder() {
		return new LoginDTOBuilder();
	}
	 public static class LoginDTOBuilder
	    {
		    private Long id;
			private UserDO userId;
			private ZonedDateTime loginTime = ZonedDateTime.now();
			private ZonedDateTime lastActivationTime = ZonedDateTime.now();
			private String sessionId;
			private String mobile_number;
			private String Password;
			
			public LoginDTOBuilder setId(Long id) {
				this.id = id;
				return this;
			}
			
			public LoginDTOBuilder setUserId(UserDO userId) {
				this.userId = userId;
				return this;
			}
			public LoginDTOBuilder setLoginTime(ZonedDateTime loginTime) {
				this.loginTime = loginTime;
				return this;
			}
			public LoginDTOBuilder setLastActivationTime(ZonedDateTime lastActivationTime) {
				this.lastActivationTime = lastActivationTime;
				return this;
			}
			public LoginDTOBuilder setSessionId(String sessionId) {
				this.sessionId = sessionId;
				return this;
			}
			public LoginDTOBuilder setMobile_number(String username) {
				this.mobile_number = username;
				return this;
			}
			public LoginDTOBuilder setPassword(String password) {
				Password = password;
				return this;
			}
		    public LoggingDTO createSessionDTO()
		    {
		        return new LoggingDTO(id,userId,loginTime,lastActivationTime,sessionId,mobile_number,Password);
		    }

	    }

}
