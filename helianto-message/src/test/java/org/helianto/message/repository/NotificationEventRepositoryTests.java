package org.helianto.message.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.message.domain.NotificationEvent;
import org.helianto.message.test.MessageTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@ContextConfiguration(classes=MessageTestConfig.class)
public class NotificationEventRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<NotificationEvent, NotificationEventRepository> {

	@Autowired
	private NotificationEventRepository repository;
	
	@Override
	protected NotificationEventRepository getRepository() {
		return repository;
	}

	@Override
	protected NotificationEvent getNewTarget() {
		return new NotificationEvent(entity, 1);
	}

	@Override
	protected Serializable getTargetId(NotificationEvent target) {
		return target.getId();
	}

	@Override
	protected NotificationEvent findByKey() {
		return getRepository().findByEntityAndInternalNumber(entity, 1);
	}
	
}
