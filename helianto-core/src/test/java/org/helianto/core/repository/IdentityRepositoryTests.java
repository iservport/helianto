package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Identity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentityRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Identity, IdentityRepository> {

	@Autowired
	private IdentityRepository repository;
	
	protected IdentityRepository getRepository() {
		return repository;
	}
	
	protected Identity getNewTarget() {
		return new Identity("principal");		
	}
	
	protected Serializable getTargetId(Identity target) {
		return target.getId();
	}
	
	protected Identity findByKey() {
		return getRepository().findByPrincipal("principal");
	}
	
}
