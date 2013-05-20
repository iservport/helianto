package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Operator;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class OperatorRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Operator, ContextRepository> {

	protected ContextRepository getRepository() {
		return operatorRepository;
	}
	
	protected Operator getNewTarget() {
		operator.setOperatorName("NAME");
		return operator;
	}
	
	protected Serializable getTargetId(Operator target) {
		return target.getId();
	}
	
	protected Operator findByKey() {
		return getRepository().findByOperatorName("NAME");
	}
		
}
