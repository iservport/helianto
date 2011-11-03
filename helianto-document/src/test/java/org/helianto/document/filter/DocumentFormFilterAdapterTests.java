package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.form.AbstractDocumentForm;
import org.helianto.document.form.DocumentForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentFormFilterAdapterTests {
	
	String OB = "order by alias.docCode ";
	String C1 = "alias.entity.id = 1 ";
	String C2 = "AND lower(alias.docCode) like '%search%' ";
	String C3 = "AND alias.docCode = 'CODE' ";
	String C4 = "AND lower(alias.docName) like '%name%' ";
	String C5 = "AND alias.priority = '0' ";
	
	@Test
	public void entity() {
		assertEquals(C1+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void search() {
		form.setSearchString("SEARCH");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		form.setDocCode("CODE");
		assertEquals(C1+C3, filter.createCriteriaAsString());
	}
	
	@Test
	public void docName() {
		form.setDocName("NAME");
		assertEquals(C1+C4+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void priority() {
		form.setPriority('0');
		assertEquals(C1+C5+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private AbstractDocumentForm form;
	private AbstractDocumentFormFilterAdapter<DocumentForm> filter;
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity();
		entity.setId(1);
		form = new AbstractDocumentForm() {};
		form.setEntity(entity);
		filter = new AbstractDocumentFormFilterAdapter(form) { };
	}

}
