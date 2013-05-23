package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Document, DocumentRepository> {

	@Autowired
	private DocumentRepository repository;
	
	@Override
	protected DocumentRepository getRepository() {
		return repository;
	}

	@Override
	protected Document getNewTarget() {
		return new Document(entity, "CODE");
	}

	@Override
	protected Serializable getTargetId(Document target) {
		return target.getId();
	}

	@Override
	protected Document findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "CODE");
	}
	
}
