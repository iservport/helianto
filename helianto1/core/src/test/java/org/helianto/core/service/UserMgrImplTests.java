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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.User;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;
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
        int size = 10;
        IdentityFilter filter = new IdentityFilter();
        String criteria = "criteria";
        List<Identity> identityList = AuthenticationTestSupport.createIdentityList(size);
        List<Identity> exclusions = new ArrayList<Identity>();
        Identity excluded = identityList.get((int) (Math.random()*size));
        exclusions.add(excluded);

        expect(identitySelectionStrategy.createCriteriaAsString(filter))
            .andReturn(criteria);
        replay(identitySelectionStrategy);
        
        expect(authenticationDao.findIdentityByCriteria(filter))        
            .andReturn(identityList);
        replay(authenticationDao);

        assertSame(identityList, userMgr.findIdentities(filter, exclusions));
        verify(authenticationDao);
        
        assertFalse(identityList.contains(excluded));
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
    
    public void testFindNextInternalNumber() {
        InternalEnumerator enumerator = new InternalEnumerator();
        enumerator.setLastNumber(10);
        Entity entity = new Entity();
        
        expect(authorizationDao.findInternalEnumerator(entity, "TYPE_NAME"))
            .andReturn(enumerator);
        authorizationDao.persistInternalEnumerator(enumerator);
        replay(authorizationDao);
        
        assertEquals(10, userMgr.findNextInternalNumber(entity, "TYPE_NAME"));
        verify(authorizationDao);
        assertEquals(11, enumerator.getLastNumber());
    }
    
    public void testFindNextInternalNumberFirstCall() {
        InternalEnumerator enumerator = null;
        Entity entity = new Entity();
        
        expect(authorizationDao.findInternalEnumerator(entity, "TYPE_NAME"))
            .andReturn(enumerator);
        authorizationDao.persistInternalEnumerator(isA(InternalEnumerator.class));
        replay(authorizationDao);
        
        assertEquals(1, userMgr.findNextInternalNumber(entity, "TYPE_NAME"));
        verify(authorizationDao);
    }
    
    private AuthenticationDao authenticationDao;
    private AuthorizationDao authorizationDao;
    private IdentitySelectionStrategy identitySelectionStrategy;
    
    @Override
    public void setUp() {
        userMgr = new UserMgrImpl();
        authenticationDao = createMock(AuthenticationDao.class);
        authorizationDao = createMock(AuthorizationDao.class);
        identitySelectionStrategy = createMock(IdentitySelectionStrategy.class);
        userMgr.setAuthenticationDao(authenticationDao);
        userMgr.setAuthorizationDao(authorizationDao);
    }
    
    @Override
    public void tearDown() {
        reset(authenticationDao);
        reset(authorizationDao);
        reset(identitySelectionStrategy);
    }
    
}
