package org.helianto.user.repository;

import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.repository.ServiceRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRoleRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<UserRole, UserRoleRepository> 
{

	@Autowired
	private UserRoleRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Autowired
	private UserAssociationRepository userAssociationRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	protected UserRoleRepository getRepository() {
		return repository;
	}
	
	private UserGroup userGroup;
	
	private Service service;
	
	private Identity identity;
	
	private User user;
	
	protected UserRole getNewTarget() {
		userGroup = userGroupRepository.saveAndFlush(new UserGroup(entity, "GROUP"));
		service = serviceRepository.saveAndFlush(new Service("DEFAULT", "SERVICE"));
		return new UserRole(userGroup, service, "READ,WRITE");
	}
	
	protected Serializable getTargetId(UserRole target) {
		return target.getId();
	}
	
	protected UserRole findByKey() {
		return getRepository().findByUserGroupAndServiceAndServiceExtension(userGroup, service, "READ,WRITE");
	}
	
	@Test
	public void findChildrenByEntityAndUserRoleServiceName() {
		List<UserGroup> childList = getRepository().findChildrenByEntityAndUserRoleServiceName(entity, "TEST", "%WRITE%");
		assertNotNull(childList);
	}
	
	@Test
	public void findByUserId() {
		identity = identityRepository.saveAndFlush(new Identity("principal"));
		user = userGroupRepository.saveAndFlush(new User(entity, identity));
		userAssociationRepository.saveAndFlush(new UserAssociation(userGroup, user));
		getRepository().saveAndFlush(getNewTarget());
		List<UserRoleReadAdapter> roleList = getRepository().findByUserId(user.getId());
		assertNotNull(roleList);
	}
	
}
