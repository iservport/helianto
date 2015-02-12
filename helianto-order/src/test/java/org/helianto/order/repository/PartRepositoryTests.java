package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractQueryRepositoryIntegrationTest;
import org.helianto.order.domain.Part;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PartRepositoryTests extends
		AbstractQueryRepositoryIntegrationTest<Part, PartRepository> {

	@Autowired
	private PartRepository repository;

	@Override
	protected PartRepository getRepository() {
		return repository;
	}

	@Override
	protected Part getNewTarget() {

		return new Part(entity, "code");
	}

	@Override
	protected Serializable getTargetId(Part target) {
		return target.getId();
	}

	@Override
	protected Part findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "code");
	}

}
