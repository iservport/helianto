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
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.Address;
import org.helianto.partner.Division;
import org.helianto.partner.DivisionType;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.PrivateEntityFilter;
import org.helianto.partner.PrivateEntityKey;
import org.helianto.partner.PartnerState;
import org.helianto.partner.Phone;
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

	public List<PrivateEntity> findPartnerRegistries(PrivateEntityFilter privateEntityFilter) {
		List<PrivateEntity> privateEntityList = (List<PrivateEntity>) privateEntityDao.find(privateEntityFilter);
    	if (logger.isDebugEnabled() && privateEntityList!=null) {
    		logger.debug("Found partner registry list of size {}", privateEntityList.size());
    	}
		return privateEntityList;
	}

    /**
     * Prepare <code>PartnerRegistry</code> to the application layer.
     * 
     * <p>Update the following transient fields:</p>
     * <ol>
     * <li>partnerList,</li>
     * <li>addressList,</li>
     * <li>mainAddress,</li>
     * <li>privateEntityKeyList.</li>
     * </ol>
     */
	public PrivateEntity preparePartnerRegistry(PrivateEntity privateEntity) {
		PrivateEntity managedPartnerRegistry = privateEntityDao.merge(privateEntity);
		managedPartnerRegistry.setPartnerList(new ArrayList<Partner>(managedPartnerRegistry.getPartners()));
		managedPartnerRegistry.setAddressList(new ArrayList<Address>(managedPartnerRegistry.getAddresses()));
		managedPartnerRegistry.setPartnerRegistryKeyList(new ArrayList<PrivateEntityKey>(managedPartnerRegistry.getPartnerRegistryKeys()));
		privateEntityDao.evict(managedPartnerRegistry);
		return managedPartnerRegistry;
	}
	
	public PrivateEntity storePartnerRegistry(PrivateEntity privateEntity) {
		privateEntityDao.saveOrUpdate(privateEntity);
		return privateEntity;
	}
	
    public void removePartnerRegistry(PrivateEntity privateEntity) {
    	privateEntityDao.remove(privateEntity);
    }

	public List<? extends Partner> findPartners(PartnerFilter partnerFilter) {
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

	public void removePartner(Partner partner) {
		partnerDao.remove(partner);
	}

	public Address storeAddress(Address address) {
		addressDao.saveOrUpdate(address);
		return address;
	}

	public PrivateEntity removeAddress(Address address) {
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

	public Phone storePhone(Phone phone) {
		return phoneDao.merge(phone);
	}

	public PrivateEntity removePhone(Phone phone) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityDao.findUnique(entity, partnerAlias);
		Division defaultDivision = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setPartnerName(partnerName);
			privateEntity.setAddress1(partnerAddress.getAddress1());
			privateEntity.setAddress2(partnerAddress.getAddress2());
			privateEntity.setAddress3(partnerAddress.getAddress3());
			privateEntity.setAddressDetail(partnerAddress.getAddressDetail());
			privateEntity.setAddressNumber(partnerAddress.getAddressNumber());
			privateEntity.setCityName(partnerAddress.getCityName());
			privateEntity.setPostalCode(partnerAddress.getPostalCode());
			privateEntity.setPostOfficeBox(partnerAddress.getPostOfficeBox());
			privateEntity.setProvince(partnerAddress.getProvince());
			privateEntityDao.saveOrUpdate(privateEntity);
		}
		else {
			defaultDivision = (Division) partnerDao.findUnique(privateEntity, "D");
		}
		if (defaultDivision==null) {
			logger.info("Creating division for {}.", partnerAlias);
			defaultDivision = new Division();
			defaultDivision.setPrivateEntity(privateEntity);
			defaultDivision.setDivisionType(DivisionType.HEADQUARTER);
			defaultDivision.setPartnerState(PartnerState.ACTIVE);
			partnerDao.saveOrUpdate(defaultDivision);
		}
		logger.info("Default division is {} ", defaultDivision);
		return defaultDivision;
	}
	
	public void installPartnerKeys(String[] keyValues, Division defaultDivision) {
		logger.debug("Ready to install key value pairs {} to {}", keyValues, defaultDivision);
		List<KeyType> keyTypes = namespaceMgr.loadKeyTypes(defaultDivision.getPrivateEntity().getEntity().getOperator());
		Map<String, PartnerKey> partnerKeyMap = loadPartnerKeyMap(defaultDivision);
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
						partnerKey = PartnerKey.partnerKeyFactory(defaultDivision, keyType);
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
	}
	
    //- collaborators
    
    private FilterDao<PrivateEntity, PrivateEntityFilter> privateEntityDao;
    private FilterDao<Partner, PartnerFilter> partnerDao;
    private BasicDao<Address> addressDao;
    private BasicDao<PartnerKey> partnerKeyDao;
    private BasicDao<Phone> phoneDao;
	private NamespaceMgr namespaceMgr;

    @Resource(name="privateEntityDao")
    public void setPrivateEntityDao(FilterDao<PrivateEntity, PrivateEntityFilter> privateEntityDao) {
        this.privateEntityDao = privateEntityDao;
    }

    @Resource(name="partnerDao")
    public void setPartnerDao(FilterDao<Partner, PartnerFilter> partnerDao) {
        this.partnerDao = partnerDao;
    }

    @Resource(name="addressDao")
    public void setAddressDao(BasicDao<Address> addressDao) {
        this.addressDao = addressDao;
    }

    @Resource(name="partnerKeyDao")
    public void setPartnerKeyDao(BasicDao<PartnerKey> partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    @Resource(name="phoneDao")
    public void setPhoneDao(BasicDao<Phone> phoneDao) {
        this.phoneDao = phoneDao;
    }
    
	@javax.annotation.Resource(name="namespaceMgr")
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
    private Logger logger = LoggerFactory.getLogger(PartnerMgrImpl.class);

}
