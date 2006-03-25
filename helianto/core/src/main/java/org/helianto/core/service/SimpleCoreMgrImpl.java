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

import org.helianto.core.Credential;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.EntityCreator;
import org.helianto.core.Home;
import org.helianto.core.HomeCreator;
import org.helianto.core.User;
import org.helianto.core.UserCreator;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.dao.UserDao;

public class SimpleCoreMgrImpl implements SimpleCoreMgr {
    
    private EntityCreator entityCreator;
    private HomeCreator homeCreator;
    private UserCreator userCreator;
    private EntityDao entityDao;
    private UserDao userDao;

    public DefaultEntity createDefaultEntity(String alias) {
        Home home = homeCreator.homeFactory(alias);
        Entity entity = entityCreator.entityFactory(home, alias);
        DefaultEntity defaultEntity = entityCreator.defaultEntityFactory(entity);
        return defaultEntity;
    }

    public void persistDefaultEntity(DefaultEntity defaultEntity) {
        entityDao.persistDefaultEntity(defaultEntity);
    }

    public void changeEntityToDefault(Entity entity) {
        // TODO Auto-generated method stub
        
    }

    public Entity findDefaultEntity() {
        return entityDao.findDefaultEntity();
    }
    
    public User createSimpleUser() {
        Entity entity = findDefaultEntity();
        return createSimpleUser(entity);
    }

    public User createSimpleUser(Entity entity) {
        Credential credential = userCreator.credentialFactory();
        return userCreator.userFactory(entity, credential);
    }

    public void persistUser(User user) {
        userDao.persistUser(user);
    }

    public void validateSimpleUser(User user, String verification) {
        // TODO Auto-generated method stub
        
    }
    
    // colaborators
    
    public void setEntityCreator(EntityCreator entityCreator) {
        this.entityCreator = entityCreator;
    }

    public void setHomeCreator(HomeCreator homeCreator) {
        this.homeCreator = homeCreator;
    }

    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    public void setUserCreator(UserCreator userCreator) {
        this.userCreator = userCreator;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
