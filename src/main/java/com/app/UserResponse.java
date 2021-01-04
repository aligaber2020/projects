package com.app;

import java.util.List;

import com.app.datatransferobject.UserDTO;

public class UserResponse {
public String successMessage="";
	
	public String errorMessage="";
	
	public List<UserDTO> users;
	public UserResponse(String success,String error,List<UserDTO> users){
		this.errorMessage=(error!=null)?error:"";
		this.successMessage=(success!=null)?success:"";
		this.users=users;
		

}
}
