package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.document.filter.internal.AbstractDocumentFormFilterAdapter;
import org.helianto.document.form.DocumentForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DocumentFormFilterAdapterTests {
	
	String OB = "order by alias.docCode ";
	String C1 = "alias.entity.id = 1 ";
	String C2 = "AND ((lower(alias.docCode) like '%search%' ) OR (lower(alias.docName) like '%search%' ) ) ";
	String C3 = "AND (" +
			"(lower(alias.docCode) like '%word1%' OR lower(alias.docCode) like '%word2%' ) OR " +
			"(lower(alias.docName) like '%word1%' OR lower(alias.docName) like '%word2%' ) " +
			") ";
	String C4 = "AND alias.docCode = 'CODE' ";
	String C5 = "AND lower(alias.docName) like '%name%' ";
	String C6 = "AND alias.priority = '0' ";
	
	@Test
	public void entity() {
		assertEquals(C1+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void search() {
		Mockito.when(form.getSearchString()).thenReturn("SEARCH");
		assertEquals(C1+C2+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void multiple() {
		Mockito.when(form.getSearchString()).thenReturn("WORD1 WORD2");
		assertEquals(C1+C3+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		Mockito.when(form.getDocCode()).thenReturn("CODE");
		assertEquals(C1+C4, filter.createCriteriaAsString());
	}
	
	@Test
	public void docName() {
		Mockito.when(form.getDocName()).thenReturn("NAME");
		assertEquals(C1+C5+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void priority() {
		Mockito.when(form.getPriority()).thenReturn('0');
		assertEquals(C1+C6+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private DocumentForm form;
	private AbstractDocumentFormFilterAdapter<DocumentForm> filter;
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	@Before
	public void setUp() {
		form = Mockito.mock(DocumentForm.class);
		filter = new AbstractDocumentFormFilterAdapter(form) { };
		Mockito.when(form.getEntityId()).thenReturn(1);
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}

}
