package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.user.domain.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserGroupRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<UserGroup, UserGroupRepository> 
{

	@Autowired
	private UserGroupRepository repository;
	
	protected UserGroupRepository getRepository() {
		return repository;
	}
	
	protected UserGroup getNewTarget() {
		return new UserGroup(entity, "KEY");
	}
	
	protected Serializable getTargetId(UserGroup target) {
		return target.getId();
	}
	
	protected UserGroup findByKey() {
		return getRepository().findByEntityAndUserKey(entity, "KEY");
	}
	
}
