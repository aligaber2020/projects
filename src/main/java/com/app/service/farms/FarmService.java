package com.app.service.farms;

import java.util.List;

import com.app.StringResponse;
import com.app.datatransferobject.FarmDTO;
import com.app.datatransferobject.GetNearestDTO;
import com.app.domainobject.LocationDO;
import com.app.domainobject.FarmDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;

public interface FarmService {

    FarmDO find(Long farmId) throws EntityNotFoundException;


    StringResponse create(FarmDO FarmDO) throws ConstraintsViolationException;
    LocationDO createLocation(LocationDO locationDTO) throws ConstraintsViolationException;



    void delete(Long itemId) throws EntityNotFoundException;


    StringResponse updateInfo(FarmDO product) throws EntityNotFoundException;


    List<FarmDO> findAll(long userid)throws EntityNotFoundException;


	List<FarmDO> findNearest(double  longitude, double latitude) throws EntityNotFoundException;


	List<FarmDO> findAllFavorites(Long userId)throws EntityNotFoundException ;
	List<FarmDO> findAllAdv(Long userId)throws EntityNotFoundException ;
    
}
