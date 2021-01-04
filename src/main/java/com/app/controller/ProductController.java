package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.ProductResponse;
import com.app.StringResponse;
import com.app.controller.mapper.ProductMapper;
import com.app.datatransferobject.GetNearestDTO;
import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.ProductDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.product.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("v1/item")
public class ProductController {

	 @Autowired
    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createItem(@RequestPart("files") MultipartFile[] files,@RequestPart("ProductDTO") String productDTO) 
    		throws ConstraintsViolationException, JsonParseException, JsonMappingException, IOException
	{
    	ProductDTO product  = new ObjectMapper().readValue(productDTO, ProductDTO.class);
    	product.setFiles(files);
		ProductDO ProductDO = ProductMapper.makeProductDO(product);
		return productService.create(ProductDO);

	}





	@DeleteMapping("/{itemId}")
    public StringResponse deleteitem(@Valid @PathVariable long itemId) throws EntityNotFoundException
    {
        productService.delete(itemId);
		return new StringResponse(0,"success");

    }


    @PutMapping
    public StringResponse updateInfo(@RequestPart("files") MultipartFile[] files,@RequestPart("ProductDTO") String productDTO)
        throws ConstraintsViolationException, EntityNotFoundException, JsonParseException, JsonMappingException, IOException
    {
    	ProductDTO product  = new ObjectMapper().readValue(productDTO, ProductDTO.class);
    	product.setFiles(files);
		ProductDO ProductDO = ProductMapper.makeProductDO(product);
        return productService.updateInfo( ProductDO);
    }
    
   @GetMapping("/{userid}/{name}")
  public @ResponseBody ProductResponse findAllItems( @PathVariable long userid, @PathVariable String name)
      throws ConstraintsViolationException, EntityNotFoundException
  {
	   
    	List<ProductDTO> items= ProductMapper.makeProductDTOList(productService.findAll(userid,name));
      return new ProductResponse("success", null,items);

  	

  }
    @PostMapping
    @RequestMapping("/getNearestpoints")
  public ProductResponse findNearestItems( @Valid @RequestBody GetNearestDTO  nearsstReq)
      throws ConstraintsViolationException, EntityNotFoundException
  {
    	List<ProductDTO> items= ProductMapper.makeProductDTOList(productService.findNearest(nearsstReq));
      return new ProductResponse("success", null,items);

  	

  }

    @GetMapping({"/", "/templates"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	ModelAndView modelAndView = new ModelAndView("redirect:/"+name);
        model.addAttribute("name", name);
        return "hello";
    }
 
    @GetMapping
    @RequestMapping("/myFavorites/{userId}")
    public @ResponseBody ProductResponse getMyFavoriteItems(@PathVariable Long userId)
        throws ConstraintsViolationException, EntityNotFoundException
    {
      	List<ProductDTO> items= ProductMapper.makeProductDTOList(productService.findAllFavorites(userId));
        return new ProductResponse("success", null,items);
   	

    } 
    
    @GetMapping
    @RequestMapping("/myAdv/{userId}")
    public @ResponseBody ProductResponse getMyAdv(@PathVariable Long userId)
        throws ConstraintsViolationException, EntityNotFoundException
    {
      	List<ProductDTO> items= ProductMapper.makeProductDTOList(productService.findAllAdv(userId));
        return new ProductResponse("success", null,items);
   	

    } 
  
}
