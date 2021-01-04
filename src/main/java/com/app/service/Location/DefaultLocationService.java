package com.app.service.Location;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.app.dataaccessobject.LocationRepository;
import com.app.dataaccessobject.LocationRepository;
import com.app.domainobject.LocationDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;


/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some security user specific things.
 * <p/>
 */
@Service
public class DefaultLocationService implements  LocationService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultLocationService.class);

    private final LocationRepository locationRepository;


    public DefaultLocationService(final LocationRepository LocationRepository)
    {
        this.locationRepository = LocationRepository;
    }



    @Override
    public LocationDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new user.
     *
     * @param LocationDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given carNumber, ... .
     */
    @Override
    public LocationDO create(LocationDO LocationDO) throws ConstraintsViolationException
    {
        LocationDO newLocation;
        try
        {
        	newLocation = locationRepository.save(LocationDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to user creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return newLocation;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
//    @Transactional
    public void delete(Long locationId) throws EntityNotFoundException
    {
        LocationDO locationDO = findCarChecked(locationId);
        
    }


    /**
     * Find all cars by typeId.
     *
     * @param typeId
     */
    @Override
    public List<LocationDO> findByLocation(String longitude,String latidute)
    {
//        return carRepository.findByEngineType(typeId);
    	return null;
    }


    private LocationDO findCarChecked(Long carID) throws EntityNotFoundException
    {
        LocationDO carDO = locationRepository.findOne(carID);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + carID);
        }
        return carDO;
    }


    /**
     * Update info for a car.
     *
     * @param carId
     * @param carColor
     * @param carCondition
     * @throws EntityNotFoundException
     */
    @Override
    public void updateInfo(long carId, String carColor, String carCondition, Integer seatCount, Integer rating, Boolean convertible)
        throws EntityNotFoundException
    {
//        LocationDO carDO = findCarChecked(carId);
//        carDO.setCarColor(carColor);
//        carDO.setCarCondition(carCondition);
//        carDO.setDateUpdated(ZonedDateTime.now());
//        carDO.setSeatCount(seatCount);
//        carDO.setRating(rating);
//        carDO.setConvertible(convertible);
    }


    /**
     * Find all cars.
     *
     */
    @Override
    public List<LocationDO> findAll()
    {
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }


    /**
     * Find all cars by manId.
     *
     * @param manId
     */

}
