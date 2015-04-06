package org.helianto.user.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.user.domain.User;
import org.helianto.user.domain.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User log repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserLogRepository extends JpaRepository<UserLog, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param user
	 * @param lastEvent
	 */
	UserLog findByUserAndLastEvent(User user, Date lastEvent);
	
}
