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
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.Address;
import org.helianto.partner.AddressType;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;
import org.helianto.partner.PartnerRegistryKey;
import org.helianto.partner.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("partnerMgr")
public class PartnerMgrImpl implements PartnerMgr {

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<PartnerRegistry> findPartnerRegistries(PartnerRegistryFilter partnerRegistryFilter) {
		List<PartnerRegistry> partnerRegistryList = (List<PartnerRegistry>) partnerRegistryDao.find(partnerRegistryFilter);
    	if (logger.isDebugEnabled() && partnerRegistryList!=null) {
    		logger.debug("Found partner registry list of size "+partnerRegistryList.size());
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
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public PartnerRegistry preparePartnerRegistry(PartnerRegistry partnerRegistry) {
		PartnerRegistry managedPartnerRegistry = partnerRegistryDao.merge(partnerRegistry);
		managedPartnerRegistry.setPartnerList(new ArrayList<Partner>(managedPartnerRegistry.getPartners()));
		managedPartnerRegistry.setAddressList(new ArrayList<Address>(managedPartnerRegistry.getAddresses()));
    	for (Address address: managedPartnerRegistry.getAddresses()) {
    		if (address.getAddressType()==AddressType.MAIN.getValue()) {
    			managedPartnerRegistry.setMainAddress(address);
    		}
    	}
		managedPartnerRegistry.setPartnerRegistryKeyList(new ArrayList<PartnerRegistryKey>(managedPartnerRegistry.getPartnerRegistryKeys()));
		partnerRegistryDao.evict(managedPartnerRegistry);
		return managedPartnerRegistry;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public PartnerRegistry storePartnerRegistry(PartnerRegistry partnerRegistry) {
		return partnerRegistryDao.merge(partnerRegistry);
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public void removePartnerRegistry(PartnerRegistry partnerRegistry) {
    	partnerRegistryDao.remove(partnerRegistry);
    }

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<? extends Partner> findPartners(PartnerFilter partnerFilter) {
		List<Partner> partnerList = (List<Partner>) partnerDao.find(partnerFilter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size "+partnerList.size());
    	}
		return partnerList;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Partner storePartner(Partner partner) {
		return partnerDao.merge(partner);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void removePartner(Partner partner) {
		partnerDao.remove(partner);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Address storeAddress(Address address) {
		return addressDao.merge(address);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public PartnerRegistry removeAddress(Address address) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public PartnerKey storePartnerKey(PartnerKey partnerKey) {
		return partnerKeyDao.merge(partnerKey);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public PartnerRegistry removePartnerKey(PartnerKey partnerKey) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Phone storePhone(Phone phone) {
		return phoneDao.merge(phone);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public PartnerRegistry removePhone(Phone phone) {
		throw new IllegalArgumentException("Not yet implemented");
	}

    //- collaborators
    
    private FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
    private FilterDao<Partner, PartnerFilter> partnerDao;
    private BasicDao<Address> addressDao;
    private BasicDao<PartnerKey> partnerKeyDao;
    private BasicDao<Phone> phoneDao;

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
    
    private Log logger = LogFactory.getLog(PartnerMgrImpl.class);

}
