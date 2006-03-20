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

import org.helianto.core.EntityCreatorImpl;
import org.helianto.core.HomeCreatorImpl;
import org.helianto.core.UserCreatorImpl;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.User;
import org.helianto.core.dao.UserDao;
import org.helianto.core.junit.AbstractIntegrationTest;

public class UserDaoImplTests extends AbstractIntegrationTest {
    
    private UserDao userDao;
    private UserCreatorImpl factory;
    private Entity entity;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new UserCreatorImpl();
        Home home = new HomeCreatorImpl().homeFactory("HOME");
        entity = new EntityCreatorImpl().entityFactory(home, "ENTITY");
    }

    public void testUserLifeCycle() {
        
        Credential credential = factory.credentialFactory("CRED");
        User user = factory.userFactory(entity, credential);
        userDao.persistUser(user);
        
        hibernateTemplate.flush();
        
        User us = userDao.findUserByEntityAliasAndPrincipal("ENTITY", "CRED");
        assertEquals(user, us);
        
        User duplicatedUser = factory.userFactory(entity, credential);
        try {
            userDao.persistUser(duplicatedUser);
            fail();
        } catch (Exception e) {
            //ok
        }

    }

}
