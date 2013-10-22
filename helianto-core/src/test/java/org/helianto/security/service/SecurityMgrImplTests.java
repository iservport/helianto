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

package org.helianto.security.service;

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
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.CredentialRepository;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.repository.IdentitySecurityRepository;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.service.SecurityMgrImpl;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        
        expect(credentialRepository.findByIdentity(identity)).andReturn(credential);
        replay(credentialRepository);
        
        assertSame(credential, securityMgr.findCredentialByIdentity(identity));
        verify(credentialRepository);
    }

    @Test
    public void findCredentialByPrincipal() {
        Credential credential = new Credential();
        Identity identity = new Identity();
        
        expect(identityRepository.findByPrincipal("PRINCIPAL")).andReturn(identity);
        replay(identityRepository);
        
        expect(credentialRepository.findByIdentity(identity)).andReturn(credential);
        replay(credentialRepository);
    
        assertSame(credential, securityMgr.findCredentialByPrincipal("PRINCIPAL"));
        verify(identityRepository);
        verify(credentialRepository);
    }

//    @Test
//    public void testFindSecureUser() {
//        PublicUserDetails pud = SecurityTestSupport.createUserDetailsAdapter();
//        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(pud, null, null));
//        assertSame(pud, securityMgr.findSecureUser());
//    }
    
    @Test
    public void storeIdentitySecurityNotRequired() {
        IdentitySecurity identitySecurity = new IdentitySecurity(new Identity(), "PASSWORD");
        
        securityMgr.storeIdentitySecurity(identitySecurity, false);
    }
    
    @Test(expected=PasswordNotVerifiedException.class)
    public void storeIdentitySecurityRequiredNotVerified() {
        IdentitySecurity identitySecurity = new IdentitySecurity();
        
        securityMgr.storeIdentitySecurity(identitySecurity, true);
    }
    
    @Test
    public void storeIdentitySecurityRequiredVerified() {
    	IdentitySecurity identitySecurity = new IdentitySecurity(new Identity(), "PASSWORD");
        identitySecurity.setVerifyPassword("PASSWORD");
        
        expect(identitySecurityRepository.saveAndFlush(identitySecurity)).andReturn(identitySecurity);
        replay(identitySecurityRepository);
        
        expect(passwordEncoder.encode(identitySecurity.getRawPassword())).andReturn("ENCODED");
        replay(passwordEncoder);
        
        assertSame(identitySecurity, securityMgr.storeIdentitySecurity(identitySecurity, true));
        verify(identitySecurityRepository);
    }
    
    @Test
    public void authentication() {
    	User user = new User();
    	user.setId(10);
    	Identity identity = new Identity();
    	identity.setId(100);
    	user.setIdentity(identity);
    	Set<UserRole> roles = new HashSet<UserRole>();
    	roles.add(new UserRole(user, new Service(), "TEST"));
    	
    	expect(userRepository.findOne(10)).andReturn(user);
        replay(userRepository);

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
    
	private UserRepository userRepository;
	private IdentityRepository identityRepository;
    private CredentialRepository credentialRepository;
	private IdentitySecurityRepository identitySecurityRepository;
	private PasswordEncoder passwordEncoder;
    
    //~ setup
    
	@Before
    public void setUp() {
        securityMgr = new SecurityMgrImpl();
        userRepository = createMock(UserRepository.class);
        identityRepository = createMock(IdentityRepository.class);
        securityMgr.setUserRepository(userRepository);
        securityMgr.setIdentityRepository(identityRepository);
        credentialRepository = createMock(CredentialRepository.class);
        securityMgr.setCredentialRepository(credentialRepository);
        identitySecurityRepository = createMock(IdentitySecurityRepository.class);
        securityMgr.setIdentitySecurityRepository(identitySecurityRepository);
        passwordEncoder = createMock(PasswordEncoder.class);
        securityMgr.setPasswordEncoder(passwordEncoder);
    }
    
    @After
    public void tearDown() {
        reset(userRepository, identityRepository, credentialRepository
        		, identitySecurityRepository, passwordEncoder);
    }

}
