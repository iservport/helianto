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

import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.springframework.orm.hibernate3.HibernateTemplate;

import junit.framework.TestCase;

public class UserMgrImplTests extends TestCase {
    
    private UserMgr userMgr;
    private HibernateTemplate hibernateTemplate;

    public void testCreateUserEntity() {

        userMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = userMgr.createUser(defaultEntity.getEntity());
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        assertEquals("", user.getIdentity().getPrincipal());
        
    }

    public void testCreateUserEntityIdentity() {

        userMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Identity identity = userMgr.createIdentity();
        assertNotNull(userMgr.createUser(identity, defaultEntity.getEntity()));
        
    }
    
    @SuppressWarnings("unchecked")
    public void testPersistUserSuccess() {
        
        // TODO refactor with userDao stub or mock
        
        userMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = userMgr.createSimpleUser();
        user.getIdentity().setPrincipal("ABC");
        userMgr.persistUser(user);
        hibernateTemplate.flush();
        
        List<User> userList = hibernateTemplate.find("from User");
        assertEquals(1, userList.size());
        User u = userList.get(0);
        assertEquals (user, u);

    }
    
    public void testPersistUserFailureNullPrincipal() {
        
        // TODO refactor with userDao stub or mock

        userMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        try {
            User user = userMgr.createSimpleUser();
            user.getIdentity().setPrincipal(null);
            userMgr.persistUser(user);
            fail();
        } catch (Exception e) {
            logger.error("Expected exception is "+e.getMessage());
            // ok
        }
        
    }


    //~ collaborator mutators

    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
}
