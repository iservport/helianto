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
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.UserTestSupport;


/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceImplTests extends TestCase {
    
    // class under test
    private UserDetailsServiceImpl userDetailsService;
    
    public void testIdentityResolutionStrategy() {
    	String principal = "TEST";
    	Identity identity = new Identity();
    	
		expect(identityResolutionStrategy.loadAndValidateIdentity(principal))
		    .andReturn(identity);
		replay(identityResolutionStrategy);
    	
		assertSame(identity, userDetailsService.loadAndValidateIdentity(principal));
		verify(identityResolutionStrategy);
    }
    
    public void testLoadAndValidateCredential() {
        Credential credential = CredentialTestSupport.createCredential();
        
        expect(identityResolutionStrategy.loadAndValidateCredential(credential.getIdentity()))
            .andReturn(credential);
        replay(identityResolutionStrategy);
        
        assertSame(credential, userDetailsService.loadAndValidateCredential(credential.getIdentity()));
        verify(identityResolutionStrategy);
    }

    public void testLoadUsers() {
    	Identity identity = new Identity();
    	List<User> users = UserTestSupport.createUserList(3);
    	
		expect(userResolutionStrategy.loadUsers(identity))
		    .andReturn(users);
		replay(userResolutionStrategy);
    	
		assertSame(users, userDetailsService.loadUsers(identity));
		verify(userResolutionStrategy);
    }
    
    public void testSelectUser() {
    	List<User> users = UserTestSupport.createUserList(3);
    	
        expect(userResolutionStrategy.selectUserFromPreviousLogin(users))
            .andReturn(users.get(0));
        replay(userResolutionStrategy);
        
        assertSame(users.get(0), userDetailsService.selectUser(users));
        verify(userResolutionStrategy);
    }

    public void testSelectUserFirstLogin() {
    	List<User> users = UserTestSupport.createUserList(3);
    	
        expect(userResolutionStrategy.selectUserFromPreviousLogin(users))
            .andReturn(null);
        expect(userResolutionStrategy.selectUserIfAny(users))
        	.andReturn(users.get(0));
        replay(userResolutionStrategy);
        
        assertSame(users.get(0), userDetailsService.selectUser(users));
        verify(userResolutionStrategy);
    }

    
    public void testCreateUser() {
    	Identity identity = new Identity();
    	User user = new User();
    	
        expect(userResolutionStrategy.createUser(identity))
            .andReturn(user);
        replay(userResolutionStrategy);
        
        assertSame(user, userDetailsService.createUser(identity));
        verify(userResolutionStrategy);
    }
    
    public void testLogUser() {
    	securityMgr.writeUserLog(isA(User.class), isA(Date.class));
        replay(securityMgr);
        
        userDetailsService.logUser(new User());
        verify(securityMgr);
    		
    }
    
    // collaborators
    private SecurityMgr securityMgr;
    private IdentityResolutionStrategy identityResolutionStrategy;
    private UserResolutionStrategy userResolutionStrategy;
    // setup
    
    @Override
    public void setUp() {
        securityMgr = createMock(SecurityMgr.class);
        identityResolutionStrategy = createMock(IdentityResolutionStrategy.class);
        userResolutionStrategy = createMock(UserResolutionStrategy.class);
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setSecurityMgr(securityMgr);
        userDetailsService.setIdentityResolutionStrategy(identityResolutionStrategy);
        userDetailsService.setUserResolutionStrategy(userResolutionStrategy);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
        reset(identityResolutionStrategy);
        reset(userResolutionStrategy);
    }
    
}
