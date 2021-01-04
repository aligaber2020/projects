package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.LoginStringResponse;
import com.app.StringResponse;
import com.app.UserResponse;
import com.app.controller.mapper.UserMapper;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.user.userService;

/**
 * All operations with a user will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/users")
public class UserController
{

    private final userService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(final userService userService , PasswordEncoder passwordEncoder )
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginStringResponse createuser(@Valid @RequestBody UserDTO userDTO) throws ConstraintsViolationException
	{
    	UserDTO user = null;
		UserDO userDO = UserMapper.makeUserDO(userDTO);
		userDO=userService.create(userDO);
		if(userDO!=null) {
		 user = UserMapper.makeuserDTO(userDO);
		 return new LoginStringResponse(0,"تم بنجاح",user);			
		} else
			return new LoginStringResponse(400,"هذا الرقم مسجل من قبل",null);
	}


    @DeleteMapping("/{userId}")
    public StringResponse deleteuser(@Valid @PathVariable long userId) throws EntityNotFoundException
    {
        userService.delete(userId);
        return new StringResponse(0,"تم بنجاح");
        }
    
    
    @GetMapping
  public UserResponse findAllUsers()
      throws ConstraintsViolationException, EntityNotFoundException
  {
    	List<UserDTO> users= UserMapper.makeuserDTOList(userService.findAll());
      return new UserResponse("تم بنجاح", null,users);

  }
    
    @GetMapping("/{userId}")
  public LoginStringResponse getUserInfo(@PathVariable Long userId)
      throws ConstraintsViolationException, EntityNotFoundException
  {
       	UserDTO userDTO= UserMapper.makeuserDTO(userService.findOne(userId));
		if (userDTO != null) {
			return new LoginStringResponse(0, null,userDTO);
		} 
		else
    	return new LoginStringResponse(400,"لا يوجد مستخدم بهذا الاسم",null);  }

    @PutMapping
    public StringResponse updateUserInfo(@Valid @RequestBody UserDTO userDTO) throws EntityNotFoundException
    {
    	UserDO userDO = UserMapper.makeUserDO(userDTO);
        return userService.updateInfo(userDO);
       
    }
    
	@PostMapping(path = "/sendverifycode")
	public void sendVerifyCode(@Valid @RequestBody UserDTO userDTO) {
		userService.sendVerifyCode(userDTO);
	}
	

}
