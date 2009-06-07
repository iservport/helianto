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
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.CreateIdentity;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserLogFilter;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImplTests {
    
	@Test
    public void testStoreIdentity() {
        Identity managedIdentity = null, identity = new Identity();
        identity.setPrincipal("principal");
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        expect(identityDao.merge(identity)).andReturn(managedIdentity);
        replay(identityDao);
        
        assertSame(managedIdentity, userMgr.storeIdentity(identity));
        verify(identityDao);
        verify(principalGenerationStrategy);
    }
    
	@Test
    public void testFindIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(identityDao.findUnique(principal))
            .andReturn(identity);
        replay(identityDao);
        
        assertSame(identity, userMgr.findIdentityByPrincipal(principal));
        verify(identityDao);
    }

	@Test
    public void testSelectIdentities() {
        int size = 10;
        IdentityFilter filter = new IdentityFilter();
        List<Identity> identityList = IdentityTestSupport.createIdentityList(size);
        List<Identity> exclusions = new ArrayList<Identity>();
        Identity excluded = identityList.get((int) (Math.random()*size));
        exclusions.add(excluded);

        expect(identityDao.find(filter))        
            .andReturn(identityList);
        replay(identityDao);

        assertSame(identityList, userMgr.findIdentities(filter, exclusions));
        verify(identityDao);
        
        assertFalse(identityList.contains(excluded));
    }
    
	@Test
    public void testUserState() {
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential(user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
    }
    
	@Test
    public void testCreateUserAssociationPrincipalExisting() {
    	UserGroup userGroup = new UserGroup();
    	Identity identity = new Identity();
    	
    	expect(identityDao.findUnique("principal")).andReturn(identity);
    	replay(identityDao);
    	
    	UserAssociation userAssociation = userMgr.createUserAssociation(userGroup, "principal");
    	verify(identityDao);
    	
    	assertSame(userGroup, userAssociation.getParent());
    	assertTrue(userAssociation.getChild() instanceof User);
    	assertSame(identity, ((User)userAssociation.getChild()).getIdentity());
    }
    
	@Test
    public void testCreateUserAssociationPrincipalNotFound() {
    	UserGroup userGroup = new UserGroup();
    	Credential managedCredential = CredentialTestSupport.createCredential();
    	managedCredential.getIdentity().setPrincipal("principal");
    	
    	expect(identityDao.findUnique("principal"))
			.andReturn(null);
    	replay(identityDao);
	
//    	expect(credentialDao.mergeCredential(isA(Credential.class)))
//    		.andReturn(managedCredential);
//    	replay(credentialDao);

    	UserAssociation userAssociation = userMgr.createUserAssociation(userGroup, "principal");
    	verify(identityDao);
    	
    	assertSame(userGroup, userAssociation.getParent());
    	assertEquals("principal", userAssociation.getChild().getUserKey());
    }
    
	@Test
    public void testFindUsers() {
    	UserFilter userFilter = new UserFilter();
    	List<UserGroup> userList = new ArrayList<UserGroup>();
    	
    	expect(userGroupDao.find(userFilter)).andReturn(userList);
    	replay(userGroupDao);
    	
    	assertSame(userList, userMgr.findUsers(userFilter));
    	verify(userGroupDao);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void testStoreUserGroupNullKey() {
		UserGroup userGroup = new UserGroup();
		userGroup.setUserKey(null);
		
		userMgr.storeUserGroup(userGroup);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void testStoreUserGroupEmptyKey() {
		UserGroup userGroup = new UserGroup();
		userGroup.setUserKey("");
		
		userMgr.storeUserGroup(userGroup);
    }
    
	@Test
    public void testStoreUserGroup() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
    	UserGroup managedUserGroup = new UserGroup();
    	
    	expect(userGroupDao.merge(userGroup)).andReturn(managedUserGroup);
    	replay(userGroupDao);
    	
    	assertSame(managedUserGroup, userMgr.storeUserGroup(userGroup));
    	verify(userGroupDao);
    }
    
	@Test
    public void testValidateEmptyCandidate() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(0);
		user.getIdentity().setPrincipal("");
		assertFalse(userMgr.validateIdentity(user));
    }
    
	@Test
    public void testValidateCandidateLoaded() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(0);
		user.setUserKey("test");
		Identity identity = new Identity();
		
		expect(identityDao.findUnique("test")).andReturn(identity);
		replay(identityDao);
		
		assertTrue(userMgr.validateIdentity(user));
		assertSame(identity, user.getIdentity());
		verify(identityDao);
    }
    
	@Test
    public void testValidateCandidateCreated() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(0);
		user.setUserKey("test");
		user.setCreateIdentity(CreateIdentity.AUTO);
		Identity identity = new Identity();
		
		expect(identityDao.findUnique("test")).andReturn(null);
		expect(identityDao.merge(isA(Identity.class))).andReturn(identity);
		replay(identityDao);
		
		assertTrue(userMgr.validateIdentity(user));
		assertSame(identity, user.getIdentity());
		verify(identityDao);
    }
    
	@Test
    public void testStoreUserAssociation() {
    	UserAssociation parentAssociation = new UserAssociation();
    	UserAssociation managedUserAssociation = new UserAssociation();
    	
    	expect(userAssociationDao.merge(parentAssociation))
    	    .andReturn(managedUserAssociation);
    	replay(userAssociationDao);
    	
    	assertSame(managedUserAssociation, userMgr.storeUserAssociation(parentAssociation));
    	verify(userAssociationDao);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void testPersistUserLogError() {
        // user must have an Identity
        userMgr.storeUserLog(new User(), new Date());
    }

	@Test
    public void testStoreUserLog() {
        Date date = new Date();
        Identity identity = new Identity();
        User user = new User();
        user.setIdentity(identity);
        UserLog managedUserLog = new UserLog();
        
        expect(userLogDao.merge(isA(UserLog.class))).andReturn(managedUserLog);
        replay(userLogDao);
        
        userMgr.storeUserLog(user,date);
        verify(userLogDao);
    }

    // 
    
    private UserMgrImpl userMgr;
    
    private FilterDao<Identity, IdentityFilter> identityDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private FilterDao<UserGroup, UserFilter> userGroupDao;
    private BasicDao<UserAssociation> userAssociationDao;
    private FilterDao<Province, ProvinceFilter> provinceDao;
    private FilterDao<UserLog, UserLogFilter> userLogDao;

    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        userMgr = new UserMgrImpl();
        identityDao = createMock(FilterDao.class);
        userMgr.setIdentityDao(identityDao);
        principalGenerationStrategy = createMock(PrincipalGenerationStrategy.class);
        userMgr.setPrincipalGenerationStrategy(principalGenerationStrategy);
        userGroupDao = createMock(FilterDao.class);
        userMgr.setUserGroupDao(userGroupDao);
        userAssociationDao = createMock(FilterDao.class);
        userMgr.setUserAssociationDao(userAssociationDao);
        provinceDao = createMock(FilterDao.class);
        userMgr.setProvinceDao(provinceDao);
        userLogDao = createMock(FilterDao.class);
        userMgr.setUserLogDao(userLogDao);
    }
    
    @After
    public void tearDown() {
        reset(identityDao);
        reset(principalGenerationStrategy);
        reset(userGroupDao);
        reset(userAssociationDao);
        reset(provinceDao);
        reset(userLogDao);
    }
    
}
