package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.process.domain.ControlPlan;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlPlanRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ControlPlan, ControlPlanRepository> {

	@Autowired
	private ControlPlanRepository repository;
	
	@Override
	protected ControlPlanRepository getRepository() {
		return repository;
	}
	
	@Override
	protected ControlPlan getNewTarget() {
		return new ControlPlan(entity, "CTP#0");
	}
	
	@Override
	protected Serializable getTargetId(ControlPlan target) {
		return target.getId();
	}
	
	@Override
	protected ControlPlan findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "CTP#0");
	}
	
}
