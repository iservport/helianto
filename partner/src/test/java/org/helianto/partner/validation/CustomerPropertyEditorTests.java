package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.Customer;
import org.helianto.partner.PartnerRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class CustomerPropertyEditorTests extends TestCase {

	private CustomerPropertyEditor customerPropertyEditor;

	public void testGetAsText() {
		Customer customer = new Customer();
		customer.setPartnerRegistry(new PartnerRegistry());
		customer.getPartnerRegistry().setPartnerAlias("ALIAS");
		customerPropertyEditor.setValue(customer);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("ALIAS", customerPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Customer customer = new Customer();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Customer.class, 1)).andReturn(customer);
		EasyMock.replay(session);
		
		customerPropertyEditor.setAsText("1");
		assertSame(customer, customerPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		customerPropertyEditor = new CustomerPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		customerPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
