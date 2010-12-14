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

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
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
	 * Automatically associate two basic services: (1) ADMIN, (2) USER.
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
	 * Install provinces from a province list.
	 * 
	 * @param defaultOperator
	 * @param provinceList
	 */
	public void installProvinces(Operator defaultOperator, List<Province> provinceList);

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
	 * <p>
	 * This method is used called from start up installers and its job is to provide at least one
	 * default entity before user interaction.
	 * </p>
	 * 
	 * @param defaultOperator
	 * @param entityAlias
	 * @param managerPricipal
	 * @param reinstall
	 */
	public Entity installEntity(Operator defaultOperator, String entityAlias, String managerPricipal, boolean reinstall);
	
	/**
	 * Install a new Entity.
	 * 
	 * <p>
	 * This method is appropriate to requests coming from the presentation layer. The embedded
	 * manager identity is required and used as a signal to perform the full installation routine.
	 * To simply update the given entity, please use {@link NamespaceMgr#storeEntity(Entity)}.
	 * </p>
	 * 
	 * @param entity
	 */
	public Entity installEntity(Entity entity);
	
	/**
	 * Install an UserGroup, if does not exist.
	 * 
	 * @param defaultEntity
	 * @param userGroupName
	 * @param reinstall
	 */
	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall);
	
	/**
	 * Install an UserRole, if does not exist.
	 * 
	 * @param userGroup
	 * @param service
	 * @param extension
	 */
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension);

}
