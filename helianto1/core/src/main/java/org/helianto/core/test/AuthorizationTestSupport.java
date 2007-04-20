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
import org.helianto.core.User;
import org.helianto.core.UserGroup;
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

    public static List<UserGroup> createUserGroupList(int entityListSize) {
        return createUserGroupList(entityListSize, 1);
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
            hibernateTemplate.persist(x);
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
    
}
