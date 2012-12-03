package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.form.AbstractCustomDocumentForm;
import org.helianto.document.form.CustomDocumentForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CustomDocumentFormFilterAdapterTests {
	
	String OB = "order by alias.docCode ";
	String C1 = "alias.entity.id = 1 ";
	String C2 = "AND alias.docCode = 'CODE' ";
	String C3 = "AND alias.series.id = 2 ";
	String C4 = "AND alias.series.builderCode = 'CODE' ";
	String C5 = "AND alias.series.contentType = 'A' ";
	
	@Test
	public void entity() {
		assertEquals(C1+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		form.setDocCode("CODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void series() {
		form.setSeries(series);
		assertEquals(C1+C3+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void builderCode() {
		form.setBuilderCode("CODE");
		assertEquals(C1+C4+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void contentType() {
		form.setContentType('A');
		assertEquals(C1+C5+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private AbstractCustomDocumentForm form;
	private AbstractCustomDocumentFormFilterAdapter<CustomDocumentForm> filter;
	private DocumentFolder series;
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity();
		entity.setId(1);
		series = new DocumentFolder(entity, "SERIES");
		series.setId(2);
		form = new AbstractCustomDocumentForm() {};
		form.setEntity(entity);
		filter = new AbstractCustomDocumentFormFilterAdapter(form) { };
	}

}
