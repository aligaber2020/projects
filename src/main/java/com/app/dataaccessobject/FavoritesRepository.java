package com.app.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.app.domainobject.FavoritesDO;


public interface FavoritesRepository extends CrudRepository<FavoritesDO, Long> {

	FavoritesDO findOneByUserIdAndItemId(Long i, Long j);

	Iterable<FavoritesDO> findAllByUserId(Long userId);
	
	FavoritesDO findByUserIdAndItemIdAndTypeId(Long userId,Long itemID,Long typeId);

}
