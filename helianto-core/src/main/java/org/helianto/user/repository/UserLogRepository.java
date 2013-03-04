package org.helianto.user.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.data.FilterRepository;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserLog;

/**
 * User log repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserLogRepository extends FilterRepository<UserLog, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param user
	 * @param lastEvent
	 */
	UserLog findByUserAndLastEvent(User user, Date lastEvent);
	
}
