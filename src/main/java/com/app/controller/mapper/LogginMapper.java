package com.app.controller.mapper;

import java.time.ZonedDateTime;

import com.app.datatransferobject.LoggingDTO;
import com.app.domainobject.LoggingDO;
import com.app.domainobject.UserDO;



public class LogginMapper
{
    public static LoggingDO makeLoggingDO(LoggingDTO loginrDTO)
    {
   		 return new LoggingDO(loginrDTO.getId(),loginrDTO.getUserId(),loginrDTO.getLoginTime(),loginrDTO.getLastActivationTime(),loginrDTO.getMobile_number(),loginrDTO.getSessionId());
   		 }
    public static LoggingDTO makeLoggingDTO(LoggingDO LoggingDO)
    {
    	LoggingDTO.LoginDTOBuilder loginDTOBuilder=LoggingDTO.newBuilder().setMobile_number(LoggingDO.getMobile_number()).setPassword(LoggingDO.getPassword());
		 return loginDTOBuilder.createSessionDTO();

    }


//    public static List<LoggingDTO> makeDriverDTOList(Collection<LoggingDO> drivers)
//    {
//        return drivers.stream()
//            .map(DriverMapper::makeDriverDTO)
//            .collect(Collectors.toList());
//    }
}
