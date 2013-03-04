package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Service;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;

/**
 * User role repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRoleRepository extends FilterRepository<UserRole, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param userGroup
	 * @param service
	 * @param serviceExtension
	 */
	UserRole findByUserGroupAndServiceAndServiceExtension(UserGroup userGroup, Service service, String serviceExtension);
	
	/**
	 * Find by user group.
	 * 
	 * @param userGroup
	 */
	List<UserRole> findByUserGroup(UserGroup userGroup);
	
}
