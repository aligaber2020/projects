package com.app.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.app.domainobject.CategoryDO;


public interface CategeoryRepository  extends CrudRepository<CategoryDO,Long>{
	List<CategoryDO> findAll();
	List<CategoryDO> findByType(int type);

}
