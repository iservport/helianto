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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserLogFilter;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.dao.InternalEnumeratorDao;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.core.filter.IdentityFilter;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;

public class UserMgrImplTests extends TestCase {
    
    private UserMgrImpl userMgr;
    
    public void testStoreIdentity() {
        Identity managedIdentity = null, identity = new Identity();
        identity.setPrincipal("principal");
        
        principalGenerationStrategy.generatePrincipal(identity, 0);
        replay(principalGenerationStrategy);
        
        expect(identityDao.mergeIdentity(identity)).andReturn(managedIdentity);
        replay(identityDao);
        
        assertSame(managedIdentity, userMgr.storeIdentity(identity));
        verify(identityDao);
        verify(principalGenerationStrategy);
    }
    
    public void testFindIdentityByPrincipal() {
        String principal = "123";
        Identity identity = new Identity();
        
        expect(identityDao.findIdentityByNaturalId(principal))
            .andReturn(identity);
        replay(identityDao);
        
        assertSame(identity, userMgr.findIdentityByPrincipal(principal));
        verify(identityDao);
    }

    public void testSelectIdentities() {
        int size = 10;
        IdentityFilter filter = new IdentityFilter();
        String criteria = "criteria";
        List<Identity> identityList = IdentityTestSupport.createIdentityList(size);
        List<Identity> exclusions = new ArrayList<Identity>();
        Identity excluded = identityList.get((int) (Math.random()*size));
        exclusions.add(excluded);

        expect(identitySelectionStrategy.createCriteriaAsString(filter, "identity"))
            .andReturn(criteria);
        replay(identitySelectionStrategy);
        
        expect(identityDao.findIdentities(criteria))        
            .andReturn(identityList);
        replay(identityDao);

        assertSame(identityList, userMgr.findIdentities(filter, exclusions));
        verify(identityDao);
        
        assertFalse(identityList.contains(excluded));
    }
    
    public void testUserState() {
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential(user.getIdentity());
        assertEquals(ActivityState.ACTIVE.getValue(), user.getUserState());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
    }
    
    public void testFindNextInternalNumber() {
        InternalEnumerator enumerator = new InternalEnumerator();
        enumerator.setLastNumber(10);
        Entity entity = new Entity();
        
        expect(internalEnumeratorDao.findInternalEnumeratorByNaturalId(entity, "TYPE_NAME"))
            .andReturn(enumerator);
        internalEnumeratorDao.persistInternalEnumerator(enumerator);
        replay(internalEnumeratorDao);
        
        assertEquals(10, userMgr.findNextInternalNumber(entity, "TYPE_NAME"));
        verify(internalEnumeratorDao);
        assertEquals(11, enumerator.getLastNumber());
    }
    
    public void testFindNextInternalNumberFirstCall() {
        InternalEnumerator discarded = null, enumerator = null;
        Entity entity = new Entity();
        
        expect(internalEnumeratorDao.findInternalEnumeratorByNaturalId(entity, "TYPE_NAME"))
            .andReturn(enumerator);
        expect(internalEnumeratorDao.mergeInternalEnumerator(isA(InternalEnumerator.class)))
            .andReturn(discarded);
        replay(internalEnumeratorDao);
        
        assertEquals(1, userMgr.findNextInternalNumber(entity, "TYPE_NAME"));
        verify(internalEnumeratorDao);
    }
    
    public void testCreateUserAssociationPrincipalExisting() {
    	UserGroup userGroup = new UserGroup();
    	Identity identity = new Identity();
    	
    	expect(identityDao.findIdentityByNaturalId("principal"))
    		.andReturn(identity);
    	replay(identityDao);
    	
    	UserAssociation userAssociation = userMgr.createUserAssociation(userGroup, "principal");
    	verify(identityDao);
    	
    	assertSame(userGroup, userAssociation.getParent());
    	assertSame(identity, userAssociation.getChild().getIdentity());
    }
    
    public void testCreateUserAssociationPrincipalNotFound() {
    	UserGroup userGroup = new UserGroup();
    	Credential managedCredential = CredentialTestSupport.createCredential();
    	managedCredential.getIdentity().setPrincipal("principal");
    	
    	expect(identityDao.findIdentityByNaturalId("principal"))
			.andReturn(null);
    	replay(identityDao);
	
//    	expect(credentialDao.mergeCredential(isA(Credential.class)))
//    		.andReturn(managedCredential);
//    	replay(credentialDao);

    	UserAssociation userAssociation = userMgr.createUserAssociation(userGroup, "principal");
    	verify(identityDao);
    	
    	assertSame(userGroup, userAssociation.getParent());
    	assertEquals("principal", userAssociation.getChild().getUserPrincipal());
    }
    
    public void testFindUsers() {
    	UserFilter userFilter = new UserFilter();
    	List<UserGroup> userList = new ArrayList<UserGroup>();
    	
    	expect(userGroupDao.find(userFilter)).andReturn(userList);
    	replay(userGroupDao);
    	
    	assertSame(userList, userMgr.findUsers(userFilter));
    	verify(userGroupDao);
    }
    
    public void testStoreUserGroup() {
    	UserGroup userGroup = new UserGroup();
    	UserGroup managedUserGroup = new UserGroup();
    	
    	expect(userGroupDao.merge(userGroup)).andReturn(managedUserGroup);
    	replay(userGroupDao);
    	
    	userMgr.storeUserGroup(userGroup);
    	verify(userGroupDao);
    }
    
    public void testStoreUserGroupAssociation() {
    	UserGroup userGroup = new UserGroup();
    	UserAssociation parentAssociation = new UserAssociation();
    	UserAssociation managedUserAssociation = new UserAssociation();
    	managedUserAssociation.setChild(userGroup);
    	
    	expect(userAssociationDao.merge(parentAssociation)).andReturn(managedUserAssociation);
    	replay(userAssociationDao);
    	
    	assertSame(userGroup, userMgr.storeUserGroup(parentAssociation));
    	verify(userAssociationDao);
    }
    
    public void testStoreUserAssociation() {
    	UserAssociation parentAssociation = new UserAssociation();
    	UserAssociation managedUserAssociation = new UserAssociation();
    	
    	expect(userAssociationDao.merge(parentAssociation))
    	    .andReturn(managedUserAssociation);
    	replay(userAssociationDao);
    	
    	assertSame(managedUserAssociation, userMgr.storeUserAssociation(parentAssociation));
    	verify(userAssociationDao);
    }
    
    public void testFindProvinceByOperator() {
        Operator operator = OperatorTestSupport.createOperator();
        List<Province> provinceList = new ArrayList<Province>();
        
        expect(provinceDao.findProvinceByOperator(operator))
            .andReturn(provinceList);
        replay(provinceDao);
        
        assertSame(provinceList, userMgr.findProvinceByOperator(operator));
        verify(provinceDao);
    }
    
    public void testPersistUserLogError() {
        // user must have an Identity
        try {
        	userMgr.storeUserLog(new User(), new Date());
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
    }

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
    
    private IdentityDao identityDao;
    private CredentialDao credentialDao;
    private InternalEnumeratorDao internalEnumeratorDao;
    private IdentitySelectionStrategy identitySelectionStrategy;
    private PrincipalGenerationStrategy principalGenerationStrategy;
    private FilterDao<UserGroup, UserFilter> userGroupDao;
    private BasicDao<UserAssociation> userAssociationDao;
    private ProvinceDao provinceDao;
    private FilterDao<UserLog, UserLogFilter> userLogDao;

    
    @SuppressWarnings("unchecked")
	@Override
    public void setUp() {
        userMgr = new UserMgrImpl();
        identityDao = createMock(IdentityDao.class);
        userMgr.setIdentityDao(identityDao);
        credentialDao = createMock(CredentialDao.class);
        userMgr.setCredentialDao(credentialDao);
        identitySelectionStrategy = createMock(IdentitySelectionStrategy.class);
        userMgr.setIdentitySelectionStrategy(identitySelectionStrategy);
        principalGenerationStrategy = createMock(PrincipalGenerationStrategy.class);
        userMgr.setPrincipalGenerationStrategy(principalGenerationStrategy);
        internalEnumeratorDao = createMock(InternalEnumeratorDao.class);
        userMgr.setInternalEnumeratorDao(internalEnumeratorDao);
        userGroupDao = createMock(FilterDao.class);
        userMgr.setUserGroupDao(userGroupDao);
        userAssociationDao = createMock(FilterDao.class);
        userMgr.setUserAssociationDao(userAssociationDao);
        provinceDao = createMock(ProvinceDao.class);
        userMgr.setProvinceDao(provinceDao);
        userLogDao = createMock(FilterDao.class);
        userMgr.setUserLogDao(userLogDao);
    }
    
    @Override
    public void tearDown() {
        reset(identityDao);
        reset(credentialDao);
        reset(internalEnumeratorDao);
        reset(identitySelectionStrategy);
        reset(principalGenerationStrategy);
        reset(userGroupDao);
        reset(userAssociationDao);
        reset(provinceDao);
        reset(userLogDao);
    }
    
}
