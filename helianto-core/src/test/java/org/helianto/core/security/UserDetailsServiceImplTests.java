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
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceImplTests {
    
    // class under test
    private UserDetailsServiceImpl2 userDetailsService;
    
    @Test
    public void loadAndValidateIdentity() {
    	Credential credential = new Credential("USERNAME", "PASSWORD");
    	List<UserGroup> userList = new ArrayList<UserGroup>();
    	User user = new User();
    	userList.add(user);
    	Set<UserRole> roles = new HashSet<UserRole>();
    	
        expect(securityMgr.findCredentialByPrincipal("USERNAME")).andReturn(credential);
		expect(userMgr.findUsers(isA(UserFilter.class))).andReturn(userList);
		expect(userSelectorStrategy.selectUser(userList)).andReturn(user);
        expect(securityMgr.findRoles(user, true)).andReturn(roles);
        replay(securityMgr);
		replay(userMgr);
        replay(userSelectorStrategy);
    	
        UserDetailsAdapter userDetails = (UserDetailsAdapter) userDetailsService.loadUserByUsername("USERNAME");
		assertSame(user, userDetails.getUser());
		assertSame("PASSWORD", userDetails.getPassword());
		verify(securityMgr);
		verify(userMgr);
		verify(userSelectorStrategy);
    }
    
    // collabs
    
    private UserMgr userMgr;
    private SecurityMgr securityMgr;
    private UserSelectorStrategy userSelectorStrategy;

    // setup
    
	@Before
    public void setUp() {
        userMgr = createMock(UserMgr.class);
        securityMgr = createMock(SecurityMgr.class);
        userSelectorStrategy = createMock(UserSelectorStrategy.class);
        userDetailsService = new UserDetailsServiceImpl2();
        userDetailsService.setUserMgr(userMgr);
        userDetailsService.setSecurityMgr(securityMgr);
        userDetailsService.setUserSelectorStrategy(userSelectorStrategy);
    }
    
    @After
    public void tearDown() {
        reset(userMgr);
        reset(securityMgr);
        reset(userSelectorStrategy);
    }
    
}
