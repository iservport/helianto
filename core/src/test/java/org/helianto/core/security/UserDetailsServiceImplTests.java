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

package org.helianto.core.security;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.core.test.CredentialTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.userdetails.UsernameNotFoundException;


/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceImplTests {
    
    // class under test
    private UserDetailsServiceImpl userDetailsService;
    
    @Test
    public void testLoadAndValidateIdentity() {
    	String principal = "TEST";
    	Identity identity = new Identity();
    	
		expect(userMgr.findIdentityByPrincipal(principal))
		    .andReturn(identity);
		replay(userMgr);
    	
		assertSame(identity, userDetailsService.loadAndValidateIdentity(principal));
		verify(userMgr);
    }
    
    @Test(expected=UsernameNotFoundException.class)
    public void testLoadAndValidateIdentityNull() {
        expect(userMgr.findIdentityByPrincipal("PRINCIPAL"))
            .andReturn(null);
        replay(userMgr);
        
        userDetailsService.loadAndValidateIdentity("PRINCIPAL");
    }

    @Test(expected=UsernameNotFoundException.class)
    public void testLoadAndValidateIdentityError() {
        expect(userMgr.findIdentityByPrincipal("PRINCIPAL"))
            .andThrow(new UsernameNotFoundException("Error"));
        replay(userMgr);
        
        userDetailsService.loadAndValidateIdentity("PRINCIPAL");
    }
    
    @Test
    public void testLoadAndValidateCredential() {
        Credential credential = CredentialTestSupport.createCredential();
        
        expect(securityMgr.findCredentialByIdentity(credential.getIdentity()))
            .andReturn(credential);
        replay(securityMgr);
        
        assertSame(credential, userDetailsService.loadAndValidateCredential(credential.getIdentity()));
        verify(securityMgr);
    }

    @Test(expected=DataRetrievalFailureException.class)
    public void testLoadAndValidateCredentialNull() {
        Identity identity = new Identity();
        
        expect(securityMgr.findCredentialByIdentity(identity))
        	.andReturn(null);
        replay(securityMgr);

        userDetailsService.loadAndValidateCredential(identity);
    }
    
    public void testLoadAndValidateRoles() {
    	Set<UserRole> userRoles = new HashSet<UserRole>();
    	User user = new User();
        
        expect(securityMgr.prepareAllUserRoles(user))
            .andReturn(userRoles);
        replay(securityMgr);
        
        assertSame(userRoles, userDetailsService.loadAndValidateRoles(user));
        verify(securityMgr);
    }
    
    
    // collaborators
    private UserMgr userMgr;
    private SecurityMgr securityMgr;

    // setup
    
	@Before
    public void setUp() {
        userMgr = createMock(UserMgr.class);
        securityMgr = createMock(SecurityMgr.class);
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserMgr(userMgr);
        userDetailsService.setSecurityMgr(securityMgr);
    }
    
    @After
    public void tearDown() {
        reset(userMgr);
        reset(securityMgr);
    }
    
}
