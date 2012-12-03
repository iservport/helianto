package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.user.domain.UserGroup;

/**
 * User group repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRepository extends FilterRepository<UserGroup, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	UserGroup findByEntityAndUserKey(Entity entity, String userKey);
	
	/**
	 * Find by user key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	List<UserGroup> findByUserKey(String userKey);
	
}
