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
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.UserGroup;
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
        UserGroup userGroup = userGroupList.get((int) (Math.random()*userGroupList.size()));
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
    
    //~ collaborator mutators
    
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

}
