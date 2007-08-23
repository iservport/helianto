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
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserLogTestSupport;
import org.helianto.core.test.UserTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class SimpleUserResolverTests extends TestCase {

    // class under test
    private SimpleUserResolver simpleUserResolver;

    public void testLoadUsers() {
    	Identity identity = IdentityTestSupport.createIdentity();
    	List<User> users = new ArrayList<User>();
    	
    	expect(securityMgr.findUsers(simpleUserResolver.getUserFromIdentityMatchSelectionCriteria(identity)))
    	    .andReturn(users);
        replay(securityMgr);
    	
    	assertSame(users,simpleUserResolver.loadUsers(identity));
        verify(securityMgr);
    }
    
    public void testSelectUserFromPreviousLoginFailure() {
    	List<User> users = new ArrayList<User>();
    	UserLog userLog = null;
    	
    	expect(securityMgr.findLastUserLog(users))
    		.andReturn(userLog);
        replay(securityMgr);
    	
    	assertNull(simpleUserResolver.selectUserFromPreviousLogin(users));
        verify(securityMgr);
    }

    public void testSelectUserFromPreviousLoginSuccess() {
    	List<User> users = new ArrayList<User>();
    	User user = new User();
    	UserLog userLog = UserLogTestSupport.createUserLog(user);
    	
    	expect(securityMgr.findLastUserLog(users))
    		.andReturn(userLog);
        replay(securityMgr);
    	
    	assertSame(user, simpleUserResolver.selectUserFromPreviousLogin(users));
        verify(securityMgr);
    }
    
    public void testSelectUserIfAnyFailure() {
    	List<User> users = new ArrayList<User>();
    	
    	assertNull(simpleUserResolver.selectUserIfAny(users));
    }

    public void testSelectUserIfAnySuccess() {
    	List<User> users = UserTestSupport.createUserList(2);
    	
    	assertSame(users.get(0), simpleUserResolver.selectUserIfAny(users));
    }
    
    public void testCreateUserNoAuto() {
    	Identity identity = new Identity();
    	
    	expect(securityMgr.isAutoCreateEnabled()).andReturn(false);
    	replay(securityMgr);
    	
    	assertNull(simpleUserResolver.createUser(identity));
        verify(securityMgr);
    }

    public void testCreateUserAuto() {
    	Identity identity = IdentityTestSupport.createIdentity();
    	User user = new User();
    	
    	expect(securityMgr.isAutoCreateEnabled()).andReturn(true);
    	expect(securityMgr.autoCreateUser(identity))
    		.andReturn(user);
    	replay(securityMgr);
    	
    	assertSame(user, simpleUserResolver.createUser(identity));
    }

    // collaborators
    private SecurityMgr securityMgr;
    
    @Override
    public void setUp() {
        securityMgr = createMock(SecurityMgr.class);
        simpleUserResolver = new SimpleUserResolver();
        simpleUserResolver.setSecurityMgr(securityMgr);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
    }
    
}
