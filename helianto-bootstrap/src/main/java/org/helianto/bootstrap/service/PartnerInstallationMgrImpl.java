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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.helianto.bootstrap.PartnerInstallationMgr;
import org.helianto.core.ContextMgr;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.form.CompositeEntityForm;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.repository.PublicEntityRepository;
import org.helianto.core.utils.AddressUtils;
import org.helianto.partner.PartnerState;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
import org.helianto.partner.repository.PartnerKeyRepository;
import org.helianto.partner.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("partnerInstallationMgr")
public class PartnerInstallationMgrImpl 
	implements PartnerInstallationMgr {
	
    private PublicEntityRepository publicEntityRepository;
    private PartnerRepository partnerRepository;
	private PartnerKeyRepository partnerKeyRepository;
    private ProvinceRepository provinceRepository;
	private ContextMgr contextMgr;
    
	/**
	 * Constructor.
	 * 
	 * @param publicEntityRepository
	 * @param partnerRepository
	 * @param partnerKeyRepository
	 * @param provinceRepository
	 * @param contextMgr
	 */
	@Autowired
	public PartnerInstallationMgrImpl(PublicEntityRepository publicEntityRepository
			, PartnerRepository partnerRepository
			, PartnerKeyRepository partnerKeyRepository
			, ProvinceRepository provinceRepository
			, ContextMgr contextMgr) {
		this.publicEntityRepository = publicEntityRepository;
		this.partnerRepository = partnerRepository;
		this.partnerKeyRepository = partnerKeyRepository;
		this.provinceRepository = provinceRepository;
		this.contextMgr = contextMgr;
	}

	public Division installDivision(Entity entity, String entityName, AbstractAddress partnerAddress, boolean reinstall) {
		String entityAlias = entity.getAlias();
		PrivateEntity privateEntity = (PrivateEntity) publicEntityRepository.findByEntityAndEntityAlias(entity, entityAlias);
		Division division = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", entityAlias);
			privateEntity = new PrivateEntity(entity, entityAlias);
			privateEntity.setEntityName(entityName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(province);
			publicEntityRepository.save(privateEntity);
		}
		else {
			division = (Division) partnerRepository.findByPrivateEntity(privateEntity); //, "D");
		}
		if (division==null) {
			logger.info("Creating division for {}.", entityAlias);
			division = new Division(privateEntity);
			division.setPartnerStateAsEnum(PartnerState.ACTIVE);
			partnerRepository.save(division);
		}
		logger.info("Default division is {} ", division);
		partnerRepository.flush();
		return division;
	}
	
	public Customer installCustomer(Entity entity, String entityName, AbstractAddress partnerAddress, boolean reinstall) {
		String entityAlias = entity.getAlias();
		PrivateEntity privateEntity = (PrivateEntity) publicEntityRepository.findByEntityAndEntityAlias(entity, entityAlias);
		Customer customer = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", entityAlias);
			privateEntity = new PrivateEntity(entity, entityAlias);
			privateEntity.setEntityName(entityName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(partnerAddress.getProvince());
			privateEntity = publicEntityRepository.save(privateEntity);
		}
		else {
			customer = (Customer) partnerRepository.findByPrivateEntity(privateEntity); //, "D");
		}
		if (customer==null) {
			logger.info("Creating division for {}.", entityAlias);
			customer = new Customer(privateEntity);
			customer.setPartnerStateAsEnum(PartnerState.ACTIVE);
		}
		logger.info("Default division is {} ", customer);
		return customer;
	}
	
	public void installPartnerKeys(String[] keyValues, Partner partner) {
		logger.debug("Ready to install key value pairs {} to {}", keyValues, partner);
		Operator operator = partner.getPrivateEntity().getEntity().getOperator();
		CompositeEntityForm compositeEntityForm = new CompositeEntityForm(operator);
		List<KeyType> keyTypes = contextMgr.findKeyTypes(compositeEntityForm);
		Map<String, PartnerKey> partnerKeyMap = loadPartnerKeyMap(partner);
		for (String keyValueTuple: keyValues) {
			String[] keyValue = keyValueTuple.split(":");
			boolean keyNotAvailable = true;
			for (KeyType keyType: keyTypes) {
				if (keyValue.length>1 && keyValue[0].trim().equals(keyType.getKeyCode())) {
					PartnerKey partnerKey = null;
					if (partnerKeyMap.containsKey(keyType.getKeyCode())) {
						partnerKey = partnerKeyMap.get(keyType.getKeyCode());
						logger.debug("Partner key {} already existing.", partnerKey);
					}
					else {
						partnerKey = new PartnerKey(partner, keyType);
					}
					partnerKey.setKeyValue(keyValue[1]);
					keyNotAvailable = false;
					break;
				}
			}
			if(keyNotAvailable) {
				logger.warn("Unable to set key value {}", keyValueTuple);
			}
		}
		partnerKeyRepository.flush();
	}
	
	public Map<String, PartnerKey> loadPartnerKeyMap(Partner partner) {
		Map<String, PartnerKey> partnerKeyMap = new HashMap<String, PartnerKey>();
		Set<PartnerKey> partnerKeys = partnerRepository.save(partner).getPartnerKeys();
		for (PartnerKey partnerKey: partnerKeys) {
			partnerKeyMap.put(partnerKey.getKeyType().getKeyCode(), partnerKey);
		}
		return partnerKeyMap;
	}

    private Logger logger = LoggerFactory.getLogger(PartnerInstallationMgrImpl.class);

}
