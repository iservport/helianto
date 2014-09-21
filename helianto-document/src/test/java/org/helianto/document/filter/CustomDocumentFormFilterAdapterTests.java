package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.document.filter.internal.AbstractCustomDocumentFormFilterAdapter;
import org.helianto.document.form.CustomDocumentForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
		Mockito.when(form.getDocCode()).thenReturn("CODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void series() {
		Mockito.when(form.getFolderId()).thenReturn(2);
		assertEquals(C1+C3+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void builderCode() {
		Mockito.when(form.getFolderCode()).thenReturn("CODE");
		assertEquals(C1+C4+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void contentType() {
		Mockito.when(form.getContentType()).thenReturn('A');
		assertEquals(C1+C5+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private CustomDocumentForm form;
	private AbstractCustomDocumentFormFilterAdapter<CustomDocumentForm> filter;
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	@Before
	public void setUp() {
		form = Mockito.mock(CustomDocumentForm.class);
		Mockito.when(form.getEntityId()).thenReturn(1);
		filter = new AbstractCustomDocumentFormFilterAdapter(form) { };
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}

}
