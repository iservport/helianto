package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.user.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

/**
 * User repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRepository extends FilterRepository<User, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	User findByEntityAndUserKey(Entity entity, String userKey);
	
	/**
	 * Find by user key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	List<User> findByUserKey(String userKey);
	
//	select user from User user where user.userKey = ? order by lastEvent DESC
	
	/**
	 * Find by user key order by lastEvent DESC.
	 * 
	 * @param userKey
	 */
	List<User> findByUserKeyOrderByLastEventDesc(String userKey);
	
	/**
	 * Find by identity id order by lastEvent DESC.
	 * 
	 * @param identityId
	 */
	List<User> findByIdentityIdOrderByLastEventDesc(Long identityId);
	
	/**
	 * Find by parent key.
	 * 
	 * @param parentKey
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) like ?1 ")
	List<User> findByParent(String parentKey);
	
	/**
	 * Find by parent key, sorting.
	 * 
	 * @param parentKey
	 * @param sort
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) like ?1 ")
	List<User> findByParent(String parentKey, Sort sort);
	
	/**
	 * Find by parent key and principal.
	 * 
	 * @param parentKey
	 * @param principal
	 * @param sort
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) = lower(?1) " +
    			"and lower(child.identity.principal) like lower(?2) ")
	List<User> findByParentAndPrincipal(String parentKey, String principal, Sort sort);
	
	/**
	 * Find by parent key and principal.
	 * 
	 * @param parentKey
	 * @param principal
	 * @param entityType
	 * @param sort
	 */
	@Query(value="select distinct child from User child " +
		   		"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) = lower(?1) " +
    			"and lower(child.identity.principal) like lower(?2) " +
    			"and parents.parent.entity.entityType = ?3 ")
	List<User> findByParentAndPrincipalAndEntityType(String parentKey, String principal, char entityType, Sort sort);
	
}
