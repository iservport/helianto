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

import java.util.Locale;

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
        // TODO changeEntityToDefault
        throw new RuntimeException("Feature not yet implemented.");
    }

    public Entity findDefaultEntity() {
        return entityDao.findDefaultEntity();
    }
    
    public Credential createEmptyCredential() {
        return userCreator.credentialFactory();
    }
    
    public User createSimpleUser() {
        Entity entity = findDefaultEntity();
        return createSimpleUser(entity);
    }

    public User createSimpleUser(Entity entity) {
        Credential credential = userCreator.credentialFactory();
        return createUser(credential, entity);
    }
    
    public User createSimpleUser(Credential credential) {
        Entity entity = findDefaultEntity();
        return createUser(credential, entity);
    }
    
    public User createUser(Credential credential, Entity entity) {
        return userCreator.userFactory(entity, credential);
    }

    public Locale getLocale(Home home) {
        try {
            return new Locale(home.getLanguage(), home.getCountry());
        } catch (Exception e) {
            return Locale.getDefault();
        }
    }
    
    /**
     * Helper method to convert principal to lower case.
     */
    static String convertToLowerCase(Locale locale, String principal) {
        if ((principal != null && principal.length() > 0)) {
            return principal.toLowerCase(locale);
        }
        throw new RuntimeException("Principal should not be null or empty.");
    }

    public void persistUser(User user) {
        String principal = user.getCredential().getPrincipal();
        Locale locale = getLocale(user.getEntity().getHome());
        user.getCredential().setPrincipal(
                convertToLowerCase(locale, principal));
        userDao.persistUser(user);
    }

    public boolean isPrincipalUnique(User user) {
        Locale locale = getLocale(user.getEntity().getHome());
        String principal = convertToLowerCase(locale, user.getCredential().getPrincipal());
        return (userDao.countCredentialByPrincipal(principal)==0) ? true : false;
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
