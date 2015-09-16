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

import org.helianto.core.domain.City;
import org.helianto.core.domain.ContextEvent;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.Service;
import org.helianto.core.domain.State;
import org.helianto.user.domain.UserRole;

/**
 * Context management interface.
 * 
 * <p>
 * A context arranges entities into a logical unit of management. Classes share 
 * instances between two or more entities of a context. Such classes are provinces, 
 * services and key types, to say a few. 
 * Data from one context must not be accessible from other contexts, if they exist.
 * </p>
 * 
 * <p>
 * Note: The class Context was originally named Operator; some references to the name
 * Operator may exist but, for all purposes, Context and Operator are the same.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ContextMgr {

    /**
     * <p>Find <code>Operator</code> list.</p>
     */
    List<Operator> findAllContexts();

    /**
     * <p>Find <code>Operator</code>.</p>
     * 
     * @param contextName
     */
    Operator findOneContext(String contextName);

    /**
     * Store <code>Operator</code>.
     * 
     * @param operator
     */
    Operator storeContext(Operator operator);

    /**
     * Find <code>State</code>s.
     * 
     * @param context
     */
	List<State> findStates(Operator context);
	
    /**
     * Store <code>State</code> to the data store.
     * 
     * @param state
     */
	State storeState(State state);
	
	/**
     * Find <code>City</code>.
     * 
     * @param state
     */
	List<City> findCities(State state);
	
	/**
     * Find <code>City</code>.
     * 
     * @param context
     * @param stateCode
     */
	List<City> findCities(Operator context, String stateCode);
	
    /**
     * Store <code>City</code>.
     * 
     * @param city
     */
	City storeCity(City city);
	
	/**
     * Find <code>Entity</code> in the current context.
     * 
     * @param alias
     */
	Entity findOneEntity(String alias);

    /**
     * Find <code>Entity</code>.
     * 
     * @param contextName
     * @param alias
     */
	Entity findOneEntity(String contextName, String alias);

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
     * Store <code>Service</code> to the data store.
     * 
     * @param service
     */
	Service storeService(Service service);
	
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

    /**
     * Store <code>Province</code> to the data store.
     * 
     * @param province
     */
	Province storeProvince(Province province);
	
}
