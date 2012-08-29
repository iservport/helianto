package org.helianto.message.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.BasicDao;
import org.helianto.message.domain.NotificationEvent;
import org.helianto.message.test.AbstractMessageDaoIntegrationTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class MessageRepositoryConfigurationTests extends AbstractMessageDaoIntegrationTest {

	@Resource BasicDao<NotificationEvent> notificationEventDao;
	
	@Test
	public void message() {
		NotificationEvent notificationEvent = new NotificationEvent(entity, Long.MAX_VALUE);
		notificationEventDao.saveOrUpdate(notificationEvent);
		assertEquals(notificationEvent, notificationEventDao.findUnique(entity, Long.MAX_VALUE));	
	}

}
