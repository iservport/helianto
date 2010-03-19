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

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.springframework.core.io.Resource;

/**
 * Post installation tasks.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PostInstallationMgr {

	/**
	 * Install an Operator, if does not exist.
	 * 
	 * <p>
	 * Automatically associate two basic services: (1) admin, (2) user.
	 * </p>
	 * 
	 * @param defaultOperatorName
	 * @param reinstall
	 */
	public Operator installOperator(String defaultOperatorName, boolean reinstall);
	
	/**
	 * Install provinces from a xml resource.
	 * 
	 * <p>Resource must contain a list of provinces:</p>
	 * <pre>
	 * &lt;provinces>
	 *     &lt;province provinceCode="XX" provinceName="xxxx" />
	 *     &lt;province provinceCode="YY" provinceName="yyyy" />
	 *     ...
	 * &lt;/provinces>
	 * </pre>
	 * 
	 * @param defaultOperator
	 * @param rs
	 */
	public void installProvinces(Operator defaultOperator, Resource rs);

	/**
	 * Install a KeyType, if does not exist.
	 * 
	 * @param defaultOperator
	 * @param keyCode
	 */
	public KeyType installKey(Operator defaultOperator, String keyCode);

	/**
	 * Install a Service, if does not exist.
	 * 
	 * @param defaultOperator
	 * @param serviceName
	 */
	public Service installService(Operator defaultOperator, String serviceName);

	/**
	 * Install an Entity, if does not exist.
	 * 
	 * @param defaultOperator
	 * @param entityAlias
	 * @param reinstall
	 */
	public Entity installEntity(Operator defaultOperator, String entityAlias, boolean reinstall);
	
	/**
	 * Install an UserGroup, if does not exist.
	 * 
	 * @param defaultEntity
	 * @param userGroupName
	 * @param reinstall
	 */
	public UserGroup instalUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall);

}
