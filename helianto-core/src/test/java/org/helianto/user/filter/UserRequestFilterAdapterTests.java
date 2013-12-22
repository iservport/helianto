package org.helianto.user.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.form.UserRequestForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRequestFilterAdapterTests {
	
	String OB = "order by alias.internalNumber ";
	String C0 = "alias.userGroup.entity.id = 1 ";
	String C1 = "alias.userGroup.id = 1 ";
	String C2 = "alias.internalNumber = 100 ";
	String C3 = "lower(alias.principal) like '%test@domain%' ";
	String C4 = "alias.nextCheckDate < '1969-12-31 21:00:01' ";
	String C5 = "alias.tempPassword = 'ABCD' ";
	
	@Test
	public void empty() {
		assertEquals(OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		Mockito.when(form.getUserGroup()).thenReturn(userGroup);
		Mockito.when(form.getInternalNumber()).thenReturn(100L);
		assertEquals(C0+"AND "+C1+"AND "+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void docName() {
		Mockito.when(form.getPrincipal()).thenReturn("TEST@domain");
		assertEquals(C3+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void nextCheckDate() {
    	Mockito.when(form.getDateFieldName()).thenReturn("nextCheckDate");
		Mockito.when(form.getToDate()).thenReturn(new Date(1000L));
		assertEquals(C4+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void tempPassword() {
		Mockito.when(form.getTempPassword()).thenReturn("ABCD");
		assertEquals(C5+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private UserRequestForm form;
	private UserRequestFormFilterAdapter filter;
	private UserGroup userGroup;
	
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity(1);
		userGroup = UserGroupTestSupport.createUserGroup(entity, 1);
		form = Mockito.mock(UserRequestForm.class);
		filter = new UserRequestFormFilterAdapter(form);
		Mockito.when(form.getEntityId()).thenReturn(1);
	}

	@After
	public void tearDown() {
		Mockito.reset(form);
	}
	
}
