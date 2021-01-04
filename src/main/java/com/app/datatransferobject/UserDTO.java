package com.app.datatransferobject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.app.domainobject.LocationDO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    
    private String firstname;
    
    private String lastname;
    
    private String password;

    private Boolean deleted = false;
        
    private String e_mail;

    private String mobile_number; 
    
	private String type;
    
    private byte[] image;
    


    private UserDTO()
    {}


    public  UserDTO(
        Long id,String username ,String firstname, String lastname, String password, Boolean deleted, 
        String mobile_number, String e_mail,String type,byte[]image)
    {
        this.id = id;
        this.username=username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile_number = mobile_number;
        this.password = password;
        this.deleted = deleted;
        this.e_mail=e_mail;
        this.type = type;
        this.image = image;
    }


	public static UserDTOBuilder newBuilder() {
		return new UserDTOBuilder();
	}
//    public UserDTO(
//    		ZonedDateTime dateCreated, String firstname, String username ,String lastname, String password, Boolean deleted, String gender,
//        String phone_number,String mobile_number, String e_mail)
//    {
//        this.dateCreated = dateCreated;
//        this.username=username;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.mobile_number = mobile_number;
//        this.password = password;
//        this.deleted = deleted;
//    }
    public Long getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}


	public String getPassword() {
		return password;
	}


	public Boolean getDeleted() {
		return deleted;
	}

	public String getE_mail() {
		return e_mail;
	}

	public String getMobile_number() {
		return mobile_number;
	}
	
    public String getType() {
		return type;
	}


	public byte[] getImage() {
		return image;
	}
	 public static class UserDTOBuilder
	    {
			private Long id;
		    private String username;
		    private String firstname;		    
		    private String lastname;		    
		    private String password;
		    private Boolean deleted = false;		    
		    private String gender;		    
		    private String e_mail;
		    private String phone_number;		    
		    private String mobile_number; 
			private String type;
		    private byte[] image;
		    
		    public UserDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public UserDTOBuilder setUsername(String username) {
			this.username = username;
			return this;
		}
		public UserDTOBuilder setFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}
		public UserDTOBuilder setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}
		public UserDTOBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
		public UserDTOBuilder setDeleted(Boolean deleted) {
			this.deleted = deleted;
			return this;
		}
		public UserDTOBuilder setGender(String gender) {
			this.gender = gender;
			return this;
		}
		public UserDTOBuilder setE_mail(String e_mail) {
			this.e_mail = e_mail;
			return this;
		}
		public UserDTOBuilder setPhone_number(String phone_number) {
			this.phone_number = phone_number;
			return this;
		}
		public UserDTOBuilder setMobile_number(String mobile_number) {
			this.mobile_number = mobile_number;
			return this;
		}
	    public UserDTOBuilder setType(String type) {
			this.type = type;
			return this;
		}

		public UserDTOBuilder setImage(byte[] image) {
			this.image = image;
			return this;
		}
	      public UserDTO createUserDTO()
	        {
	            return new UserDTO( id, username, firstname,  lastname,  password,  deleted, mobile_number,  e_mail,type,image);
	        }
	
	    }



   
}
