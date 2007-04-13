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

import org.helianto.core.UserRole;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.test.AuthorizationTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleDaoImplTests extends AuthorizationTestSupport {
    
    private AuthorizationDao authorizationDao;


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
