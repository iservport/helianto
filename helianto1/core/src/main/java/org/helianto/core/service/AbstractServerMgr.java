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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.creation.AuthenticationCreator;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.type.IdentityType;
import org.helianto.core.type.OperationMode;

/**
 * <code>ServerMgr</code> base class.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractServerMgr implements ServerMgr {

    protected AuthenticationDao authenticationDao;
    
    protected AuthorizationDao authorizationDao;
    
    public User prepareSystemConfiguration(Identity managerIdentity) {
        Entity defaultEntity = createDefaultEntity();
        if (logger.isDebugEnabled()) {
            logger.debug("Created as default: "+defaultEntity);
        }
        authorizationDao.persistEntity(defaultEntity);
//        UserGroup adminGroup = findOrCreateUserGroup(defaultEntity, "ADMIN", new String[] {"MANAGER"});
//        if (logger.isDebugEnabled()) {
//            logger.debug("ADMIN group is: "+adminGroup);
//        }
//        UserGroup userGroup = findOrCreateUserGroup(defaultEntity, "USER", new String[] {"ALL", "DEL"});
//        if (logger.isDebugEnabled()) {
//            logger.debug("USER group is: "+userGroup);
//        }
//        User manager = AuthorizationCreator.userFactory(adminGroup,
//                managerIdentity);
//        AuthorizationCreator.createUserAssociation(userGroup, manager);
//        if (logger.isDebugEnabled()) {
//            logger.debug("Created manager (member of ADMIN, USER): "+manager);
//        }
        User manager = createManager(defaultEntity, managerIdentity);
        return manager;
    }

    public Entity createDefaultEntity() {
        Operator operator = OperatorCreator.operatorFactory("DEFAULT",
                OperationMode.LOCAL, Locale.getDefault());
        return OperatorCreator.entityFactory(operator, "DEFAULT");
    }

    public UserGroup findOrCreateUserGroup(Entity entity, String groupName) {
        Identity groupIdentity = authenticationDao.findIdentityByPrincipal(groupName);
        if (groupIdentity==null) {
            groupIdentity = AuthenticationCreator.identityFactory(groupName, groupName);
            groupIdentity.setIdentityType(IdentityType.GROUP.getValue());
            authenticationDao.persistIdentity(groupIdentity);
            if (logger.isDebugEnabled()) {
                logger.debug("Persisted "+groupIdentity);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved "+groupIdentity);
            }
        }
        UserGroup userGroup = null;
        if (entity.getId()!=0) {
            userGroup = authorizationDao.findUserGroupByNaturalId(entity, groupIdentity);
        }
        if (userGroup==null) {
            userGroup = AuthorizationCreator.userGroupFactory(entity, groupIdentity);
            authenticationDao.persistIdentity(groupIdentity);
            if (logger.isDebugEnabled()) {
                logger.debug("Persisted "+userGroup);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved "+userGroup);
            }
        }
        return userGroup;
    }
    
    public UserGroup findOrCreateUserGroup(Entity entity, String serviceName, String[] extensions) {
        UserGroup userGroup = findOrCreateUserGroup(entity, serviceName);
        Service service = OperatorCreator.serviceFactory(entity
                .getOperator(), serviceName);
        for (String extension: extensions) {
            AuthorizationCreator.userRoleFactory(
                    userGroup, service, extension);
        }
        return userGroup;
    }
    
    public User createManager(Entity entity, Identity managerIdentity) {
        UserGroup adminGroup = findOrCreateUserGroup(entity, "ADMIN", new String[] {"MANAGER"});
        if (logger.isDebugEnabled()) {
            logger.debug("ADMIN group is: "+adminGroup);
        }
        UserGroup userGroup = findOrCreateUserGroup(entity, "USER", new String[] {"ALL", "DEL"});
        if (logger.isDebugEnabled()) {
            logger.debug("USER group is: "+userGroup);
        }
        User manager = AuthorizationCreator.userFactory(adminGroup,
                managerIdentity);
        AuthorizationCreator.createUserAssociation(userGroup, manager);
        if (logger.isDebugEnabled()) {
            logger.debug("Created manager (member of ADMIN, USER): "+manager);
        }
        return manager;
    }

    //~ collaborators

    public void setAuthenticationDao(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    private final Log logger = LogFactory.getLog(AbstractServerMgr.class);

}
