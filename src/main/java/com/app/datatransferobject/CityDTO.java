package com.app.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDTO {

	private Long ID;
	private String CITYNAME;
	private Long countryID;
	private String ALIAS;
	private String CODE;
	private String PHONE_NUMBER;
	private String FAX_NUMBER;
	private String DESCRIPTION;


	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}


	public String getALIAS() {
		return ALIAS;
	}

	public void setALIAS(String aLIAS) {
		ALIAS = aLIAS;
	}


	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	public String getFAX_NUMBER() {
		return FAX_NUMBER;
	}

	public void setFAX_NUMBER(String fAX_NUMBER) {
		FAX_NUMBER = fAX_NUMBER;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

}
