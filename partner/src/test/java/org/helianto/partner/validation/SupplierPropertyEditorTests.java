package org.helianto.partner.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Supplier;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class SupplierPropertyEditorTests extends TestCase {

	private SupplierPropertyEditor supplierPropertyEditor;

	public void testGetAsText() {
		Supplier supplier = new Supplier();
		supplier.setPartnerRegistry(new PartnerRegistry());
		supplier.getPartnerRegistry().setPartnerAlias("ALIAS");
		supplierPropertyEditor.setValue(supplier);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("ALIAS", supplierPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Supplier supplier = new Supplier();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Supplier.class, 1)).andReturn(supplier);
		EasyMock.replay(session);
		
		supplierPropertyEditor.setAsText("1");
		assertSame(supplier, supplierPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		supplierPropertyEditor = new SupplierPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		supplierPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
