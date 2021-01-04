package com.app.dataaccessobject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.domainobject.CategoryDO;
import com.app.domainobject.FarmDO;
import com.app.exception.EntityNotFoundException;
public interface FarmRepository  extends CrudRepository<FarmDO, Long>
{
	   
    @Query("select type from CategoryDO type where id=:id")
    CategoryDO findCategoryByid(@Param("id") Long typeId) throws EntityNotFoundException;

}
