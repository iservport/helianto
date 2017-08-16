package org.helianto.document.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.DocumentSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentSessionRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<DocumentSession, DocumentSessionRepository> {

	@Autowired
	private DocumentSessionRepository repository;
	
	@Override
	protected DocumentSessionRepository getRepository() {
		return repository;
	}
	
	private Date lastEventDate;

	@Override
	protected DocumentSession getNewTarget() {
		return new DocumentSession(10, lastEventDate);
	}

	@Override
	protected Serializable getTargetId(DocumentSession target) {
		return target.getId();
	}

	@Override
	protected DocumentSession findByKey() {
		return getRepository().findByUserIdAndLastEventDate(10, lastEventDate);
	}
	
}
