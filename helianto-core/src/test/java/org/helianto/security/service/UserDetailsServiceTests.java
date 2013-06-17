package org.helianto.security.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.easymock.EasyMock;
import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.ConnectionData;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.repository.ConnectionDataRepository;
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
		List<ConnectionData> connectionDataList = new ArrayList<ConnectionData>();
				
		EasyMock.expect(connectionDataRepository.findByIdentityId(123)).andReturn(connectionDataList);
		EasyMock.expect(connectionDataRepository.findByConsumerKey("123")).andReturn(null);
		EasyMock.replay(connectionDataRepository);
		
		userDetailsService.loadUserByUsername("123");
	}
	
	@Test
	public void identityIdFound() {
		Identity identity = new Identity("123");
		identity.setId(123);
		List<ConnectionData> connectionDataList = new ArrayList<ConnectionData>();
		connectionDataList.add(new ConnectionData(identity, "secret"));
		List<User> userList = new ArrayList<User>();
		User user = new User(new Entity(), identity);
		userList.add(user);
		Set<UserRole> roles = new HashSet<UserRole>();
				
		EasyMock.expect(connectionDataRepository.findByIdentityId(123L)).andReturn(connectionDataList);
		EasyMock.replay(connectionDataRepository);
		
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
		List<ConnectionData> connectionDataList = new ArrayList<ConnectionData>();
		connectionDataList.add(new ConnectionData(identity, "secret"));
		List<User> userList = new ArrayList<User>();
		User user = new User(new Entity(), identity);
		userList.add(user);
		Set<UserRole> roles = new HashSet<UserRole>();
				
		EasyMock.expect(connectionDataRepository.findByIdentityId(123L)).andReturn(null);
		EasyMock.expect(connectionDataRepository.findByConsumerKey("123")).andReturn(connectionDataList.get(0));
		EasyMock.replay(connectionDataRepository);
		
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
    private ConnectionDataRepository connectionDataRepository;
    private UserRepository userRepository;
    
    @Before
    public void setUp() {
    	userDetailsService = new UserDetailsServiceImpl();
        securityMgr = EasyMock.createMock(SecurityMgr.class);
        userDetailsService.setSecurityMgr(securityMgr);
        userSelectorStrategy = EasyMock.createMock(UserSelectorStrategy.class);
        userDetailsService.setUserSelectorStrategy(userSelectorStrategy);
        connectionDataRepository = EasyMock.createMock(ConnectionDataRepository.class);
        userDetailsService.setConnectionDataRepository(connectionDataRepository);
        userRepository = EasyMock.createMock(UserRepository.class);
        userDetailsService.setUserRepository(userRepository);
    }
    
    @After
    public void tearDown() {
    	EasyMock.reset(securityMgr, userSelectorStrategy, connectionDataRepository, userRepository);
    }
    
}
