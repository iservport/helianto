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
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.IdentityMgr;
import org.helianto.core.PublicEntityMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.classic.TestingFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserLog;
import org.helianto.user.domain.UserRole;
import org.helianto.user.filter.UserFormFilterAdapter;
import org.helianto.user.form.UserGroupForm;
import org.helianto.user.repository.UserGroupRepository;
import org.helianto.user.repository.UserRepository;
import org.helianto.user.service.UserMgrImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserMgrImplTests {
    
	@Test
    public void findUsers() {
    	List<UserGroup> userList = new ArrayList<UserGroup>();
    	UserGroupForm form = EasyMock.createMock(UserGroupForm.class);
    	
    	expect(userGroupRepository.find(EasyMock.isA(UserFormFilterAdapter.class))).andReturn(userList);
    	replay(userGroupRepository);
    	
    	assertSame(userList, userMgr.findUsers(form));
    	verify(userGroupRepository);
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
    	
    	expect(userGroupRepository.saveAndFlush(userGroup)).andReturn(userGroup);
    	replay(userGroupRepository);
    	
    	assertSame(userGroup, userMgr.storeUserGroup(userGroup));
    	verify(userGroupRepository);
    }
	
	@Test
    public void publicEntity() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
    	userGroup.getEntity().setNature("S, E");
    	PublicEntity publicEntity = new PublicEntity(userGroup.getEntity());
    	
    	expect(userGroupRepository.saveAndFlush(userGroup)).andReturn(userGroup);
    	replay(userGroupRepository);
    	    	
		EasyMock.expect(publicEntityMgr.installPublicEntity(userGroup.getEntity())).andReturn(publicEntity);
//    	EasyMock.expect(publicEntityMgr.storePublicEntity(EasyMock.eq(publicEntity))).andReturn(publicEntity);
    	replay(publicEntityMgr);
    	
    	assertSame(userGroup, userMgr.storeUserGroup(userGroup));
    	verify(userGroupRepository);
    	verify(publicEntityMgr);
    }
	
	@Test
	public void findUserGroupParentRoot() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
		
    	userGroupRepository.refresh(userGroup);
    	replay(userGroupRepository);
    	
    	List<UserGroup> expectedUserGroupList = userMgr.findParentChain(userGroup);
    	verify(userGroupRepository);
    	
    	assertEquals(0, expectedUserGroupList.size());
	}
    
	@Test
	public void findUserGroupParentLevel1() {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
		User user = new User(new Entity(), new Identity("p"));
		UserAssociation association = new UserAssociation(userGroup, user);
		user.getParentAssociations().add(association);
		
    	userGroupRepository.refresh(user);
    	replay(userGroupRepository);
    	
    	List<UserGroup> expectedUserGroupList = userMgr.findParentChain(user);
    	verify(userGroupRepository);
    	
    	assertSame(userGroup, expectedUserGroupList.get(0));
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
		UserRole userRole = new UserRole();
		
		userRoleDao.saveOrUpdate(userRole);
		userRoleDao.flush();
		replay(userRoleDao);
		
		assertSame(userRole , userMgr.storeUserRole(userRole));
		verify(userRoleDao);
	}
	
    // 
    
    private UserMgrImpl userMgr;
    
    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;
    private FilterDao<UserAssociation> userAssociationDao;
    private FilterDao<UserLog> userLogDao;
    private FilterDao<UserRole> userRoleDao;
	private IdentityMgr identityMgr;
	private PublicEntityMgr publicEntityMgr;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        userMgr = new UserMgrImpl();
        userGroupRepository = createMock(UserGroupRepository.class);
        userMgr.setUserGroupRepository(userGroupRepository);
        userRepository = createMock(UserRepository.class);
        userMgr.setUserRepository(userRepository);
        userAssociationDao = createMock(FilterDao.class);
        userMgr.setUserAssociationDao(userAssociationDao);
        userLogDao = createMock(FilterDao.class);
        userMgr.setUserLogDao(userLogDao);
		userRoleDao = createMock(FilterDao.class);
		userMgr.setUserRoleDao(userRoleDao);
		identityMgr = createMock(IdentityMgr.class);
		userMgr.setIdentityMgr(identityMgr);
		publicEntityMgr = createMock(PublicEntityMgr.class);
		userMgr.setPublicEntityMgr(publicEntityMgr);
    }
    
    @After
    public void tearDown() {
        reset(userGroupRepository);
        reset(userRepository);
        reset(userAssociationDao);
        reset(userLogDao);
		reset(userRoleDao);
        reset(identityMgr);
        reset(publicEntityMgr);
    }
    
}
