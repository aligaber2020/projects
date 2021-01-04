package com.app;

import java.util.List;

import com.app.datatransferobject.StockMarket;

public class StockMarketResponse {
	public String successMessage = "";
	public String errorMessage = "";
	public List<StockMarket>	 stockmarket;
	
	public StockMarketResponse(String success,String error,List<StockMarket> stockmarket) {
	this.errorMessage=(error!=null)?error:"";
	this.successMessage=(success!=null)?success:"";
	this.stockmarket=stockmarket;
	}

}
