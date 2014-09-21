package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.user.domain.User;
import org.springframework.data.domain.Pageable;
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
	 * Find by natural key.
	 * 
	 * @param entityId
	 * @param identityId
	 */
	User findByEntity_IdAndIdentity_Id(int entityId, int identityId);
	
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
	List<User> findByIdentityIdOrderByLastEventDesc(int identityId);
	
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
	 * Find by parent id, pageable.
	 * 
	 * @param parentGroupId
	 * @param page
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where parents.parent.id = ?1 ")
	List<User> findByParent(int parentGroupId, Pageable page);
	
	/**
	 * Find by parent id and state, pageable.
	 * 
	 * @param parentGroupId
	 * @param userState
	 * @param page
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where parents.parent.id = ?1 " +
    			"and child.userState = ?2 ")
	List<User> findByParent(int parentGroupId, char userState, Pageable page);
	
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
	
	/**
	 * Find ordered user entities by identity id and entity type.
	 * 
	 * @param identityId
	 * @param entityType
	 * @param pageable
	 */
	@Query(value="select distinct child.entity from User child " +
		   		"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) = 'user' " +
    			"and child.identity.id = ?1 " +
    			"and parents.parent.entity.entityType = ?2 " +
    			"order by child.lastEvent DESC ")
	List<Entity> findByIdentityIdAndEntityTypeOrderByLastEventDesc(int identityId, char entityType, Pageable pageable);
	
	/**
	 * Find users by entity id and search string like key or name.
	 * 
	 * @param entityId
	 * @param searchString
	 * @param pageable
	 */
	@Query(value="select user from User user where user.id in (" +
			"select u.id from User u " +
    			"where u.entity.id = ?1 " +
    			"and (" +
    				"lower(u.userKey) like ?2 " +
    				"or lower(u.userName) like ?2 " +
    			") )")
	List<User> findByEntity_IdAndSearchString(int entityId, String searchString, Pageable pageable);
	
	/**
	 * Find users by entity id and search string like key or name and state.
	 * 
	 * @param entityId
	 * @param searchString
	 * @param userState
	 * @param pageable
	 */
	@Query(value="select user from User user where user.id in (" +
			"select u.id from User u " +
    			"where u.entity.id = ?1 " +
    			"and ( " +
    				"lower(u.userKey) like ?2 " +
    				"or lower(u.userName) like ?2 " +
    			") " +
    			"and u.userState = ?3 ) ")
	List<User> findByEntity_IdAndSearchStringAndUserState(int entityId, String searchString, char userState, Pageable pageable);
	
}
