package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.form.FolderForm;
import org.helianto.document.filter.internal.AbstractFolderFormFilterAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FolderFormFilterAdapterTests {
	
	private static final String O0 = "order by alias.folderCode ASC ";
	private static final String C1 = "alias.entity.id = 1 ";
	private static final String C2 = "alias.folderCode = 'FOLDER' ";
	

	@Test
	public void empty() {
		assertEquals(O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		Mockito.when(form.getEntityId()).thenReturn(1);
		Mockito.when(form.getFolderCode()).thenReturn("FOLDER");
		assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
	}
	
	// locals
	
	private FolderForm form;
	private AbstractFolderFormFilterAdapter<FolderForm> filter;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		form = Mockito.mock(FolderForm.class);
		filter = new AbstractFolderFormFilterAdapter<FolderForm>(form) {
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {}
		};
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}
	
}
