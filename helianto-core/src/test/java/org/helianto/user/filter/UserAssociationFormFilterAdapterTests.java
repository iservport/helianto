package org.helianto.user.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.filter.UserAssociationFormFilterAdapter;
import org.helianto.core.filter.form.AssociationForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserAssociationFormFilterAdapterTests {

	@Test
	public void empty() {
		assertEquals("", filter.createCriteriaAsString());
	}

	@Test
	public void parent() {
		Mockito.when(form.getParentId()).thenReturn(1);
		assertEquals("alias.parent.id = 1 ", filter.createCriteriaAsString());
	}

	@Test
	public void child() {
		Mockito.when(form.getChildId()).thenReturn(2);
		assertEquals("alias.child.id = 2 ", filter.createCriteriaAsString());
	}

	@Test
	public void key() {
		Mockito.when(form.getParentId()).thenReturn(1);
		Mockito.when(form.getChildId()).thenReturn(2);
		assertEquals("alias.parent.id = 1 AND alias.child.id = 2 ", filter.createCriteriaAsString());
	}

	// locals
	
	private UserAssociationFormFilterAdapter filter;
	private AssociationForm form;
	
	@Before
	public void setUp() {
     	form = Mockito.mock(AssociationForm.class);
		filter = new UserAssociationFormFilterAdapter(form);
	}
	
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }

}
