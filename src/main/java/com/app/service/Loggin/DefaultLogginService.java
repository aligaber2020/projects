package com.app.service.Loggin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.controller.mapper.LogginMapper;
import com.app.controller.mapper.UserMapper;
import com.app.dataaccessobject.LoggingRepository;
import com.app.dataaccessobject.userRepository;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.LoggingDO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.user.userService;
import com.querydsl.core.types.Predicate;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultLogginService implements LogginService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultLogginService.class);

    private final LoggingRepository logginRepository;

    @Autowired
    private userService userService;


    public DefaultLogginService(final LoggingRepository logginRepository)
    {
        this.logginRepository = logginRepository;
        
    }


    /**
     * Creates a new login session.
     *
     * @param LoggingDO
     * @return
     * @throws ConstraintsViolationException 
     */
    @Override
    public LoggingDO create(LoggingDO LoggingDO) throws ConstraintsViolationException
    {
        LoggingDO login;
        try
        {
        	login = logginRepository.save(LoggingDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to loggin creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return login;
    }


	@Override
	public LoggingDO find(Long userId) throws EntityNotFoundException {
		LoggingDO LoginDo;
		LoginDo = logginRepository.findOne(userId);
		return LoginDo;
	}
	@Override
    public UserDO findUserChecked(String mobile_number,String password) throws EntityNotFoundException
    {
    	
		UserDO result = userService.find(mobile_number,password);
//        if (result == null)
//        {
//        	
//            throw new EntityNotFoundException("user name / password incorrect ");
//        }
        return result;
    }

	@Override
	public void delete( String  mobile) throws EntityNotFoundException {
		logginRepository.delete(mobile);
		
	}


	@Override
	public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<LoggingDO> find() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LoggingDO> search(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LoggingDO findSessionChecked(String sessionId) throws EntityNotFoundException {
    	
		LoggingDO result = null;//LogginMapper.makeLoggingDTO(logginRepository.findBySession(sessionId));
        if (result == null)
        {
            throw new EntityNotFoundException("user name / password incorrect ");
        }
        return result;
    }


 

}
