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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.service.PostInstallationMgr;
import org.springframework.beans.factory.InitializingBean;

/**
 * Convenient to install the default entity if the namespace does not require multiple entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityInstaller implements InitializingBean {
	
	private String defaultEntityAlias = "DEFAULT";
	private String[] requiredUserGroupList;
	private boolean reinstall = false;
	
	/**
	 * @return the defaultEntityAlias
	 */
	public String getDefaultEntityAlias() {
		return defaultEntityAlias;
	}
	public void setDefaultEntityAlias(String defaultEntityAlias) {
		this.defaultEntityAlias = defaultEntityAlias;
	}
	
	/**
	 * @return the requiredUserGroupList
	 */
	public String[] getRequiredUserGroupList() {
		return requiredUserGroupList;
	}
	public void setRequiredUserGroupList(String[] requiredUserGroupList) {
		this.requiredUserGroupList = requiredUserGroupList;
	}
	
	/**
	 * @return the reinstall
	 */
	public boolean isReinstall() {
		return reinstall;
	}
	public void setReinstall(boolean reinstall) {
		this.reinstall = reinstall;
	}

	/**
	 * 
	 */
	public void afterPropertiesSet() throws Exception {
		Operator defaultOperator = namespace.getDefaultOperator();
		Entity defaultEntity = postInstallationMgr.installEntity(defaultOperator, getDefaultEntityAlias(), isReinstall());
		namespace.setDefaultEntity(defaultEntity);
		
		if (getRequiredUserGroupList()!=null) {
			for (String userGroupName: getRequiredUserGroupList()) {
				postInstallationMgr.instalUserGroup(defaultEntity, userGroupName, isReinstall());
			}
		}
	}
	
	// collabs
	
	private NamespaceDefaults namespace;
	private PostInstallationMgr postInstallationMgr;
	
	@Resource
	public void setNamespace(NamespaceDefaults namespace) {
		this.namespace = namespace;
	}

	@Resource
	public void setPostInstallationMgr(PostInstallationMgr postInstallationMgr) {
		this.postInstallationMgr = postInstallationMgr;
	}
	
}
