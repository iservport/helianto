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

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.IdentityType;
import org.helianto.core.OperationMode;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.dao.ServerDao;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.dao.UserDao;
import org.helianto.core.dao.UserRoleDao;
import org.helianto.core.mail.ConfigurableMailSenderFactory;
import org.helianto.core.mail.compose.MailMessageComposer;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Default implementation for <code>ServerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerMgrImpl  implements ServerMgr {

    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm)
            throws MessagingException {
        List<Server> serverList = serverDao.findServerActive(mailForm.getOperator());
        JavaMailSender sender = configurableMailSenderFactory.create(serverList);
        sender.send(mailMessageComposer.composeMessage("PASSWORD", mailForm));
    }
    
    public Identity findOrCreateIdentity(String principal) {
        Identity identity = identityDao.findUnique(principal);
        if (identity==null) {
            identity = Identity.identityFactory(principal, principal);
            identityDao.persist(identity);
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
            userGroup = userGroupDao.findUnique(entity, groupIdentity);
        }
        if (userGroup==null) {
            userGroup = UserGroup.userGroupFactory(entity, groupIdentity);
            userGroupDao.persist(userGroup);
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
            userGroupDao.persist(user);
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
        Identity groupIdentity = identityDao.findUnique(groupName);
        if (groupIdentity==null) {
            groupIdentity = createGroupIdentity(groupName);
            identityDao.persist(groupIdentity);
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
            userGroup = userGroupDao.findUnique(entity, groupIdentity);
        }
        if (userGroup==null) {
            userGroup = UserGroup.userGroupFactory(entity, groupIdentity);
            identityDao.persist(groupIdentity);
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
    
    protected Identity createGroupIdentity(String groupName) {
    	Identity groupIdentity = Identity.identityFactory(groupName, groupName);
        groupIdentity.setIdentityType(IdentityType.GROUP.getValue());
    	return groupIdentity;
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
        userGroupDao.merge(userGroup);
        return userGroup;
    }

    
    public User writeManager(Identity managerIdentity) {
        Operator operator = Operator.operatorFactory("DEFAULT",
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
        userAssociationDao.merge(adminAssociation);
        UserAssociation userAssociation = UserAssociation.userAssociationFactory(userGroup, manager);
        userAssociationDao.merge(userAssociation);
//        userGroupDao.mergeUserGroup(manager);
        if (logger.isDebugEnabled()) {
            logger.debug("Manager (member of ADMIN, USER): "+manager);
        }
        return manager;
    }

    //~ collaborators 

    private ServerDao serverDao;
    private ServiceDao serviceDao;
    private UserRoleDao userRoleDao;
    private FilterDao<Entity, EntityFilter> entityDao;
    private FilterDao<Identity, IdentityFilter> identityDao;
    private FilterDao<UserGroup, UserFilter> userGroupDao;
    private UserDao userDao;
    private BasicDao<UserAssociation> userAssociationDao;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;

    
    @Resource
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    @Resource
    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Resource
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Resource(name="entityDao")
    public void setEntityDao(FilterDao<Entity, EntityFilter> entityDao) {
        this.entityDao = entityDao;
    }

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity, IdentityFilter> identityDao) {
        this.identityDao = identityDao;
    }

    @Resource(name="userGroupDao")
	public void setUserGroupDao(FilterDao<UserGroup, UserFilter> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

    @Resource(name="userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Resource(name="userAssociationDao")
    public void setUserAssociationDao(BasicDao<UserAssociation> userAssociationDao) {
        this.userAssociationDao = userAssociationDao;
    }
    
    @Resource
    public void setConfigurableMailSenderFactory(
            ConfigurableMailSenderFactory configurableMailSenderFactory) {
        this.configurableMailSenderFactory = configurableMailSenderFactory;
    }

    @Resource(name="basicMailMessageComposer")
    public void setMailMessageComposer(MailMessageComposer mailMessageComposer) {
        this.mailMessageComposer = mailMessageComposer;
    }

    private final Log logger = LogFactory.getLog(ServerMgr.class);

}
