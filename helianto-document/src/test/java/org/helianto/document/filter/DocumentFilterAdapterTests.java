package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.Document;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentFilterAdapterTests {
	
	String OB = "order by alias.docCode ";
	String C1 = "alias.entity.id = 0 ";
	String C2 = "AND alias.docCode = 'CODE' ";
	String C3 = "AND lower(alias.docName) like '%name%' ";
	String C4 = "AND alias.priority = '0' ";
	String C5 = "AND alias.series.builderCode = 'CODE' ";
	String C6 = "AND alias.series.contentType = 'A' ";
	
	@Test
	public void empty() {
		assertEquals(C1+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		target.setDocCode("CODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void docName() {
		target.setDocName("NAME");
		assertEquals(C1+C3+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void priority() {
		target.setPriority('0');
		assertEquals(C1+C4+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void builderCode() {
		filter = new DocumentFilterAdapter<Document>(target);
		((DocumentFilterAdapter<?>) filter).setBuilderCode("CODE");
		assertEquals(C1+C5+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void contentType() {
		filter = new DocumentFilterAdapter<Document>(target);
		((DocumentFilterAdapter<?>) filter).setContentType('A');
		assertEquals(C1+C6+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private Document target;
	private AbstractDocumentFilterAdapter<Document> filter;
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity();
		target = new Document(entity, "");
		filter = new AbstractDocumentFilterAdapter(target) {
		};
	}

}
