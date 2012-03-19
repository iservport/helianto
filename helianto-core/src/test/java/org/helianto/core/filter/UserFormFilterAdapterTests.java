package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.UserGroup;
import org.helianto.core.def.UserState;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.filter.form.UserGroupForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserFormFilterAdapterTests {
	
	static String S1 = "select alias from UserGroup alias ";
	static String S2 = "inner join alias.parentAssociations as parentAssociations ";
	static String O0 = "order by alias.userKey ";
	static String C0 = "alias.entity.id = 10 ";
	static String C1 = "AND alias.userKey = 'USERKEY' ";
	static String C2 = "AND alias.userState = 'A' ";
	static String C3 = "AND alias.identity.id not in (  1 ,  2 ) ";
	static String C4 = "AND alias.class = 'G' ";
	static String C5 = "AND parentAssociations.parent.id = 100 ";
	static String C6 = "AND parentAssociations.parent.userKey = 'USER' ";
	static String C7 = "AND alias.identity.id = 1 ";
	static String C8 = "alias.userKey = 'USERKEY' ";
	
	@Test
	public void empty() {
		assertEquals(C0+O0, filter.createCriteriaAsString());
	}

	@Test
	public void select() {
		((CompositeUserForm) form).setUserKey("USERKEY");
		assertEquals(C0+C1, filter.createCriteriaAsString());
	}

	@Test
	public void userState() {
		((CompositeUserForm) form).setUserState(UserState.ACTIVE.getValue());
		assertEquals(C0+C2+O0, filter.createCriteriaAsString());
	}

	@Test
	public void exclusions() {
		List<Identity> exclusions = new ArrayList<Identity>();
		Identity i1 = new Identity("1");
		i1.setId(1);
		exclusions.add(i1);
		Identity i2 = new Identity("2");
		i2.setId(2);
		exclusions.add(i2);
		form.setExclusions(exclusions);
		assertEquals(C0+C3+O0, filter.createCriteriaAsString());
	}

	@Test
	public void userGroupType() {
		form.setUserGroupType('G');
		assertEquals(C0+C4+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parent() {
		UserGroup parent = new UserGroup(form.getEntity(), "PARENT");
		parent.setId(100);
		form.setParent(parent);
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C0+C5+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentUserKey() {
		((CompositeUserForm) form).setParentUserKey("USER");
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C0+C6+O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void identity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		form.setIdentity(identity);
		assertEquals(C0+C7+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentIdentity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		form.setIdentity(identity);
		((CompositeUserForm) form).setParentUserKey("USER");
		assertEquals(C0+C6+C7+O0, filter.createCriteriaAsString());
	}

	@Test
	public void userKey() {
		form = new CompositeUserForm("USERKEY");
		filter = new UserFormFilterAdapter(form);
		assertEquals(C8+O0, filter.createCriteriaAsString());
	}

	// collabs
	
	private UserFormFilterAdapter filter;
	private UserGroupForm form;
	
    private Entity entity;

	@Before
	public void setUp() {
		entity = EntityTestSupport.createEntity(10);
		form = new CompositeUserForm(entity);
		filter = new UserFormFilterAdapter(form);
	}
}
