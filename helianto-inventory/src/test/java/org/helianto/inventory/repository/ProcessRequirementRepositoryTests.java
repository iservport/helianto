package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.inventory.domain.ProcessRequirement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessRequirementRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ProcessRequirement, ProcessRequirementRepository> {

	@Autowired
	private ProcessRequirementRepository repository;
	
	@Override
	protected ProcessRequirementRepository getRepository() {
		return repository;
	}

	@Override
	protected ProcessRequirement getNewTarget() {
		return new ProcessRequirement(entity, 1);
	}

	@Override
	protected Serializable getTargetId(ProcessRequirement target) {
		return target.getId();
	}

	@Override
	protected ProcessRequirement findByKey() {
		return getRepository().findByEntityAndInternalNumber(entity, 1);
	}
	
}
