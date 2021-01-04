package com.app.service.Location;

import java.util.List;

import com.app.domainobject.LocationDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LocationService
{



    LocationDO find(Long locationId) throws EntityNotFoundException;


    LocationDO create(LocationDO locationDO) throws ConstraintsViolationException;


    void delete(Long locationId) throws EntityNotFoundException;


    void updateInfo(long carId, String carColor, String carCondition, Integer seatCount, Integer rating, Boolean convertible) throws EntityNotFoundException;


    List<LocationDO> findAll();


    List<LocationDO> findByLocation(String longitude,String latidute);

}
