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
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.repository.BasicDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation for <code>PostInstallation</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("postInstallationMgr")
@Transactional
public class PostInstallationMgrImpl implements PostInstallationMgr {

	public Operator installOperator(String defaultOperatorName, boolean reinstall) {
		if (defaultOperatorName==null) {
			defaultOperatorName = "DEFAULT";
		}
		Operator defaultOperator = null;
		if (!reinstall) {
			defaultOperator = operatorDao.findUnique(defaultOperatorName);
		}
		if (defaultOperator==null) {
			logger.info("Creating operator with name "+defaultOperatorName); 
			defaultOperator = Operator.operatorFactory(defaultOperatorName, Locale.getDefault());
			defaultOperator = operatorDao.merge(defaultOperator);
		}
		logger.info("Default operator set to "+defaultOperator);
		
		Service adminService = Service.serviceFactory(defaultOperator, "ADMIN");
		logger.info("Admin service created as "+adminService);
		defaultOperator.getServiceMap().put(adminService.getServiceName(), adminService);
		
		Service userService = Service.serviceFactory(defaultOperator, "USER");
		logger.info("User service created as "+userService);
		defaultOperator.getServiceMap().put(userService.getServiceName(), userService);
		
		return defaultOperator;
	}

	public KeyType installKey(Operator defaultOperator, String keyCode) {
		KeyType keyType = keyTypeDao.findUnique(defaultOperator, keyCode);
		if (keyType==null) {
			logger.info("Installing key type "+keyCode);
			keyType = KeyType.keyTypeFactory(defaultOperator, keyCode);
			keyType = keyTypeDao.merge(keyType);
		}
		logger.info("KeyType "+keyType+" is now available");
		return keyType;
	}

	public Service installService(Operator defaultOperator, String serviceName) {
		Service service = serviceDao.findUnique(defaultOperator, serviceName);
		if (service==null) {
			logger.info("Instalando chave para "+serviceName);
			service = Service.serviceFactory(defaultOperator, serviceName);
			service = serviceDao.merge(service);
		}
		logger.info("Sevice "+service+" is now available");
		return service;
	}
	
	// collabs
	
	private BasicDao<Operator> operatorDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;

	@javax.annotation.Resource(name="operatorDao")
	public void setOperatorDao(BasicDao<Operator> operatorDao) {
		this.operatorDao = operatorDao;
	}
	
	@javax.annotation.Resource(name="keyTypeDao")
	public void setKeyTypeDao(BasicDao<KeyType> keyTypeDao) {
		this.keyTypeDao = keyTypeDao;
	}
	
	@javax.annotation.Resource(name="serviceDao")
	public void setServiceDao(BasicDao<Service> serviceDao) {
		this.serviceDao = serviceDao;
	}

	private static final Log logger = LogFactory.getLog(PostInstallationMgrImpl.class);

}
