package com.app.controller;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.LoginStringResponse;
import com.app.controller.mapper.LogginMapper;
import com.app.controller.mapper.UserMapper;
import com.app.datatransferobject.LoggingDTO;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.LoggingDO;
import com.app.domainobject.UserDO;
import com.app.exception.ConstraintsViolationException;
import com.app.exception.EntityNotFoundException;
import com.app.service.Loggin.LogginService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/login")
public class LoggingController
{

    private final LogginService loginSession;
    public static char[] Enc = { 'A', 'L', 'I' };

    @Autowired
    public LoggingController(final LogginService loginSession)
    {
        this.loginSession = loginSession;
    }


    @GetMapping("/{userId}")
//    @PreAuthorize("hasAuthority('Driver_Access')")
    public LoggingDTO getUser(@Valid @PathVariable long userId) throws EntityNotFoundException
    {
       return LogginMapper.makeLoggingDTO(loginSession.find(userId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE__Access')")
    public LoginStringResponse createSession(@Valid @RequestBody LoggingDTO loginDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
    	String encPassword=Enc(loginDTO.getPassword());
		UserDO result =  loginSession.findUserChecked(loginDTO.getMobile_number(),encPassword); //UserMapper.makeuserDTO(userService.findUserChecked(loginDTO.getUsername()));
    	if(result!=null) {
    		UserDO userDO=result;
    		loginDTO.setUserId(userDO);
    		loginDTO.setLoginTime(ZonedDateTime.now());
    		String sessionId=generateSession();
    		loginDTO.setSessionId(sessionId);
    		loginDTO.setLastActivationTime(ZonedDateTime.now());
        LoggingDO loginDO = LogginMapper.makeLoggingDO(loginDTO);
        loginDO=  loginSession.create(loginDO);
		if (loginDO != null) {
			UserDTO userDTO=UserMapper.makeuserDTO(result);
			return new LoginStringResponse(0, null,userDTO);
		} 
    	}
    	return new LoginStringResponse(400,"إسم المستخدم أو كلمة المرور غير صحيحه",null);

    }


    private String generateSession() {
        UUID idOne = UUID.randomUUID();
//        UUID idTwo = UUID.randomUUID();
		return ""+idOne;
	}


	@DeleteMapping("/{mobile}")
//    @PreAuthorize("hasAuthority('Driver_Access')")
    public void deleteDriver(@Valid @PathVariable  String mobile) throws EntityNotFoundException
    {
        loginSession.delete(mobile);
    }


    @PutMapping("/{userId}")
//    @PreAuthorize("hasAuthority('Driver_Access')")
    public void updateLocation(
        @Valid @PathVariable long userId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        loginSession.updateLocation(userId, longitude, latitude);
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
