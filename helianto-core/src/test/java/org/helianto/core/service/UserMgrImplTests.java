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
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserLog;
import org.helianto.user.domain.UserRole;
import org.helianto.user.filter.UserFormFilterAdapter;
import org.helianto.user.form.UserGroupForm;
import org.helianto.user.repository.UserAssociationRepository;
import org.helianto.user.repository.UserGroupRepository;
import org.helianto.user.repository.UserLogRepository;
import org.helianto.user.repository.UserRepository;
import org.helianto.user.repository.UserRoleRepository;
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
	
    	expect(userAssociationRepository.saveAndFlush(parentAssociation)).andReturn(parentAssociation);
    	replay(userAssociationRepository);
    	
    	assertSame(parentAssociation, userMgr.storeUserAssociation(parentAssociation));
    	verify(userAssociationRepository);
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
        UserLog userLog = new UserLog(user, date);
        
    	expect(userLogRepository.saveAndFlush(isA(UserLog.class))).andReturn(userLog);
        replay(userLogRepository);
        
		assertSame(userLog , userMgr.storeUserLog(user,date));
        verify(userLogRepository);
    }

	@Test
	public void findUserRoles() {
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		Filter filter = new TestingFilter();
		
		expect(userRoleRepository.find(filter)).andReturn(userRoleList);
		replay(userRoleRepository);
		
		assertSame(userRoleList , userMgr.findUserRoles(filter));
		verify(userRoleRepository);
	}
	
	@Test
	public void storeUserRole() {
		UserRole userRole = new UserRole();
		
		expect(userRoleRepository.saveAndFlush(userRole)).andReturn(userRole);
		replay(userRoleRepository);
		
		assertSame(userRole , userMgr.storeUserRole(userRole));
		verify(userRoleRepository);
	}
	
    // 
    
    private UserMgrImpl userMgr;
    
    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;
    private UserAssociationRepository userAssociationRepository;
    private UserLogRepository userLogRepository;
    private UserRoleRepository userRoleRepository;
	private IdentityMgr identityMgr;
	private PublicEntityMgr publicEntityMgr;
    
	@Before
    public void setUp() {
        userMgr = new UserMgrImpl();
        userGroupRepository = createMock(UserGroupRepository.class);
        userMgr.setUserGroupRepository(userGroupRepository);
        userRepository = createMock(UserRepository.class);
        userMgr.setUserRepository(userRepository);
        userAssociationRepository = createMock(UserAssociationRepository.class);
        userMgr.setUserAssociationRepository(userAssociationRepository);
        userLogRepository = createMock(UserLogRepository.class);
        userMgr.setUserLogRepository(userLogRepository);
		userRoleRepository = createMock(UserRoleRepository.class);
		userMgr.setUserRoleRepository(userRoleRepository);
		identityMgr = createMock(IdentityMgr.class);
		userMgr.setIdentityMgr(identityMgr);
		publicEntityMgr = createMock(PublicEntityMgr.class);
		userMgr.setPublicEntityMgr(publicEntityMgr);
    }
    
    @After
    public void tearDown() {
        reset(userGroupRepository);
        reset(userRepository);
        reset(userAssociationRepository);
        reset(userLogRepository);
		reset(userRoleRepository);
        reset(identityMgr);
        reset(publicEntityMgr);
    }
    
}
