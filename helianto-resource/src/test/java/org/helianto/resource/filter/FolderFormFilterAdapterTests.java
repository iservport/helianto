package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.form.AbstractTrunkForm;
import org.helianto.core.form.FolderForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

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
		entity = null;
		assertEquals(O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		folderCode = "FOLDER";
		assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
	}
	
	// locals
	
	private FolderForm form;
	private AbstractFolderFormFilterAdapter<FolderForm> filter;
	private Entity entity;
	private String folderCode;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = EntityTestSupport.createEntity(1);
		form = new FolderFormStub();
		filter = new AbstractFolderFormFilterAdapter<FolderForm>(form) {
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {}
		};
	}
	
	@SuppressWarnings("serial")
	private class FolderFormStub extends AbstractTrunkForm implements FolderForm {

		public Entity getEntity() {
			return entity;
		}

		public String getFolderCode() {
			return folderCode;
		}
		
	}

}
