package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class EntityRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Entity, EntityRepository> {

	protected EntityRepository getRepository() {
		return entityRepository;
	}
	
	protected Entity getNewTarget() {
		entity.setAlias("ALIAS");
		return entity;		
	}
	
	protected Serializable getTargetId(Entity target) {
		return target.getId();
	}
	
	protected Entity findByKey() {
		return getRepository().findByOperatorAndAlias(operator, "ALIAS");
	}
		
}
