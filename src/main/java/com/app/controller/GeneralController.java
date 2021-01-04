package com.app.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.CategeoryResponse;
import com.app.StockMarketResponse;
import com.app.controller.mapper.CategeoryMapper;
import com.app.controller.mapper.ProductMapper;
import com.app.datatransferobject.Categories;
import com.app.datatransferobject.CategoryDTO;
import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.CategoryDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.categeory.DefaultCategeoryService;

@RestController
@RequestMapping("v2")
public class GeneralController {
	@Autowired
	private DefaultCategeoryService defaultCategeoryService;
	
	  @GetMapping("/get-categeories")
	  public @ResponseBody CategeoryResponse findAllItems()
	      throws ConstraintsViolationException, EntityNotFoundException
	  {
		  Categories categories= defaultCategeoryService.findAll();
	    	if(categories!=null)
	      return new CategeoryResponse("success", null,categories);
		      return new CategeoryResponse(null, "fail",null);


	  	

	  }
	  @GetMapping("/stockmarket")
	  public @ResponseBody StockMarketResponse stockmarket()
	      throws ConstraintsViolationException, EntityNotFoundException, ParseException
	  {
         return defaultCategeoryService.findStockMarket();

	  	

	  }

}
