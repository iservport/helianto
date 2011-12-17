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
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.ProvinceForm;

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
     * 
     * @param operatorFilter
     */
    public List<Operator> findOperators(Filter operatorFilter);

    /**
     * Store <code>Operator</code>.
     * 
     * @param operator
     */
    public Operator storeOperator(Operator operator);

    /**
     * Find <code>Province</code>s.
     * 
     * @param form
     */
	public List<Province> findProvinces(ProvinceForm form);
	
    /**
     * Find <code>Province</code>s.
     * 
     * @param filter
     */
	public List<Province> findProvinces(Filter filter);
	
    /**
     * Store <code>Province</code> to the data store.
     * 
     * @param province
     */
	public Province storeProvince(Province province);
	
    /**
     * Find <code>Entity</code>(ies).
     * 
     * @param filter
     */
	public List<Entity> findEntities(Filter filter);

    /**
     * Store <code>Entity</code> to the data store.
     * 
     * @param entity
     */
	public Entity storeEntity(Entity entity);

    /**
     * Find <code>KeyType</code>(s).
     * 
     * @param keyTypeFilter
     */
	public List<KeyType> findKeyTypes(Filter keyTypeFilter);

    /**
     * Store <code>KeyType</code> to the data store.
     * 
     * @param keyType
     */
	public KeyType storeKeyType(KeyType keyType);

    /**
     * Find <code>Service</code>(s).
     * 
     * @param serviceFilter
     */
	public List<Service> findServices(Filter serviceFilter);

    /**
     * Store <code>Service</code> to the data store.
     * 
     * @param service
     */
	public Service storeService(Service service);

    /**
	 * Load a service name map.
	 */
	public Map<String, String> loadServiceNameMap(Operator operator, UserRole userRole);

}
