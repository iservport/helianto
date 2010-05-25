package org.helianto.core.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Set;

import org.helianto.core.Credential;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserDetailsFactoryBeanTests {
	
	UserDetailsFactoryBean userDetailsFactoryBean;
	
	@Test
	public void convertUserRoleToString() {
		UserRole userRole = new UserRole(user, service);
		assertEquals("ROLE_USER_READ", userDetailsFactoryBean.convertUserRoleToString(userRole));
	}

	@Test
	public void createUserDetails() {
		user.getRoleList().add(new UserRole(user, service));
	    Credential credential = new Credential();
		UserDetailsAdapter userDetails = (UserDetailsAdapter) userDetailsFactoryBean.createUserDetails(user, credential);
		assertSame(user, userDetails.getUser());
		assertEquals(user.getUserKey(), userDetails.getUsername());
		assertSame(credential, userDetails.getCredential());
		assertEquals("default", userDetails.getPassword());
		assertEquals("ROLE_USER_READ", userDetails.getAuthorities()[0].toString());
	}

    // domain objects
    
	Service service;
    User user;
    boolean create = false;
    Set<UserRole> roles;
    
    AbstractUserDetailsServiceTemplate userDetailsService;
    
	@Before
    public void setUp() {
		user = UserTestSupport.createUser();
		service = new Service(user.getOperator());
		userDetailsFactoryBean = new UserDetailsFactoryBean();
    }
    
}
