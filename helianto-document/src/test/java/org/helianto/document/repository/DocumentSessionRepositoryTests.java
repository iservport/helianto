package org.helianto.document.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.domain.Identity;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.DocumentSession;
import org.helianto.user.domain.User;
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
	
	private User user;
	
	private Date lastEventDate;

	@Override
	protected DocumentSession getNewTarget() {
		Identity identity = identityRepository.saveAndFlush(new Identity("principal"));
		user = userRepository.saveAndFlush(new User(entity, identity));
		return new DocumentSession(user, lastEventDate);
	}

	@Override
	protected Serializable getTargetId(DocumentSession target) {
		return target.getId();
	}

	@Override
	protected DocumentSession findByKey() {
		return getRepository().findByUserAndLastEventDate(user, lastEventDate);
	}
	
}
