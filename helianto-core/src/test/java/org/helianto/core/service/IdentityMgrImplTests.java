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

import org.helianto.core.DuplicateIdentityException;
import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.repository.CredentialRepository;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.service.internal.PrincipalGenerationStrategy;
import org.helianto.user.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class IdentityMgrImplTests {
    
	/**
	 * Simplest case: new identity is persisted.
	 */
	@Test
    public void storeNewIdentity() {
        Identity identity = new Identity();
        identity.setPrincipal("principal");
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        expect(identityRepository.saveAndFlush(identity)).andReturn(identity);
        // null below means no duplication in the db
        expect(identityRepository.findByPrincipal(identity.getPrincipal())).andReturn(null);
        replay(identityRepository);
        
        assertSame(identity, identityMgr.storeIdentity(identity, true));
        verify(identityRepository);
        verify(principalGenerationStrategy);
    }
    
	/**
	 * Existing identity is persisted.
	 */
	@Test
    public void storeIdentity() {
        Identity identity = new Identity();
        identity.setPrincipal("principal");
        
        expect(identityRepository.saveAndFlush(identity)).andReturn(identity);
        replay(identityRepository);
        
        assertSame(identity, identityMgr.storeIdentity(identity));
        verify(identityRepository);
    }
    
	/**
	 * Duplicate identity, exception raised.
	 */
	@Test(expected=DuplicateIdentityException.class)
    public void duplicateIdentity() {
        Identity managedIdentity = null, identity = new Identity();
        identity.setPrincipal("principal");
        // not null identity below is supposed to be retrieved from db
        Identity checkForPreviousIdentity = new Identity();
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        expect(identityRepository.findByPrincipal(identity.getPrincipal())).andReturn(checkForPreviousIdentity);
        replay(identityRepository);
        
        assertSame(managedIdentity, identityMgr.storeIdentity(identity, true));
        verify(identityRepository);
        verify(principalGenerationStrategy);
    }
    
	@Test
    public void findIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(identityRepository.findByPrincipal(principal))
            .andReturn(identity);
        replay(identityRepository);
        
        assertSame(identity, identityMgr.findIdentityByPrincipal(principal));
        verify(identityRepository);
    }

	@Test
    public void userState() {
        User user = new User(new Entity(), new Identity());
        Credential credential = new Credential(user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(ActivityState.ACTIVE.getValue(), credential.getCredentialState());
    }
    
    private IdentityMgrImpl identityMgr;
    
    private IdentityRepository identityRepository;
    private CredentialRepository credentialRepository;
    private PrincipalGenerationStrategy principalGenerationStrategy;

    
	@Before
    public void setUp() {
        identityMgr = new IdentityMgrImpl();
        identityRepository = createMock(IdentityRepository.class);
        identityMgr.setIdentityRepository(identityRepository);
        principalGenerationStrategy = createMock(PrincipalGenerationStrategy.class);
        identityMgr.setPrincipalGenerationStrategy(principalGenerationStrategy);
        credentialRepository = createMock(CredentialRepository.class);
        identityMgr.setCredentialRepository(credentialRepository);
    }
    
    @After
    public void tearDown() {
        reset(credentialRepository);
        reset(identityRepository);
        reset(principalGenerationStrategy);
    }
    
}
