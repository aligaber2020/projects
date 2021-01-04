package com.app.service.favorites;

import java.util.List;

import com.app.datatransferobject.FavoritesDTO;
import com.app.domainobject.FavoritesDO;

public interface FavoritesService {

	List<FavoritesDTO> myFavorites(Long userId);
	
	void addToFavorite(FavoritesDO favoritesDO);

	void removeFromFavorite(Long id);
	
	Boolean findByUserIdAndItemIdAndTypeId(Long userId,Long itemID,Long TypeId);

}
