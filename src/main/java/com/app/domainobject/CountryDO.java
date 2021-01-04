package com.app.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "app_Country", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
public class CountryDO {
	@Id
	@GeneratedValue
	private Long ID;
	@Column(nullable = false)
	@NotNull(message = "Country name can not be null!")
	private String COUNTRYNAME;
	@Column
	private String ALIAS;
	@Column
	private String CODE;
	@Column
	private String PHONE_NUMBER;
	@Column
	private String FAX_NUMBER;
	@Column
	private String DESCRIPTION;

}
