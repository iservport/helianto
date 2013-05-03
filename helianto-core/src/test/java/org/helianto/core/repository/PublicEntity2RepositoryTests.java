package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.PublicEntity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntity2RepositoryTests extends AbstractJpaRepositoryIntegrationTest<PublicEntity, PublicEntityRepository> {

	@Autowired
	private PublicEntityRepository repository;
	
	protected PublicEntityRepository getRepository() {
		return repository;
	}
	
	protected PublicEntity getNewTarget() {
		entity.setAlias("ALIAS");
		return new PublicEntity(entity);
	}
	
	protected Serializable getTargetId(PublicEntity target) {
		return target.getId();
	}
	
	protected PublicEntity findByKey() {
		return getRepository().findByEntityAndEntityAlias(entity, "ALIAS");
	}
	
}
