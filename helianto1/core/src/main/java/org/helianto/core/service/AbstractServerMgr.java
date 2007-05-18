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
import org.helianto.core.IdentityType;
import org.helianto.core.OperationMode;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Required;

/**
 * <code>ServerMgr</code> base class.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractServerMgr implements ServerMgr {

    protected OperatorDao operatorDao;
    protected ServiceDao serviceDao;
    protected EntityDao entityDao;
    protected IdentityDao identityDao;
    protected AuthorizationDao authorizationDao;
    
    public User prepareSystemConfiguration(Identity managerIdentity) {
        Entity defaultEntity = createDefaultEntity();
        if (logger.isDebugEnabled()) {
            logger.debug("Created as default: "+defaultEntity);
        }
        entityDao.persistEntity(defaultEntity);
        User manager = createManager(defaultEntity, managerIdentity);
        return manager;
    }

    public Entity createDefaultEntity() {
        Operator operator = OperatorCreator.operatorFactory("DEFAULT",
                OperationMode.LOCAL, Locale.getDefault());
        return Entity.entityFactory(operator, "DEFAULT");
    }

    public UserGroup findOrCreateUserGroup(Entity entity, String groupName) {
        Identity groupIdentity = identityDao.findIdentityByNaturalId(groupName);
        if (groupIdentity==null) {
            groupIdentity = Identity.identityFactory(groupName, groupName);
            groupIdentity.setIdentityType(IdentityType.GROUP.getValue());
            identityDao.persistIdentity(groupIdentity);
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
            userGroup = UserGroup.userGroupFactory(entity, groupIdentity);
            identityDao.persistIdentity(groupIdentity);
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
        Service service = serviceDao.findServiceByNaturalId(entity.getOperator(), serviceName);
        if (service==null) {
            service = Service.serviceFactory(entity
                    .getOperator(), serviceName);
        }
        for (String extension: extensions) {
            UserRole.userRoleFactory(
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
        User manager = User.userFactory(adminGroup,
                managerIdentity);
        UserAssociation.userAssociationFactory(userGroup, manager);
        if (logger.isDebugEnabled()) {
            logger.debug("Created manager (member of ADMIN, USER): "+manager);
        }
        return manager;
    }

    //~ collaborators

    @Required
    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    @Required
    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Required
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Required
    public void setIdentityDao(IdentityDao identityDao) {
        this.identityDao = identityDao;
    }

    @Required
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    private final Log logger = LogFactory.getLog(AbstractServerMgr.class);

}
