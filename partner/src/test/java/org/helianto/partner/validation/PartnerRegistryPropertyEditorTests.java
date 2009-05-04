package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.PartnerRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class PartnerRegistryPropertyEditorTests extends TestCase {

	private PartnerRegistryPropertyEditor partnerRegistryPropertyEditor;

	public void testGetAsText() {
		PartnerRegistry partnerRegistry = new PartnerRegistry();
		partnerRegistry.setPartnerAlias("ALIAS");
		partnerRegistryPropertyEditor.setValue(partnerRegistry);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("ALIAS", partnerRegistryPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		PartnerRegistry partnerRegistry = new PartnerRegistry();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(PartnerRegistry.class, 1)).andReturn(partnerRegistry);
		EasyMock.replay(session);
		
		partnerRegistryPropertyEditor.setAsText("1");
		assertSame(partnerRegistry, partnerRegistryPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		partnerRegistryPropertyEditor = new PartnerRegistryPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		partnerRegistryPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
