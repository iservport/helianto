package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.UserGroup;
import org.helianto.core.filter.form.AssociationForm;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

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
		((CompositeUserForm) form).setParent(parent);
		assertEquals("alias.parent.id = 1 ", filter.createCriteriaAsString());
	}

	@Test
	public void child() {
		((CompositeUserForm) form).setChildId(2);
		assertEquals("alias.child.id = 2 ", filter.createCriteriaAsString());
	}

	@Test
	public void key() {
		((CompositeUserForm) form).setParent(parent);
		((CompositeUserForm) form).setChildId(2);
		assertEquals("alias.parent.id = 1 AND alias.child.id = 2 ", filter.createCriteriaAsString());
	}

	// locals
	
	private UserAssociationFormFilterAdapter filter;
	private AssociationForm form;
	private UserGroup parent;
	
	@Before
	public void setUp() {
		parent = UserTestSupport.createUser(1);
		form = new CompositeUserForm();
		filter = new UserAssociationFormFilterAdapter(form);
	}
}
