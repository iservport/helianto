package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository  extends JpaRepository<UserRequest, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param userGroup
	 * @param internalNumber
	 */
	UserRequest findByUserGroupAndInternalNumber(UserGroup userGroup, long internalNumber);

}
