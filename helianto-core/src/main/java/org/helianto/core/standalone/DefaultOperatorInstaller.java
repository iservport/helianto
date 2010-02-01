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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.service.PostInstallationMgr;
import org.springframework.beans.factory.InitializingBean;

/**
 * Convenient to install the default operator if the namespace does not require multiple operators.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultOperatorInstaller implements InitializingBean {
	
	private String defaultOperatorName = "DEFAULT";
	private String[] requiredKeyTypeList;
	private String[] requiredServiceList;
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
	 * A comma separated list of required key type codes.
	 */
	public String[] getRequiredKeyTypeList() {
		return requiredKeyTypeList;
	}
	public void setRequiredKeyTypeList(String[] requiredKeyTypeList) {
		this.requiredKeyTypeList = requiredKeyTypeList;
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
		if (getRequiredKeyTypeList()!=null) {
			for (String keyCodeTuple: getRequiredKeyTypeList()) {
				String[] keyCodes = keyCodeTuple.split(":");
				String keyCode = keyCodes[0].trim();
				KeyType keyType = postInstallationMgr.installKey(defaultOperator, keyCode);
				if (keyCodes.length>1) {
					keyType.setKeyName(keyCodes[1].trim());
				}
				keyTypeMap.put(keyCode, keyType);
				logger.debug("Key type {} mapped to {}", keyType, keyCode);
			}
		}

		Map<String, Service> serviceMap = new HashMap<String, Service>();
		if (getRequiredServiceList()!=null) {
			for (String serviceName: getRequiredServiceList()) {
				serviceMap.put(serviceName, postInstallationMgr.installService(defaultOperator, serviceName));
			}
		}

		namespace.setDefaultOperator(defaultOperator);
		namespace.setKeyTypeMap(keyTypeMap);
		namespace.setServiceMap(serviceMap);
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
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultOperatorInstaller.class);
	
}
