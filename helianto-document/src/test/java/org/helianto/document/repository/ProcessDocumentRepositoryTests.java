package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.ProcessDocument;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessDocumentRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ProcessDocument, ProcessDocumentRepository> {

	@Autowired
	private ProcessDocumentRepository repository;
	
	@Override
	protected ProcessDocumentRepository getRepository() {
		return repository;
	}
	
	@Override
	protected ProcessDocument getNewTarget() {
		return new ProcessDocument(entity, "CODE");
	}
	
	@Override
	protected Serializable getTargetId(ProcessDocument target) {
		return target.getId();
	}
	
	@Override
	protected ProcessDocument findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "CODE");
	}
	
}
