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


package org.helianto.core;

import java.util.List;
import java.util.Map;

import org.helianto.core.domain.ContextEvent;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.form.ContextEventForm;
import org.helianto.core.form.KeyTypeForm;
import org.helianto.core.form.ProvinceForm;
import org.helianto.core.form.ServiceForm;
import org.helianto.user.domain.UserRole;

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
public interface ContextMgr {

    /**
     * <p>Find <code>Operator</code> list.</p>
     * 
     * @param operatorFilter
     */
    List<Operator> findOperators(Filter operatorFilter);

    /**
     * Store <code>Operator</code>.
     * 
     * @param operator
     */
    Operator storeOperator(Operator operator);

    /**
     * Find <code>Province</code>s.
     * 
     * @param form
     */
	List<Province> findProvinces(ProvinceForm form);
	
    /**
     * Find <code>Province</code>s.
     * 
     * @param filter
     */
	List<Province> findProvinces(Filter filter);
	
    /**
     * Store <code>Province</code> to the data store.
     * 
     * @param province
     */
	Province storeProvince(Province province);
	
    /**
     * Find <code>Entity</code>(ies).
     * 
     * @param filter
     */
	List<Entity> findEntities(Filter filter);

    /**
     * Store <code>Entity</code> to the data store.
     * 
     * @param entity
     */
	Entity storeEntity(Entity entity);

    /**
     * Find <code>KeyType</code>(s).
     * 
     * @param operator
     */
	List<KeyType> findKeyTypes(Operator operator);
	
    /**
     * Find <code>KeyType</code>(s).
     * 
     * @param form
     */
	List<KeyType> findKeyTypes(KeyTypeForm form);

    /**
     * Store <code>KeyType</code> to the data store.
     * 
     * @param keyType
     */
	KeyType storeKeyType(KeyType keyType);

    /**
     * Find <code>Service</code>(s).
     * 
     * @param operator
     */
	List<Service> findServices(Operator operator);

    /**
     * Find <code>Service</code>(s).
     * 
     * @param form
     */
	List<Service> findServices(ServiceForm form);

    /**
     * Store <code>Service</code> to the data store.
     * 
     * @param service
     */
	Service storeService(Service service);
	
    /**
     * Find <code>ContextEvent</code>(s).
     * 
     * @param form
     */
	List<ContextEvent> findContextEvents(ContextEventForm form);
	
    /**
     * Store <code>Service</code> to the data store.
     * 
     * @param service
     */
	ContextEvent storeContextEvent(ContextEvent contextEvent);

    /**
	 * Load a service name map.
	 */
	Map<String, String> loadServiceNameMap(Operator operator, UserRole userRole);

}
