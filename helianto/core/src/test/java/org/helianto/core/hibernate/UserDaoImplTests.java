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
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.helianto.core.EntityCreator;
import org.helianto.core.EntityCreatorImpl;
import org.helianto.core.HomeCreatorImpl;
import org.helianto.core.UserCreatorImpl;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;
import org.helianto.core.junit.AbstractIntegrationTest;

public class UserDaoImplTests extends AbstractIntegrationTest {
    
    private UserDao userDao;
    private UserCreatorImpl userCreator;
    private Home home = new HomeCreatorImpl().homeFactory("HOME");
    private Entity entity;
    private EntityCreator entityCreator;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        userCreator = new UserCreatorImpl();
        entityCreator = new EntityCreatorImpl();
        entity = entityCreator.entityFactory(home, "ENTITY");
    }

    public void testUserLifeCycle() {
        
        Credential credential = userCreator.credentialFactory("CRED");
        User user = userCreator.userFactory(entity, credential);
        userDao.persistUser(user);
        
        hibernateTemplate.flush();
        
        User us = userDao.findUserByEntityAliasAndPrincipal("ENTITY", "CRED");
        assertEquals(user, us);
        
        User duplicatedUser = userCreator.userFactory(entity, credential);
        try {
            userDao.persistUser(duplicatedUser);
            fail();
        } catch (Exception e) {
            //ok
        }

    }
    
    public void testfindUserByEntity() {
        // TODO
    }

    public void testFindLastUserLog() {
        
        List<User> users = populateUsersAndLogs();
        User u = users.get(0);  
        
        UserLog userLog = userDao.findLastUserLog(u.getCredential().getPrincipal());
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+userLog.getUser()+" - "+userLog.getLastLogin());
        }
        
    }
    
    public void testCreateAndPersistUserLog() {
        
    }
    
    private List<User> populateUsersAndLogs() {
        List<Entity> entities = new ArrayList<Entity>();
        for (int i = 1; i<=10; i++) {
            Entity e = entityCreator.entityFactory(home, "ENTITY"+i);
            entities.add(e);
        }
        List<Credential> credentials = new ArrayList<Credential>();
        for (int i = 1; i<=3; i++) {
            Credential c = userCreator.credentialFactory("CRED"+i);
            credentials.add(c);
        }
        List<User> users = new ArrayList<User>();
        long time = (new Date()).getTime();
        for (Entity e: entities) {
            Random r = new Random();
            int repeat1 = r.nextInt(2);
            for (int i=0; i<=repeat1;i++) {
                User u = userCreator.userFactory(e, credentials.get(i));
                userDao.persistUser(u);
                users.add(u);
                int repeat2 = r.nextInt(8);
                for (int j=0; j<=repeat2;j++) {
                    int oneSecond = 1000;
                    Date notRepeatedDate = new Date(time+i*60*oneSecond+j*oneSecond);
                    if (logger.isDebugEnabled()) {
                        logger.debug("Repeat ("+i+", "+j+") UserLog to: "+u+" - "+notRepeatedDate);
                    }
                    ((UserDaoImpl) userDao).createAndPersistUserLog(u, notRepeatedDate);
                }
            }
        }
        hibernateTemplate.flush();
        return users;
    }
    
}
