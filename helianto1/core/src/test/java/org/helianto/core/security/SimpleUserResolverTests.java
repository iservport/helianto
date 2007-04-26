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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserLogTestSupport;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.service.SecurityMgr;

import junit.framework.TestCase;

/**
 * @author Mauricio Fernandes de Castro
 */
public class SimpleUserResolverTests extends TestCase {

    // class under test
    private SimpleUserResolver simpleUserResolver;
    // collaborators
    private SecurityMgr securityMgr;
    
    private User prepareSuccessfullLoadOrCreateUser(Identity identity) {
        UserLog userLog = UserLogTestSupport.createUserLog();
        userLog.getUser().setIdentity(identity);
        
        expect(securityMgr.findLastUserLog(identity))
            .andReturn(userLog);
        securityMgr.writeUserLog(isA(User.class), isA(Date.class));
        replay(securityMgr);
        return userLog.getUser();
    }

    public void testLoadOrCreateUser() {
        Credential credential = CredentialTestSupport.createCredential();
        Identity identity = credential.getIdentity();
        User user = prepareSuccessfullLoadOrCreateUser(identity);
        
        assertSame(user, simpleUserResolver.loadOrCreateUser(identity));
        verify(securityMgr);
    }
    
    private User prepareSuccessfullLoadOrCreateUserFirstLogin(Identity identity, int e) {
        List<Entity> entityList = EntityTestSupport.createEntityList(e);
        List<User> userList = new ArrayList<User>();
        for (Entity entity: entityList) {
            User u = AuthorizationCreator.userFactory(entity, identity);
            userList.add(u);
            identity.getUsers().add(u);
        }
        User user = userList.get((int) Math.random()*e);
        
        expect(securityMgr.findLastUserLog(identity))
            .andReturn(null);
        securityMgr.writeUserLog(isA(User.class), isA(Date.class));
        replay(securityMgr);
        return user;
    }

    public void testLoadOrCreateUserFirstLogin() {
        Identity identity = IdentityTestSupport.createIdentity();
        User user = prepareSuccessfullLoadOrCreateUserFirstLogin(identity, 3);
        
        assertSame(user.getIdentity(), simpleUserResolver.loadOrCreateUser(identity).getIdentity());
        verify(securityMgr);
    }

    public void testLoadOrCreateUserNoUser() {
        Identity identity = IdentityTestSupport.createIdentity();
        
        expect(securityMgr.findLastUserLog(identity))
            .andReturn(null);
        expect(securityMgr.isAutoCreateEnabled())
            .andReturn(false);
        replay(securityMgr);
        
        try {
        	simpleUserResolver.loadOrCreateUser(identity); fail();
        } catch (UsernameNotFoundException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }

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