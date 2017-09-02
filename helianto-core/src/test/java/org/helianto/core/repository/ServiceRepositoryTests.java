package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Service;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ServiceRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Service, ServiceRepository> {

	@Autowired
	private ServiceRepository repository;
	
	protected ServiceRepository getRepository() {
		return repository;
	}
	
	protected Service getNewTarget() {
		return new Service("DEFAULT", "NAME");
	}
	
	protected Serializable getTargetId(Service target) {
		return target.getId();
	}
	
	protected Service findByKey() {
		return getRepository().findByContextNameAndServiceName("DEFAULT", "NAME");
	}
	
	@Override
	protected void setUp() {
		repository.deleteAll();
	}
	
}
