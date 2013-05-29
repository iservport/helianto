package org.helianto.message.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import org.helianto.core.SequenceMgr;
import org.helianto.message.domain.NotificationEvent;
import org.helianto.message.repository.NotificationEventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class MessageMgrImplTests {

	@Test
	public void storeNotificationEvent() {
		NotificationEvent notificationEvent = new NotificationEvent();
		
		sequenceMgr.validateInternalNumber(notificationEvent);
		replay(sequenceMgr);
		
		expect(notificationEventRepository.saveAndFlush(notificationEvent)).andReturn(notificationEvent);
		replay(notificationEventRepository);
		
		assertSame(notificationEvent, cardMgr.storeNotificationEvent(notificationEvent));
		verify(sequenceMgr);
		verify(notificationEventRepository);
	}
	
	// collabs
	
	private MessageMgrImpl cardMgr;
	private NotificationEventRepository notificationEventRepository;
	private SequenceMgr sequenceMgr;
	
	@Before
	public void setUp() {
		cardMgr = new MessageMgrImpl();
		notificationEventRepository = createMock(NotificationEventRepository.class);
		cardMgr.setNotificationEventRepository(notificationEventRepository);
		sequenceMgr = createMock(SequenceMgr.class);
		cardMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		reset(notificationEventRepository);
		reset(sequenceMgr);
	}
	

}
