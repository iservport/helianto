package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.user.domain.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User group repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	UserGroup findByEntityAndUserKey(Entity entity, String userKey);
	
	/**
	 * Find by natural key.
	 * 
	 * @param entityId
	 * @param userKey
	 */
	UserGroup findByEntity_IdAndUserKey(int entityId, String userKey);
	
	/**
	 * Find by user key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	List<UserGroup> findByUserKey(String userKey);
	
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
	
	/**
	 * Find parents by child.
	 * 
	 * @param childId
	 */
	@Query(value="select association.parent from UserAssociation association " +
    		   	"where association.child.id = ?1 ")
	List<UserGroup> findParentsByChildId(int childId);
	
	/**
	 * Find by entity userType.
	 * 
	 * @param entityId
	 * @param userType
	 * @param page
	 */
	List<UserGroup> findByEntity_IdAndUserType(int entityId, Character userType, Pageable page);

	/**
	 * Find by entity and nature (containing).
	 * 
	 * @param entityId
	 * @param userNature
	 */
	List<UserGroup> findByEntity_IdAndNatureContainingOrderByUserKeyAsc(int entityId, String userNature);

	/**
	 * Find by identity id.
	 * 
	 * @param identityId
	 * @return
	 */
	@Query("select new "
			+ "org.helianto.user.repository.UserReadAdapter"
			+ "(user.id"
			+ ", user.entity.operator.id"
			+ ", user.entity.id"
			+ ", user.entity.alias"
			+ ", user.identity.id"
			+ ", user.userKey"
			+ ", user.userName"
			+ ", user.userState"
			+ ") "
			+ "from User user "
			+ "where user.identity.id = ?1 "
			+ "and user.class = 'U' "
			+ "and user.userState = 'A' "
			+ "order by user.lastEvent DESC ")
	List<UserReadAdapter> findByIdentityIdOrderByLastEventDesc(int identityId);

	/**
	 * Find a parent given a child id.
	 * 
	 * @param userId
	 * @deprecated
	 */
	@Query("select parents.parent "
			+ "from User child "
			+ "join child.parentAssociations parents "
			+ "where parents.parent.userKey = 'USER' "
			+ "and child.id = ?1 ")
	UserGroup findAllUserGroup(int userId);
	
	/**
	 * Query to read from UserGroup.
	 */
	public final static String QUERY_GROUP = "select new "
			+ "org.helianto.user.domain.UserGroup"
			+ "( userGroup.id"
			+ ", userGroup.userKey"
			+ ", userGroup.userName"
			+ ", userGroup.locale"
			+ ", userGroup.lastEvent"
			+ ", userGroup.userState"
			+ ", userGroup.userType"
			+ ", userGroup.accountNonExpired"
			+ ", userGroup.userDesc"
			+ ", userGroup.nature"
			+ ", userGroup.minimalEducationRequirement"
			+ ", userGroup.minimalExperienceRequirement"
			+ ", userGroup.scriptItems"
			+ ") "
			+ "from UserGroup userGroup ";

	/**
	 * Read user pages from entity and type.
	 * 
	 * @param entityId
	 */
	@Query(QUERY_GROUP
			+ "where userGroup.entity.id = ?1 "
			+ "and userGroup.userType = ?2 "
			+ "and userGroup.userState = 'A' ")
	Page<UserGroup> findActiveUserGroupsByUserType(int entityId, char userType, Pageable page);
	
	/**
	 * Read user pages from entity and types.
	 * 
	 * @param entityId
	 */
	@Query(QUERY_GROUP
			+ "where userGroup.entity.id = ?1 "
			+ "and userGroup.userType in ?2 "
			+ "and userGroup.userState = 'A' ")
	Page<UserGroup> findActiveUserGroupsByUserTypes(int entityId, char[] userTypes, Pageable page);
	
	/**
	 * Read by id.
	 * 
	 * @param groupId
	 */
	@Query(QUERY_GROUP
			+ "where userGroup.id = ?1 ")
	UserGroup findById(int groupId);

	/**
	 * Read groups by entity.
	 * 
	 * @param entityId
	 * @param page
	 * @deprecated
	 */
	@Query(QUERY_GROUP
			+ "where userGroup.entity.id = ?1 "
			+ "and userGroup.class = 'G' "
			+ "and userGroup.userType not in ('A', 'G')")
	List<UserGroup> findByEntity_Id(Integer entityId, Pageable page);
	
	/**
	 * Query to read from parent UserGroup.
	 */
	public final static String QUERY_PARENT = "select new "
			+ "org.helianto.user.domain.UserGroup"
			+ "( association.parent.id"
			+ ", association.parent.userKey"
			+ ", association.parent.userName"
			+ ", association.parent.locale"
			+ ", association.parent.lastEvent"
			+ ", association.parent.userState"
			+ ", association.parent.userType"
			+ ", association.parent.accountNonExpired"
			+ ", association.parent.userDesc"
			+ ", association.parent.nature"
			+ ", association.parent.minimalEducationRequirement"
			+ ", association.parent.minimalExperienceRequirement"
			+ ", association.parent.scriptItems"
			+ ") "
			+ "from UserAssociation association ";

	/**
	 * List groups related to a child user.
	 * 
	 * @param child
	 * @deprecated
	 */
	@Query(QUERY_PARENT
			+ "where association.child.id = ?1 ")
	List<UserGroup> findParentsByChildId(int childId, Pageable page);
	
	/**
	 * Page groups related to a child user.
	 * 
	 * @param child
	 */
	@Query(QUERY_PARENT
			+ "where association.child.id = ?1 ")
	Page<UserGroup> find2ParentsByChildId(int childId, Pageable page);

}
