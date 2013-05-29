package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.inventory.domain.ProcessAgreement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessAgreementRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ProcessAgreement, ProcessAgreementRepository> {

	@Autowired
	private ProcessAgreementRepository repository;
	
	@Override
	protected ProcessAgreementRepository getRepository() {
		return repository;
	}

	@Override
	protected ProcessAgreement getNewTarget() {
		return new ProcessAgreement(entity, 1);
	}

	@Override
	protected Serializable getTargetId(ProcessAgreement target) {
		return target.getId();
	}

	@Override
	protected ProcessAgreement findByKey() {
		return getRepository().findByEntityAndInternalNumber(entity, 1);
	}
	
}
