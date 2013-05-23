package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.DocumentFolder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentFolderRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<DocumentFolder, DocumentFolderRepository> {

	@Autowired
	private DocumentFolderRepository repository;
	
	@Override
	protected DocumentFolderRepository getRepository() {
		return repository;
	}

	@Override
	protected DocumentFolder getNewTarget() {
		return new DocumentFolder(entity, "CODE");
	}

	@Override
	protected Serializable getTargetId(DocumentFolder target) {
		return target.getId();
	}

	@Override
	protected DocumentFolder findByKey() {
		return getRepository().findByEntityAndFolderCode(entity, "CODE");
	}
	
}
