package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.StringResponse;
import com.app.controller.mapper.LocationMapper;
import com.app.controller.mapper.UserMapper;
import com.app.datatransferobject.LocationDTO;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.LocationDO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.Location.LocationService;
@RestController
@RequestMapping("v1/location")
public class LocationController {

    private final LocationService locationService;


    @Autowired
    public LocationController(final LocationService LocationService)
    {
        this.locationService = LocationService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('user_Access')")
    public StringResponse createLocation(@Valid @RequestBody LocationDTO locationDTO) throws ConstraintsViolationException
    {
        LocationDO locationDO = LocationMapper.makeLocationDO(locationDTO);
        LocationDTO result= LocationMapper.makelocationDTO(locationService.create(locationDO));
		if (result != null) {
			return new StringResponse(0,"success");
		} else
			return new StringResponse(400,"Fail");
	}
    


    @DeleteMapping("/{userId}")
//    @PreAuthorize("hasAuthority('user_Access')")
    public void deleteuser(@Valid @PathVariable long locationId) throws EntityNotFoundException
    {
    	locationService.delete(locationId);
    }


    @PutMapping("/{userId}")
//    @PreAuthorize("hasAuthority('user_Access')")
    public void updateLocationInfo(
        @Valid @PathVariable long carId, @RequestParam String carColor, @RequestParam String carCondition, @RequestParam Integer seatCount,
        @RequestParam Integer rating, @RequestParam Boolean convertible)
        throws ConstraintsViolationException, EntityNotFoundException
    {
    	locationService.updateInfo(carId, carColor, carCondition, seatCount, rating, convertible);
    }
}
