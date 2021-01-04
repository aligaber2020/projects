package com.app.service.categeory;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.StockMarketResponse;
import com.app.controller.mapper.CategeoryMapper;
import com.app.controller.mapper.StockMarketMapper;
import com.app.dataaccessobject.CategeoryRepository;
import com.app.dataaccessobject.StockMarketRepository;
import com.app.datatransferobject.Categories;
import com.app.datatransferobject.CategoryDTO;
import com.app.datatransferobject.StockMarket;
import com.app.domainobject.CategoryDO;
import com.app.domainobject.StockMarketDO;
import com.app.service.Loggin.DefaultLogginService;

@Service
public class DefaultCategeoryService{

	private  CategeoryRepository categeoryRepository;
	private StockMarketRepository stockMarketRepository;
	private ModelMapper modelMapper;

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultLogginService.class);

	
	@Autowired
	public DefaultCategeoryService ( final CategeoryRepository categeoryRepository,final  ModelMapper modelMapper,final StockMarketRepository stockMarketRepository ) {
		this.categeoryRepository=categeoryRepository;
		this.stockMarketRepository = stockMarketRepository;
		this.modelMapper=modelMapper;
	}
	public DefaultCategeoryService(){}

	public  Categories findAll() {
		
		List<CategoryDTO> fruites = CategeoryMapper.makeCategoryDTOList(categeoryRepository.findByType(1));
		List<CategoryDTO> vegetables = CategeoryMapper.makeCategoryDTOList(categeoryRepository.findByType(2));
		List<CategoryDTO> others = CategeoryMapper.makeCategoryDTOList(categeoryRepository.findByType(3));
		
		Categories allCategories = new  Categories();
		allCategories.setFruites(fruites);
		allCategories.setOthers(others);
		allCategories.setVegetables(vegetables);

		
		return allCategories;
	}
	
	
	public StockMarketResponse findStockMarket() throws ParseException {
		
			List<StockMarket> stockMarketList = StockMarketMapper.makeStockMarketList((Collection<StockMarketDO>) stockMarketRepository.findAll());  		

			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyy-MM-dd");
			String newDate = sdf.format(stockMarketList.get(0).getDate());
			stockMarketList.get(0).setDate(Date.valueOf(newDate));

		  return new StockMarketResponse("success", "", stockMarketList);
    	  
	}
	
	@Transactional
	public void loadData() {
		try {
			
			
			
			//1- trncate table
			stockMarketRepository.truncateStockMarketDO();
			//2- get data
    		List<StockMarketDO> stockMarketList = new ArrayList<StockMarketDO>();
    		for(int n=1; n<=5;n++) {
    			String url = "https://www.bashaier.net/prices/?page="+n;
    	       Document doc = Jsoup.connect(url).get();
    	       Elements tableElements = doc.select("table");       
    	        List<Node> tableRows = tableElements.get(0).childNodes().get(3).childNodes();
    	        int numRows = tableRows.size();
    	        for (int i = 1; i < numRows; i++) {
    	            Node row = tableRows.get(i);
    	            if (row instanceof Element) {
    	            	Node item = row.childNode(1).childNode(0);
    	            	Node value = row.childNode(3).childNode(0);
    	            	Node minValue = row.childNode(5).childNode(0);
    	            	Node maxValue = row.childNode(7).childNode(0);
    	            	Node date = row.childNode(9).childNode(0);
    	            	StockMarketDO stockMarket=new StockMarketDO(item.toString(),value.toString(),minValue.toString(),maxValue.toString(),Date.valueOf(date.toString()));
    	            	stockMarketList.add(stockMarket);
    	            }
    	           
    	        }  
    	      //3- save
    	        stockMarketRepository.save(stockMarketList);
    		}
    	     } catch (IOException e) {

    	     }
	}

	{
	 Timer timer = new Timer ();

	TimerTask t = new TimerTask () {
	    @Override
	    public void run () {
	    	loadData();
	    }
	};

	timer.schedule (t, 0l, 1000*60*60*24);
	}

}
