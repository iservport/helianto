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
import org.springframework.dao.DataIntegrityViolationException;

public class UserDaoImplTests extends AbstractIntegrationTest {
    
    private UserDao userDao;

    private Entity entity;
    private EntityCreator entityCreator;
    
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        entityCreator = new EntityCreatorImpl();
        Home home = new HomeCreatorImpl().homeFactory("HOME");
        entity = entityCreator.entityFactory(home, "ENTITY");
    }

    /*
     * UserLog tests
     */

    private User createAndPersistUser(UserDao userDao) {
        Identity identity = UserCreatorImpl.identityFactory("IDENTITY", "ALIAS");
        User user = UserCreatorImpl.userFactory(entity, identity);
        userDao.persistUser(user);
        return user;
    }
    
    public void testPersistUser() {
        // write
        User user = createAndPersistUser(userDao);
        hibernateTemplate.flush();
        // read
        User u = userDao.findUserByEntityAndIdentity(user.getEntity(), user.getIdentity());
        assertEquals(user, u);
    }
    
    public void testFindUser() {
        // bulk write
        int e = 3, i = 4;
        List<User> userList = createUsers(e, i);
        assertEquals(e*i, userList.size());
        hibernateTemplate.saveOrUpdateAll(userList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // bulk read
        User user = userList.get(0);
        List<User> listSameEntity = userDao.findUserByEntity(user.getEntity());
        assertEquals(i, listSameEntity.size());
        for (User u: listSameEntity) {
            assertEquals(user.getEntity(), u.getEntity());
        }
    }
    
    public void testUserError() {
        try {
            userDao.persistUser(null); fail();
        } catch (IllegalArgumentException e) { }
        try {
            userDao.findUserByEntityAndIdentity(null, new Identity()); fail();
        } catch (IllegalArgumentException e) { }
        try {
            userDao.findUserByEntityAndIdentity(new Entity(), null); fail();
        } catch (IllegalArgumentException e) { }
        try {
            userDao.findUserByEntity(null); fail();
        } catch (IllegalArgumentException e) { }
    }
    
    public void testUserDuplicate() {
        // write
        User user = createAndPersistUser(userDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(user); fail();
        } catch (DataIntegrityViolationException e) { }
    }
    
    public void testUserRemove() {
        // bulk write
        int e = 1, i = 10;
        List<User> userList = createUsers(e, i);
        assertEquals(e*i, userList.size());
        hibernateTemplate.saveOrUpdateAll(userList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // pick one
        List<User> all = (ArrayList<User>) hibernateTemplate.find("from User");
        assertEquals(e*i, all.size());
        User user = all.get(0);
        // remove
        userDao.removeUser(user);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<User> list = (ArrayList<User>) hibernateTemplate.find("from User");
        assertEquals(e*i-1, list.size());
        assertFalse(list.contains(user));
    }
    
    /*
     * UserLog tests
     */
    
    private UserLog createAndPersistUserLog(UserDao userDao, Date date) {
        User user = createAndPersistUser(userDao);
        UserLog userLog = UserCreatorImpl.userLogFactory(user, date);
        userDao.persistUserLog(userLog);
        return userLog;
    }
    
    public void testPersistUserLog() {
        // write
        Date date = new Date();
        UserLog userLog = createAndPersistUserLog(userDao, date);
        hibernateTemplate.flush();
        // read
        List<UserLog> list = userDao.findUserLogByUser(userLog.getUser());
        assertEquals(1, list.size());
        UserLog l = list.get(0);
        assertEquals(userLog, l);
    }
    
    public void testFindUserLog() {
        // bulk write
        int e = 3, i = 4, d = 3;
        List<User> userList = createUsers(e, i);
        List<UserLog> userLogList = createUserLogs(userList, d);
        assertEquals(e*i*d, userLogList.size());
        hibernateTemplate.saveOrUpdateAll(userList);
        hibernateTemplate.saveOrUpdateAll(userLogList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // bulk read
        UserLog userLog = userLogList.get(0);
        List<UserLog> list = userDao.findUserLogByUser(userLog.getUser());
        assertEquals(d, list.size());
        for (UserLog l: list) {
            assertEquals(userLog.getUser(), l.getUser());
        }
    }
    
    public void testFindLastUserLog() {
        // bulk write
        int e = 3, i = 4, d = 3;
        List<User> userList = createUsers(e, i);
        List<UserLog> userLogList = createUserLogs(userList, d);
        assertEquals(e*i*d, userLogList.size());
        hibernateTemplate.saveOrUpdateAll(userList);
        hibernateTemplate.saveOrUpdateAll(userLogList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        User user = userList.get(0);  
        // read
        UserLog userLog = userDao.findLastUserLog(user.getIdentity());
        assertNotNull(userLog);
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+userLog.getUser()+" - "+userLog.getLastLogin());
        }
        
        Date maxLastLogin = userLog.getLastLogin();
        for (User u: userList) {
            // check only users within selected identity
            if (u.getIdentity().equals(user.getIdentity())) {
                List<UserLog> userLogs = userDao.findUserLogByUser(u);
                for (UserLog ul: userLogs) {
                    if (ul.getLastLogin().getTime() > maxLastLogin.getTime()) {
                        fail("Max last login is actually "+ul.getLastLogin());
                    }
                }
            }
        }
        
    }
    
    public void testUserLogError() {
        try {
            userDao.persistUserLog(null); fail();
        } catch (IllegalArgumentException e) { }
        try {
            userDao.findUserLogByUser(null); fail();
        } catch (IllegalArgumentException e) { }
        try {
            userDao.findLastUserLog(null); fail();
        } catch (IllegalArgumentException e) { }
    }
    
    public void testUserLogRemove() {
        // bulk write
        int e = 1, i = 10, d = 3;
        List<User> userList = createUsers(e, i);
        List<UserLog> userLogList = createUserLogs(userList, d);
        assertEquals(e*i*d, userLogList.size());
        hibernateTemplate.saveOrUpdateAll(userList);
        hibernateTemplate.saveOrUpdateAll(userLogList);
        hibernateTemplate.clear();
        // pick one
        List<UserLog> all = (ArrayList<UserLog>) hibernateTemplate.find("from UserLog");
        assertEquals(e*i*d, all.size());
        UserLog userLog = all.get(0);
        // remove
        userDao.removeUserLog(userLog);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<UserLog> list = (ArrayList<UserLog>) hibernateTemplate.find("from UserLog");
        assertEquals(e*i*d-1, list.size());
        assertFalse(list.contains(userLog));
    }
    
    public void testfindLastIdentityLogDate() {
        // TODO Code to be removed
//        List<Entity> entities = EntityDaoImplTests.createEntities(3);
//        List<User> userList = populateUsersAndLogs(entities);
//        assertTrue(userList.size()>0);
//        Identity identity = userList.get(0).getIdentity();
//        logger.info("**> Identity to test "+identity);
//        assertNotNull(identity);
//        Set<User> users = identity.getUsers();
//        logger.info("**> Users that share the same identity are "+users.size());
//        List<UserLog> userLogList = new ArrayList<UserLog>();
//        for (User u: users) {
//            userLogList.addAll(userDao.findUserLogByUser(u));
//            logger.info("**> User "+u.getIdentity().getPrincipal()+" "+u.getEntity().getAlias());
//        }
//        Date lastLogin = userDao.findLastIdentityLogDate(identity.getPrincipal());
//        assertNotNull(lastLogin);
//        logger.info("**> Last date found "+lastLogin);
//        for (UserLog ul: userLogList) {
//            logger.info("**> UserLog "+ul.getUser().getIdentity().getPrincipal()+":"+ul.getUser().getEntity().getAlias()+" "+ul.getLastLogin());
//            if (ul.getLastLogin().getTime() > lastLogin.getTime()) {
//                fail();
//            }
//        }
    }
    
    //~ utility methods
    
    /**
     * Utility method to create users.
     * 
     * @param identities
     * @param entities
     * @return
     */
    public static List<User> createUsers(List<Entity> entities, List<Identity> identities) {
        List<User> users = new ArrayList<User>();
        for (Identity i: identities) {
            for (Entity e: entities) {
                User u = UserCreatorImpl.userFactory(e, i);
                i.getUsers().add(u);
                users.add(u);
            }
        }
        return users;
    }
    
    /**
     * Utility method to create users.
     * 
     * @param identityListSize
     * @param entityListSize
     * @return
     */
    public static List<User> createUsers(int entityListSize, int identityListSize) {
        List<Entity> entities = EntityDaoImplTests.createEntities(entityListSize);
        List<Identity> identities = CredentialDaoImplTests.createIdentities(identityListSize);
        return createUsers(entities, identities);
    }
    
    /**
     * Utility method to create user logs.
     * @param users
     * @param size
     */
    private List<UserLog> createUserLogs(List<User> users, int size) {
        List<UserLog> userLogs = new ArrayList<UserLog>();
        long time = new Date().getTime();
        for (User u: users) {
            for (int i = 0; i<size; i++) {
                userLogs.add(UserCreatorImpl.userLogFactory(u, new Date(time++)));
            }
        }
        return userLogs;
    }
    
    //~ collaborator mutators
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
