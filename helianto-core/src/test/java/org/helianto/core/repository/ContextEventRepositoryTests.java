package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.ContextEvent;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextEventRepositoryTests extends AbstractJpaRepositoryIntegrationTest<ContextEvent, ContextEventRepository> {

	@Autowired
	private ContextEventRepository repository;
	
	protected ContextEventRepository getRepository() {
		return repository;
	}
	
	protected ContextEvent getNewTarget() {
		return new ContextEvent(operator, Long.MAX_VALUE);	
	}
	
	protected Serializable getTargetId(ContextEvent target) {
		return target.getId();
	}
	
	protected ContextEvent findByKey() {
		return getRepository().findByOperatorAndPublicNumber(operator, Long.MAX_VALUE);
	}
	
}

