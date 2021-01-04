package com.app.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.domainobject.CategoryDO;
import com.app.domainobject.ProductDO;
import com.app.domainobject.UserDO;
import com.app.exception.EntityNotFoundException;

/**
 * Database Access Object for security role table.
 * <p/>
 */
public interface ProductRepository extends CrudRepository<ProductDO, Long>
{
	   
    @Query("select type from CategoryDO type where id=:id")
    CategoryDO findCategoryByid(@Param("id") Long typeId) throws EntityNotFoundException;
    
//    @Query("
//    ProductDO findNearestPoints(@Param("latitude") Double longitude,@Param( "longitude") Double latitude) throws EntityNotFoundException;
}
