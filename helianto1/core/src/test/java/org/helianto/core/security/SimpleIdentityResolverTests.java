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

import org.springframework.security.userdetails.UsernameNotFoundException;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.service.SecurityMgr;
import org.springframework.dao.DataRetrievalFailureException;

import junit.framework.TestCase;

public class SimpleIdentityResolverTests extends TestCase {

    // class under test
    private SimpleIdentityResolver simpleIdentityResolver;
    
    public void testLoadAndValidateIdentity() {
        Identity identity = IdentityTestSupport.createIdentity();

        expect(securityMgr.findIdentityByPrincipal("PRINCIPAL"))
            .andReturn(identity);
        replay(securityMgr);

        assertSame(identity, simpleIdentityResolver.loadAndValidateIdentity("PRINCIPAL"));
        verify(securityMgr);
    }
    
    public void testLoadAndValidateIdentityNull() {
        expect(securityMgr.findIdentityByPrincipal("PRINCIPAL"))
            .andReturn(null);
        replay(securityMgr);
        
        try {
            simpleIdentityResolver.loadAndValidateIdentity("PRINCIPAL"); fail();
        } catch (UsernameNotFoundException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }

    public void testLoadAndValidateIdentityError() {
        expect(securityMgr.findIdentityByPrincipal("PRINCIPAL"))
            .andThrow(new UsernameNotFoundException("Error"));
        replay(securityMgr);
        
        try {
            simpleIdentityResolver.loadAndValidateIdentity("PRINCIPAL"); fail();
        } catch (UsernameNotFoundException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }
    
    public void testLoadAndValidateCredential() {
        Identity identity = IdentityTestSupport.createIdentity();
        Credential credential = CredentialTestSupport.createCredential(identity);
        
        expect(securityMgr.findCredentialByIdentity(identity))
        	.andReturn(credential);
        replay(securityMgr);

        assertSame(credential, simpleIdentityResolver.loadAndValidateCredential(identity));
        verify(securityMgr);
    }
    
    public void testLoadAndValidateCredentialNull() {
        Identity identity = new Identity();
        
        expect(securityMgr.findCredentialByIdentity(identity))
        	.andReturn(null);
        replay(securityMgr);

        try {
        	simpleIdentityResolver.loadAndValidateCredential(identity); fail();
        } catch (DataRetrievalFailureException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }
    
    // collaborators
    private SecurityMgr securityMgr;

    @Override
    public void setUp() {
        securityMgr = createMock(SecurityMgr.class);
        simpleIdentityResolver = new SimpleIdentityResolver();
        simpleIdentityResolver.setSecurityMgr(securityMgr);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
    }
    
    // private members
    
}
