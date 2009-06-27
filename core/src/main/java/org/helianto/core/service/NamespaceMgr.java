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
import java.util.Map;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.Service;
import org.helianto.core.UserRole;

/**
 * Namespace management interface.
 * 
 * <p>
 * Namespaces group entites into a logical unit of management and are controlled by
 * an operator. Classes share instances between two or more entities of a namespace 
 * if are related to the operator. Such classes are provinces, services and key types, 
 * to say a few. Data from one namespace are not accessible from an external
 * namespace.
 * </p>
 * 
 * <p>
 * The datastore must have at least one namespace, i.e. one operator. If no one
 * exists, a default is created. The creation of a namespace implies in the creation of
 * basic namespace defaults, namely, two default services, two default user groups and
 * the corresponding roles to bind each of the services above to its user groups.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface NamespaceMgr {

    /**
     * <p>Find <code>Operator</code> list.</p>
     */
    public List<Operator> findOperator();

    /**
     * <p>Persist a new <code>Entity</code>.</p>
     * 
     * <p>Two user groups are automatically created and associated to the new 
     * entity: (1) the admin user, reserved to hold privileges to manage other groups
     * and users, (2) the default user group, to grant access to the application entry 
     * point. </p>
     */
    public Entity createAndPersistEntity(Operator operator, String alias);
    
    /**
     * <p>Find <code>Operator</code> by name.</p>
     */
    public Operator findOperatorByName(String operatorName);

	/**
	 * Prepare a <code>Province</code> to the presentation layer.
	 */
	public Province prepareProvince(Province province);
	
	/**
	 * Prepare a new <code>Province</code> to the presentation layer.
	 */
	public Province prepareNewProvince(Entity entity);
	
    /**
     * Store <code>Operator</code>.
     */
    public Operator storeOperator(Operator operator);

    /**
     * Find <code>Province</code>s.
     */
	public List<Province> findProvinces(ProvinceFilter filter);
	
    /**
     * Store <code>Province</code> to the data store.
     */
	public Province storeProvince(Province province);
	
    /**
     * Find <code>Entity</code>(ies).
     */
	public List<Entity> findEntities(EntityFilter filter);

    /**
     * Prepare <code>Entity</code> in session.
     */
	public Entity prepareEntity(Entity entity);

    /**
     * Store <code>Entity</code> to the data store.
     */
	public Entity storeEntity(Entity entity);

    /**
     * Load <code>KeyType</code>(s).
     */
	public List<KeyType> loadKeyTypes(Operator operator);

    /**
     * Store <code>KeyType</code> to the data store.
     */
	public KeyType storeKeyType(KeyType keyType);

    /**
     * Load <code>Service</code>(s).
     */
	public List<Service> loadServices(Operator operator);

    /**
     * Store <code>Service</code> to the data store.
     */
	public Service storeService(Service service);

    /**
     * Store <code>UserRole</code> to the data store.
     */
	public UserRole storeUserRole(UserRole userRole);

	/**
	 * Load a service name map.
	 */
	public Map<String, String> loadServiceNameMap(Operator operator, UserRole userRole);

}
