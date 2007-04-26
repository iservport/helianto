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

import java.util.Date;

import junit.framework.TestCase;

import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.dao.UserLogDao;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.test.SecurityTestSupport;

public class SecurityMgrImplTests extends TestCase {

    // class under test
    private SecurityMgrImpl securityMgr;

    public void testFindIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(identityDao.findIdentityByNaturalId(principal))
            .andReturn(identity);
        replay(identityDao);
        
        assertSame(identity, securityMgr.findIdentityByPrincipal(principal));
        verify(identityDao);
    }

    public void testFindCredentialByIdentity() {
        Identity identity = new Identity();
        Credential credential = new Credential();
        
        expect(credentialDao.findCredentialByNaturalId(identity))
            .andReturn(credential);
        replay(credentialDao);
        
        assertSame(credential, securityMgr.findCredentialByIdentity(identity));
        verify(credentialDao);
    }

    public void testFindLastUserLog() {
        Identity identity = new Identity();
        UserLog userLog = new UserLog();
        
        expect(userLogDao.findLastUserLog(identity))
            .andReturn(userLog);
        replay(userLogDao);
        
        assertSame(userLog, securityMgr.findLastUserLog(identity));
        verify(userLogDao);
    }

    public void testPersistUserLogError() {
        // user must have an Identity
        try {
            securityMgr.persistUserLog(new User(), new Date());
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
    }

    public void testPersistUserLog() {
        Date date = new Date();
        Identity identity = new Identity();
        User user = new User();
        user.setIdentity(identity);
        
        userLogDao.persistUserLog(isA(UserLog.class));
        replay(userLogDao);
        identityDao.persistIdentity(user.getIdentity());
        replay(identityDao);
        
        securityMgr.persistUserLog(user,date);
        verify(userLogDao);
        verify(identityDao);
        assertSame(date, identity.getLastLogin());
    }

    public void testPersistUserLogNullDate() {
        Identity identity = new Identity();
        User user = new User();
        user.setIdentity(identity);
        
        userLogDao.persistUserLog(isA(UserLog.class));
        replay(userLogDao);
        identityDao.persistIdentity(user.getIdentity());
        replay(identityDao);
        
        Date date = new Date();
        securityMgr.persistUserLog(user,null);
        verify(userLogDao);
        verify(identityDao);
        assertTrue(Math.abs(date.getTime() - identity.getLastLogin().getTime()) < 1000);
    }
    
    public void testVerifyPasswordSuccess() {
        Credential credential = new Credential();
        String password = String.valueOf(new Date().getTime());
        credential.setPassword(password);
        credential.setVerifyPassword(password);
        
        assertTrue(securityMgr.verifyPassword(credential));
        assertEquals(password, credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertEquals(ActivityState.ACTIVE.getValue(), credential.getCredentialState());
        assertFalse(credential.isPasswordDirty());
    }
    
    public void testVerifyPasswordError() {
        Credential credential = new Credential();
        String password = String.valueOf(new Date().getTime());
        credential.setPassword(password);
        credential.setVerifyPassword(password+"1");
        
        assertFalse(securityMgr.verifyPassword(credential));
        assertEquals("", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
        assertTrue(credential.isPasswordDirty());
    }
    
    public void testFindSecureUser() {
        PublicUserDetails pud = SecurityTestSupport.createUserDetailsAdapter();
        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(pud, null, null));
        assertSame(pud, securityMgr.findSecureUser());
    }
    
    //~ pending

    public void testIsAutoCreateEnabled() {
        assertFalse(securityMgr.isAutoCreateEnabled());
    }
    
    public void testAutoCreateUser(Identity identity) {
        assertNull(securityMgr.autoCreateUser(new Identity()));
    }
    
    //~ collaborators
    
    private IdentityDao identityDao;
    private AuthorizationDao authorizationDao;
    private CredentialDao credentialDao;
    private UserLogDao userLogDao;
    
    //~ setup
    
    @Override
    public void setUp() {
        securityMgr = new SecurityMgrImpl();
        identityDao = createMock(IdentityDao.class);
        securityMgr.setIdentityDao(identityDao);
        authorizationDao = createMock(AuthorizationDao.class);
        securityMgr.setAuthorizationDao(authorizationDao);
        credentialDao = createMock(CredentialDao.class);
        securityMgr.setCredentialDao(credentialDao);
        userLogDao = createMock(UserLogDao.class);
        securityMgr.setUserLogDao(userLogDao);
    }
    
    @Override
    public void tearDown() {
        reset(identityDao);
        reset(authorizationDao);
        reset(credentialDao);
        reset(userLogDao);
    }

}
