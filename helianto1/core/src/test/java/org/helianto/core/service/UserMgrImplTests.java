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

package org.helianto.core.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.IdentityFilter;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.AuthorizationTestSupport;
import org.helianto.core.type.ActivityState;

public class UserMgrImplTests extends TestCase {
    
    private UserMgrImpl userMgr;
    
    public void testCreateEmptyIdentity() {
        Identity identity = userMgr.createEmptyIdentity();
        assertEquals("", identity.getPrincipal());
        assertEquals("", identity.getOptionalAlias());
    }
    
    public void testPersistIdentity() {
        Identity identity = new Identity();
        
        authenticationDao.persistIdentity(identity);
        replay(authenticationDao);
        
        userMgr.persistIdentity(identity);
        verify(authenticationDao);
    }
    
    public void testSelectIdentities() {
        IdentityFilter filter = new IdentityFilter();
        String criteria = "criteria";
        List<Identity> identityList = new ArrayList<Identity>();;

        expect(identitySelectionStrategy.createCriteriaAsString(filter))
            .andReturn(criteria);
        replay(identitySelectionStrategy);
        
        expect(authenticationDao.findIdentityByCriteria(criteria))
            .andReturn(identityList);
        replay(authenticationDao);

        assertSame(identityList, userMgr.selectIdentities(filter));
        verify(authenticationDao);
    }
    
    public void testCreateCredential() {
        Identity identity = new Identity();
        Credential credential = userMgr.createCredential(identity);
        assertSame(identity, credential.getIdentity());
        assertEquals("empty", credential.getPassword());
    }

    public void testCreateCredentialAndIdentity() {
        Credential credential = userMgr.createCredentialAndIdentity();
        assertEquals("", credential.getIdentity().getPrincipal());
        assertEquals("", credential.getIdentity().getOptionalAlias());
        assertEquals("empty", credential.getPassword());
    }

    public void testPersistCredential() {
        Credential credential = new Credential();
        
        authenticationDao.persistCredential(credential);
        replay(authenticationDao);
        
        userMgr.persistCredential(credential);
        verify(authenticationDao);
    }
    
    public void testUserState() {
        User user = AuthorizationTestSupport.createUser();
        Credential credential = AuthenticationTestSupport.createCredential(user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
        
        user.setUserState(ActivityState.INITIAL.getValue());
        userMgr.activateUser(user, credential);
        assertEquals(ActivityState.INITIAL.getValue(), user.getUserState());
        
        credential.setCredentialState(ActivityState.ACTIVE.getValue());
        userMgr.activateUser(user, credential);
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        
        userMgr.suspendUser(user);
        assertEquals(ActivityState.SUSPENDED.getValue(), user.getUserState());
        
        userMgr.cancelUser(user);
        assertEquals(ActivityState.CANCELLED.getValue(), user.getUserState());
    }
    
    private AuthenticationDao authenticationDao;
    private IdentitySelectionStrategy identitySelectionStrategy;
    
    @Override
    public void setUp() {
        userMgr = new UserMgrImpl();
        authenticationDao = createMock(AuthenticationDao.class);
        identitySelectionStrategy = createMock(IdentitySelectionStrategy.class);
        userMgr.setAuthenticationDao(authenticationDao);
        userMgr.setIdentitySelectionStrategy(identitySelectionStrategy);
    }
    
    @Override
    public void tearDown() {
        reset(authenticationDao);
        reset(identitySelectionStrategy);
    }
    
}
