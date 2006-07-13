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
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.creation.UserCreator;
import org.helianto.core.hibernate.CredentialDaoImplTests;
import org.helianto.core.hibernate.EntityDaoImplTests;
import org.helianto.core.hibernate.UserDaoImplTests;
import org.helianto.core.service.SecurityMgr;
import org.springframework.dao.DataRetrievalFailureException;


public class UserDetailsServiceImplTests extends TestCase {
    
    // class under test
    private UserDetailsServiceImpl userDetailsService;
    // collaborators
    private SecurityMgr securityMgr;
    private IdentityResolutionStrategy identityResolutionStrategy;
    
    private Identity prepapreSuccessfullLoadAndValidate(String principal) {
        Identity identity = CredentialDaoImplTests.createAndPersistIdentity(null);

        expect(securityMgr.findIdentityByPrincipal(principal))
            .andReturn(identity);
        replay(securityMgr);
        return identity;
    }
    
    private Credential prepareSuccessfullLoadAndValidateCredential(Identity identity) {
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        credential.setIdentity(identity);
        
        expect(securityMgr.findCredentialByIdentity(credential.getIdentity()))
            .andReturn(credential);
        replay(securityMgr);
        return credential;
    }

    public void testLoadAndValidateCredential() {
        Credential credential = prepareSuccessfullLoadAndValidateCredential(new Identity());
        
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
    
    private User prepareSuccessfullLoadOrCreateUser(Identity identity) {
        UserLog userLog = UserDaoImplTests.createAndPersistUserLog(null, new Date());
        userLog.getUser().setIdentity(identity);
        
        expect(securityMgr.findLastUserLog(identity))
            .andReturn(userLog);
        securityMgr.persistUserLog(isA(User.class), isA(Date.class));
        replay(securityMgr);
        return userLog.getUser();
    }

    public void testLoadOrCreateUser() {
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        Identity identity = credential.getIdentity();
        User user = prepareSuccessfullLoadOrCreateUser(identity);;
        
        assertSame(user, userDetailsService.loadOrCreateUser(identity));
        verify(securityMgr);
    }
    
    private User prepareSuccessfullLoadOrCreateUserFirstLogin(Identity identity, int e) {
        List<Entity> entityList = EntityDaoImplTests.createEntityList(e);
        List<User> userList = new ArrayList<User>();
        for (Entity entity: entityList) {
            User u = UserCreator.userFactory(entity, identity);
            userList.add(u);
            identity.getUsers().add(u);
        }
        User user = userList.get((int) Math.random()*e);
        
        expect(securityMgr.findLastUserLog(identity))
            .andReturn(null);
        securityMgr.persistUserLog(isA(User.class), isA(Date.class));
        replay(securityMgr);
        return user;
    }

    public void testLoadOrCreateUserFirstLogin() {
        Identity identity = CredentialDaoImplTests.createAndPersistIdentity(null);
        User user = prepareSuccessfullLoadOrCreateUserFirstLogin(identity, 3);
        
        assertSame(user.getIdentity(), userDetailsService.loadOrCreateUser(identity).getIdentity());
        verify(securityMgr);
    }

    public void testLoadOrCreateUserNoUser() {
        Identity identity = CredentialDaoImplTests.createAndPersistIdentity(null);
        
        expect(securityMgr.findLastUserLog(identity))
            .andReturn(null);
        expect(securityMgr.isAutoCreateEnabled())
            .andReturn(false);
        replay(securityMgr);
        
        try {
            userDetailsService.loadOrCreateUser(identity); fail();
        } catch (UsernameNotFoundException e) {
        } catch (Exception e) { fail(); }
        verify(securityMgr);
    }

    public void testLoadUserByUsername() {
        //FIXME
//        Identity identity = CredentialDaoImplTests.createAndPersistIdentity(null);
//        Credential credential = prepareSuccessfullLoadAndValidateCredential(identity);
//        User user = prepareSuccessfullLoadOrCreateUserFirstLogin(identity, 1);
//        
//        UserDetails userDetails = userDetailsService.loadUserByUsername(identity.getPrincipal());
//        verify(securityMgr);
//        assertSame(user, ((PublicUserDetails) userDetails).getUser());
//        assertSame(credential, ((SecureUserDetails) userDetails).getCredential());
    }
    
    // setup
    
    @Override
    public void setUp() {
        securityMgr = createMock(SecurityMgr.class);
        identityResolutionStrategy = createMock(IdentityResolutionStrategy.class);
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setSecurityMgr(securityMgr);
        userDetailsService.setIdentityResolutionStrategy(identityResolutionStrategy);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
        reset(identityResolutionStrategy);
    }
    
    // private members
    
    private final Log logger = LogFactory.getLog(getClass());
    
    // inner stub class
    
}
