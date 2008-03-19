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
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.dao.AccountDao;
import org.helianto.partner.dao.AddressDao;
import org.helianto.partner.dao.AgentDao;
import org.helianto.partner.dao.ContactDao;
import org.helianto.partner.dao.CustomerDao;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.dao.PartnerKeyDao;
import org.helianto.partner.dao.PartnerRegistryDao;
import org.helianto.partner.dao.PhoneDao;
import org.helianto.partner.dao.SupplierDao;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImpl implements PartnerMgr {

    private ProvinceDao provinceDao;
    private AddressDao addressDao;
    private ContactDao contactDao;
    private PartnerRegistryDao partnerRegistryDao;
    private PartnerDao partnerDao;
    private PartnerKeyDao partnerKeyDao;
    private PhoneDao phoneDao;
    private AccountDao accountDao;
    private AgentDao agentDao;
    private CustomerDao customerDao;
    private SupplierDao supplierDao;

    public List<Province> findProvinceByOperator(Operator operator) {
        return provinceDao.findProvinceByOperator(operator);
    }

    public void writePartnerRegistry(PartnerRegistry partnerRegistry) {
        // TODO Auto-generated method stub
        
    }

    public void removePartnerRegistry(PartnerRegistry partnerRegistry) {
        // TODO Auto-generated method stub
        
    }

    public List<PartnerRegistry> findPartnerRegistry(String partnerAssociationSearchString) {
        // TODO Auto-generated method stub
        return null;
    }

    //- collaborators
    
    @Resource
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }
    @Resource
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
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
    @Resource
    public void setPartnerRegistryDao(PartnerRegistryDao partnerRegistryDao) {
        this.partnerRegistryDao = partnerRegistryDao;
    }
    @Resource
    public void setPartnerDao(PartnerDao partnerDao) {
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
    @Resource
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
    
    private Log logger = LogFactory.getLog(PartnerMgrImpl.class);

}
