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
import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.test.UserTestSupport;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.springframework.dao.DataRetrievalFailureException;


/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceImplTests extends TestCase {
    
    // class under test
    private UserDetailsServiceImpl userDetailsService;
    
    public void testIdentityResolutionStrategy() {
    	String principal = "TEST";
    	Identity identity = IdentityTestSupport.createIdentity();
    	
		expect(identityResolutionStrategy.loadAndValidateIdentity(principal))
		    .andReturn(identity);
		replay(identityResolutionStrategy);
    	
		assertSame(identity, userDetailsService.loadAndValidateIdentity(principal));
		verify(identityResolutionStrategy);
    }
    
    public void testUserResolutionStrategy() {
    	Identity identity = IdentityTestSupport.createIdentity();
    	User user = UserTestSupport.createUser();
    	
		expect(userResolutionStrategy.loadOrCreateUser(identity))
		    .andReturn(user);
		replay(userResolutionStrategy);
    	
		assertSame(user, userDetailsService.loadOrCreateUser(identity));
		verify(userResolutionStrategy);
    }
    
    public void testLoadAndValidateCredential() {
        Credential credential = CredentialTestSupport.createCredential();
        credential.setIdentity(new Identity());
        
        expect(securityMgr.findCredentialByIdentity(credential.getIdentity()))
            .andReturn(credential);
        replay(securityMgr);
        
        assertSame(credential,userDetailsService.loadAndValidateCredential(credential.getIdentity()));
        verify(securityMgr);
    }

    public void testLoadAndValidateCredentialNull() {
        expect(securityMgr.findCredentialByIdentity(new Identity()))
            .andReturn(null);
        replay(securityMgr);
        
        try {
            userDetailsService.loadAndValidateCredential(new Identity()); fail();
        } catch (DataRetrievalFailureException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }

    
    public void testLoadAndValidateCredentialError() {
        expect(securityMgr.findCredentialByIdentity(new Identity()))
            .andThrow(new DataRetrievalFailureException(""));
        replay(securityMgr);
        
        try {
            userDetailsService.loadAndValidateCredential(new Identity()); fail();
        } catch (DataRetrievalFailureException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }
    
    // collaborators
    private SecurityMgr securityMgr;
    private IdentityResolutionStrategy identityResolutionStrategy;
    private UserResolutionStrategy userResolutionStrategy;
    // setup
    
    @Override
    public void setUp() {
        securityMgr = createMock(SecurityMgr.class);
        identityResolutionStrategy = createMock(IdentityResolutionStrategy.class);
        userResolutionStrategy = createMock(UserResolutionStrategy.class);
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setSecurityMgr(securityMgr);
        userDetailsService.setIdentityResolutionStrategy(identityResolutionStrategy);
        userDetailsService.setUserResolutionStrategy(userResolutionStrategy);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
        reset(identityResolutionStrategy);
        reset(userResolutionStrategy);
    }
    
}
