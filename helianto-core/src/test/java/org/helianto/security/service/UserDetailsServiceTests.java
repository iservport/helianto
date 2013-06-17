package org.helianto.security.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.easymock.EasyMock;
import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.repository.IdentitySecurityRepository;
import org.helianto.core.security.UserSelectorStrategy;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserDetailsServiceTests {
	
	@Test(expected=UsernameNotFoundException.class)
	public void nullUsername() {
		userDetailsService.loadUserByUsername(null);
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void identityIdNotFound() {
		List<IdentitySecurity> identitySecurityList = new ArrayList<IdentitySecurity>();
				
		EasyMock.expect(identitySecurityRepository.findByIdentityId(123)).andReturn(identitySecurityList);
		EasyMock.expect(identitySecurityRepository.findByConsumerKey("123")).andReturn(null);
		EasyMock.replay(identitySecurityRepository);
		
		userDetailsService.loadUserByUsername("123");
	}
	
	@Test
	public void identityIdFound() {
		Identity identity = new Identity("123");
		identity.setId(123);
		List<IdentitySecurity> identitySecurityList = new ArrayList<IdentitySecurity>();
		identitySecurityList.add(new IdentitySecurity(identity, "secret"));
		List<User> userList = new ArrayList<User>();
		User user = new User(new Entity(), identity);
		userList.add(user);
		Set<UserRole> roles = new HashSet<UserRole>();
				
		EasyMock.expect(identitySecurityRepository.findByIdentityId(123L)).andReturn(identitySecurityList);
		EasyMock.replay(identitySecurityRepository);
		
		EasyMock.expect(userRepository.findByIdentityIdOrderByLastEventDesc(123L)).andReturn(userList);
		EasyMock.expect(userRepository.saveAndFlush(user)).andReturn(user);
		EasyMock.replay(userRepository);
		
		EasyMock.expect(userSelectorStrategy.selectUser(userList, null)).andReturn(user);
		EasyMock.replay(userSelectorStrategy);
		
		EasyMock.expect(securityMgr.findRoles(user, true)).andReturn(roles);
		EasyMock.replay(securityMgr);
		
		UserDetails ud = userDetailsService.loadUserByUsername("123");
		assertEquals("123", ud.getUsername());
		assertEquals("secret", ud.getPassword());
	}
	
	@Test
	public void usernameFound() {
		Identity identity = new Identity("123");
		identity.setId(123);
		List<IdentitySecurity> identitySecurityaList = new ArrayList<IdentitySecurity>();
		identitySecurityaList.add(new IdentitySecurity(identity, "secret"));
		List<User> userList = new ArrayList<User>();
		User user = new User(new Entity(), identity);
		userList.add(user);
		Set<UserRole> roles = new HashSet<UserRole>();
				
		EasyMock.expect(identitySecurityRepository.findByIdentityId(123L)).andReturn(null);
		EasyMock.expect(identitySecurityRepository.findByConsumerKey("123")).andReturn(identitySecurityaList.get(0));
		EasyMock.replay(identitySecurityRepository);
		
		EasyMock.expect(userRepository.findByIdentityIdOrderByLastEventDesc(123L)).andReturn(userList);
		EasyMock.expect(userRepository.saveAndFlush(user)).andReturn(user);
		EasyMock.replay(userRepository);
		
		EasyMock.expect(userSelectorStrategy.selectUser(userList, null)).andReturn(user);
		EasyMock.replay(userSelectorStrategy);
		
		EasyMock.expect(securityMgr.findRoles(user, true)).andReturn(roles);
		EasyMock.replay(securityMgr);
		
		UserDetails ud = userDetailsService.loadUserByUsername("123");
		assertEquals("123", ud.getUsername());
		assertEquals("secret", ud.getPassword());
	}
	
	private UserDetailsServiceImpl userDetailsService;

	//- collabs
    private SecurityMgr securityMgr;
    private UserSelectorStrategy userSelectorStrategy;
    private IdentitySecurityRepository identitySecurityRepository;
    private UserRepository userRepository;
    
    @Before
    public void setUp() {
    	userDetailsService = new UserDetailsServiceImpl();
        securityMgr = EasyMock.createMock(SecurityMgr.class);
        userDetailsService.setSecurityMgr(securityMgr);
        userSelectorStrategy = EasyMock.createMock(UserSelectorStrategy.class);
        userDetailsService.setUserSelectorStrategy(userSelectorStrategy);
        identitySecurityRepository = EasyMock.createMock(IdentitySecurityRepository.class);
        userDetailsService.setIdentitySecurityRepository(identitySecurityRepository);
        userRepository = EasyMock.createMock(UserRepository.class);
        userDetailsService.setUserRepository(userRepository);
    }
    
    @After
    public void tearDown() {
    	EasyMock.reset(securityMgr, userSelectorStrategy, identitySecurityRepository, userRepository);
    }
    
}
