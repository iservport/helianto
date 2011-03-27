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
import org.helianto.core.Credential;
import org.helianto.core.DuplicateIdentityException;
import org.helianto.core.Identity;
import org.helianto.core.Province;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.filter.classic.IdentityFilter;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImplTests {
    
	/**
	 * Simplest case: new identity is persisted.
	 */
	@Test
    public void storeNewIdentity() {
        Identity managedIdentity = null, identity = new Identity();
        identity.setPrincipal("principal");
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        expect(identityDao.merge(identity)).andReturn(managedIdentity);
        // null below means no duplication in the db
        expect(identityDao.findUnique(identity.getPrincipal())).andReturn(null);
        replay(identityDao);
        
        assertSame(managedIdentity, userMgr.storeIdentity(identity));
        verify(identityDao);
        verify(principalGenerationStrategy);
    }
    
	/**
	 * Existing identity is persisted.
	 */
	@Test
    public void storeExistingIdentity() {
        Identity managedIdentity = null, identity = new Identity();
        identity.setPrincipal("principal");
        // id other than zero is our best guess to say the identity is not new
        identity.setId(1);
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        expect(identityDao.merge(identity)).andReturn(managedIdentity);
        replay(identityDao);
        
        assertSame(managedIdentity, userMgr.storeIdentity(identity));
        verify(identityDao);
        verify(principalGenerationStrategy);
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
        
//        expect(identityDao.merge(identity)).andReturn(managedIdentity);
        expect(identityDao.findUnique(identity.getPrincipal())).andReturn(checkForPreviousIdentity);
        replay(identityDao);
        
        assertSame(managedIdentity, userMgr.storeIdentity(identity));
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
        
        assertSame(identity, userMgr.findIdentityByPrincipal(principal));
        verify(identityDao);
    }

	@Test
    public void selectIdentities() {
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
    public void userState() {
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential(user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
    }
    
	@Test
    public void prepareNewUserAssociation() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
    	
    	UserAssociation userAssociation = userMgr.prepareNewUserAssociation(userGroup);
    	
    	assertSame(userGroup, userAssociation.getParent());
    }
    
	@Test
    public void findUsers() {
    	UserFilter userFilter = new UserFilter();
    	List<UserGroup> userList = new ArrayList<UserGroup>();
    	
    	expect(userGroupDao.find(userFilter)).andReturn(userList);
    	replay(userGroupDao);
    	
    	assertSame(userList, userMgr.findUsers(userFilter));
    	verify(userGroupDao);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void storeUserGroupNullKey() {
		UserGroup userGroup = new UserGroup();
		userGroup.setUserKey(null);
		
		userMgr.storeUserGroup(userGroup);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void storeUserGroupEmptyKey() {
		UserGroup userGroup = new UserGroup();
		userGroup.setUserKey("");
		
		userMgr.storeUserGroup(userGroup);
    }
    
	@Test
    public void storeUserGroup() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
    	UserGroup managedUserGroup = new UserGroup();
    	
    	expect(userGroupDao.merge(userGroup)).andReturn(managedUserGroup);
    	replay(userGroupDao);
    	
    	assertSame(managedUserGroup, userMgr.storeUserGroup(userGroup));
    	verify(userGroupDao);
    }
    
	@Test
    public void validateEmptyCandidate() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(0);
		user.getIdentity().setPrincipal("");
		assertTrue(userMgr.validateIdentity(user)==null);
    }
    
	@Test
    public void validateCandidateLoaded() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(0);
		user.getIdentity().setPrincipal("test");
		Identity identity = new Identity();
		
		expect(identityDao.findUnique("test")).andReturn(identity);
		replay(identityDao);
		
		assertTrue(userMgr.validateIdentity(user)!=null);
		assertSame(identity, user.getIdentity());
		verify(identityDao);
    }
    
	// TODO review test
//	@Test
//    public void validateCandidateCreated() {
//		User user = UserTestSupport.createUser();
//		user.getIdentity().setId(0);
//		user.getIdentity().setPrincipal("test");
//		user.setCreateIdentity(CreateIdentity.AUTO);
//		Identity identity = new Identity();
//		
//		expect(identityDao.findUnique("test")).andReturn(null);
//		expect(identityDao.merge(isA(Identity.class))).andReturn(identity);
//		replay(identityDao);
//		
//		assertTrue(userMgr.validateIdentity(user));
//		assertSame(identity, user.getIdentity());
//		verify(identityDao);
//    }
//    
	@Test(expected=IllegalArgumentException.class)
    public void storeUserAssociationNullKey() {
    	UserAssociation parentAssociation = new UserAssociation();
    	
    	userMgr.storeUserAssociation(parentAssociation);
    }
    
	@Test
    public void storeUserAssociation() {
    	UserAssociation parentAssociation = new UserAssociation();
    	parentAssociation.setChild(UserTestSupport.createUser());
    	UserAssociation managedUserAssociation = new UserAssociation();
		Identity identity = new Identity();
    	
		expect(identityDao.findUnique(parentAssociation.getChild().getUserKey())).andReturn(identity);
		replay(identityDao);
		
    	expect(userAssociationDao.merge(parentAssociation))
    	    .andReturn(managedUserAssociation);
    	replay(userAssociationDao);
    	
    	assertSame(managedUserAssociation, userMgr.storeUserAssociation(parentAssociation));
    	verify(userAssociationDao);
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void persistUserLogError() {
        // user must have an Identity
        userMgr.storeUserLog(new User(), new Date());
    }

	@Test
    public void storeUserLog() {
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

    /**
     * All roles come from the user.
     */
	@Test
    public void prepareUserGroupLocal() {
        UserGroup user =  new UserGroup();
        UserGroup managedUser =  UserGroupTestSupport.createUserGroup();
        UserRole[] roles = UserRoleTestSupport.createUserRoles(managedUser, "E1", "E2");
        
    	expect(userGroupDao.merge(user)).andReturn(managedUser);
    	userGroupDao.evict(managedUser);
    	replay(userGroupDao);
    	
    	List<UserRole> roleList = userMgr.prepareUserGroup(user).getRoleList();
        verify(userGroupDao);

        assertEquals(2, roleList.size());
        assertTrue(roleList.contains(roles[0]));
        assertTrue(roleList.contains(roles[1]));
    }
    
    /**
     * Some roles come from the ancestor.
     */
	@Test
    public void prepareUserGroupFromAncestor() {
        UserGroup user =  new UserGroup();
        UserGroup managedUser =  UserGroupTestSupport.createUserGroup();
        UserGroup parent = UserGroupTestSupport.createUserGroup();
        UserRole[] roles1 = UserRoleTestSupport.createUserRoles(managedUser, "E1", "E2");
        UserRole[] roles2 = UserRoleTestSupport.createUserRoles(parent, "E2", "E3");
        UserAssociation association = new UserAssociation(parent, managedUser);
        parent.getChildAssociations().add(association);
        managedUser.getParentAssociations().add(association);

    	expect(userGroupDao.merge(user)).andReturn(managedUser);
    	userGroupDao.evict(managedUser);
    	replay(userGroupDao);
    	
    	List<UserRole> roleList = userMgr.prepareUserGroup(user).getRoleList();
        verify(userGroupDao);

        assertEquals(4, roleList.size());
        assertTrue(roleList.contains(roles1[0]));
        assertTrue(roleList.contains(roles1[1]));
        assertTrue(roleList.contains(roles2[0]));
        assertTrue(roleList.contains(roles2[1]));
    }
    
    // 
    
    private UserMgrImpl userMgr;
    
    private FilterDao<Identity> identityDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private FilterDao<UserGroup> userGroupDao;
    private FilterDao<UserAssociation> userAssociationDao;
    private FilterDao<Province> provinceDao;
    private FilterDao<UserLog> userLogDao;

    
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
