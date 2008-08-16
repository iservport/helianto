package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Laboratory;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class LaboratoryPropertyEditorTests extends TestCase {

	private LaboratoryPropertyEditor laboratoryPropertyEditor;

	public void testGetAsText() {
		Laboratory laboratory = new Laboratory();
		laboratory.setPartnerRegistry(new PartnerRegistry());
		laboratory.getPartnerRegistry().setPartnerAlias("ALIAS");
		laboratoryPropertyEditor.setValue(laboratory);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("ALIAS", laboratoryPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Laboratory laboratory = new Laboratory();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Laboratory.class, 1)).andReturn(laboratory);
		EasyMock.replay(session);
		
		laboratoryPropertyEditor.setAsText("1");
		assertSame(laboratory, laboratoryPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		laboratoryPropertyEditor = new LaboratoryPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		laboratoryPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
