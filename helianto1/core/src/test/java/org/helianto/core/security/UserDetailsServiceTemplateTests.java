/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.security;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.EventType;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.userdetails.UserDetails;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceTemplateTests {

	@Test
    public void testLoadUserByUsernameSuccess() {
    	UserLog userLog = UserLog.userLogFactory(selectedUser, new Date(), EventType.LOGIN_ATTEMPT);
    	assertNotNull(userLog.getUser());
    	List<UserGroup> candidates = new ArrayList<UserGroup>();
    	candidates.add(selectedUser);
    	
    	expect(userMgr.findUsers(isA(Identity.class))).andReturn(candidates);
    	expect(userMgr.storeUserLog(isA(User.class), isA(Date.class))).andReturn(userLog);
    	replay(userMgr);

    	UserDetails userDetails = userDetailsService.loadUserByUsername("TEST");
        assertSame(selectedUser, ((PublicUserDetails) userDetails).getUser());
        assertSame(loadedCredential, ((SecureUserDetails) userDetails).getCredential());
    }
	
	public void testConvertUserRoleToString() {
		UserRole userRole = UserRoleTestSupport.createUserRole(selectedUser);
		String roleName = userDetailsService.convertUserRoleToString(userRole);
		assertEquals(roleName, "ROLE_"+userRole.getService().getServiceName()+"_"+userRole.getServiceExtension());
	}
    
//  public void testLoadUsers() {
//	Identity identity = new Identity();
//	List<User> users = UserTestSupport.createUserList(3);
//	
//	expect(userResolutionStrategy.loadUsers(identity))
//	    .andReturn(users);
//	replay(userResolutionStrategy);
//	
//	assertSame(users, userDetailsService.loadUsers(identity));
//	verify(userResolutionStrategy);
//}
//
//public void testSelectUser() {
//	List<User> users = UserTestSupport.createUserList(3);
//	
//    expect(userResolutionStrategy.selectUserFromPreviousLogin(users))
//        .andReturn((User) users.get(0));
//    replay(userResolutionStrategy);
//    
//    assertSame(users.get(0), userDetailsService.selectUser(users));
//    verify(userResolutionStrategy);
//}
//
//public void testSelectUserFirstLogin() {
//	List<User> users = UserTestSupport.createUserList(3);
//	
//    expect(userResolutionStrategy.selectUserFromPreviousLogin(users))
//        .andReturn(null);
//    expect(userResolutionStrategy.selectUserIfAny(users))
//    	.andReturn(users.get(0));
//    replay(userResolutionStrategy);
//    
//    assertSame(users.get(0), userDetailsService.selectUser(users));
//    verify(userResolutionStrategy);
//}
//
//
//public void testLogUser() {
//	User user = new User();
//	UserLog managedUserLog = new UserLog();
//	managedUserLog.setUser(user);
//	
//	expect(userLogDao.merge(isA(UserLog.class)))
//	    .andReturn(managedUserLog);
//    replay(userMgr);
//    
//    userDetailsService.logUser(user);
//    verify(userMgr);
//		
//}
    // domain objects
    
    List<UserGroup> loadedUsers;
    User selectedUser, createdUser;
    Credential loadedCredential;
    Identity loadedIdentity;
    boolean create = false;
    Set<UserRole> roles;
    
    AbstractUserDetailsServiceTemplate userDetailsService;
    
    // collaborators
    private UserMgr userMgr;


	@Before
    public void setUp() {
    	userDetailsService = new UserDetailsServiceStub();
    	List<Entity> entityList = EntityTestSupport.createEntityList(3);
    	List<Identity> identityList = IdentityTestSupport.createIdentityList(1);
        loadedIdentity = identityList.get(0);
        loadedUsers = new ArrayList<UserGroup>(); 
        loadedUsers.addAll(UserTestSupport.createUserList(entityList, identityList));
        selectedUser = (User) loadedUsers.get(0);
        createdUser = UserTestSupport.createUser();
        loadedCredential = CredentialTestSupport.createCredential(loadedIdentity);
        roles = new HashSet<UserRole>();

        userMgr = createMock(SecurityMgr.class);
        userDetailsService.setUserMgr(userMgr);
    }
    
    public class UserDetailsServiceStub extends AbstractUserDetailsServiceTemplate {

		@Override
		public Identity loadAndValidateIdentity(String principal) {
			assertEquals("TEST", principal);
			return loadedIdentity;
		}

		@Override
		public Credential loadAndValidateCredential(Identity identity) {
			assertSame(loadedIdentity, identity);
			return loadedCredential;
		}

		@Override
		protected Set<UserRole> loadAndValidateRoles(User user) {
			assertSame(selectedUser, user);
			return roles;
		}
        
    }
    
}
