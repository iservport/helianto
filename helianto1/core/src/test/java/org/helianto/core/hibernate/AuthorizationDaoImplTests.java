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

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.test.AuthorizationTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AuthorizationDaoImplTests extends AuthorizationTestSupport {
    
    private AuthorizationDao authorizationDao;

    public void testPersistUserGroup() {
        // write
        UserGroup userGroup = createAndPersistUserGroup(authorizationDao);
        hibernateTemplate.flush();
        // read
        assertEquals(userGroup,  authorizationDao.findUserGroupByNaturalId(userGroup.getEntity(), userGroup.getIdentity()));
    }
    
    public void testPersistUserGroupAndParents() {
        // if userGroup is persisted parents should also be
        UserGroup parent1 = createUserGroup();
        UserGroup parent2 = createUserGroup();
        UserGroup user = createUserGroup();
        AuthorizationCreator.createUserAssociation(parent1, user);
        AuthorizationCreator.createUserAssociation(parent2, user);
        authorizationDao.persistUserGroup(user);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        
        UserGroup found = authorizationDao.findUserGroupByNaturalId(user.getEntity(), user.getIdentity());
        assertEquals(2, found.getParents().size());
        Set<UserGroup> userSet = new HashSet<UserGroup>();
        for (UserAssociation a: found.getParents()) {
            assertEquals(user, a.getChild());
            userSet.add(a.getParent());
        }
        assertTrue(userSet.contains(parent1));
        assertTrue(userSet.contains(parent2));
        
    }
    
    private List<UserGroup> writeUserGroupList() {
        int e = 2; // entities
        int d = 5; // identities
        List<UserGroup> userGroupList = createAndPersistUserGroupList(hibernateTemplate, e, d);
        assertEquals(e*d, userGroupList.size());
        return userGroupList;
    }
    
    public void testFindUserGroup() {
        // write
        List<UserGroup> userGroupList = writeUserGroupList();
        // read
        UserGroup userGroup = userGroupList.get((int) Math.random()*userGroupList.size());
        assertEquals(userGroup,  authorizationDao.findUserGroupByNaturalId(userGroup.getEntity(), userGroup.getIdentity()));
        // TODO add some more finders
    }

    public void testUserGroupErrors() {
        try {
             authorizationDao.persistUserGroup(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             authorizationDao.removeUserGroup(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
            authorizationDao.findUserGroupByNaturalId(null, new Identity()); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
            authorizationDao.findUserGroupByNaturalId(new Entity(), null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
            authorizationDao.findUserGroupByEntity(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testUserGroupDuplicate() {
        // write
        UserGroup userGroup = createAndPersistUserGroup(authorizationDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(userGroup); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveUserGroup() {
        // write
        List<UserGroup> userGroupList = writeUserGroupList();
        // remove
        UserGroup userGroup = userGroupList.get((int) Math.random()*userGroupList.size());
        authorizationDao.removeUserGroup(userGroup);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<UserGroup> all = (ArrayList<UserGroup>) hibernateTemplate.find("from UserGroup");
        assertEquals(userGroupList.size()-1, all.size());
        assertFalse(all.contains(userGroup));
    }
    
    /*
     * User
     */

    public void testFindUser() {
//        // bulk write
//        int e = 3, i = 4;
//        List<User> userList = createAndPersistUserList(hibernateTemplate, e, i);
//        assertEquals(e*i, userList.size());
//        // bulk read
//        User user = userList.get(0);
//        List<User> listSameEntity = authorizationDao.findUserByEntity(user.getEntity());
//        assertEquals(i, listSameEntity.size());
//        for (User u: listSameEntity) {
//            assertEquals(user.getEntity(), u.getEntity());
//        }
    }
    
    public void testPersistUserLog() {
        //write
        UserLog userLog = createAndPersistUserLog(authorizationDao);
        hibernateTemplate.flush();
        //read
        assertEquals(userLog,  authorizationDao.findUserLogByNaturalId(userLog.getUser(), userLog.getLastEvent()));
    }
    
    private List<UserLog> writeUserLogList() {
        int i = 5;
        int u = 2; // users
        List<UserLog> userLogList = createAndPersistUserLogList(hibernateTemplate, i, u);
        assertEquals(i*u, userLogList.size());
        return userLogList;
    }
    
    public void testFindUserLog() {
        // write
        List<UserLog> userLogList = writeUserLogList();
        // read
        UserLog userLog = userLogList.get((int) Math.random()*userLogList.size());
        assertEquals(userLog,  authorizationDao.findUserLogByNaturalId(userLog.getUser(), userLog.getLastEvent()));
        // TODO add some more finders
        List<UserLog> list = authorizationDao.findUserLogByUser(userLog.getUser());
//        assertEquals(d, list.size());
        for (UserLog l: list) {
            assertEquals(userLog.getUser(), l.getUser());
        }
        
    }

    public void testFindLastUserLog() {
//        // bulk write
//        int e = 3, i = 4, d = 3;
//        List<User> userList = createUserList(e, i);
//        List<UserLog> userLogList = createUserLogList(userList, d);
//        assertEquals(e*i*d, userLogList.size());
//        hibernateTemplate.saveOrUpdateAll(userList);
//        hibernateTemplate.saveOrUpdateAll(userLogList);
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        User user = userList.get(0);  
//        // read
//        UserLog userLog = authorizationDao.findLastUserLog(user.getIdentity());
//        assertNotNull(userLog);
//        if (logger.isDebugEnabled()) {
//            logger.debug("Found "+userLog.getUser()+" - "+userLog.getLastEvent());
//        }
//        
//        Date maxLastLogin = userLog.getLastEvent();
//        for (User u: userList) {
//            // check only users within selected identity
//            if (u.getIdentity().equals(user.getIdentity())) {
//                List<UserLog> userLogs = authorizationDao.findUserLogByUser(u);
//                for (UserLog ul: userLogs) {
//                    if (ul.getLastEvent().getTime() > maxLastLogin.getTime()) {
//                        fail("Max last login is actually "+ul.getLastEvent());
//                    }
//                }
//            }
//        }
//        
//    }
//    
//    public void testUserLogErrors() {
//        try {
//             authorizationDao.persistUserLog(null); fail();
//        } catch (IllegalArgumentException e) { 
//        } catch (Exception e) { fail(); }
//        try {
//             authorizationDao.removeUserLog(null); fail();
//        } catch (IllegalArgumentException e) { 
//        } catch (Exception e) { fail(); }
//        // TODO add some more methods
//    }
//
//    public void testUserLogDuplicate() {
//        // write
//        UserLog userLog = createAndPersistUserLog( authorizationDao);
//        hibernateTemplate.clear();
//        // duplicate
//        try {
//            hibernateTemplate.save(userLog); fail();
//        } catch (DataIntegrityViolationException e) { 
//        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveUserLog() {
        // write
        List<UserLog> userLogList = writeUserLogList();
        // remove
        UserLog userLog = userLogList.get((int) Math.random()*userLogList.size());
        authorizationDao.removeUserLog(userLog);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<UserLog> all = (ArrayList<UserLog>) hibernateTemplate.find("from UserLog");
        assertEquals(userLogList.size()-1, all.size());
        assertFalse(all.contains(userLog));
    }
    
    /*
     * UserRole
     */

    public void testPersistUserRole() {
        //write
        UserRole userRole = createAndPersistUserRole(authorizationDao);
        hibernateTemplate.flush();
        //read
        assertEquals(userRole,  authorizationDao.findUserRoleByNaturalId(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));
    }
    
    private List<UserRole> writeUserRole() {
        int i= 5, 
        e = 3, 
        d = 4, 
        s = 2;
        List<UserRole> userRoleList = createAndPersistUserRoleList(hibernateTemplate, i, e, d, s);
        assertEquals(i*e*d*s, userRoleList.size());
        return userRoleList;
    }
    
    public void testFindUserRole() {
        // write list
        List<UserRole> userRoleList = writeUserRole();
        // read
        UserRole userRole = userRoleList.get((int) Math.random()*userRoleList.size());
        assertEquals(userRole,  authorizationDao.findUserRoleByNaturalId(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));
    }

    public void testUserRoleErrors() {
        try {
            authorizationDao.persistUserRole(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           authorizationDao.removeUserRole(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testUserRoleDuplicate() {
        // write
        UserRole userRole = createAndPersistUserRole(authorizationDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(userRole); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveUserRole() {
        // write
        List<UserRole> userRoleList = writeUserRole();
        // remove
        UserRole userRole = userRoleList.get((int) Math.random()*userRoleList.size());
        authorizationDao.removeUserRole(userRole);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<UserRole> all = (ArrayList<UserRole>) hibernateTemplate.find("from UserRole");
        assertEquals(userRoleList.size()-1, all.size());
        assertFalse(all.contains(userRole));
    }

    //~ collaborator mutators
    
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

}
