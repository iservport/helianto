package org.helianto.core.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserDetailsAdapterTests {
	
	@Test
	public void grantAuthorities() {
		User user = new User(new Entity(), new Identity("p"));
		user.getIdentity().setId(100);
		IdentitySecurity identitySecurity = new IdentitySecurity("principal", "password");
		adapter = new UserDetailsAdapter(user, identitySecurity);

		Collection<UserRole> roles = new HashSet<UserRole>();
		roles.add(new UserRole(user, new Service(new Operator(), "A"), "READ, WRITE"));
		roles.add(new UserRole(user, new Service(new Operator(), "B"), "TEST"));
		
		adapter.grantAuthorities(roles);
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

}
