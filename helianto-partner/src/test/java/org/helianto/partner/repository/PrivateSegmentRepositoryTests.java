package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.PrivateSegment;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateSegmentRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PrivateSegment, PrivateSegmentRepository> {

	@Autowired
	private PrivateSegmentRepository repository;
	
	@Override
	protected PrivateSegmentRepository getRepository() {
		return repository;
	}

	@Override
	protected PrivateSegment getNewTarget() {
		return new PrivateSegment(entity, "ALIAS");
	}

	@Override
	protected Serializable getTargetId(PrivateSegment target) {
		return target.getId();
	}

	@Override
	protected PrivateSegment findByKey() {
		return getRepository().findByEntityAndSegmentAlias(entity, "ALIAS");
	}
	
}
