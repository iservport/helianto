package org.helianto.resource.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.resource.domain.ResourceGroup;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ResourceGroupRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ResourceGroup, ResourceGroupRepository> {

	@Autowired
	private ResourceGroupRepository repository;
	
	@Override
	protected ResourceGroupRepository getRepository() {
		return repository;
	}

	@Override
	protected ResourceGroup getNewTarget() {
		return new ResourceGroup(entity, "CODE");
	}

	@Override
	protected Serializable getTargetId(ResourceGroup target) {
		return target.getId();
	}

	@Override
	protected ResourceGroup findByKey() {
		return getRepository().findByEntityAndResourceCode(entity, "CODE");
	}
	
}
