package com.app.domainobject;

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

@Entity
@Table(name = "app_City", uniqueConstraints = @UniqueConstraint(name = "recid", columnNames = { "id" }))
public class CityDO {
	@Id
	@GeneratedValue
	private Long ID;
	@Column(nullable = false)
	@NotNull(message = "City name can not be null!")
	private String CITYNAME;
	@OneToOne
	@JoinColumn(name = "countryID", updatable = false)
	@NotNull(message = "Country can not be null!")
	private CountryDO countryID;
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
