package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Unit;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UnitRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Unit, UnitRepository> {

	@Autowired
	private UnitRepository repository;
	
	protected UnitRepository getRepository() {
		return repository;
	}
	
	protected Unit getNewTarget() {
		return new Unit(entity, "CODE");
	}
	
	protected Serializable getTargetId(Unit target) {
		return target.getId();
	}
	
	protected Unit findByKey() {
		return getRepository().findByEntityAndUnitCode(entity, "CODE");
	}
	
}
