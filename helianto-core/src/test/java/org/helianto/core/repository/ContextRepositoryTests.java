package org.helianto.core.repository;

import java.io.Serializable;

import javax.inject.Inject;

import org.helianto.core.domain.Context;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Context, ContextRepository> {
	
	@Inject
	private ContextRepository contextRepository;

	protected ContextRepository getRepository() {
		return contextRepository;
	}
	
	protected Context getNewTarget() {
		return new Context("NAME");
	}
	
	protected Serializable getTargetId(Context target) {
		return target.getId();
	}
	
	protected Context findByKey() {
		return getRepository().findByContextName("NAME");
	}
		
}
