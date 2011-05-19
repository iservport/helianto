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
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.repository.FilterDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class SecurityMgrImplTests {

    // class under test
    private SecurityMgrImpl securityMgr;

    @Test
    public void findCredentialByIdentity() {
        Identity identity = new Identity();
        Credential credential = new Credential();
        
        expect(credentialDao.findUnique(identity))
            .andReturn(credential);
        replay(credentialDao);
        
        assertSame(credential, securityMgr.findCredentialByIdentity(identity));
        verify(credentialDao);
    }

    @Test
    public void findCredentialByPrincipal() {
        Credential credential = new Credential();
        Identity identity = new Identity();
        
        expect(identityDao.findUnique("PRINCIPAL"))
            .andReturn(identity);
        replay(identityDao);
        
        expect(credentialDao.findUnique(identity))
        	.andReturn(credential);
        replay(credentialDao);
    
        assertSame(credential, securityMgr.findCredentialByPrincipal("PRINCIPAL"));
        verify(identityDao);
        verify(credentialDao);
    }

//    @Test
//    public void testFindSecureUser() {
//        PublicUserDetails pud = SecurityTestSupport.createUserDetailsAdapter();
//        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(pud, null, null));
//        assertSame(pud, securityMgr.findSecureUser());
//    }
    
    @Test(expected=PasswordNotVerifiedException.class)
    public void storeCredentialNotVerified() {
        Credential credential = Credential.credentialFactory("");
        
        securityMgr.storeCredential(credential);
    }
    
    @Test
    public void storeCredentialVerified() {
        Credential credential = Credential.credentialFactory("PASSWORD");
        credential.setVerifyPassword("PASSWORD");
        
        credentialDao.saveOrUpdate(credential);
        replay(credentialDao);
        
        assertSame(credential, securityMgr.storeCredential(credential));
        verify(credentialDao);
    }
    
    //~ collaborators
    
	private FilterDao<Identity> identityDao;
	private FilterDao<Credential> credentialDao;
    
    //~ setup
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        securityMgr = new SecurityMgrImpl();
        identityDao = createMock(FilterDao.class);
        securityMgr.setIdentityDao(identityDao);
        credentialDao = createMock(FilterDao.class);
        securityMgr.setCredentialDao(credentialDao);
    }
    
    @After
    public void tearDown() {
        reset(identityDao);
        reset(credentialDao);
    }

}
