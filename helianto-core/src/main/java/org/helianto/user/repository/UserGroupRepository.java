package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.user.domain.UserGroup;
import org.springframework.data.jpa.repository.Query;

/**
 * User group repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserGroupRepository extends FilterRepository<UserGroup, Serializable> {
	
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
	
//	select user from User user where user.userKey = ? order by lastEvent DESC
	
	/**
	 * Find by user key order by lastEvent DESC.
	 * 
	 * @param userKey
	 */
	List<UserGroup> findByUserKeyOrderByLastEventDesc(String userKey);
	
	/**
	 * Find children by parent key.
	 * 
	 * @param parentKey
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) like ?1 ")
	List<UserGroup> findByParent(String parentKey);

	/**
	 * Find parents by child.
	 * 
	 * @param child
	 */
	@Query(value="select association.parent from UserAssociation association " +
    		   	"where association.child = ?1 ")
	List<UserGroup> findParentsByChild(UserGroup child);

}
