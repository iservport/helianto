package org.helianto.finance.repository;

import java.io.Serializable;

import org.helianto.finance.domain.CostCentre;
import org.helianto.finance.test.FinanceRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CostCentreRepositoryTests 
	extends FinanceRepositoryIntegrationTest<CostCentre, CostCentreRepository> {

	@Autowired
	private CostCentreRepository repository;
	
	@Override
	protected CostCentreRepository getRepository() {
		return repository;
	}

	@Override
	protected CostCentre getNewTarget() {
		return new CostCentre(entity, "CODE");
	}

	@Override
	protected Serializable getTargetId(CostCentre target) {
		return target.getId();
	}

	@Override
	protected CostCentre findByKey() {
		return getRepository().findByEntityAndCostCentreCode(entity, "CODE");
	}
	
}
