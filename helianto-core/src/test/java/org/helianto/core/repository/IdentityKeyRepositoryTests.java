package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentitySecret;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentityKeyRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<IdentitySecret, IdentitySecretRepository> {

	@Autowired
	private IdentitySecretRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	protected IdentitySecretRepository getRepository() {
		return repository;
	}
	
	private Identity identity;
	
	protected IdentitySecret getNewTarget() {
		identity = identityRepository.saveAndFlush(new Identity("principal"));
		return new IdentitySecret(identity, "principalKey");		
	}
	
	protected Serializable getTargetId(IdentitySecret target) {
		return target.getId();
	}
	
	protected IdentitySecret findByKey() {
		return getRepository().findByIdentityAndIdentityKey(identity, "principalKey");
	}
	
}
