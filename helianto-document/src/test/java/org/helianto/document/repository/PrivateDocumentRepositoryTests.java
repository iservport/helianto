package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.PrivateDocument;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateDocumentRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PrivateDocument, PrivateDocumentRepository> {

	@Autowired
	private PrivateDocumentRepository repository;
	
	@Override
	protected PrivateDocumentRepository getRepository() {
		return repository;
	}

	@Override
	protected PrivateDocument getNewTarget() {
		return new PrivateDocument(entity, "CODE");
	}

	@Override
	protected Serializable getTargetId(PrivateDocument target) {
		return target.getId();
	}

	@Override
	protected PrivateDocument findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "CODE");
	}
	
}
