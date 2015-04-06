package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Service;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User role repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Serializable> {
	
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
	
	/**
	 * Find by user id.
	 * 
	 * @param userGroup
	 */
	@Query("select new "
			+ "org.helianto.user.repository.UserRoleReadAdapter"
			+ "(userRole.id, userRole.service.id, userAssociation.parent.id"
			+ ", userRole.service.serviceName, userRole.serviceExtension"
			+ ", userRole.partnershipExtension)"
			+ "from UserAssociation userAssociation "
			+ "inner join userAssociation.parent.roles userRole "
			+ "where userAssociation.child.id = ?1 ")
	List<UserRoleReadAdapter> findByUserId(int userId);
	
	/**
	 * Find by service name.
	 * 
	 * @param entity
	 * @param serviceName
	 * @param extension
	 */
	@Query("select distinct userAssociation.child from UserAssociation userAssociation " +
			"inner join userAssociation.parent.roles userRole " +
			"where userAssociation.child.entity = ?1 " +
			"and userRole.service.serviceName = ?2 " +
			"and lower(userRole.serviceExtension) like ?3 " +
			"and userAssociation.child.userState = 'A' ")
	List<UserGroup> findChildrenByEntityAndUserRoleServiceName(Entity entity, String serviceName, String extension);
	
	/**
	 * Pesquisa por grupo e serviço.
	 * 
	 * @param userGroupId
	 * @param serviceName
	 * @param serviceExtensions
	 */
	List<UserRole> findByUserGroupIdAndServiceServiceNameAndServiceServiceExtensionsContains(
			int userGroupId, String serviceName, String serviceExtensions);
	
	/**
	 * Pesquisa por usuário.
	 * 
	 * @param userId
	 * @param page
	 */
	@Query(
			"select new "
			+ "org.helianto.user.repository.UserRoleReadAdapter"
			+ "( userRole.id" 
			+ ", userAssociation.child.id" 
			+ ", userRole.service.id" 
			+ ", userRole.service.serviceName" 
			+ ", userRole.serviceExtension" 
			+ ", userRole.activityState" 
			+ ", userRole.partnershipExtension" 
			+ ") "
			+ "from UserAssociation userAssociation "
			+ "inner join userAssociation.parent.roles userRole "
			+ "where userAssociation.child.id = ?1 "
			)
	Page<UserRoleReadAdapter> findByUserId(int userId, Pageable page);
	
	/**
	 * Pesquisa por usuário.
	 * 
	 * @param userId
	 * @param page
	 */
	@Query(
			"select new "
			+ "org.helianto.user.repository.UserRoleReadAdapter"
			+ "( userRole.id" 
			+ ", userRole.userGroup.id" 
			+ ", userRole.service.id" 
			+ ", userRole.service.serviceName" 
			+ ", userRole.serviceExtension" 
			+ ", userRole.activityState" 
			+ ", userRole.partnershipExtension" 
			+ ") "
			+ "from UserRole userRole "
			+ "where userRole.id = ?1 "
			)
	UserRoleReadAdapter findByUserRoleId(int userRoleId);

	@Query(
			"SELECT userRole.id "
			+ "FROM UserRole userRole "
			+ "WHERE userRole.userGroup.id = ?1 "
			+ "AND userRole.service.id = ?2 "
			+ "AND LOWER(userRole.serviceExtension) LIKE ?3 "
			)
	Integer findByUserIdAndServiceIdAndServiceExtensionLike(Integer userId, Integer serviceId, String ServiceExtension);

}
