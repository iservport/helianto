package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.State;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class StateRepositoryTests extends AbstractJpaRepositoryIntegrationTest<State, StateRepository> {

	@Autowired
	private StateRepository repository;
	
	protected StateRepository getRepository() {
		return repository;
	}
	
	protected State getNewTarget() {
		return new State(operator, "CODE");		
	}
	
	protected Serializable getTargetId(State target) {
		return target.getId();
	}
	
	protected State findByKey() {
		return getRepository().findByContextAndStateCode(operator, "CODE");
	}
	
}
