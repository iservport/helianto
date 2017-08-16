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

package org.helianto.bootstrap.service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.helianto.bootstrap.ContextInstallationMgr;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.repository.OperatorRepository;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.classic.service.strategy.ProvinceResourceParserStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

/**
 * Default implementation for <code>ContextInstallationMgr</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("contextInstallationMgr")
public class ContextInstallationMgrImpl 
	implements ContextInstallationMgr {

	private OperatorRepository operatorRepository;
	private ProvinceRepository provinceRepository;
	private KeyTypeRepository keyTypeRepository;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	
	/**
	 * Constructor.
	 * 
	 * @param operatorRepository
	 * @param provinceRepository
	 * @param keyTypeRepository
	 * @param provinceResourceParserStrategy
	 */
	@Autowired
	public ContextInstallationMgrImpl(OperatorRepository operatorRepository
			, ProvinceRepository provinceRepository
			, KeyTypeRepository keyTypeRepository
			, ProvinceResourceParserStrategy provinceResourceParserStrategy) {
				this.operatorRepository = operatorRepository;
				this.provinceRepository = provinceRepository;
				this.keyTypeRepository = keyTypeRepository;
				this.provinceResourceParserStrategy = provinceResourceParserStrategy;
	}
	
	
	public Operator installOperator(String defaultOperatorName, boolean reinstall) {
		
		if (defaultOperatorName==null) {
			defaultOperatorName = "DEFAULT";
		}
		
		logger.debug("Check operator {} installation with 'reinstall={}'", defaultOperatorName, reinstall);
		Operator defaultOperator = null;
		if (!reinstall) {
			defaultOperator = operatorRepository.findByOperatorName(defaultOperatorName);
		}
		if (defaultOperator==null) {
			logger.debug("Will install operator {} ...", defaultOperatorName); 
			defaultOperator = new Operator(defaultOperatorName, Locale.getDefault());
			defaultOperator = operatorRepository.save(defaultOperator);
		}
		logger.debug("Default operator AVAILABLE as {}.", defaultOperator);
		
		operatorRepository.flush();
		return defaultOperator;
	}

	public void installProvinces(Operator defaultOperator, Resource rs) {
		
		defaultOperator = operatorRepository.save(defaultOperator);
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(defaultOperator, rs);
		installProvinces(defaultOperator, provinceList);
	}
	
	public void installProvinces(Operator defaultOperator, List<Province> provinceList) {
		
		logger.debug("Will install {} province(s) ...", provinceList.size());
		Collections.sort(provinceList);
		// due to problems with postgres, the installation is now in two phases
		// first only  provinces
		for (Province p: provinceList) {
			if (p.getClass().equals(Province.class)) {
				Province province = provinceRepository.findByOperatorAndProvinceCode(defaultOperator, p.getProvinceCode());
		    	if (province==null) {
		    		logger.debug("New province {}", p.getProvinceCode());
		    		p.setOperator(defaultOperator);
		    		if (p.getParent()!=null) {
		    			throw new IllegalArgumentException("Progrmming error: instances of Province class must not have a parent");
		    		}
		    		p = provinceRepository.save(p);
		    	}
		    	else {
			    	logger.debug("Province AVAILABLE as {}.", province);	    		
		    	}
			}
		}
		provinceRepository.flush();
    	// then other descendants
		for (Province d: provinceList) {
			if (!d.getClass().equals(Province.class)) {
				Province province = provinceRepository.findByOperatorAndProvinceCode(defaultOperator, d.getProvinceCode());
		    	if (province==null) {
		    		logger.debug("New province {}", d.getProvinceCode());
		    		d.setOperator(defaultOperator);
		    		if (d.getParent()!=null) {
		    			Province parent = provinceRepository.findByOperatorAndProvinceCode(defaultOperator, d.getProvinceCode());
		    			if (parent==null) {
		    				logger.debug("New parent {}", d.getParent().getProvinceCode());
		    				d.setParent(parent);
		    			}
		    			else {
		    				logger.debug("Current parent {}", d.getParent().getProvinceCode());
		    			}
		    		}
			        d = provinceRepository.save(d);
		    	}
		    	else {
			    	logger.debug("Province AVAILABLE as {}.", province);	    		
		    	}
			}
		}
		provinceRepository.flush();
	}
	
	public KeyType installKey(Operator defaultOperator, String keyCode) {
		
		defaultOperator = operatorRepository.save(defaultOperator);
		
		logger.debug("Check key code {} installation ...", keyCode);
		KeyType keyType = keyTypeRepository.findByOperatorAndKeyCode(defaultOperator, keyCode);
		if (keyType==null) {
			logger.debug("Will install key code {} ...", keyCode); 
			keyType = new KeyType(defaultOperator, keyCode);
			keyType = keyTypeRepository.save(keyType);
		}
		logger.debug("KeyType  AVAILABLE as {}.", keyType);
		keyTypeRepository.flush();
		return keyType;
	}

	private static final Logger logger = LoggerFactory.getLogger(ContextInstallationMgrImpl.class);

}
