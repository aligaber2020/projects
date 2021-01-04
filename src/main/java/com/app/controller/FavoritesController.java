package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.StringResponse;
import com.app.controller.mapper.FavoritesMapper;
import com.app.controller.mapper.ProductMapper;
import com.app.datatransferobject.FavoritesDTO;
import com.app.domainobject.FavoritesDO;
import com.app.domainobject.ProductDO;
import com.app.service.favorites.FavoritesService;

@RestController
@RequestMapping("v2")
public class FavoritesController {
	
	@Autowired 
	private  final FavoritesService favoriesService;
	
	 
	 @Autowired  
	public  FavoritesController(final FavoritesService favoriesService) {
		this.favoriesService = favoriesService;
	}
	 @PostMapping("/add-to-favorite")
	 @ResponseStatus(HttpStatus.CREATED)
	public StringResponse addToFavorite(@Valid @RequestBody FavoritesDTO favorites){
		 FavoritesDO favoritesDO = FavoritesMapper.makeFavoritesDO(favorites);

		 favoriesService.addToFavorite(favoritesDO);
		return new StringResponse(0, "success");
	 }
	 
	 @DeleteMapping("/delete-from-favorite/{id}")
	public void removeFromFavorite(@Valid @PathVariable Long id) {
		 favoriesService.removeFromFavorite(id);
	 }
}
