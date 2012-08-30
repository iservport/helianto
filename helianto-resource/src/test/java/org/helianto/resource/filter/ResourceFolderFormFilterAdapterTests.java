package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.filter.form.AbstractTrunkForm;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.form.CompositeResourceForm;
import org.helianto.resource.form.ResourceFolderForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ResourceFolderFormFilterAdapterTests {
	
	private static final String O0 = "order by alias.folderCode ASC ";
	private static final String C1 = "alias.entity.id = 1 ";
	private static final String C2 = "alias.folderCode = 'FOLDER' ";
	private static final String C3 = "alias.resourceType = 'E' ";
	

	@Test
	public void empty() {
		((AbstractTrunkForm) form).setEntity(null);
		assertEquals(O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		((CompositeResourceForm) form).setFolderCode("FOLDER");
		assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void resourceType() {
		((CompositeResourceForm) form).setResourceType(ResourceType.EQUIPMENT.getValue());
		assertEquals(C1+"AND "+C3+O0, filter.createCriteriaAsString());
	}
	
	// locals
	
	private ResourceFolderForm form;
	private ResourceFolderFormFilterAdapter filter;
	
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity(1);
		form = new CompositeResourceForm(entity);
		filter = new ResourceFolderFormFilterAdapter(form);
	}

}
