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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

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
        Credential credential = new Credential();
        
        securityMgr.storeCredential(credential);
    }
    
    @Test
    public void storeCredentialVerified() {
        Credential credential = new Credential("PRINCIPAL", "PASSWORD");
        credential.setVerifyPassword("PASSWORD");
        
        credentialDao.saveOrUpdate(credential);
        replay(credentialDao);
        
        assertSame(credential, securityMgr.storeCredential(credential));
        verify(credentialDao);
    }
    
    @Test
    public void authentication() {
    	User user = new User();
    	Identity identity = new Identity();
    	identity.setId(100);
    	user.setIdentity(identity);
    	Set<UserRole> roles = new HashSet<UserRole>();
    	roles.add(new UserRole(user, new Service(), "TEST"));
    	securityMgr.authenticate(user, roles);
    	PublicUserDetails authenticatedUserDetails = securityMgr.findAuthenticatedUser();
    	assertSame(user, authenticatedUserDetails.getUser());
//    	for (GrantedAuthority auth: ((UserDetailsAdapter) authenticatedUserDetails).getAuthorities()) {
//		System.out.println(auth);
//	}
    	List<String> authorityList = new ArrayList<String>();
    	for (GrantedAuthority auth: ((UserDetailsAdapter) authenticatedUserDetails).getAuthorities()) {
    		authorityList.add(auth.getAuthority());
    	}
    	assertEquals(3, authorityList.size());
    	assertTrue(authorityList.contains("ROLE_USER"));
    	assertTrue(authorityList.contains("ROLE_USER_TEST"));
    	assertTrue(authorityList.contains("ROLE_SELF_ID_100"));
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
