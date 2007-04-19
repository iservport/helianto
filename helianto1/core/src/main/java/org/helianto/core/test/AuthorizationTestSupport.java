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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.dao.AuthorizationDao;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AuthorizationTestSupport extends AbstractHibernateIntegrationTest {

    private static int testKey = 1;

    /*
     * UserGroup tests 
     */
    
    public static UserGroup createUserGroup(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        Identity identity;
        try {
            identity = (Identity) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            identity = AuthenticationTestSupport.createIdentity();
        }
        UserGroup userGroup = AuthorizationCreator.userGroupFactory(entity, identity);
        logger.info("+++ "+userGroup);
        return userGroup;
    }

    public static UserGroup createAndPersistUserGroup(AuthorizationDao authorizationDao) {
        UserGroup userGroup = createUserGroup();
        if (authorizationDao!=null) {
//            authorizationDao.persistEntity(userGroup.getEntity());
            authorizationDao.persistUserGroup(userGroup);
        }
        return userGroup;
    }

    public static List<UserGroup> createUserGroupList(int entityListSize, int identityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<Identity> identityList = AuthenticationTestSupport.createIdentityList(identityListSize);
        return createUserGroupList(entityList, identityList);
    }

    public static List<UserGroup> createUserGroupList(List<Entity> entityList, List<Identity> identityList) {
        List<UserGroup> userGroupList = new ArrayList<UserGroup>();
        for (Entity e: entityList) {
            for (Identity d: identityList) {
                userGroupList.add(createUserGroup(e, d));
            }
        }
        return userGroupList;
    }

    public static List<UserGroup> createAndPersistUserGroupList(HibernateTemplate hibernateTemplate, int entityListSize, int identityListSize) {
        List<UserGroup> userGroupList = createUserGroupList(entityListSize, identityListSize);
        for (UserGroup x: userGroupList) {
            hibernateTemplate.save(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return userGroupList;
    }
    
    /*
     * User
     */
    
    public static User createUser(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        Identity identity;
        try {
            identity = (Identity) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            identity = AuthenticationTestSupport.createIdentity();
        }
        User user = AuthorizationCreator.userFactory(entity, identity);
        return user;
    }

    public static UserGroup createAndPersistUser(AuthorizationDao authorizationDao) {
        User user= createUser();
        if (authorizationDao!=null) {
            authorizationDao.persistUserGroup(user);
        }
        return user;
    }

    public static List<User> createUserList(int identityListSize) {
        return createUserList(1, identityListSize);
    }

    public static List<User> createUserList(int entityListSize, int identityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<Identity> identityList = AuthenticationTestSupport.createIdentityList(identityListSize);
        return createUserList(entityList, identityList);
    }

    public static List<User> createUserList(List<Entity> entityList, List<Identity> identityList) {
        List<User> userList = new ArrayList<User>();
        for (Entity e: entityList) {
            for (Identity d: identityList) {
                userList.add(createUser(e, d));
            }
        }
        return userList;
    }

    public static List<User> createAndPersistUserList(HibernateTemplate hibernateTemplate, int entityListSize, int identityListSize) {
        List<User> userList = createUserList(entityListSize, identityListSize);
        for (UserGroup x: userList) {
            hibernateTemplate.persist(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return userList;
    }
    
//    // @see UserLogTestSupport
//    
//    public static UserLog createUserLog(Object... args) {
//        User user;
//        try {
//            user = (User) args[0];
//        } catch(ArrayIndexOutOfBoundsException e) {
//            user = AuthorizationTestSupport.createUser();
//        }
//        Date date;
//        try {
//            date = (Date) args[1];
//        } catch(ArrayIndexOutOfBoundsException e) {
//            date = new Date();
//        }
//        UserLog userLog = AuthorizationCreator.userLogFactory(user, UserEventType.LOGIN_SUCCESS, date);
//        logger.info("+++ "+userLog);
//        return userLog;
//    }
//
//    public static UserLog createAndPersistUserLog(AuthorizationDao authorizationDao) {
//        UserLog userLog = createUserLog();
//        if (authorizationDao!=null) {
//            authorizationDao.persistUserLog(userLog);
//        }
//        return userLog;
//    }
//
//    public static List<UserLog> createUserLogList(int size, int userListSize) {
//        List<User> userList = AuthorizationTestSupport.createUserList(1, userListSize);
//        return createUserLogList(size, userList);
//    }
//
//    public static List<UserLog> createUserLogList(int size, List<User> userList) {
//        List<UserLog> userLogList = new ArrayList<UserLog>();
//        long milis = new Date().getTime();
//        for (User u: userList) {
//            for (int i=0;i<size;i++) {
//                userLogList.add(createUserLog(u, new Date(milis++)));
//            }
//        }
//        return userLogList;
//    }
//
//    public static List<UserLog> createAndPersistUserLogList(HibernateTemplate hibernateTemplate, int size, int userListSize) {
//        List<UserLog> userLogList = createUserLogList(size, userListSize);
//        for (UserLog x: userLogList) {
//            hibernateTemplate.merge(x);
//        }
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        return userLogList;
//    }
//    
    /*
     * UserRole
     */
    
    public static UserRole createUserRole(Object... args) {
        User user;
        try {
            user = (User) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            user = createUser();
        }
        Service service;
        try {
            service = (Service) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            service = ServiceTestSupport.createService();
        }
        UserRole userRole = AuthorizationCreator.userRoleFactory(user, service, generateKey(20, testKey++));
        return userRole;
    }

    public static UserRole createAndPersistUserRole(AuthorizationDao authorizationDao) {
        UserRole userRole = createUserRole();
        if (authorizationDao!=null) {
            authorizationDao.persistUserRole(userRole);
        }
        return userRole;
    }

    public static List<UserRole> createAndPersistUserRoleList(HibernateTemplate hibernateTemplate, int size, int entityListSize, int identityListSize, int serviceListSize) {
        List<UserRole> userRoleList = createUserRoleList(size, entityListSize, identityListSize, serviceListSize);
        for (UserRole x: userRoleList) {
            hibernateTemplate.persist(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return userRoleList;
    }

    public static List<UserRole> createUserRoleList(int size, int entityListSize, int identityListSize, int serviceListSize) {
        List<User> userList = createUserList(entityListSize, identityListSize);
        List<Service> serviceList = ServiceTestSupport.createServiceList(serviceListSize, 1);
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        for (User u: userList) {
            for (Service s: serviceList) {
                for (int i = 0;i<size;i++) {
                    if (i==0) {
                        userRoleList.add(AuthorizationCreator.userRoleFactory(u, s, ""));
                    } else {
                        userRoleList.add(AuthorizationCreator.userRoleFactory(u, s, "EXT"+i));
                    }
                }
            }
        }
        return userRoleList ;
    }

    
    
    
}
