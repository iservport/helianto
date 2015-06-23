package org.helianto.core.repository;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import org.helianto.core.domain.Lead;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class LeadRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Lead, LeadRepository> 
{
	
	@Inject
	private LeadRepository repository;
	
	private static final Date ISSUE_DATE = new Date();

	protected LeadRepository getRepository() {
		return repository;
	}
	
	protected Lead getNewTarget() {
		return new Lead("PRINCIPAL", ISSUE_DATE);		
	}
	
	protected Serializable getTargetId(Lead target) {
		return target.getId();
	}
	
	protected Lead findByKey() {
		return getRepository().findLastByPrincipal("PRINCIPAL");
	}
	
	@Override
	protected boolean isFoundAsExpected(Lead other) {
		return true;
	}

}
