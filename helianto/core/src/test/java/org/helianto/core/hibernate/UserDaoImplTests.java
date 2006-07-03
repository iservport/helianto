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

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.creation.EntityCreatorImpl;
import org.helianto.core.creation.HomeCreatorImpl;
import org.helianto.core.creation.UserCreatorImpl;
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

    private User createAndPersistUser() {
        Identity identity = userCreator.identityFactory("IDENTITY", "ALIAS");
        User user = userCreator.userFactory(entity, identity);
        userDao.persistUser(user);
        return user;
    }
    
    public void testUserLifeCycle() {
        
        User user = createAndPersistUser();
        hibernateTemplate.flush();
        
        User us = userDao.findUserByEntityAliasAndPrincipal("ENTITY", "IDENTITY");
        assertEquals(user, us);
        
    }
    
    public void testfindUserByEntity() {
        List<Entity> entities = createEntities();
        populateUsersAndLogs(entities);
        for (Entity e: entities) {
            List<User> users = userDao.findUserByEntity(e);
            for (User u: users) {
                assertEquals(e, u.getEntity());
            }
        }
        
    }

    public void testfindUserLogByUser() {
        List<Entity> entities = createEntities();
        List<User> users = populateUsersAndLogs(entities);
        for (User u: users) {
            List<UserLog> userLogs = userDao.findUserLogByUser(u);
            for (UserLog ul: userLogs) {
                assertEquals(u, ul.getUser());
            }
        }
        
    }

    public void testFindLastUserLog() {
        
        List<Entity> entities = createEntities();
        List<User> users = populateUsersAndLogs(entities);
        User user = users.get(0);  
        String principal = user.getIdentity().getPrincipal();
        
        UserLog userLog = userDao.findLastUserLog(principal);
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+userLog.getUser()+" - "+userLog.getLastLogin());
        }
        
        Date maxLastLogin = userLog.getLastLogin();
        for (User u: users) {
            // check only users with selected principal
            if (u.getIdentity().getPrincipal().compareTo(principal)==0) {
                List<UserLog> userLogs = userDao.findUserLogByUser(u);
                for (UserLog ul: userLogs) {
                    if (ul.getLastLogin().getTime() > maxLastLogin.getTime()) {
                        fail("Max last login is actually "+ul.getLastLogin());
                    }
                }
            }
        }
        
    }
    
    public void testCreateAndPersistUserLog() {

        User user = createAndPersistUser();
        Date date = new Date();
        UserLog userLog = ((UserDaoImpl) userDao).createAndPersistUserLog(user, date);
        hibernateTemplate.flush();
        
        List<UserLog> userLogs = userDao.findUserLogByUser(user);
        assertTrue(userLogs.size()==1);
        UserLog ul = userLogs.get(0);
        assertEquals(userLog, ul);
    }
    
    private List<Entity> createEntities() {
        List<Entity> entities = new ArrayList<Entity>();
        for (int i = 1; i<=10; i++) {
            Entity e = entityCreator.entityFactory(home, "ENTITY"+i);
            entities.add(e);
        }
        return entities;
    }
    
    private List<User> populateUsersAndLogs(List<Entity> entities) {
        List<Identity> identities = new ArrayList<Identity>();
        for (int i = 1; i<=3; i++) {
            Identity c = userCreator.identityFactory("CRED"+i, "ALIAS"+i);
            identities.add(c);
        }
        List<User> users = new ArrayList<User>();
        for (Entity e: entities) {
            Random r = new Random();
            int repeat1 = r.nextInt(2);
            for (int i=0; i<=repeat1;i++) {
                User u = userCreator.userFactory(e, identities.get(i));
                userDao.persistUser(u);
                users.add(u);
                int repeat2 = r.nextInt(8);
                for (int j=0; j<=repeat2;j++) {
                    Date date = randomDateGenerator(r);
                    if (logger.isDebugEnabled()) {
                        logger.debug("Repeat ("+i+", "+j+") UserLog to: "+u+" - "+date);
                    }
                    // there is a very small chance to have a duplicated date here
                    ((UserDaoImpl) userDao).createAndPersistUserLog(u, date);
                }
            }
        }
        hibernateTemplate.flush();
        return users;
    }
    
    private Date randomDateGenerator(Random r) {
        long sixMonthPeriodInMinutes = 60*24*180;
        long timeSixMonthAgo = (new Date()).getTime() - sixMonthPeriodInMinutes*60*1000;
        int randomShift = r.nextInt((int)sixMonthPeriodInMinutes);
        long randomTimeInLastSixMonth = timeSixMonthAgo + (long)randomShift*60*1000;
        Date likelyNotRepeatedDate = new Date(randomTimeInLastSixMonth);
        if (logger.isDebugEnabled()) {
            logger.debug("Random generated (likely not repeated) date: "+likelyNotRepeatedDate);
        }
        return likelyNotRepeatedDate;
    }
    
}
