package com.app.datatransferobject;

import java.io.Serializable;
import java.util.Date;

public class StockMarket {
	
	private String item;
	private String value;
	private String minValue;
	private String maxValue;
	private Date date;
	
	public StockMarket() {};
	
	public StockMarket(String item, String value, String minValue, String maxValue, Date date) {
		super();
		this.item = item;
		this.value = value;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.date = date;
	}
	
	public static StockMarketBuilder newBuilder() {
		return new StockMarketBuilder();
	} 
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}



public static class StockMarketBuilder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3017439200125134963L;

	private String item;
	private String value;
	private String minValue;
	private String maxValue;
	private Date date;
	
	

	
	public StockMarketBuilder setItem(String item) {
		this.item = item;
		return this;
	}

	public StockMarketBuilder setValue(String value) {
		this.value = value;
		return this;
	}

	public StockMarketBuilder setMinValue(String minValue) {
		this.minValue = minValue;
		return this;
	}

	public StockMarketBuilder setMaxValue(String maxValue) {
		this.maxValue = maxValue;
		return this;
	}

	public StockMarketBuilder setDate(Date date) {
		this.date = date;
		return this;
	}
	
	public StockMarket createStockMarket() {
		return new StockMarket(item, value, minValue, maxValue, date);
	}
}


}