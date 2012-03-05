package org.helianto.core.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserDetailsAdapterTests {
	
	@Test
	public void grantAuthorities() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(100);
		Collection<UserRole> roles = new HashSet<UserRole>();
		roles.add(new UserRole(user, new Service(new Operator(), "A"), "READ, WRITE"));
		roles.add(new UserRole(user, new Service(new Operator(), "B"), "TEST"));
		
		adapter.grantAuthorities(roles, user);
		assertEquals(6, adapter.getAuthorities().size());
        for (GrantedAuthority authority : adapter.getAuthorities()) {
        	assertTrue(adapter.getAuthorities().contains(authority));
        }
		
	}
	
	@Test
	public void anonymous() {
		Entity entity = EntityTestSupport.createEntity(100);
		UserGroup userGroup  = new UserGroup(entity, "USER");
		adapter = new UserDetailsAdapter(userGroup);
		assertTrue(adapter.isAnonymous());
	}
	
	private UserDetailsAdapter adapter;
	
	@Before
	public void setUp() {
		adapter = new UserDetailsAdapter();
	}

}
