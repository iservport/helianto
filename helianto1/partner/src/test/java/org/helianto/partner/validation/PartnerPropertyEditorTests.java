package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.Partner;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class PartnerPropertyEditorTests extends TestCase {

	private PartnerPropertyEditor partnerPropertyEditor;

	public void testGetAsText() {
		Partner partner = new Partner();
		partner.setSequence(1);
		partnerPropertyEditor.setValue(partner);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("1", partnerPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Partner partner = new Partner();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Partner.class, 1)).andReturn(partner);
		EasyMock.replay(session);
		
		partnerPropertyEditor.setAsText("1");
		assertSame(partner, partnerPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		partnerPropertyEditor = new PartnerPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		partnerPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
