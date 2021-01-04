package com.app;

public class StringResponse {
	public String errorMessage="";
	
	public int errorCode=0;
	
	public StringResponse(int code,String error){
		this.errorMessage=(error!=null)?error:"";
		this.errorCode=(code>=0)?code:-1;
	}
	

}
