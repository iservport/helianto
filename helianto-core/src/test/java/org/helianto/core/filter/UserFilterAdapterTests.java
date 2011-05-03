package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserState;
import org.helianto.core.test.UserGroupTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserFilterAdapterTests {
	
	@Test
	public void constructor() {
		assertSame(target, filter.getForm());
		
		Entity entity = new Entity();
		filter = new UserFilterAdapter(entity, "OTHERKEY");
		assertSame(entity, filter.getForm().getEntity());
		assertEquals("OTHERKEY", filter.getForm().getUserKey());
		
		Identity identity = new Identity("PRINCIPAL");
		filter = new UserFilterAdapter(entity, identity);
		assertSame(entity, filter.getForm().getEntity());
		assertEquals("principal", filter.getForm().getUserKey());
		assertSame(identity, ((User)filter.getForm()).getIdentity());
	}
	
	static String S1 = "select alias from UserGroup alias ";
	static String S2 = "inner join alias.parentAssociations as parentAssociations ";
	static String O0 = "order by alias.userKey ";
	static String C0 = "alias.entity.id = 0 ";
	static String C1 = "AND alias.userKey = 'USERKEY' ";
	static String C2 = "AND alias.userState = 'A' ";
	static String C3 = "AND alias.identity.id not in (  1 ,  2 ) ";
	static String C4 = "AND alias.class=UserGroup ";
	static String C5 = "AND parentAssociations.parent.id = 100 ";
	static String C6 = "parentAssociations.parent.userKey = 'USER' ";
	static String C7 = "AND alias.identity.id = 1 ";
	
	@Test
	public void empty() {
		assertEquals(C0+O0, filter.createCriteriaAsString());
	}

	@Test
	public void select() {
		target.setUserKey("USERKEY");
		assertEquals(C0+C1, filter.createCriteriaAsString());
	}

	@Test
	public void userState() {
		target.setUserStateAsEnum(UserState.ACTIVE);
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
		filter.setExclusions(exclusions);
		assertEquals(C0+C3+O0, filter.createCriteriaAsString());
	}

	@Test
	public void clazz() {
		filter.setClazz(UserGroup.class);
		assertEquals(C0+C4+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parent() {
		UserGroup parent = new UserGroup(target.getEntity(), "PARENT");
		parent.setId(100);
		filter.setParent(parent);
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C0+C5+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentUserKey() {
		filter = new UserFilterAdapter("USER");
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C6+O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void identity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		filter.setIdentity(identity);
		assertEquals(C0+C7+O0, filter.createCriteriaAsString());
	}

	// collabs
	
	private UserFilterAdapter filter;
	private UserGroup target;
	
	@Before
	public void setUp() {
		target = UserGroupTestSupport.createUserGroup();
		target.setUserKey("");
		filter = new UserFilterAdapter(target);
	}
}
