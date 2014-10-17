package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentityAlias;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentityAliasRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<IdentityAlias, IdentityAliasRepository> {

	@Autowired
	private IdentityAliasRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	protected IdentityAliasRepository getRepository() {
		return repository;
	}
	
	private Identity identity;
	
	protected IdentityAlias getNewTarget() {
		identity = identityRepository.saveAndFlush(new Identity("principal"));
		return new IdentityAlias(identity, "principalAlias");		
	}
	
	protected Serializable getTargetId(IdentityAlias target) {
		return target.getId();
	}
	
	protected IdentityAlias findByKey() {
		return getRepository().findByIdentityAndIdentityAlias(identity, "principalAlias");
	}
	
}
