package com.app;

import java.util.List;

import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.ProductDO;

public class ProductResponse {
public String successMessage="";
	
	public String errorMessage="";
	
	public List<ProductDTO> item;
	public ProductResponse(String success,String error,List<ProductDTO> item){
		this.errorMessage=(error!=null)?error:"";
		this.successMessage=(success!=null)?success:"";
		this.item=item;
		

}
}
