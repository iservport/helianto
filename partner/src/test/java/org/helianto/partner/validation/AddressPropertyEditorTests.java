package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.Address;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class AddressPropertyEditorTests extends TestCase {

	private AddressPropertyEditor addressPropertyEditor;

	public void testGetAsText() {
		Address address = new Address();
		address.setSequence(1);
		addressPropertyEditor.setValue(address);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("1", addressPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Address address = new Address();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Address.class, 1)).andReturn(address);
		EasyMock.replay(session);
		
		addressPropertyEditor.setAsText("1");
		assertSame(address, addressPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		addressPropertyEditor = new AddressPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		addressPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
