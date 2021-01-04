package com.app.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.app.domainobject.LocationDO;
import com.app.domainobject.LoggingDO;
import com.app.domainobject.UserDO;
/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface LoggingRepository extends CrudRepository<LoggingDO, Long>
{
	@Query("delete from LoggingDO ")
	void delete(@Param("mobile")  String mobile);
	
    @Query("select u from UserDO u where sessionId=:sessionId")
    UserDO findBySession(@Param("sessionId") String sessionId);
	
//    List<LoggingDO> findByOnlineStatus(OnlineStatus onlineStatus);
//
//
//    @Query("select o.id from DriverDO o where o.car is not null and o.car.id=:carId and o.onlineStatus=:onlineStatus")
//    Long getDriverIdByCarId(@Param("carId") Long carId, @Param("onlineStatus") OnlineStatus onlineStatus);
	
}
