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

import junit.framework.TestCase;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.dao.AuthenticationDao;

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
    
    private AuthenticationDao authenticationDao;
    
    @Override
    public void setUp() {
        userMgr = new UserMgrImpl();
        authenticationDao = createMock(AuthenticationDao.class);
        userMgr.setAuthenticationDao(authenticationDao);
    }
    
    @Override
    public void tearDown() {
        reset(authenticationDao);
    }
    
}
