package com.app;

import java.util.List;

import com.app.datatransferobject.FarmDTO;

public class FarmResponse {
public String errorMessage="";
	
	public int errorCode=0;
	
	public List<FarmDTO> item;
	public FarmResponse(int code,String error,List<FarmDTO> item){
		this.errorMessage=(error!=null)?error:"";
		this.errorCode=(code>=0)?code:-1;
		this.item=item;
		

}
}
