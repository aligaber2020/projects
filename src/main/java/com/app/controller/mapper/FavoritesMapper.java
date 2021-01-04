package com.app.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.app.datatransferobject.FarmDTO;
import com.app.datatransferobject.FavoritesDTO;
import com.app.domainobject.FarmDO;
import com.app.domainobject.FavoritesDO;

public class FavoritesMapper {
	
	public static FavoritesDO makeFavoritesDO(FavoritesDTO favoritesDTO) {
		return new FavoritesDO(favoritesDTO.getUserId(), favoritesDTO.getItemId(), favoritesDTO.getType());
	}
	
	public static FavoritesDTO makeFavoritesDTO(FavoritesDO favoritesDO) {
		 FavoritesDTO.FavoritesDTOBuilder favoritesDTOBuilder=FavoritesDTO.newBuilder().setUserId(favoritesDO.getUserId()).setItemId(favoritesDO.getItemId())
				 .setType(favoritesDO.getTypeId());
		 return favoritesDTOBuilder.createFavoritesDTO();
	}

	public static List<FavoritesDTO> makeFavoritesDTOList(Collection<FavoritesDO> favorites) {
		return favorites.stream().map(FavoritesMapper::makeFavoritesDTO).collect(Collectors.toList());
	}
	
}
