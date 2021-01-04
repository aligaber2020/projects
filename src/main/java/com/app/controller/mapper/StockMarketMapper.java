package com.app.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.app.datatransferobject.CategoryDTO;
import com.app.datatransferobject.StockMarket;
import com.app.domainobject.CategoryDO;
import com.app.domainobject.StockMarketDO;

public class StockMarketMapper {
public static StockMarketDO makeStockMarketDO (StockMarket stockMarketDO) {
		
		return new StockMarketDO (stockMarketDO.getItem(),stockMarketDO.getValue(),stockMarketDO.getMinValue(),stockMarketDO.getMaxValue(),stockMarketDO.getDate());
	}

	public static StockMarket makeStockMarket (StockMarketDO stockMarketDO) {
		StockMarket.StockMarketBuilder stockMarketBuilder = StockMarket.newBuilder().setItem(stockMarketDO.getItem()).setValue(stockMarketDO.getValue())
				.setMinValue(stockMarketDO.getMinValue()).setMaxValue(stockMarketDO.getMaxValue()).setDate(stockMarketDO.getDate());
		
		return stockMarketBuilder.createStockMarket();
		
	}
	public static List<StockMarket> makeStockMarketList(Collection<StockMarketDO> stockMarketDOs){
		return stockMarketDOs.stream().map(StockMarketMapper::makeStockMarket).collect(Collectors.toList());

		
	}

}
