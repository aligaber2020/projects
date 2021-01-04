package com.app.controller.mapper;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.app.datatransferobject.LocationDTO;
import com.app.datatransferobject.LocationDTO.LocationDTOBuilder;
import com.app.datatransferobject.UserDTO;
import com.app.domainobject.LocationDO;
import com.app.domainobject.UserDO;


public class UserMapper {

	public static UserDO makeUserDO(UserDTO userDTO) {
		 return new UserDO(userDTO.getId(),
		 userDTO.getUsername(),userDTO.getFirstname(), userDTO.getLastname(),
		 userDTO.getPassword(),
		 userDTO.getDeleted(),userDTO.getE_mail(),userDTO.getMobile_number(),userDTO.getImage(),userDTO.getType());
		
	}

   

	public static UserDTO makeuserDTO(UserDO userDO) {		
		UserDTO.UserDTOBuilder userDTOBuilder=UserDTO.newBuilder().setId(userDO.getId()).setDeleted(userDO.getDeleted())
				.setE_mail(userDO.getE_mail()).setFirstname(userDO.getFirstname()).setLastname(userDO.getLastname()).setMobile_number(userDO.getMobile_number())
				.setPassword(userDO.getPassword()).setUsername(userDO.getUsername()).setType(userDO.getType()).setImage(userDO.getImage());
		
				
		 return userDTOBuilder.createUserDTO();
	}


	public static List<UserDTO> makeuserDTOList(Collection<UserDO> cars) {
		return cars.stream().map(UserMapper::makeuserDTO).collect(Collectors.toList());
	}
}
