package com.app.service.favorites;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dataaccessobject.FavoritesRepository;
import com.app.datatransferobject.FavoritesDTO;
import com.app.domainobject.FavoritesDO;
import com.app.service.Loggin.DefaultLogginService;

@Service
public class DefaultFavoritesService implements FavoritesService{
	
	@Autowired
	private final FavoritesRepository favoritesRepository;	
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultLogginService.class);
    
    
  public   DefaultFavoritesService (final FavoritesRepository favoritesRepository) {
	  this.favoritesRepository=favoritesRepository;

  }

	public List<FavoritesDTO> myFavorites(Long userId) {
		
		Iterable<FavoritesDO> favoritesDOs = favoritesRepository.findAllByUserId(userId);
		List<FavoritesDTO> favoritesDTOs = new ArrayList();
		for (FavoritesDO favoritesDO : favoritesDOs){
			FavoritesDTO favoritesDTO=new FavoritesDTO();
		}
		
		 return favoritesDTOs ;
	}

	public void removeFromFavorite(Long id) {
		favoritesRepository.delete(id);		
	}

	public void addToFavorite(FavoritesDO favoritesDO) {
		//check if exist
		FavoritesDO existObject=favoritesRepository.findByUserIdAndItemIdAndTypeId(favoritesDO.getUserId(),favoritesDO.getItemId(),favoritesDO.getTypeId());
		if(existObject!=null) {
			favoritesRepository.delete(existObject);
		}else {
		favoritesRepository.save(favoritesDO);	
		}
	}

	public Boolean findByUserIdAndItemIdAndTypeId(Long userId,Long itemID,Long typeId) {
		if( favoritesRepository.findByUserIdAndItemIdAndTypeId(userId, itemID, typeId)==null)
		return false;
		return true;
}
}
