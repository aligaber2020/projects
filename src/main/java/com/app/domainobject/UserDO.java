package com.app.domainobject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "app_Users", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
public class UserDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private ZonedDateTime dateCreated = ZonedDateTime.now();
	@Column
	private String username;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String password;

	@Column(nullable = false)
	private Boolean deleted = false;

	@Column(nullable = false)
	private String e_mail;

	@Column(nullable = false ,unique=true)
	@NotNull(message = "mobile number can not be null!")
	@XmlElement(name ="mobile_number" )
	private String mobileNumber;
	@Column
    private String type ;
    @Column
    private byte[] image ;

	public UserDO(Long id,String username,String firstname,String lastname,String password,
			boolean deleted,String e_mail,String mobileNO, byte[] image,String type ) {
		this.id=id;
		this.dateCreated=dateCreated;
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.password=password;
		this.deleted=deleted;
		this.e_mail=e_mail;
		this.mobileNumber=mobileNO;
		this.type= type;
		this.image = image;
		
	}
	public UserDO() {}

	public Long getId() {
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}



	public String getMobile_number() {
		return mobileNumber;
	}

	public void setMobile_number(String mobile_number) {
		this.mobileNumber = mobile_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}



}
