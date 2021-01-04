package com.app.service.Loggin;

import java.util.List;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.LoggingDO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
public interface LogginService
{

    LoggingDO find(Long userId) throws EntityNotFoundException;


    LoggingDO create(LoggingDO LoggingDO) throws ConstraintsViolationException;


    void delete( String userId) throws EntityNotFoundException;


    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;
    
    UserDO findUserChecked(String mobile_number,String password) throws EntityNotFoundException;
    
    LoggingDO findSessionChecked(String sessionId) throws EntityNotFoundException;


    List<LoggingDO> find();

    List<LoggingDO> search(Long userId);

}
