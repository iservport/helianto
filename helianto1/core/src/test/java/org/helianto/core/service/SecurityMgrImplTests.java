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

import java.util.Date;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

public class SecurityMgrImplTests extends TestCase {

    // class under test
    private SecurityMgrImpl securityMgr;

    public void testFindIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(authenticationDao.findIdentityByPrincipal(principal))
            .andReturn(identity);
        replay(authenticationDao);
        
        assertSame(identity, securityMgr.findIdentityByPrincipal(principal));
        verify(authenticationDao);
    }

    public void testFindCredentialByIdentity() {
        Identity identity = new Identity();
        Credential credential = new Credential();
        
        expect(authenticationDao.findCredentialByIdentity(identity))
            .andReturn(credential);
        replay(authenticationDao);
        
        assertSame(credential, securityMgr.findCredentialByIdentity(identity));
        verify(authenticationDao);
    }

    public void testFindLastUserLog() {
        Identity identity = new Identity();
        UserLog userLog = new UserLog();
        
        expect(authorizationDao.findLastUserLog(identity))
            .andReturn(userLog);
        replay(authorizationDao);
        
        assertSame(userLog, securityMgr.findLastUserLog(identity));
        verify(authorizationDao);
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
        
        authorizationDao.persistUserLog(isA(UserLog.class));
        replay(authorizationDao);
        authenticationDao.persistIdentity(user.getIdentity());
        replay(authenticationDao);
        
        securityMgr.persistUserLog(user,date);
        verify(authorizationDao);
        verify(authenticationDao);
        assertSame(date, identity.getLastLogin());
    }

    public void testPersistUserLogNullDate() {
        Identity identity = new Identity();
        User user = new User();
        user.setIdentity(identity);
        
        authorizationDao.persistUserLog(isA(UserLog.class));
        replay(authorizationDao);
        authenticationDao.persistIdentity(user.getIdentity());
        replay(authenticationDao);
        
        Date date = new Date();
        securityMgr.persistUserLog(user,null);
        verify(authorizationDao);
        verify(authenticationDao);
        assertTrue(Math.abs(date.getTime() - identity.getLastLogin().getTime()) < 1000);
    }
    
    /*
    public boolean verifyPassword(Credential credential) {
        if (credential.getPassword().compareTo(credential.getVerifyPassword())!=0) {
            credential.setPassword("");
            credential.setVerifyPassword("");
            credential.setPasswordDirty(true);
            return false;
        }
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        return true;
    }
    
     */
    
    public void testVerifyPasswordSuccess() {
        Credential credential = new Credential();
        String password = String.valueOf(new Date().getTime());
        credential.setPassword(password);
        credential.setVerifyPassword(password);
        
        assertTrue(securityMgr.verifyPassword(credential));
        assertEquals(password, credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
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
        assertTrue(credential.isPasswordDirty());
    }
    
    //~ pending

    public void testIsAutoCreateEnabled() {
        assertFalse(securityMgr.isAutoCreateEnabled());
    }
    
    public void testAutoCreateUser(Identity identity) {
        assertNull(securityMgr.autoCreateUser(new Identity()));
    }
    
    //~ collaborators
    
    private AuthenticationDao authenticationDao;
    
    private AuthorizationDao authorizationDao;
    
    //~ setup
    
    @Override
    public void setUp() {
        securityMgr = new SecurityMgrImpl();
        authenticationDao = createMock(AuthenticationDao.class);
        securityMgr.setAuthenticationDao(authenticationDao);
        authorizationDao = createMock(AuthorizationDao.class);
        securityMgr.setAuthorizationDao(authorizationDao);
    }
    
    @Override
    public void tearDown() {
        reset(authenticationDao);
        reset(authorizationDao);
    }

}
