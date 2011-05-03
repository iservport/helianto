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

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.filter.ServerFilterAdapter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation for <code>ServerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("serverMgr")
public class ServerMgrImpl  implements ServerMgr {
	
	public Server findActiveServer(Operator operator, int n) {
    	ServerFilterAdapter filter = new ServerFilterAdapter(new Server());
    	filter.getForm().setOperator(operator);
        List<Server> serverList = (List<Server>) serverDao.find(filter);
		if (serverList!=null && serverList.size()> n) {
			return serverList.get(n);
		}
		return null;
	}

    public Identity findOrCreateIdentity(String principal) {
        Identity identity = identityDao.findUnique(principal);
        if (identity==null) {
            identity = Identity.identityFactory(principal, principal);
            identityDao.persist(identity);
            logger.debug("Persisted {}", identity);
        } else {
            logger.debug("Retrieved {}", identity);
        }
        return identity;
    }

    public UserGroup findOrCreateUserGroup(Entity entity, String userKey) {
        UserGroup userGroup = null;
        if (entity.getId()!=0) {
            userGroup = userGroupDao.findUnique(entity, userKey);
        }
        if (userGroup==null) {
            userGroup = new UserGroup(entity, userKey);
            userGroupDao.persist(userGroup);
            logger.debug("Persisted {}", userGroup);
        } else {
            logger.debug("Retrieved {}", userGroup);
        }
        return userGroup;
    }

    public User findOrCreateUser(Entity entity, Identity identity) {
        User user = null;
        if (entity.getId()!=0) {
            user = (User) userGroupDao.findUnique(entity, identity);
        }
        if (user==null) {
            user = new User(entity, identity);
            userGroupDao.persist(user);
            logger.debug("Persisted {}", user);
        } else {
            logger.debug("Retrieved {}", user);
        }
        return user;
    }

    public Service findOrCreateService(Entity entity, String serviceName) {
        Service service = serviceDao.findUnique(entity.getOperator(), serviceName);
        if (service==null) {
            service = new Service(entity.getOperator(), serviceName);
            serviceDao.persist(service);
            logger.debug("Persisted {}", service);
        } else {
            logger.debug("Retrieved {}", service);
        }
        return service;
    }

    public UserRole findOrCreateUserRole(UserGroup userGroup, Service service, String extension) {
        UserRole userRole = userRoleDao.findUnique(userGroup, service, extension);
        if (userRole==null) {
            userRole = new UserRole(userGroup, service, extension);
            logger.debug("Persisted {}", userRole);
        } else {
            logger.debug("Retrieved {}", userRole);
        }
        return userRole;
    }

    protected UserGroup grant(Entity entity, String serviceName, String[] extensions) {
        UserGroup userGroup = findOrCreateUserGroup(entity, serviceName);
        Service service = findOrCreateService(entity, serviceName);
        for (String extension: extensions) {
            UserRole userRole = findOrCreateUserRole(userGroup, service, extension);
            logger.debug("Role granted: {}", userRole);
        }
        userGroupDao.merge(userGroup);
        return userGroup;
    }

    
    public User storeManager(Identity managerIdentity) {
        Operator operator = new Operator("DEFAULT", Locale.getDefault());
        Entity entity = new Entity(operator, "DEFAULT");
        logger.debug("Entity created with defaults: {}", entity);
        return storeManager(entity, managerIdentity);
    }

    public User storeManager(Entity entity, Identity managerIdentity) {
        UserGroup adminGroup = grant(entity, "ADMIN", new String[] {"MANAGER"});
        UserGroup userGroup = grant(entity, "USER", new String[] {"ALL", "DEL"});
        logger.debug("Parent groups ADMIN, USER stored ");
        User manager = new User(entity, managerIdentity);
        UserAssociation adminAssociation = new UserAssociation(adminGroup, manager);
        userAssociationDao.merge(adminAssociation);
        UserAssociation userAssociation = new UserAssociation(userGroup, manager);
        userAssociationDao.merge(userAssociation);
//        userGroupDao.mergeUserGroup(manager);
        logger.debug("Manager (member of ADMIN, USER): {}", manager);
        return manager;
    }

    //~ collabs 

    private FilterDao<Server> serverDao;
    private BasicDao<UserRole> userRoleDao;
    private FilterDao<Service> serviceDao;
    private FilterDao<Identity> identityDao;
    private FilterDao<UserGroup> userGroupDao;
    private BasicDao<UserAssociation> userAssociationDao;

    
    @Resource(name="serverDao")
    public void setServerDao(FilterDao<Server> serverDao) {
        this.serverDao = serverDao;
    }

    @Resource(name="userRoleDao")
    public void setUserRoleDao(BasicDao<UserRole> userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Resource(name="serviceDao")
    public void setServiceDao(FilterDao<Service> serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity> identityDao) {
        this.identityDao = identityDao;
    }

    @Resource(name="userGroupDao")
	public void setUserGroupDao(FilterDao<UserGroup> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

    @Resource(name="userAssociationDao")
    public void setUserAssociationDao(BasicDao<UserAssociation> userAssociationDao) {
        this.userAssociationDao = userAssociationDao;
    }
    
    private final Logger logger = LoggerFactory.getLogger(ServerMgrImpl.class);

}
