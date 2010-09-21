package org.helianto.message.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.Service;
import org.helianto.core.repository.BasicDao;
import org.helianto.message.ServiceEvent;
import org.helianto.message.test.AbstractMessageDaoIntegrationTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class MessageRepositoryConfigurationTests extends AbstractMessageDaoIntegrationTest {

	@Resource BasicDao<Service> serviceDao;
	@Resource BasicDao<ServiceEvent> serviceEventDao;
	
	@Test
	public void message() {
		Service service = new Service(entity.getOperator(), "SERVICE");
		serviceDao.saveOrUpdate(service);
		ServiceEvent serviceEvent = new ServiceEvent(service, "EVENTCODE");
		serviceEventDao.saveOrUpdate(serviceEvent);
		assertEquals(serviceEvent, serviceEventDao.findUnique(service, "EVENTCODE"));
		
	}

}
