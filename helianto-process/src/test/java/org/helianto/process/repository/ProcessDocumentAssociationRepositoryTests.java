package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.process.domain.Operation;
import org.helianto.process.domain.ProcessDocument;
import org.helianto.process.domain.ProcessDocumentAssociation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessDocumentAssociationRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ProcessDocumentAssociation, ProcessDocumentAssociationRepository> {

	@Autowired
	private ProcessDocumentAssociationRepository repository;
	
	@Override
	protected ProcessDocumentAssociationRepository getRepository() {
		return repository;
	}
	
	private ProcessDocument parent;
	private ProcessDocument child;
	
	@Autowired
	private ProcessDocumentRepository processDocumentRepository;
	
	@Autowired
	private KeyTypeRepository keyTypeRepository;
	
	@Override
	protected ProcessDocumentAssociation getNewTarget() {
		return new ProcessDocumentAssociation(parent, child);
	}
	
	@Override
	protected Serializable getTargetId(ProcessDocumentAssociation target) {
		return target.getId();
	}
	
	@Override
	protected ProcessDocumentAssociation findByKey() {
		return getRepository().findByParentAndChild(parent, child);
	}
	
	@Override
	protected void setUp() {
		entity = entityRepository.save(new Entity());
		parent = processDocumentRepository.save(new Operation(entity, "10"));
		child = processDocumentRepository.save(new Operation(entity, "20"));
	}

}
