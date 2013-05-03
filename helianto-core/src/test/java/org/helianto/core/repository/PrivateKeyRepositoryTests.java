package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PrivateKey;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateKeyRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PrivateKey, PrivateKeyRepository> {

	@Autowired
	private PrivateKeyRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	private Identity identity;
	private Credential credential;
	
	protected PrivateKeyRepository getRepository() {
		return repository;
	}
	
	protected PrivateKey getNewTarget() {
		return new PrivateKey(credential, "");		
	}
	
	protected Serializable getTargetId(PrivateKey target) {
		return target.getId();
	}
	
	protected PrivateKey findByKey() {
		return getRepository().findByCredential(credential);
	}
	
	public void setUp() {
		identity = identityRepository.save(new Identity("PRINCIPAL"));
		credential = credentialRepository.save(new Credential(identity));
	}
	
}
