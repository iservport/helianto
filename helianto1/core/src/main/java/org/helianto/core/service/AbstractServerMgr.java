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
import org.helianto.core.dao.UserAssociationDao;
import org.helianto.core.dao.UserDao;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Required;

/**
 * <code>ServerMgr</code> base class.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractServerMgr implements ServerMgr {

    protected OperatorDao operatorDao;
    protected ServiceDao serviceDao;
    protected UserRoleDao userRoleDao;
    protected EntityDao entityDao;
    protected IdentityDao identityDao;
    protected UserGroupDao userGroupDao;
    protected UserDao userDao;
    protected UserAssociationDao userAssociationDao;
    
    @Deprecated
    public User prepareSystemConfiguration(Identity managerIdentity) {
        Entity defaultEntity = createDefaultEntity();
        if (logger.isDebugEnabled()) {
            logger.debug("Created as default: "+defaultEntity);
        }
        entityDao.persistEntity(defaultEntity);
        User manager = writeManager(defaultEntity, managerIdentity);
        return manager;
    }

    @Deprecated
    public Entity createDefaultEntity() {
        Operator operator = OperatorCreator.operatorFactory("DEFAULT",
                OperationMode.LOCAL, Locale.getDefault());
        return Entity.entityFactory(operator, "DEFAULT");
    }
    
    public Identity findOrCreateIdentity(String principal) {
        Identity identity = identityDao.findIdentityByNaturalId(principal);
        if (identity==null) {
            identity = Identity.identityFactory(principal, principal);
            identityDao.persistIdentity(identity);
            if (logger.isDebugEnabled()) {
                logger.debug("Persisted "+identity);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved "+identity);
            }
        }
        return identity;
    }

    public UserGroup findOrCreateUserGroup(Entity entity, Identity groupIdentity) {
        UserGroup userGroup = null;
        if (entity.getId()!=0) {
            userGroup = userGroupDao.findUserGroupByNaturalId(entity, groupIdentity);
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

    public User findOrCreateUser(Entity entity, Identity identity) {
        User user = null;
        if (entity.getId()!=0) {
            user = userDao.findUserByNaturalId(entity, identity);
        }
        if (user==null) {
            user = User.userFactory(entity, identity);
            identityDao.persistIdentity(identity);
            if (logger.isDebugEnabled()) {
                logger.debug("Persisted "+user);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved "+user);
            }
        }
        return user;
    }

    public Service findOrCreateService(Entity entity, String serviceName) {
        Service service = serviceDao.findServiceByNaturalId(entity.getOperator(), serviceName);
        if (service==null) {
            service = Service.serviceFactory(entity
                    .getOperator(), serviceName);
            serviceDao.persistService(service);
            if (logger.isDebugEnabled()) {
                logger.debug("Persisted "+service);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved "+service);
            }
        }
        return service;
    }

    public UserRole findOrCreateUserRole(UserGroup userGroup, Service service, String extension) {
        UserRole userRole = userRoleDao.findUserRoleByNaturalId(userGroup, service, extension);
        if (userRole==null) {
            userRole = UserRole.userRoleFactory(
                    userGroup, service, extension);
            if (logger.isDebugEnabled()) {
                logger.debug("Persisted "+userRole);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieved "+userRole);
            }
        }
        return userRole;
    }

    @Deprecated
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
            userGroup = userGroupDao.findUserGroupByNaturalId(entity, groupIdentity);
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
    
    @Deprecated
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
    
    protected UserGroup grant(Entity entity, String serviceName, String[] extensions) {
        Identity groupIdentity = findOrCreateIdentity(serviceName);
        groupIdentity.setIdentityType(IdentityType.GROUP.getValue());
        UserGroup userGroup = findOrCreateUserGroup(entity, groupIdentity);
        Service service = findOrCreateService(entity, serviceName);
        for (String extension: extensions) {
            UserRole userRole = findOrCreateUserRole(userGroup, service, extension);
            if (logger.isDebugEnabled()) {
                logger.debug("Role granted: "+userRole);
            }
        }
        userGroupDao.mergeUserGroup(userGroup);
        return userGroup;
    }

    
    public User writeManager(Identity managerIdentity) {
        Operator operator = OperatorCreator.operatorFactory("DEFAULT",
                OperationMode.LOCAL, Locale.getDefault());
        Entity entity = Entity.entityFactory(operator, "DEFAULT");
        if (logger.isDebugEnabled()) {
            logger.debug("Entity created with defaults: "+entity);
        }
        return writeManager(entity, managerIdentity);
    }

    public User writeManager(Entity entity, Identity managerIdentity) {
        UserGroup adminGroup = grant(entity, "ADMIN", new String[] {"MANAGER"});
        UserGroup userGroup = grant(entity, "USER", new String[] {"ALL", "DEL"});
        if (logger.isDebugEnabled()) {
            logger.debug("Parent groups ADMIN, USER stored ");
        }
        User manager = User.userFactory(entity, managerIdentity);
        UserAssociation adminAssociation = UserAssociation.userAssociationFactory(adminGroup, manager);
        userAssociationDao.mergeUserAssociation(adminAssociation);
        UserAssociation userAssociation = UserAssociation.userAssociationFactory(userGroup, manager);
        userAssociationDao.mergeUserAssociation(userAssociation);
//        userGroupDao.mergeUserGroup(manager);
        if (logger.isDebugEnabled()) {
            logger.debug("Manager (member of ADMIN, USER): "+manager);
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
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
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
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Required
    public void setUserAssociationDao(UserAssociationDao userAssociationDao) {
        this.userAssociationDao = userAssociationDao;
    }
    
    public void init() {
    	System.out.println("-----------------------------");
    }

    private final Log logger = LogFactory.getLog(AbstractServerMgr.class);

}
