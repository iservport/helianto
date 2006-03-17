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

package org.helianto.core.dao;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.User;
import org.helianto.core.hibernate.CredentialDao;
import org.helianto.core.hibernate.UserDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.core.service.CoreFactoryImpl;

public class UserDaoImplTests extends AbstractIntegrationTest {
    
    private UserDao userDao;
    private CoreFactoryImpl factory;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new CoreFactoryImpl();
    }

    public void testUserLifeCycle() {
        
        Credential credential = factory.credentialFactory("CRED");
        Home home = factory.homeFactory("HOME");
        Entity entity = factory.entityFactory(home, "ENTITY");
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
