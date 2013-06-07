package org.helianto.user.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.def.ActivityState;
import org.helianto.core.def.UserState;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.user.form.UserGroupForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserFormFilterAdapterTests {
	
	static String S1 = "select alias from UserGroup alias ";
	static String S2 = "inner join alias.parentAssociations as parentAssociations ";
	static String O0 = "order by alias.userKey ";
	static String O1 = "order by alias.lastEvent DESC ";
	static String C0 = "alias.entity.id = 10 ";
	static String C1 = "AND alias.userKey = 'USERKEY' ";
	static String C2 = "AND alias.userState = 'A' ";
	static String C2a = "AND alias.entity.activityState = 'A' ";
	static String C3 = "AND alias.identity.id not in (  1 ,  2 ) ";
	static String C4 = "AND alias.class = 'G' ";
	static String C5 = "parentAssociations.parent.id = 100 ";
	static String C6 = "AND parentAssociations.parent.userKey = 'USER' ";
	static String C7 = "AND alias.identity.id = 1 ";
	static String C8 = "alias.userKey = 'USERKEY' ";
	static String C9 = "AND ((lower(alias.identity.principal) like '%principal@domain%' ) ) ";
	static String C10 = "AND (" +
			"(lower(alias.identity.principal) like '%first%' OR lower(alias.identity.principal) like '%last%' ) OR " +
			"(lower(alias.identity.optionalAlias) like '%first%' OR lower(alias.identity.optionalAlias) like '%last%' ) OR " +
			"(lower(alias.identity.personalData.firstName) like '%first%' OR lower(alias.identity.personalData.firstName) like '%last%' ) OR " +
			"(lower(alias.identity.personalData.lastName) like '%first%' OR lower(alias.identity.personalData.lastName) like '%last%' ) " +
			") ";
	
	@Test
	public void empty() {
		assertEquals(C0+O0, filter.createCriteriaAsString());
	}

	@Test
	public void lastEventOrder() {
		Mockito.when(form.getUserOrderBy()).thenReturn('L');
		assertEquals(C0+O1, filter.createCriteriaAsString());
	}

	@Test
	public void select() {
		Mockito.when(form.getUserKey()).thenReturn("USERKEY");
		assertEquals(C0+C1, filter.createCriteriaAsString());
	}

	@Test
	public void userState() {
		Mockito.when(form.getUserState()).thenReturn(UserState.ACTIVE.getValue());
		assertEquals(C0+C2+O0, filter.createCriteriaAsString());
	}

	@Test
	public void entityActivityState() {
		Mockito.when(form.getEntityActivityState()).thenReturn(ActivityState.ACTIVE.getValue());
		assertEquals(C0+C2a+O0, filter.createCriteriaAsString());
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
		Mockito.when(form.getExclusions()).thenReturn(exclusions);
		assertEquals(C0+C3+O0, filter.createCriteriaAsString());
	}

	@Test
	public void userGroupType() {
		Mockito.when(form.getUserGroupType()).thenReturn('G');
		assertEquals(C0+C4+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parent() {
		Mockito.when(form.getUserGroupParentId()).thenReturn(100);
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C5+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentUserKey() {
		Mockito.when(form.getParentUserKey()).thenReturn("USER");
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C0+C6+O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void identity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		Mockito.when(form.getIdentity()).thenReturn(identity);
		assertEquals(C0+C7+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentIdentity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		Mockito.when(form.getIdentity()).thenReturn(identity);
		Mockito.when(form.getParentUserKey()).thenReturn("USER");
		assertEquals(C0+C6+C7+O0, filter.createCriteriaAsString());
	}

	@Test
	public void userKey() {
		Mockito.when(form.getEntity()).thenReturn(null);
		Mockito.when(form.getUserKey()).thenReturn("USERKEY");
		assertEquals(C8+O0, filter.createCriteriaAsString());
	}

	@Test
	public void searchNone() {
		Mockito.when(form.getSearchString()).thenReturn("");
		assertEquals(C0+O0, filter.createCriteriaAsString());
	}

	@Test
	public void searchPrincipal() {
		Mockito.when(form.getSearchString()).thenReturn("principal@domain");
		Mockito.when(form.getSearchMode()).thenReturn('P');
		assertEquals(C0+C9+O0, filter.createCriteriaAsString());
	}

	@Test
	public void searchName() {
		Mockito.when(form.getSearchString()).thenReturn("First Last");
		Mockito.when(form.getSearchMode()).thenReturn('A');
		assertEquals(C0+C10+O0, filter.createCriteriaAsString());
	}

	// collabs
	
	private UserFormFilterAdapter filter;
	private UserGroupForm form;
	
    private Entity entity;

	@Before
	public void setUp() {
		entity = EntityTestSupport.createEntity(10);
		form = Mockito.mock(UserGroupForm.class);
		Mockito.when(form.getEntity()).thenReturn(entity);
		filter = new UserFormFilterAdapter(form);
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}
	
}