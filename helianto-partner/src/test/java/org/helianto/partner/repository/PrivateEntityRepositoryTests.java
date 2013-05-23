package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateEntityRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PrivateEntity, PrivateEntityRepository> {

	@Autowired
	private PrivateEntityRepository repository;
	
	@Override
	protected PrivateEntityRepository getRepository() {
		return repository;
	}

	@Override
	protected PrivateEntity getNewTarget() {
		return new PrivateEntity(entity, "ALIAS");
	}

	@Override
	protected Serializable getTargetId(PrivateEntity target) {
		return target.getId();
	}

	@Override
	protected PrivateEntity findByKey() {
		return getRepository().findByEntityAndEntityAlias(entity, "ALIAS");
	}
	
}
