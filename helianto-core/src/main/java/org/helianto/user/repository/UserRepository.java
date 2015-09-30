package org.helianto.user.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRepository extends JpaRepository<User, Serializable> {
	
	/**
	 * Query to read from user.
	 */
	public static final String QUERY = "select new "
			+ "org.helianto.user.domain.User"
			+ "( user_.id"
			+ ", user_.entity.operator.id"
			+ ", user_.entity.id"
			+ ", 0"
			+ ", user_.entity.alias"
			+ ", user_.identity.id"
			+ ", user_.identity.personalData.firstName"
			+ ", user_.identity.personalData.lastName"
			+ ", user_.identity.displayName"
			+ ", user_.identity.personalData.gender"
			+ ", user_.identity.personalData.imageUrl"
			+ ", user_.userKey"
			+ ", user_.userName"
			+ ", user_.userState"
			+ ", user_.userType"
			+ ", user_.userJob.jobId"
			+ ", user_.userJob.jobTitle"
			+ ", user_.accountNonExpired"
			+ ") "
			+ "from User user_ ";

	@Query("select user_.id "
			+ "from User user_ "
			+ "where user_.entity.id = ?1 "
			+ "AND user_.userKey = ?2")
	Integer findIdByEntity_IdAndUserKey(Integer entityId, String userKey);
	
	/**
	 * Find by user id.
	 * 
	 * @param userId
	 */
	@Query(QUERY
			+ "where user_.id = ?1 ")
	User findAdapter(int userId);

	/**
	 * Find by identity principal.
	 * 
	 * @param principal
	 */
	@Query(QUERY
			+ "where user_.identity.principal = ?1 ")
	List<User> findByIdentityPrincipal(String principal);

	/**
	 * Page by entity.
	 * 
	 * @param entityId
	 * @param page
	 */
	@Query(QUERY
			+ "where user_.entity.id = ?1 "
			)
	Page<User> findByEntity_Id2(Integer entityId, Pageable page);

	/**
	 * Page by user key.
	 * 
	 * @param userKey
	 * @param page
	 */
	@Query(QUERY
			+ "where user_.identity.id = ?1 "
			)
	Page<User> pageByIdentityId(int identityId, Pageable page);

	/**
	 * Page by user key.
	 * 
	 * @param userKey
	 * @param page
	 */
	@Query(QUERY
			+ "where user_.userKey = ?1 "
			)
	Page<User> pageByUserKey(String userKey, Pageable page);

	/**
	 * Page by entity id and user type.
	 * 
	 * @param entityId
	 * @param userTypes
	 * @param page
	 */
	@Query(QUERY
			+ "where user_.entity.id = ?1 "
			+ "and user_.userType in ?2 "
			)
	Page<User> pageByEntityIdAndUserTypes(int entityId, Collection<Character> userTypes, Pageable page);

	/**
	 * Page by jobId.
	 * 
	 * @param jobId
	 * @param page
	 */
	@Query(QUERY
			+ "where user_.userJob.jobId = ?1 "
			)
	Page<User> pageByJobId(int jobId, Pageable page);

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
	
	/**
	 * Query (join) to read from user and parent.
	 */
	public static final String QUERY_JOIN = "select new "
			+ "org.helianto.user.domain.User"
			+ "( user_.id"
			+ ", user_.entity.operator.id"
			+ ", user_.entity.id"
			+ ", parent_.parent.id"
			+ ", user_.entity.alias"
			+ ", user_.identity.id"
			+ ", user_.identity.personalData.firstName"
			+ ", user_.identity.personalData.lastName"
			+ ", user_.identity.displayName"
			+ ", user_.identity.personalData.gender"
			+ ", user_.identity.personalData.imageUrl"
			+ ", user_.userKey"
			+ ", user_.userName"
			+ ", user_.userState"
			+ ", user_.userType"
			+ ", user_.userJob.jobId"
			+ ", user_.userJob.jobTitle"
			+ ", user_.accountNonExpired"
			+ ") "
			+ "from User user_ "
			+ "join user_.parentAssociations parent_ ";

	/**
	 * Find by parent id and state, pageable.
	 * 
	 * @param parentGroupId
	 * @param userState
	 * @param page
	 */
	@Query(value=QUERY_JOIN
			+ "where parent_.parent.id = ?1 "
			+ "and user_.userState in ?2")
	Page<User> findByParent(int parentGroupId, char[] userState, Pageable page);
	
	/**
	 * Find by parent key and state, pageable.
	 * 
	 * @param entityId
	 * @param userKey
	 * @param exclusions
	 * @param userState
	 * @param page
	 */
	@Query(value=QUERY_JOIN
			+ "where parent_.parent.entity.id = ?1 "
			+ "and parent_.parent.userKey = ?2 "
			+ "and user_.id not in ?3 "
			+ "and user_.userState in ?4 "
			+ "order by user_.userName, user_.userKey")
	Page<User> findByParentUserKey(int entityId, String userKey, Integer[] exclusions, char[] userState, Pageable page);
	
	/**
	 * Find by parent key and state, pageable.
	 * 
	 * @param entityId
	 * @param userKey
	 * @param exclusions
	 * @param userState
	 * @param page
	 */
	@Query(value=QUERY_JOIN
			+ "where parent_.parent.entity.id = ?1 "
			+ "and user_.id not in ?2 "
			+ "and (lower(user_.userKey) like %?3% or lower(user_.userName) like %?3% ) "
			+ "and parent_.parent.userType = ?4 "
			+ "and user_.userState in ?5 "
			)
	Page<User> searchByParentUserType(int entityId, Collection<Integer> exclusions, String searchString, Character userType, char[] userStates, Pageable page);
	
	/**
	 * Find by user parent type.
	 * 
	 * @param entityId
	 * @param userType
	 * @param userState
	 * @param page
	 */
	@Query(value=QUERY_JOIN
			+ "where parent_.parent.entity.id = ?1 "
			+ "and parent_.parent.userType = ?2 "
			+ "and user_.userState in ?3 ")
	Page<User> findByParentUserType(int entityId, Character userType, char[] userState, Pageable page);
	
	/**
	 * Find by search string.
	 * 
	 * @param entityId
	 * @param searchString
	 * @param page
	 * @return
	 */
	@Query(value=QUERY_JOIN
			+ "where lower(parent_.parent.userKey) = 'user' "
			+ "and user_.id in ("
			+ "  select u.id from User u "
			+ "  where u.entity.id = ?1 "
			+ "  and ( lower(u.userKey) like ?2 or lower(u.userName) like ?2 ))")
	public Page<User> pageByEntity_IdAndSearchString(int entityId, String searchString, Pageable page);
	
	/**
	 * Find users by entity id and search string like key or name.
	 * 
	 * @param entityId
	 * @param searchString
	 * @param pageable
	 * @deprecated
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
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param userKey
	 * @deprecated
	 */
	User findByEntityAndUserKey(Entity entity, String userKey);
	
	/**
	 * Find by natural key.
	 * 
	 * @param entityId
	 * @param identityId
	 * @deprecated
	 */
	User findByEntity_IdAndIdentity_Id(int entityId, int identityId);
	
	/**
	 * Find by key names.
	 * 
	 * @param contextName
	 * @param entityAlias
	 * @param principal
	 * @deprecated
	 */
	@Query("select user_ "
			+ "from User user_ "
			+ "where user_.entity.operator.operatorName = ?1 "
			+ "and user_.entity.alias = ?2 "
			+ "and user_.identity.principal = ?3 ")
	User findByEntityAliasAndPrincipal(String contextName, String entityAlias, String principal);
	
	/**
	 * Find by user key.
	 * 
	 * @param entity
	 * @param userKey
	 * @deprecated
	 */
	List<User> findByUserKey(String userKey);
	
	/**
	 * Find by user key order by lastEvent DESC.
	 * 
	 * @param userKey
	 * @deprecated
	 */
	List<User> findByUserKeyOrderByLastEventDesc(String userKey);
	
	/**
	 * Find by identity id order by lastEvent DESC.
	 * 
	 * @param identityId
	 * @deprecated
	 */
	List<User> findByIdentity_IdOrderByLastEventDesc(int identityId);
	
	/**
	 * Find by parent key.
	 * 
	 * @param parentKey
	 * @deprecated
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
	 * @deprecated
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
	 * @deprecated
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
	 * @deprecated
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
	 * @deprecated
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
	 * @deprecated
	 */
	@Query(value="select distinct child from User child " +
		   		"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) = lower(?1) " +
    			"and lower(child.identity.principal) like lower(?2) " +
    			"and parents.parent.entity.entityType = ?3 ")
	List<User> findByParentAndPrincipalAndEntityType(String parentKey, String principal, char entityType, Sort sort);
	
	/**
	 * Find entity in user.
	 * 
	 * @param userId
	 */
	@Query(value="select user.entity "
			+ "from User user "
			+ "where user.id = ?1 ")
	Entity findEntityByUserId(int userId);
	
	/**
	 * Find identity in user.
	 * 
	 * @param userId
	 */
	@Query(value="select user.identity "
			+ "from User user "
			+ "where user.id = ?1 ")
	Identity findIdentityByUserId(int userId);
	
}
