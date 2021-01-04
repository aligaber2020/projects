package com.app.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.domainobject.LocationDO;

/**
 * Database Access Object for security user role table.
 * <p/>
 */
public interface LocationRepository extends CrudRepository<LocationDO, Long>
{

//    @Query("select o.role.roleName from UserSecurityRoleDO o where o.user.id=:userId")
//    public List<String> findRoleByUserName(@Param("userId") Long userId);
}
