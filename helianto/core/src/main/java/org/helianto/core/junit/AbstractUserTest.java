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

package org.helianto.core.junit;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.creation.UserCreator;
import org.helianto.core.dao.UserDao;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AbstractUserTest extends AbstractCredentialTest {

    public static User createAndPersistUser(UserDao userDao) {
        Home home = EntityCreator.homeFactory("HOME");
        Entity entity = EntityCreator.entityFactory(home, "ENTITY");
        Identity identity = UserCreator.identityFactory("IDENTITY", "ALIAS");
        User user = UserCreator.userFactory(entity, identity);
        if (userDao!=null) {
            userDao.persistUser(user);
        }
        return user;
    }
    
    public static List<User> createAndPersistUserList(HibernateTemplate hibernateTemplate, int e, int i) {
        List<User> userList = createUsers(e, i);
        hibernateTemplate.saveOrUpdateAll(userList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return userList;
    }
    
    /**
     * Utility method to create users.
     * 
     * @param identityListSize
     * @param entityListSize
     * @return
     */
    public static List<User> createUsers(int entityListSize, int identityListSize) {
        List<Entity> entities = createEntityList(entityListSize);
        List<Identity> identities = createIdentities(identityListSize);
        return createUsers(entities, identities);
    }
    
    /**
     * Utility method to create users.
     * 
     * @param credentialListSize
     * @param entityListSize
     * @return
     */
    public static List<User> createValidUserList(int entityListSize, int credentialListSize) {
        List<Entity> entities = createEntityList(entityListSize);
        List<Credential> credentials = createCredentialList(credentialListSize);
        return createValidUserList(entities, credentials);
    }
    
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
                User u = UserCreator.userFactory(e, i);
                users.add(u);
            }
        }
        return users;
    }
    
    /**
     * Utility method to create users having a valid credential.
     * 
     * @param credentials
     * @param entities
     * @return
     */
    public static List<User> createValidUserList(List<Entity> entities, List<Credential> credentials) {
        List<User> users = new ArrayList<User>();
        for (Credential c: credentials) {
            for (Entity e: entities) {
                User u = UserCreator.userFactory(e, c.getIdentity());
                users.add(u);
            }
        }
        return users;
    }
    
}
