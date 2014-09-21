package org.helianto.core.repository;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.helianto.core.domain.PublicEntity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.junit.Test;
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
		PublicEntity publicEntity = new PublicEntity(entity);
		assertEquals("ALIAS", publicEntity.getEntityAlias());
		return publicEntity;
	}
	
	protected Serializable getTargetId(PublicEntity target) {
		return target.getId();
	}
	
	protected PublicEntity findByKey() {
		return getRepository().findByEntity(entity);
	}
	
}
