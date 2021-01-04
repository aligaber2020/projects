package com.app.dataaccessobject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.domainobject.UserDO;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface userRepository extends CrudRepository<UserDO, Long>
{
    
    
    @Query("")
    UserDO findByid(@Param("id") Long typeId);

    @Query("select u from UserDO u where mobile_number=:mobile_number and password=:password")
    UserDO findByname(@Param("mobile_number") String mobile_number,@Param("password") String password);

	UserDO findByUsernameOrMobileNumber(String username, String mobile_number);

	UserDO findByMobileNumber(String mobile_number);

//    @Query("delete from Logg")
//    List<UserDO> findByManufacturer(@Param("manId") Long manId);

}
