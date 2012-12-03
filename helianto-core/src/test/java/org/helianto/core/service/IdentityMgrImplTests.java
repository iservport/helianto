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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.DuplicateIdentityException;
import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.filter.IdentityFilterAdapter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.strategy.PrincipalGenerationStrategy;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserTestSupport;
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
        
        identityDao.saveOrUpdate(identity);
        // null below means no duplication in the db
        expect(identityDao.findUnique(identity.getPrincipal())).andReturn(null);
        replay(identityDao);
        
        assertSame(identity, identityMgr.storeIdentity(identity, true));
        verify(identityDao);
        verify(principalGenerationStrategy);
    }
    
	/**
	 * Existing identity is persisted.
	 */
	@Test
    public void storeIdentity() {
        Identity identity = new Identity();
        identity.setPrincipal("principal");
        
        identityDao.saveOrUpdate(identity);
        replay(identityDao);
        
        assertSame(identity, identityMgr.storeIdentity(identity));
        verify(identityDao);
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
        
        expect(identityDao.findUnique(identity.getPrincipal())).andReturn(checkForPreviousIdentity);
        replay(identityDao);
        
        assertSame(managedIdentity, identityMgr.storeIdentity(identity, true));
        verify(identityDao);
        verify(principalGenerationStrategy);
    }
    
	@Test
    public void findIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(identityDao.findUnique(principal))
            .andReturn(identity);
        replay(identityDao);
        
        assertSame(identity, identityMgr.findIdentityByPrincipal(principal));
        verify(identityDao);
    }

	@Test
    public void selectIdentities() {
        int size = 10;
        IdentityFilterAdapter filter = new IdentityFilterAdapter("");
        List<Identity> identityList = IdentityTestSupport.createIdentityList(size);
        List<Identity> exclusions = new ArrayList<Identity>();
        Identity excluded = identityList.get((int) (Math.random()*size));
        exclusions.add(excluded);

        expect(identityDao.find(filter)).andReturn(identityList);
        replay(identityDao);

        assertSame(identityList, identityMgr.findIdentities(filter, exclusions));
        verify(identityDao);
        
        assertFalse(identityList.contains(excluded));
    }
    
	@Test
    public void userState() {
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential(user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
    }
    
    private IdentityMgrImpl identityMgr;
    
    private FilterDao<Identity> identityDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;

    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        identityMgr = new IdentityMgrImpl();
        identityDao = createMock(FilterDao.class);
        identityMgr.setIdentityDao(identityDao);
        principalGenerationStrategy = createMock(PrincipalGenerationStrategy.class);
        identityMgr.setPrincipalGenerationStrategy(principalGenerationStrategy);
    }
    
    @After
    public void tearDown() {
        reset(identityDao);
        reset(principalGenerationStrategy);
    }
    
}
