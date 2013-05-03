package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.PrivateSequence;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateSequenceRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PrivateSequence, PrivateSequenceRepository> {

	@Autowired
	private PrivateSequenceRepository repository;
	
	protected PrivateSequenceRepository getRepository() {
		return repository;
	}
	
	protected PrivateSequence getNewTarget() {
		return new PrivateSequence(entity, "TYPE");
	}
	
	protected Serializable getTargetId(PrivateSequence target) {
		return target.getId();
	}
	
	protected PrivateSequence findByKey() {
		return getRepository().findByEntityAndTypeName(entity, "TYPE");
	}
	
}
