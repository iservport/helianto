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

import java.util.Map;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;


/**
 * Holds defaults in a context bean to hide the isolation complexity from developers building
 * a stand-alone application.
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
public class NamespaceDefaults {
	
	private Operator defaultOperator;
	private Map<String, KeyType> keyTypeMap;
	private Map<String, Service> serviceMap;
	private Entity defaultEntity;
	
	/**
	 * The default operator instance controlling this namespace.
	 */
	public Operator getDefaultOperator() {
		return defaultOperator;
	}
	protected final void setDefaultOperator(Operator defaultOperator) {
		this.defaultOperator = defaultOperator;
	}
	
	/**
	 * Key types used in this namespace.
	 */
	public KeyType getKeyType(String keyCode) {
		return keyTypeMap.get(keyCode);
	}
	public Map<String, KeyType> getKeyTypeMap() {
		return keyTypeMap;
	}
	protected final void setKeyTypeMap(Map<String, KeyType> keyTypeMap) {
		this.keyTypeMap = keyTypeMap;
	}
	
	/**
	 * Services used in this namespace.
	 */
	public Service getService(String serviceName) {
		return serviceMap.get(serviceName);
	}
	public Map<String, Service> getServiceMap() {
		return serviceMap;
	}
	protected final void setServiceMap(Map<String, Service> serviceMap) {
		this.serviceMap = serviceMap;
	}
	
	/**
	 * The default entity instance, if the installation is single entity.
	 */
	public Entity getDefaultEntity() {
		return defaultEntity;
	}
	public void setDefaultEntity(Entity defaultEntity) {
		this.defaultEntity = defaultEntity;
	}
	
}
