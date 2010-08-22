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
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;
import org.helianto.partner.PartnerRegistryKey;
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

	public List<PartnerRegistry> findPartnerRegistries(PartnerRegistryFilter partnerRegistryFilter) {
		List<PartnerRegistry> partnerRegistryList = (List<PartnerRegistry>) partnerRegistryDao.find(partnerRegistryFilter);
    	if (logger.isDebugEnabled() && partnerRegistryList!=null) {
    		logger.debug("Found partner registry list of size {}", partnerRegistryList.size());
    	}
		return partnerRegistryList;
	}

    /**
     * Prepare <code>PartnerRegistry</code> to the application layer.
     * 
     * <p>Update the following transient fields:</p>
     * <ol>
     * <li>partnerList,</li>
     * <li>addressList,</li>
     * <li>mainAddress,</li>
     * <li>partnerRegistryKeyList.</li>
     * </ol>
     */
	public PartnerRegistry preparePartnerRegistry(PartnerRegistry partnerRegistry) {
		PartnerRegistry managedPartnerRegistry = partnerRegistryDao.merge(partnerRegistry);
		managedPartnerRegistry.setPartnerList(new ArrayList<Partner>(managedPartnerRegistry.getPartners()));
		managedPartnerRegistry.setAddressList(new ArrayList<Address>(managedPartnerRegistry.getAddresses()));
		managedPartnerRegistry.setPartnerRegistryKeyList(new ArrayList<PartnerRegistryKey>(managedPartnerRegistry.getPartnerRegistryKeys()));
		partnerRegistryDao.evict(managedPartnerRegistry);
		return managedPartnerRegistry;
	}
	
	public PartnerRegistry storePartnerRegistry(PartnerRegistry partnerRegistry) {
		partnerRegistryDao.saveOrUpdate(partnerRegistry);
		return partnerRegistry;
	}
	
    public void removePartnerRegistry(PartnerRegistry partnerRegistry) {
    	partnerRegistryDao.remove(partnerRegistry);
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

	public PartnerRegistry removeAddress(Address address) {
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

	public PartnerRegistry removePartnerKey(PartnerKey partnerKey) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public Phone storePhone(Phone phone) {
		return phoneDao.merge(phone);
	}

	public PartnerRegistry removePhone(Phone phone) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PartnerRegistry partnerRegistry = partnerRegistryDao.findUnique(entity, partnerAlias);
		Division defaultDivision = null;
		if (partnerRegistry==null) {
			logger.info("Creating registry for {}.", partnerAlias);
			partnerRegistry = PartnerRegistry.partnerRegistryFactory(entity, partnerAlias);
			partnerRegistry.setPartnerName(partnerName);
			partnerRegistry.setAddress1(partnerAddress.getAddress1());
			partnerRegistry.setAddress2(partnerAddress.getAddress2());
			partnerRegistry.setAddress3(partnerAddress.getAddress3());
			partnerRegistry.setAddressDetail(partnerAddress.getAddressDetail());
			partnerRegistry.setAddressNumber(partnerAddress.getAddressNumber());
			partnerRegistry.setCityName(partnerAddress.getCityName());
			partnerRegistry.setPostalCode(partnerAddress.getPostalCode());
			partnerRegistry.setPostOfficeBox(partnerAddress.getPostOfficeBox());
			partnerRegistry.setProvince(partnerAddress.getProvince());
			partnerRegistryDao.saveOrUpdate(partnerRegistry);
		}
		else {
			defaultDivision = (Division) partnerDao.findUnique(partnerRegistry, "D");
		}
		if (defaultDivision==null) {
			logger.info("Creating division for {}.", partnerAlias);
			defaultDivision = new Division();
			defaultDivision.setPartnerRegistry(partnerRegistry);
			defaultDivision.setDivisionType(DivisionType.HEADQUARTER);
			defaultDivision.setPartnerState(PartnerState.ACTIVE);
			partnerDao.saveOrUpdate(defaultDivision);
		}
		logger.info("Default division is {} ", defaultDivision);
		return defaultDivision;
	}
	
	public void installPartnerKeys(String[] keyValues, Division defaultDivision) {
		logger.debug("Ready to install key value pairs {} to {}", keyValues, defaultDivision);
		List<KeyType> keyTypes = namespaceMgr.loadKeyTypes(defaultDivision.getPartnerRegistry().getEntity().getOperator());
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
    
    private FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
    private FilterDao<Partner, PartnerFilter> partnerDao;
    private BasicDao<Address> addressDao;
    private BasicDao<PartnerKey> partnerKeyDao;
    private BasicDao<Phone> phoneDao;
	private NamespaceMgr namespaceMgr;

    @Resource(name="partnerRegistryDao")
    public void setPartnerRegistryDao(FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao) {
        this.partnerRegistryDao = partnerRegistryDao;
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
