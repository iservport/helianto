package org.helianto.core.repository;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CredentialRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Credential, CredentialRepository> {

	@Autowired
	private CredentialRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	private Identity identity;
	
	protected CredentialRepository getRepository() {
		return repository;
	}
	
	protected Credential getNewTarget() {
		return new Credential(identity);		
	}
	
	protected Serializable getTargetId(Credential target) {
		return target.getId();
	}
	
	protected Credential findByKey() {
		return getRepository().findByIdentity(identity);
	}
	
	@Test
	public void findByIdentityPrincipal() {
		getRepository().save(getNewTarget());
		List<Credential> credentialList = getRepository().findByIdentityPrincipal("principal");
		assertEquals(identity, credentialList.iterator().next().getIdentity());
	}
	
	public void setUp() {
		identity = identityRepository.save(new Identity("principal"));
	}
	
}
