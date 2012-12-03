package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRequest;

public interface UserRequestRepository  extends FilterRepository<UserRequest, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param userGroup
	 * @param internalNumber
	 */
	UserRequest findByUserGroupAndInternalNumber(UserGroup userGroup, long internalNumber);

}
