package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Service;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.springframework.data.jpa.repository.Query;

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
	
}
