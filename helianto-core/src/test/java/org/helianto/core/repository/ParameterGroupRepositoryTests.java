package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.ParameterGroup;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ParameterGroupRepositoryTests extends AbstractJpaRepositoryIntegrationTest<ParameterGroup, ParameterGroupRepository> {

	@Autowired
	private ParameterGroupRepository repository;
	
	protected ParameterGroupRepository getRepository() {
		return repository;
	}
	
	protected ParameterGroup getNewTarget() {
		return new ParameterGroup(operator, "NAME");
	}
	
	protected Serializable getTargetId(ParameterGroup target) {
		return target.getId();
	}
	
	protected ParameterGroup findByKey() {
		return getRepository().findByOperatorAndParameterName(operator, "NAME");
	}

}
