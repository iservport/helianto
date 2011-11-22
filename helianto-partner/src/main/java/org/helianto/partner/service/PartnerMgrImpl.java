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

package org.helianto.partner.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.KeyTypeFilterAdapter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.service.SequenceMgr;
import org.helianto.core.utils.AddressUtils;
import org.helianto.partner.PrivateAddress;
import org.helianto.partner.Customer;
import org.helianto.partner.Division;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.PartnerPhone;
import org.helianto.partner.PartnerState;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.PrivateEntityKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("partnerMgr")
public class PartnerMgrImpl implements PartnerMgr {

	public List<PrivateEntity> findPrivateEntities(Filter privateEntityFilter) {
		List<PrivateEntity> privateEntityList = (List<PrivateEntity>) privateEntityDao.find(privateEntityFilter);
    	if (logger.isDebugEnabled() && privateEntityList!=null) {
    		logger.debug("Found partner registry list of size {}", privateEntityList.size());
    	}
		return privateEntityList;
	}

	public PrivateEntity preparePrivateEntity(PrivateEntity privateEntity) {
		PrivateEntity managedPartnerRegistry = privateEntityDao.merge(privateEntity);
		managedPartnerRegistry.setPartnerList(new ArrayList<Partner>(managedPartnerRegistry.getPartners()));
		managedPartnerRegistry.setAddressList(new ArrayList<PrivateAddress>(managedPartnerRegistry.getAddresses()));
		managedPartnerRegistry.setPartnerRegistryKeyList(new ArrayList<PrivateEntityKey>(managedPartnerRegistry.getPartnerRegistryKeys()));
		privateEntityDao.evict(managedPartnerRegistry);
		return managedPartnerRegistry;
	}
	
	public PrivateEntity storePrivateEntity(PrivateEntity privateEntity) {
		privateEntityDao.saveOrUpdate(privateEntity);
		sequenceMgr.validateInternalNumber(privateEntity);
		return privateEntity;
	}
	
    public void removePrivateEntity(PrivateEntity privateEntity) {
    	privateEntityDao.remove(privateEntity);
    }

	public List<? extends Partner> findPartners(Filter partnerFilter) {
		List<Partner> partnerList = (List<Partner>) partnerDao.find(partnerFilter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size {}", partnerList.size());
    	}
		return partnerList;
	}
	
	public Partner storePartner(Partner partner) {
		partnerDao.saveOrUpdate(partner);
		return partner;
	}

	public Partner storePartner(Partner partner, Entity entity) {
		if (partner.isNewPrivateEntityRequested(entity)) {
			return storePartner(partner);
		}
		throw new IllegalArgumentException("Unable to create partner: a private entity is required.");
	}

	public void removePartner(Partner partner) {
		partnerDao.remove(partner);
	}

	public PrivateAddress storeAddress(PrivateAddress address) {
		addressDao.saveOrUpdate(address);
		return address;
	}

	public PrivateEntity removeAddress(PrivateAddress address) {
		throw new IllegalArgumentException("Not yet implemented");
	}
	
	public Map<String, PartnerKey> loadPartnerKeyMap(Partner partner) {
		Map<String, PartnerKey> partnerKeyMap = new HashMap<String, PartnerKey>();
		Set<PartnerKey> partnerKeys = partnerDao.merge(partner).getPartnerKeys();
		for (PartnerKey partnerKey: partnerKeys) {
			partnerKeyMap.put(partnerKey.getKeyType().getKeyCode(), partnerKey);
		}
		return partnerKeyMap;
	}

	public PartnerKey storePartnerKey(PartnerKey partnerKey) {
		partnerDao.saveOrUpdate(partnerKey.getPartner());
		partnerKeyDao.saveOrUpdate(partnerKey);
		return partnerKey;
	}

	public PrivateEntity removePartnerKey(PartnerKey partnerKey) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public PartnerPhone storePhone(PartnerPhone phone) {
		return phoneDao.merge(phone);
	}

	public PrivateEntity removePhone(PartnerPhone phone) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityDao.findUnique(entity, partnerAlias);
		Division division = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setPartnerName(partnerName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(province);
			privateEntityDao.saveOrUpdate(privateEntity);
		}
		else {
			division = (Division) partnerDao.findUnique(privateEntity, "D");
		}
		if (division==null) {
			logger.info("Creating division for {}.", partnerAlias);
			division = new Division(privateEntity);
			division.setPartnerState(PartnerState.ACTIVE);
			partnerDao.saveOrUpdate(division);
		}
		logger.info("Default division is {} ", division);
		partnerDao.flush();
		return division;
	}
	
	public Customer installCustomer(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityDao.findUnique(entity, partnerAlias);
		Customer customer = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setPartnerName(partnerName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(partnerAddress.getProvince());
			privateEntityDao.saveOrUpdate(privateEntity);
		}
		else {
			customer = (Customer) partnerDao.findUnique(privateEntity, "D");
		}
		if (customer==null) {
			logger.info("Creating division for {}.", partnerAlias);
			customer = new Customer(privateEntity);
			customer.setPartnerState(PartnerState.ACTIVE);
			partnerDao.saveOrUpdate(customer);
		}
		logger.info("Default division is {} ", customer);
		partnerDao.flush();
		return customer;
	}
	
	public void installPartnerKeys(String[] keyValues, Partner partner) {
		logger.debug("Ready to install key value pairs {} to {}", keyValues, partner);
		Operator operator = partner.getPrivateEntity().getEntity().getOperator();
		List<KeyType> keyTypes = namespaceMgr.findKeyTypes(new KeyTypeFilterAdapter(operator, ""));
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
					partnerKeyDao.saveOrUpdate(partnerKey);
					keyNotAvailable = false;
					break;
				}
			}
			if(keyNotAvailable) {
				logger.warn("Unable to set key value {}", keyValueTuple);
			}
		}
		partnerKeyDao.flush();
	}
	
    //- collaborators
    
    private FilterDao<PrivateEntity> privateEntityDao;
    private FilterDao<Partner> partnerDao;
    private BasicDao<PrivateAddress> addressDao;
    private BasicDao<PartnerKey> partnerKeyDao;
    private BasicDao<Province> provinceDao;
    private BasicDao<PartnerPhone> phoneDao;
	private NamespaceMgr namespaceMgr;
	private SequenceMgr sequenceMgr;

    @Resource(name="privateEntityDao")
    public void setPrivateEntityDao(FilterDao<PrivateEntity> privateEntityDao) {
        this.privateEntityDao = privateEntityDao;
    }

    @Resource(name="partnerDao")
    public void setPartnerDao(FilterDao<Partner> partnerDao) {
        this.partnerDao = partnerDao;
    }

    @Resource(name="addressDao")
    public void setAddressDao(BasicDao<PrivateAddress> addressDao) {
        this.addressDao = addressDao;
    }

    @Resource(name="partnerKeyDao")
    public void setPartnerKeyDao(BasicDao<PartnerKey> partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    
    @Resource(name="provinceDao")
    public void setProvinceDao(BasicDao<Province> provinceDao) {
		this.provinceDao = provinceDao;
	}
    
    @Resource(name="phoneDao")
    public void setPhoneDao(BasicDao<PartnerPhone> phoneDao) {
        this.phoneDao = phoneDao;
    }
    
	@Resource(name="namespaceMgr")
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
	@Resource(name="sequenceMgr")
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
    private Logger logger = LoggerFactory.getLogger(PartnerMgrImpl.class);

}
