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


package org.helianto.core.standalone;

import javax.annotation.Resource;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;
import org.helianto.core.service.IdentityMgr;
import org.helianto.core.service.PostInstallationMgr;
import org.helianto.user.UserMgr;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Convenient to install the default entity if the namespace does not 
 * require multiple entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityInstaller implements InitializingBean {
	
	private String defaultEntityAlias = "DEFAULT";
	private String[] requiredUserGroupList;
	private String defaultManager = "manager";
	private boolean reinstall = false;
	
	/**
	 * @return the default entity alias.
	 */
	public String getDefaultEntityAlias() {
		return defaultEntityAlias;
	}
	public void setDefaultEntityAlias(String defaultEntityAlias) {
		this.defaultEntityAlias = defaultEntityAlias;
	}
	
	/**
	 * @return the required user group list.
	 */
	public String[] getRequiredUserGroupList() {
		return requiredUserGroupList;
	}
	public void setRequiredUserGroupList(String[] requiredUserGroupList) {
		this.requiredUserGroupList = requiredUserGroupList;
	}
	
	/**
	 * @return the default manager.
	 */
	public String getDefaultManager() {
		return defaultManager;
	}
	public void setDefaultManager(String defaultManager) {
		this.defaultManager = defaultManager;
	}
	
	/**
	 * True if reinstall is required.
	 */
	public boolean isReinstall() {
		return reinstall;
	}
	public void setReinstall(boolean reinstall) {
		this.reinstall = reinstall;
	}

	/**
	 * After properties set, the installation must expose:
	 * 
	 * <ul>
	 * <li>An <code>Entity</code> with the name provided in {@link #getDefaultEntityAlias()},</li>
	 * <li>Both a <code>Credential</code> and an <code>Identity</code> manager,
	 * after the provided {@link #getDefaultManager()} principal,</li>
	 * <li>Default groups 'USER' and 'ADMIN' associated to the above <code>Entity</code>,</li>
	 * <li>Default roles 'READ'and 'WRITE' granted to the above groups, and</li>
	 * <li>The manager as member of the above groups.</li>
	 * </ul>
	 * 
	 */
	public void afterPropertiesSet() throws Exception {
		Operator defaultOperator = namespace.getDefaultOperator();
		
		logger.info("A default entity is a minium requirement; checking installation for operator {}.", defaultOperator);
		Entity entity = postInstallationMgr.installEntity(new Entity(defaultOperator, getDefaultEntityAlias().trim()), isReinstall());

		Credential credential = identityMgr.installIdentity(getDefaultManager());
		logger.info("The manager is {}.", credential.getIdentity());
		
		Service adminService = defaultOperator.getServiceMap().get("ADMIN");
		doInstallUserGroup(adminService, entity, credential, "READ, WRITE");

		Service userService = defaultOperator.getServiceMap().get("USER");
		doInstallUserGroup(userService, entity, credential, "READ, WRITE");

		if (getRequiredUserGroupList()!=null) {
			for (String userGroupName: getRequiredUserGroupList()) {
				userMgr.installUserGroup(entity, userGroupName, isReinstall());
			}
		}

		namespace.setDefaultEntity(entity);
	}
	
	/**
	 * Actually installs an UserGroup with the same name of the service and immediately associates
	 * the group with a any user found through the provided credential.
	 * 
	 * @param service
	 * @param entity
	 * @param credential
	 */
	protected void doInstallUserGroup(Service service, Entity entity, Credential credential, String extensions) {
		if (service==null) {
			throw new IllegalArgumentException("Unable to load required service");
		}
		
		UserGroup userGroup = userMgr.installUserGroup(entity, service.getServiceName(), false);
		
		UserRole userRole = userMgr.installUserRole(userGroup, service, extensions);
		logger.debug("User role {} GRANTED to {}.", userRole, userGroup);
		
		UserAssociation userAssociation = userMgr.installUser(userGroup, credential, true);
		logger.debug("Association to {} group AVAILABLE as {}.", service.getServiceName(), userAssociation);
	}
	
	// collabs
	
	private NamespaceDefaults namespace;
	private PostInstallationMgr postInstallationMgr;
	private IdentityMgr identityMgr;
	private UserMgr userMgr;
	
	@Resource
	public void setNamespace(NamespaceDefaults namespace) {
		this.namespace = namespace;
	}

	@Resource
	public void setPostInstallationMgr(PostInstallationMgr postInstallationMgr) {
		this.postInstallationMgr = postInstallationMgr;
	}
	
	@Resource
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
	
	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultEntityInstaller.class);
	
}
