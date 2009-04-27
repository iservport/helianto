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
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.partner.Address;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;
import org.helianto.partner.dao.AccountDao;
import org.helianto.partner.dao.ContactDao;
import org.helianto.partner.dao.PartnerKeyDao;
import org.helianto.partner.dao.PhoneDao;

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
		List<Partner> partnerList = (List<Partner>) partnerDao.find(partnerFilter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size "+partnerList.size());
    	}
		return partnerList;
	}

	public Partner storePartner(Partner partner) {
		return partnerDao.merge(partner);
	}

	public void removePartner(Partner partner) {
		partnerDao.remove(partner);
	}

	public Address storeAddress(Address address) {
		return addressDao.merge(address);
	}

	public PartnerRegistry removeAddress(Address address) {
		throw new IllegalArgumentException("Not yet implemented");
	}

    //- collaborators
    
    private BasicDao<Address> addressDao;
    private FilterDao<Province, ProvinceFilter> provinceDao;
    private FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
    private FilterDao<Partner, PartnerFilter> partnerDao;
	
	private ContactDao contactDao;
    private PartnerKeyDao partnerKeyDao;
    private PhoneDao phoneDao;

    @Resource(name="provinceDao")
    public void setProvinceDao(FilterDao<Province, ProvinceFilter> provinceDao) {
        this.provinceDao = provinceDao;
    }
    @Resource(name="addressDao")
    public void setAddressDao(BasicDao<Address> addressDao) {
        this.addressDao = addressDao;
    }

    @Resource
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
    
    @Resource(name="partnerRegistryDao")
    public void setPartnerRegistryDao(FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao) {
        this.partnerRegistryDao = partnerRegistryDao;
    }

    @Resource(name="partnerDao")
    public void setPartnerDao(FilterDao<Partner, PartnerFilter> partnerDao) {
        this.partnerDao = partnerDao;
    }

    @Resource
    public void setPartnerKeyDao(PartnerKeyDao partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    @Resource
    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }
    
    private Log logger = LogFactory.getLog(PartnerMgrImpl.class);

}
