package com.app;

import com.app.datatransferobject.Categories;

public class CategeoryResponse {
	
	public String successMessage = "";
	public String errorMessage = "";
	public Categories categeories;
	
	public CategeoryResponse(String success,String error,Categories	 categeories) {
	this.errorMessage=(error!=null)?error:"";
	this.successMessage=(success!=null)?success:"";
	this.categeories=categeories;
	}

}
