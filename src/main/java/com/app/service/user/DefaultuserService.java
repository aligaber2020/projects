package com.app.service.user;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.app.StringResponse;
import com.app.controller.mapper.ProductMapper;
import com.app.controller.mapper.UserMapper;
import com.app.dataaccessobject.ValidationCodeRepository;
import com.app.dataaccessobject.userRepository;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.ProductDO;
import com.app.domainobject.UserDO;
import com.app.domainobject.ValidationCode;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultuserService implements userService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultuserService.class);

    private final userRepository userRepository;
    @Autowired
    private ValidationCodeRepository validationCodeRepository;
  
    public static char[] Enc = { 'A', 'L', 'I' };
    @Autowired
    public DefaultuserService(final userRepository userRepository , ValidationCodeRepository validationCodeRepository)
    {
        this.userRepository = userRepository;
        this.validationCodeRepository = validationCodeRepository ;
    }



    @Override
    public UserDO find(String mobile_number,String password) throws EntityNotFoundException
    {
        return findUserChecked(mobile_number,password);
    }


    /**
     * Creates a new user.
     *
     * @param UserDO
     * @return
     * @throws , ... .
     */
    @Override
    public UserDO create(UserDO userDO) throws ConstraintsViolationException
    {
		userDO.setPassword(Enc(userDO.getPassword()));
    	UserDO user = (UserDO) userRepository.findByMobileNumber(userDO.getMobile_number());
    	if(user!=null)
    	return null;
    	 user = userRepository.save(userDO);
        return user;
    }


    
    
    private UserDO findUserChecked(String mobile_number,String password) throws EntityNotFoundException
    {
        UserDO userDO = (UserDO) userRepository.findByname(mobile_number,password);
//        if (userDO == null)
//        {
//        	
//            throw new EntityNotFoundException("Could not find user with name");
//        }
        return userDO;
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
    public StringResponse updateInfo(UserDO user)
        throws EntityNotFoundException
    {
    	try
        {
    		
    		
	    	UserDO userItem =userRepository.findOne(user.getId());
	        if (userItem == null)
	        return new StringResponse(0,"لا يوجد مستخدم بهذا الاسم");
	        
    		if(user.getPassword() != null && !("").equalsIgnoreCase(user.getPassword())) {
        	user.setPassword(Enc(user.getPassword()));
    		}else {
    	        user.setPassword(userItem.getPassword());
    		}
    		
	        userRepository.save(user);
	        return new StringResponse(0,"تم التعديل بنجاح");

        }
        catch (DataIntegrityViolationException e)
        {
            throw new EntityNotFoundException(e.getMessage());
        }
    }


    @Override
    public List<UserDO> findAll()
    {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }



	@Override
	public void delete(Long carId) throws EntityNotFoundException {
	
		
	}



	@Override
	public UserDO findOne(Long userID) {
	    UserDO userDO = (UserDO) userRepository.findByid(userID);
        if (userDO == null)
        {
        	return null;
        }
        return userDO;
    
	}

	@Override
	public void sendVerifyCode(UserDTO userId) {
		String validationCode = getVerificationCode(userId.getId());
		
		
	}
	
	   private String getVerificationCode(Long userId) {
	        Random random = new Random();
	        ValidationCode validationCode = null;
	        String candidateCode = null;

	        boolean isValidCode = false;
	        while (!isValidCode) {
	            candidateCode = String.format("%04d", random.nextInt(10000));
	            validationCode = validationCodeRepository
	                    .findByValidationCodeAndUserIdAndActive(candidateCode, userId, true);
	            if (validationCode == null)
	                isValidCode = true;
	        }
	        validationCode = new ValidationCode(userId, candidateCode, true);

	        validationCodeRepository.save(validationCode);
	        return candidateCode;
	    }
		private String Enc(String S) {
			if (S == null) {
				return "";
			}
			int j = 0;
			char[] C = S.toCharArray();
			for (int i = 0; i < C.length; i++) {
				C[i] ^= Enc[j];
				j++;
				if (j > 2) {
					j = 0;
				}
			}
			return String.valueOf(C);
		}
}
