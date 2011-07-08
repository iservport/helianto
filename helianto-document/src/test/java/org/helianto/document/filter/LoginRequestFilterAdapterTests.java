package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.LoginRequest;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class LoginRequestFilterAdapterTests {
	
	String OB = "order by alias.internalNumber ";
	String C1 = "alias.entity.id = 0 ";
	String C2 = "AND alias.internalNumber = 100 ";
	String C3 = "AND lower(alias.principal) like '%test@domain%' ";
	String C4 = "AND (alias.nextCheckDate >= '1969-12-24 23:59:59' AND alias.nextCheckDate < '1969-12-31 21:00:01' ) ";
	
	@Test
	public void empty() {
		assertEquals(C1+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void select() {
		form.setInternalNumber(100);
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void docName() {
		form.setPrincipal("TEST@domain");
		assertEquals(C1+C3+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void nextCheckDate() {
		form.setNextCheckDate(new Date(1000L));
		assertEquals(C1+C4+OB, filter.createCriteriaAsString());
	}
	
	// locals
	
	private LoginRequest form;
	private LoginRequestFilterAdapter filter;
	
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity();
		form = new LoginRequest(entity, 0);
		filter = new LoginRequestFilterAdapter(form);
	}

}
