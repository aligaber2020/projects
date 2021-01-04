package com.app.service.product;

import java.util.List;

import com.app.StringResponse;
import com.app.datatransferobject.FarmDTO;
import com.app.datatransferobject.GetNearestDTO;
import com.app.datatransferobject.ProductDTO;
import com.app.domainobject.FarmDO;
import com.app.domainobject.LocationDO;
import com.app.domainobject.ProductDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;

public interface ProductService
{

    ProductDO find(Long productId) throws EntityNotFoundException;


    StringResponse create(ProductDO productDO) throws ConstraintsViolationException;
    LocationDO createLocation(LocationDO locationDTO) throws ConstraintsViolationException;



    void delete(Long itemId) throws EntityNotFoundException;


    StringResponse updateInfo(ProductDO product) throws EntityNotFoundException;


    List<ProductDO> findAll(long userid,String name)throws EntityNotFoundException;


	List<ProductDO> findNearest(GetNearestDTO getNearestReq) throws EntityNotFoundException;

	 List<ProductDO> findAllFavorites(Long userId) throws EntityNotFoundException ;
	 List<ProductDO> findAllAdv(Long userId) throws EntityNotFoundException ;
    

    
}
