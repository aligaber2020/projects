package com.app;


import com.app.datatransferobject.UserDTO;

public class LoginStringResponse {
public int errorCode=0;
	
	public String errorMessage="";
	
	public UserDTO user;
	public LoginStringResponse(int code,String errorMessage,UserDTO user){
		this.errorMessage=(errorMessage!=null)?errorMessage:"";
		this.errorCode=(code>=0)?code:0;
		this.user=user;
		

}
}
