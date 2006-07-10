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
import org.helianto.core.dao.UserDao;

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

public class SecurityMgrImplTests extends TestCase {

    // class under test
    private SecurityMgrImpl securityMgr;

    public void testFindIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(userDao.findIdentityByPrincipal(principal))
            .andReturn(identity);
        replay(userDao);
        
        assertSame(identity, securityMgr.findIdentityByPrincipal(principal));
        verify(userDao);
    }

    public void testFindCredentialByIdentity() {
        Identity identity = new Identity();
        Credential credential = new Credential();
        
        expect(userDao.findCredentialByIdentity(identity))
            .andReturn(credential);
        replay(userDao);
        
        assertSame(credential, securityMgr.findCredentialByIdentity(identity));
        verify(userDao);
    }

    public void testFindLastUserLog() {
        Identity identity = new Identity();
        UserLog userLog = new UserLog();
        
        expect(userDao.findLastUserLog(identity))
            .andReturn(userLog);
        replay(userDao);
        
        assertSame(userLog, securityMgr.findLastUserLog(identity));
        verify(userDao);
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
        
        userDao.persistUserLog(isA(UserLog.class));
        userDao.persistIdentity(user.getIdentity());
        replay(userDao);
        
        securityMgr.persistUserLog(user,date);
        verify(userDao);
        assertSame(date, identity.getLastLogin());
    }

    public void testPersistUserLogNullDate() {
        Identity identity = new Identity();
        User user = new User();
        user.setIdentity(identity);
        
        userDao.persistUserLog(isA(UserLog.class));
        userDao.persistIdentity(user.getIdentity());
        replay(userDao);
        
        Date date = new Date();
        securityMgr.persistUserLog(user,null);
        verify(userDao);
        assertTrue(Math.abs(date.getTime() - identity.getLastLogin().getTime()) < 1000);
    }
    
    //~ pending

    public void testIsAutoCreateEnabled() {
        assertFalse(securityMgr.isAutoCreateEnabled());
    }
    
    public void testAutoCreateUser(Identity identity) {
        assertNull(securityMgr.autoCreateUser(new Identity()));
    }
    
    //~ collaborators
    
    private UserDao userDao;
    
    //~ setup
    
    @Override
    public void setUp() {
        securityMgr = new SecurityMgrImpl();
        userDao = createMock(UserDao.class);
        securityMgr.setUserDao(userDao);
    }
    
    @Override
    public void tearDown() {
        reset(userDao);
    }

}
