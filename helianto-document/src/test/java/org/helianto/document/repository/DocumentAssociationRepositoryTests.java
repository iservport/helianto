package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentAssociation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentAssociationRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<DocumentAssociation, DocumentAssociationRepository> {

	@Autowired
	private DocumentAssociationRepository repository;
	
	@Override
	protected DocumentAssociationRepository getRepository() {
		return repository;
	}

	@Autowired
	private DocumentRepository documentRepository;
	
	private Document parent;
	private Document child;
	
	@Override
	protected DocumentAssociation getNewTarget() {
		return new DocumentAssociation(parent, child);
	}

	@Override
	protected Serializable getTargetId(DocumentAssociation target) {
		return target.getId();
	}

	@Override
	protected DocumentAssociation findByKey() {
		return getRepository().findByParentAndChild(parent, child);
	}
	
	@Override
	protected void setUp() {
		parent = documentRepository.save(new Document(entity, "PARENT"));
		child = documentRepository.save(new Document(entity, "CHILD"));
	}

}
