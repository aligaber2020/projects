package com.app.service.user;

import java.util.List;

import com.app.StringResponse;
import com.app.datatransferobject.ProductDTO;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;

public interface userService
{

    UserDO find(String mobile_number,String password) throws EntityNotFoundException;
    UserDO findOne(Long userID) throws EntityNotFoundException;

    

    UserDO create(UserDO carDO) throws ConstraintsViolationException;


    void delete(Long carId) throws EntityNotFoundException;


    StringResponse updateInfo(UserDO userDTO) throws EntityNotFoundException;


    List<UserDO> findAll();
	void sendVerifyCode(UserDTO userId);

}
