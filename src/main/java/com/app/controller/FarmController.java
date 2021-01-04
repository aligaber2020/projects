package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.FarmResponse;
import com.app.StringResponse;
import com.app.controller.mapper.FarmMapper;
import com.app.datatransferobject.FarmDTO;
import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.FarmDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.farms.FarmService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("v2/farm")
public class FarmController {
	

	 @Autowired
   private final FarmService farmService;
	 
	 @Autowired
   public FarmController(final FarmService farm)
   {
       this.farmService = farm;
   }

	 @PostMapping()
   @ResponseStatus(HttpStatus.CREATED)
//   @PreAuthorize("hasAuthority('user_Access')")
   public StringResponse createItem(@RequestPart("files") MultipartFile[] files,@RequestPart("FarmDTO") String farmDTO) throws ConstraintsViolationException, JsonParseException, JsonMappingException, IOException
	{
	   FarmDTO farm = new ObjectMapper().readValue(farmDTO, FarmDTO.class);
	   farm.setFiles(files);
		FarmDO farmDO = FarmMapper.makeFarmDO(farm);
		return farmService.create(farmDO);

	}

	@DeleteMapping("/{itemId}")
   public StringResponse deleteitem(@Valid @PathVariable long itemId) throws EntityNotFoundException
   {
       farmService.delete(itemId);
		return new StringResponse(0,"success");

   }


   @PutMapping
   public StringResponse updateInfo(
		   @RequestPart("files") MultipartFile[] files,@RequestPart("FarmDTO") String farmDTO)
       throws ConstraintsViolationException, EntityNotFoundException, JsonParseException, JsonMappingException, IOException
   {
	   
	   FarmDTO farm = new ObjectMapper().readValue(farmDTO, FarmDTO.class);
	   farm.setFiles(files);
		FarmDO farmDO = FarmMapper.makeFarmDO(farm);
        return farmService.updateInfo(farmDO);
   }
   
   @GetMapping("/{userid}")
 public FarmResponse findAllfarms(@PathVariable long userid)
     throws ConstraintsViolationException, EntityNotFoundException
 {
   	List<FarmDTO> farms= FarmMapper.makeFarmDTOList(farmService.findAll(userid));
     return new FarmResponse(0, null,farms);

 	

 }
   @GetMapping
   @RequestMapping("/getNearestpoints/{longitude}/{latitude}")
   //@PreAuthorize("hasAuthority('Driver_Access')")
 public FarmResponse findNearestItems(@PathVariable Double longitude, @PathVariable Double latitude )
     throws ConstraintsViolationException, EntityNotFoundException
 {
   	List<FarmDTO> items= FarmMapper.makeFarmDTOList(farmService.findNearest(longitude,latitude));
     return new FarmResponse(0, null,items);

 	

 }
   
   @GetMapping
   @RequestMapping("/myFavorites/{userId}")
   public @ResponseBody FarmResponse getMyFavoriteItems(@PathVariable Long userId)
       throws ConstraintsViolationException, EntityNotFoundException
   {
	   List<FarmDTO> farms= FarmMapper.makeFarmDTOList(farmService.findAllFavorites(userId));
	   return new FarmResponse(0, null,farms);
  	

   }
   
   @GetMapping
   @RequestMapping("/myAdv/{userId}")
   public @ResponseBody FarmResponse getMyAdv(@PathVariable Long userId)
       throws ConstraintsViolationException, EntityNotFoundException
   {
	   List<FarmDTO> farms= FarmMapper.makeFarmDTOList(farmService.findAllAdv(userId));
	   return new FarmResponse(0, null,farms);
  	

   }

}
