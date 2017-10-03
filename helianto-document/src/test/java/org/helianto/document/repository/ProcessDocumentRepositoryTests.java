package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.ProcessDocument2;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessDocumentRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ProcessDocument2, ProcessDocumentRepository2> {

	@Autowired
	private ProcessDocumentRepository2 repository;
	
	@Override
	protected ProcessDocumentRepository2 getRepository() {
		return repository;
	}
	
	@Override
	protected ProcessDocument2 getNewTarget() {
		return new ProcessDocument2(entity, "CODE");
	}
	
	@Override
	protected Serializable getTargetId(ProcessDocument2 target) {
		return target.getId();
	}
	
	@Override
	protected ProcessDocument2 findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "CODE");
	}
	
}
