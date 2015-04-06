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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.helianto.core.ContextMgr;
import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.City;
import org.helianto.core.domain.ContextEvent;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.Service;
import org.helianto.core.domain.State;
import org.helianto.core.filter.ContextEventFilterAdapter;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.ProvinceFormFilterAdapter;
import org.helianto.core.filter.ServiceFormFilterAdapter;
import org.helianto.core.form.ContextEventForm;
import org.helianto.core.form.ProvinceForm;
import org.helianto.core.form.ServiceForm;
import org.helianto.core.repository.CityRepository;
import org.helianto.core.repository.ContextEventRepository;
import org.helianto.core.repository.EntityRepository;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.repository.OperatorRepository;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.repository.ServiceRepository;
import org.helianto.core.repository.StateRepository;
import org.helianto.user.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

/**
 * <code>ContextMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("namespaceMgr")
public class ContextMgrImpl 
	implements ContextMgr {

	@Transactional(readOnly=true)
	public List<Operator> findAllContexts() {
    	return operatorRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Operator findOneContext(String contextName) {
    	return operatorRepository.findByOperatorName(contextName);
	}
	
	@Transactional
	public Operator storeContext(Operator operator) {
		return operatorRepository.saveAndFlush(operator);
	}
	
	public List<State> findStates(Operator context) {
		return (List<State>) stateRepository.findByContext(context, new Sort(Direction.ASC, "stateName"));
	}
	
	public State storeState(State state) {
		return stateRepository.saveAndFlush(state);
	}

	public List<City> findCities(State state) {
		return (List<City>) cityRepository.findByState(state, new Sort(Direction.ASC, "cityName"));
	}
	
	public List<City> findCities(Operator context, String stateCode) {
		return (List<City>) cityRepository.findByContextAndStateStateCode(context, stateCode, new Sort(Direction.ASC, "cityName"));
	}
	
	public City storeCity(City city) {
		return cityRepository.saveAndFlush(city);
	}

	@Transactional(readOnly=true)
	public Entity findOneEntity(String alias) {
		return entityRepository.findByContextNameAndAlias("DEFAULT", alias);
	}
	
	@Transactional(readOnly=true)
	public Entity findOneEntity(String contextName, String alias) {
		return entityRepository.findByContextNameAndAlias(contextName, alias);
	}
	
	@Transactional
	public Entity storeEntity(Entity entity) {
		return entityRepository.saveAndFlush(entity);
	}

	@Transactional(readOnly=true)
	public List<KeyType> findKeyTypes(Operator operator) {
		List<KeyType> keyTypeList = (List<KeyType>) keyTypeRepository.findByOperator(operator);
    	if (keyTypeList!=null && keyTypeList.size()>0) {
        	logger.debug("Found key type list of size {}", keyTypeList.size());
    	}
    	return keyTypeList;
	}

	@Transactional
	public KeyType storeKeyType(KeyType keyType) {
		return keyTypeRepository.saveAndFlush(keyType);
	}

	@Transactional(readOnly=true)
	public List<Service> findServices(Operator operator) {
    	return (List<Service>) serviceRepository.findByOperator(operator);
	}

	@Transactional(readOnly=true)
	public List<Service> findServices(ServiceForm form) {
    	return (List<Service>) serviceRepository.find(new ServiceFormFilterAdapter(form));
	}

	@Transactional
	public Service storeService(Service service) {
		return serviceRepository.saveAndFlush(service);
	}

	@Transactional(readOnly=true)
	public List<ContextEvent> findContextEvents(ContextEventForm form) {
		Filter filter = new ContextEventFilterAdapter(form);
		List<ContextEvent> contextEventList = (List<ContextEvent>) contextEventRepository.find(filter);
    	if (contextEventList!=null && contextEventList.size()>0) {
        	logger.debug("Found context event list of size {}", contextEventList.size());
    	}
    	return contextEventList;
	}

	@Transactional
	public ContextEvent storeContextEvent(ContextEvent contextEvent) {
		sequenceMgr.validatePublicNumber(contextEvent);
		return contextEventRepository.saveAndFlush(contextEvent);
	}

	@Transactional
	public Map<String, String> loadServiceNameMap(Operator operator, UserRole userRole) {
		Operator managedOperator = operatorRepository.save(operator);
		Map<String, String> serviceNameMap = new HashMap<String, String>();
		Collection<Service> services = managedOperator.getServiceMap().values();
		if (services!=null && services.size()>0) {
			for (Service service: services) {
				if (userRole.getService()==null) {
					// the first one as default, if empty
					userRole.setService(service);
				}
				serviceNameMap.put(String.valueOf(service.getId()), service.getServiceName());
			}
		}
		else {
			throw new IllegalArgumentException("Unable to map from empty services list.");
		}
    	logger.debug("Loaded service map {}", serviceNameMap);
		return serviceNameMap;
	}

	@Transactional(readOnly=true)
	public List<Province> findProvinces(ProvinceForm form) {
		Filter filter = new ProvinceFormFilterAdapter(form);
    	List<Province> provinceList = (List<Province>) provinceRepository.find(filter);
    	if (provinceList!=null && provinceList.size()>0) {
        	logger.debug("Found province list of size {}", provinceList.size());
    	}
    	return provinceList;
	}

	@Transactional
	public Province storeProvince(Province province) {
		return provinceRepository.saveAndFlush(province);
	}
	
	// collabs
	
	private OperatorRepository operatorRepository;
	
	private StateRepository stateRepository;
	private CityRepository cityRepository;
	private EntityRepository entityRepository;
	private KeyTypeRepository keyTypeRepository;
	private ServiceRepository serviceRepository;
	private ContextEventRepository contextEventRepository;
	private SequenceMgr sequenceMgr;
	
	private ProvinceRepository provinceRepository;

	@javax.annotation.Resource
	public void setOperatorRepository(OperatorRepository contextRepository) {
		this.operatorRepository = contextRepository;
	}
	
	@Resource
	public void setStateRepository(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}
	
	@Resource
	public void setCityRepository(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

    @Resource
    public void setEntityRepository(EntityRepository entityRepository) {
		this.entityRepository = entityRepository;
	}
    
    @Resource
    public void setKeyTypeRepository(KeyTypeRepository keyTypeRepository) {
		this.keyTypeRepository = keyTypeRepository;
	}
    
    @Resource
    public void setServiceRepository(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}
    
    @Resource
    public void setContextEventRepository(ContextEventRepository contextEventRepository) {
		this.contextEventRepository = contextEventRepository;
	}
    
    @Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
    
    @Resource
    public void setProvinceRepository(ProvinceRepository provinceRepository) {
		this.provinceRepository = provinceRepository;
	}

    private final Logger logger = LoggerFactory.getLogger(CategoryMgrImpl.class);

}
