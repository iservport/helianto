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
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.IdentityFilterAdapter;
import org.helianto.core.filter.TestingFilter;
import org.helianto.core.filter.UserFilterAdapter;
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
        Identity identity = new Identity();
        identity.setPrincipal("principal");
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        identityDao.saveOrUpdate(identity);
        // null below means no duplication in the db
        expect(identityDao.findUnique(identity.getPrincipal())).andReturn(null);
        replay(identityDao);
        
        assertSame(identity, userMgr.storeIdentity(identity, true));
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
        
        assertSame(identity, userMgr.storeIdentity(identity));
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
        
        assertSame(managedIdentity, userMgr.storeIdentity(identity, true));
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
        IdentityFilterAdapter filter = new IdentityFilterAdapter("");
        List<Identity> identityList = IdentityTestSupport.createIdentityList(size);
        List<Identity> exclusions = new ArrayList<Identity>();
        Identity excluded = identityList.get((int) (Math.random()*size));
        exclusions.add(excluded);

        expect(identityDao.find(filter)).andReturn(identityList);
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
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
    }
    
	@Test
    public void findUsers() {
    	UserFilterAdapter userFilter = new UserFilterAdapter(new User());
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
    	
    	userGroupDao.saveOrUpdate(userGroup);
    	userGroupDao.flush();
    	replay(userGroupDao);
    	
    	assertSame(userGroup, userMgr.storeUserGroup(userGroup));
    	verify(userGroupDao);
    }
	
	@Test
	public void findUserGroupParentRoot() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
		
    	userGroupDao.saveOrUpdate(userGroup);
    	userGroupDao.refresh(userGroup);
    	replay(userGroupDao);
    	
    	List<UserGroup> expectedUserGroupList = userMgr.findParentChain(userGroup);
    	verify(userGroupDao);
    	
    	assertSame(userGroup, expectedUserGroupList.get(0));
	}
    
	@Test
	public void findUserGroupParentLevel1() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
		User user = UserTestSupport.createUser();
		UserAssociation association = new UserAssociation(userGroup, user);
		user.getParentAssociations().add(association);
		
    	userGroupDao.saveOrUpdate(user);
    	userGroupDao.refresh(user);
    	replay(userGroupDao);
    	
    	List<UserGroup> expectedUserGroupList = userMgr.findParentChain(user);
    	verify(userGroupDao);
    	
    	assertSame(user, expectedUserGroupList.get(0));
    	assertSame(userGroup, expectedUserGroupList.get(1));
	}
    
	@Test
    public void validateEmptyCandidate() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setId(0);
		user.getIdentity().setPrincipal("");
		assertTrue(userMgr.validateIdentity(user)==null);
    }
    
	@Test
    public void storeUserAssociation() {
    	UserAssociation parentAssociation = new UserAssociation();
	
    	userAssociationDao.saveOrUpdate(parentAssociation);
    	replay(userAssociationDao);
    	
    	assertSame(parentAssociation, userMgr.storeUserAssociation(parentAssociation));
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
        
        userLogDao.saveOrUpdate(isA(UserLog.class));
        replay(userLogDao);
        
        userMgr.storeUserLog(user,date);
        verify(userLogDao);
    }

	@Test
	public void findUserRoles() {
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		Filter filter = new TestingFilter();
		
		expect(userRoleDao.find(filter)).andReturn(userRoleList);
		replay(userRoleDao);
		
		assertSame(userRoleList , userMgr.findUserRoles(filter));
		verify(userRoleDao);
	}
	
	@Test
	public void storeUserRole() {
		UserRole userRole = UserRoleTestSupport.createUserRole();
		
		userRoleDao.saveOrUpdate(userRole);
		userRoleDao.flush();
		replay(userRoleDao);
		
		assertSame(userRole , userMgr.storeUserRole(userRole));
		verify(userRoleDao);
	}
	
    // 
    
    private UserMgrImpl userMgr;
    
    private FilterDao<Identity> identityDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private FilterDao<UserGroup> userGroupDao;
    private FilterDao<UserAssociation> userAssociationDao;
    private FilterDao<Province> provinceDao;
    private FilterDao<UserLog> userLogDao;
    private FilterDao<UserRole> userRoleDao;

    
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
		userRoleDao = createMock(FilterDao.class);
		userMgr.setUserRoleDao(userRoleDao);
    }
    
    @After
    public void tearDown() {
        reset(identityDao);
        reset(principalGenerationStrategy);
        reset(userGroupDao);
        reset(userAssociationDao);
        reset(provinceDao);
        reset(userLogDao);
		reset(userRoleDao);
    }
    
}
