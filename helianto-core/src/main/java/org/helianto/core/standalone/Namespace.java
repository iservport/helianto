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

import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.service.PostInstallationMgr;
import org.springframework.beans.factory.InitializingBean;

/**
 * Top isolation level to <code>org.helianto.core.Entity</code> instances.
 * 
 * <p>
 * The topmost isolation level to domain objects is typically assured by the application 
 * context itself, or by some database connection. This namespace definition explicitly 
 * takes this responsibility and becomes the owner of a second isolation level formed by
 * <code>org.helianto.core.Entity</code>, <code>org.helianto.core.Service</code>, 
 * <code>org.helianto.core.Province</code>, <code>org.helianto.core.KeyType</code> and 
 * some ohter classes.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class Namespace implements InitializingBean {
	
	private String defaultOperatorName = "DEFAULT";
	private Operator defaultOperator;
	private String[] requiredKeyTypeList;
	private Map<String, KeyType> keyTypeMap;
	private String[] requiredServiceList;
	private Map<String, Service> serviceMap;
	private boolean reinstall = false;
	
	/**
	 * Name used to locate the default operator.
	 */
	public String getDefaultOperatorName() {
		return defaultOperatorName;
	}
	public void setDefaultOperatorName(String defaultOperatorName) {
		this.defaultOperatorName = defaultOperatorName;
	}
	
	/**
	 * The default operator instance controlling ths namespace (RO).
	 */
	public Operator getDefaultOperator() {
		return defaultOperator;
	}
	protected final void setDefaultOperator(Operator defaultOperator) {
		this.defaultOperator = defaultOperator;
	}
	
	/**
	 * A comma separated list of required key type codes.
	 */
	public String[] getRequiredKeyTypeList() {
		return requiredKeyTypeList;
	}
	public void setRequiredKeyTypeList(String[] requiredKeyTypeList) {
		this.requiredKeyTypeList = requiredKeyTypeList;
	}
	
	/**
	 * Key types used in this namespace (RO).
	 */
	public Map<String, KeyType> getKeyTypeMap() {
		return keyTypeMap;
	}
	protected final void setKeyTypeMap(Map<String, KeyType> keyTypeMap) {
		this.keyTypeMap = keyTypeMap;
	}
	
	/**
	 * A comma separated list of required service names.
	 */
	public String[] getRequiredServiceList() {
		return requiredServiceList;
	}
	public void setRequiredServiceList(String[] requiredServiceList) {
		this.requiredServiceList = requiredServiceList;
	}
	
	/**
	 * Services used in this namespace (RO).
	 */
	public Map<String, Service> getServiceMap() {
		return serviceMap;
	}
	protected final void setServiceMap(Map<String, Service> serviceMap) {
		this.serviceMap = serviceMap;
	}
	
	/**
	 * True if operator must be reinstalled after properties are set.
	 */
	public boolean isReinstall() {
		return reinstall;
	}
	public void setReinstall(boolean reinstall) {
		this.reinstall = reinstall;
	}

	public void afterPropertiesSet() throws Exception {
		Operator defaultOperator = postInstallationMgr.installOperator(getDefaultOperatorName(), isReinstall());

		Map<String, KeyType> keyTypeMap = new HashMap<String, KeyType>();
		for (String keyCode: getRequiredKeyTypeList()) {
			keyTypeMap.put(keyCode, postInstallationMgr.installKey(defaultOperator, keyCode));
		}

		Map<String, Service> serviceMap = new HashMap<String, Service>();
		for (String serviceName: getRequiredServiceList()) {
			serviceMap.put(serviceName, postInstallationMgr.installService(defaultOperator, serviceName));
		}

		setDefaultOperator(defaultOperator);
		setKeyTypeMap(keyTypeMap);
		setServiceMap(serviceMap);
	}

	// collabs
	
	private PostInstallationMgr postInstallationMgr;
	
	@Resource
	public void setPostInstallationMgr(PostInstallationMgr postInstallationMgr) {
		this.postInstallationMgr = postInstallationMgr;
	}
	
}
