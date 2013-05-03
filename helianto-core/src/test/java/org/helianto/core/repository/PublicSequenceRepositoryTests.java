package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.PublicSequence;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicSequenceRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PublicSequence, PublicSequenceRepository> {

	@Autowired
	private PublicSequenceRepository repository;
	
	protected PublicSequenceRepository getRepository() {
		return repository;
	}
	
	protected PublicSequence getNewTarget() {
		return new PublicSequence(operator, "NAME");		
	}
	
	protected Serializable getTargetId(PublicSequence target) {
		return target.getId();
	}
	
	protected PublicSequence findByKey() {
		return getRepository().findByOperatorAndTypeName(operator, "NAME");
	}
	
}
