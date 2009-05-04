package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Manufacturer;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class ManufacturerPropertyEditorTests extends TestCase {

	private ManufacturerPropertyEditor manufacturerPropertyEditor;

	public void testGetAsText() {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setPartnerRegistry(new PartnerRegistry());
		manufacturer.getPartnerRegistry().setPartnerAlias("ALIAS");
		manufacturerPropertyEditor.setValue(manufacturer);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("ALIAS", manufacturerPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Manufacturer manufacturer = new Manufacturer();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Manufacturer.class, 1)).andReturn(manufacturer);
		EasyMock.replay(session);
		
		manufacturerPropertyEditor.setAsText("1");
		assertSame(manufacturer, manufacturerPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		manufacturerPropertyEditor = new ManufacturerPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		manufacturerPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
