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

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.User;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.dao.InternalEnumeratorDao;
import org.helianto.core.filter.IdentityFilter;
import org.helianto.core.test.UserTestSupport;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;

public class UserMgrImplTests extends TestCase {
    
    private UserMgrImpl userMgr;
    
    public void testWriteIdentity() {
        Identity managedIdentity = null, identity = new Identity();
        
        expect(identityDao.mergeIdentity(identity)).andReturn(managedIdentity);
        replay(identityDao);
        
        userMgr.writeIdentity(identity);
        verify(identityDao);
    }
    
    public void testSelectIdentities() {
        int size = 10;
        IdentityFilter filter = new IdentityFilter();
        String criteria = "criteria";
        List<Identity> identityList = IdentityTestSupport.createIdentityList(size);
        List<Identity> exclusions = new ArrayList<Identity>();
        Identity excluded = identityList.get((int) (Math.random()*size));
        exclusions.add(excluded);

        expect(identitySelectionStrategy.createCriteriaAsString(filter, "identity"))
            .andReturn(criteria);
        replay(identitySelectionStrategy);
        
        expect(identityDao.findIdentities(criteria))        
            .andReturn(identityList);
        replay(identityDao);

        assertSame(identityList, userMgr.findIdentities(filter, exclusions));
        verify(identityDao);
        
        assertFalse(identityList.contains(excluded));
    }
    
    public void testWriteCredential() {
        Credential managedCredential = null, credential = new Credential();
        
        expect(credentialDao.mergeCredential(credential)).andReturn(managedCredential);
        replay(credentialDao);
        
        userMgr.writeCredential(credential);
        verify(credentialDao);
    }
    
    public void testUserState() {
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential(user.getIdentity());
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
        
        expect(internalEnumeratorDao.findInternalEnumeratorByNaturalId(entity, "TYPE_NAME"))
            .andReturn(enumerator);
        internalEnumeratorDao.persistInternalEnumerator(enumerator);
        replay(internalEnumeratorDao);
        
        assertEquals(10, userMgr.findNextInternalNumber(entity, "TYPE_NAME"));
        verify(internalEnumeratorDao);
        assertEquals(11, enumerator.getLastNumber());
    }
    
    public void testFindNextInternalNumberFirstCall() {
        InternalEnumerator enumerator = null;
        Entity entity = new Entity();
        
        expect(internalEnumeratorDao.findInternalEnumeratorByNaturalId(entity, "TYPE_NAME"))
            .andReturn(enumerator);
        internalEnumeratorDao.persistInternalEnumerator(isA(InternalEnumerator.class));
        replay(internalEnumeratorDao);
        
        assertEquals(1, userMgr.findNextInternalNumber(entity, "TYPE_NAME"));
        verify(internalEnumeratorDao);
    }
    
    private IdentityDao identityDao;
    private AuthorizationDao authorizationDao;
    private InternalEnumeratorDao internalEnumeratorDao;
    private CredentialDao credentialDao;
    private IdentitySelectionStrategy identitySelectionStrategy;
    
    @Override
    public void setUp() {
        userMgr = new UserMgrImpl();
        identityDao = createMock(IdentityDao.class);
        userMgr.setIdentityDao(identityDao);
        authorizationDao = createMock(AuthorizationDao.class);
        userMgr.setAuthorizationDao(authorizationDao);
        identitySelectionStrategy = createMock(IdentitySelectionStrategy.class);
        userMgr.setIdentitySelectionStrategy(identitySelectionStrategy);
        internalEnumeratorDao = createMock(InternalEnumeratorDao.class);
        userMgr.setInternalEnumeratorDao(internalEnumeratorDao);
        credentialDao = createMock(CredentialDao.class);
        userMgr.setCredentialDao(credentialDao);

    }
    
    @Override
    public void tearDown() {
        reset(identityDao);
        reset(authorizationDao);
        reset(internalEnumeratorDao);
        reset(credentialDao);
        reset(identitySelectionStrategy);
    }
    
}
