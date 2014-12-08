package org.helianto.user.repository;

import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Identity;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.user.domain.UserGroup;
import org.junit.Test;
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
	
	@Autowired
	private IdentityRepository identityRepository;
	
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
	
	@Test
	public void findByIdentityId() {
		Identity identity = identityRepository.saveAndFlush(new Identity("principal"));
		getRepository().saveAndFlush(getNewTarget());
		List<UserReadAdapter> userList = getRepository().findByIdentityIdOrderByLastEventDesc(identity.getId());
		assertNotNull(userList);
	}
	
}
