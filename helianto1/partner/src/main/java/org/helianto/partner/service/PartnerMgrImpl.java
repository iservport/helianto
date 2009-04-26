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

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.filter.SelectionStrategy;
import org.helianto.partner.Address;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;
import org.helianto.partner.dao.AccountDao;
import org.helianto.partner.dao.AddressDao;
import org.helianto.partner.dao.AgentDao;
import org.helianto.partner.dao.ContactDao;
import org.helianto.partner.dao.CustomerDao;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.dao.PartnerKeyDao;
import org.helianto.partner.dao.PhoneDao;
import org.helianto.partner.dao.SupplierDao;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImpl implements PartnerMgr {

	public List<PartnerRegistry> findPartnerRegistries(PartnerRegistryFilter partnerRegistryFilter) {
		List<PartnerRegistry> partnerRegistryList = (List<PartnerRegistry>) partnerRegistryDao.find(partnerRegistryFilter);
    	if (logger.isDebugEnabled() && partnerRegistryList!=null) {
    		logger.debug("Found partner registry list of size "+partnerRegistryList.size());
    	}
		return partnerRegistryList;
	}

	public PartnerRegistry storePartnerRegistry(PartnerRegistry partnerRegistry) {
		return partnerRegistryDao.merge(partnerRegistry);
	}
	
    public void removePartnerRegistry(PartnerRegistry partnerRegistry) {
    	partnerRegistryDao.remove(partnerRegistry);
    }

	public List<? extends Partner> findPartners(PartnerFilter partnerFilter) {
		String criteria = partnerSelectionStrategy.createCriteriaAsString(partnerFilter, "partner");
		List<Partner> partnerList = partnerDao.findPartners(criteria);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size "+partnerList.size());
    	}
		return partnerList;
	}

	public Partner storePartner(Partner partner) {
		return partnerDao.mergePartner(partner);
	}

	public void removePartner(Partner partner) {
		partnerDao.removePartner(partner);
	}

	public Address storeAddress(Address address) {
		return addressDao.mergeAddress(address);
	}

	public PartnerRegistry removeAddress(Address address) {
		throw new IllegalArgumentException("Not yet implemented");
	}

    //- collaborators
    
    private AddressDao addressDao;
    
    private FilterDao<Province, ProvinceFilter> provinceDao;
	private ContactDao contactDao;
    private FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
	private PartnerDao partnerDao;
	private SelectionStrategy<PartnerFilter> partnerSelectionStrategy;
	
    private PartnerKeyDao partnerKeyDao;
    private PhoneDao phoneDao;
    private AccountDao accountDao;
    private AgentDao agentDao;
    private CustomerDao customerDao;
    private SupplierDao supplierDao;

    @Resource
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    @Resource(name="provinceDao")
    public void setProvinceDao(FilterDao<Province, ProvinceFilter> provinceDao) {
        this.provinceDao = provinceDao;
    }
    @Resource
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    @Resource
    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }
    @Resource
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    @Resource(name="partnerRegistryDao")
    public void setPartnerRegistryDao(FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao) {
        this.partnerRegistryDao = partnerRegistryDao;
    }

    @Resource
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
	@Resource
	public void setPartnerSelectionStrategy(SelectionStrategy<PartnerFilter> partnerSelectionStrategy) {
		this.partnerSelectionStrategy = partnerSelectionStrategy;
	}
    @Resource
    public void setPartnerKeyDao(PartnerKeyDao partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    @Resource
    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }
    @Resource
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
    
    private Log logger = LogFactory.getLog(PartnerMgrImpl.class);

}
