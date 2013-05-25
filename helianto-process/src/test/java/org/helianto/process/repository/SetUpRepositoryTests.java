package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.process.domain.Operation;
import org.helianto.process.domain.Setup;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.repository.ResourceGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SetUpRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Setup, SetupRepository> {

	@Autowired
	private SetupRepository repository;
	
	@Override
	protected SetupRepository getRepository() {
		return repository;
	}
	
	private Operation operation;
	private ResourceGroup resourceGroup;
	
	@Autowired
	private ProcessDocumentRepository operationRepository;
	
	@Autowired
	private ResourceGroupRepository resourceGroupRepository;
	
	@Override
	protected Setup getNewTarget() {
		return new Setup(operation, resourceGroup);
	}
	
	@Override
	protected Serializable getTargetId(Setup target) {
		return target.getId();
	}
	
	@Override
	protected Setup findByKey() {
		return getRepository().findByOperationAndResource(operation, resourceGroup);
	}
	
	@Override
	protected void setUp() {
		entity = entityRepository.save(new Entity());
		operation = operationRepository.save(new Operation(entity, "10"));
		resourceGroup = resourceGroupRepository.save(new ResourceGroup(entity, "CODE"));
	}

}
