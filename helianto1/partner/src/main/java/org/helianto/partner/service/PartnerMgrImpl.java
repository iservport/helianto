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

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.dao.AccountDao;
import org.helianto.partner.dao.AddressDao;
import org.helianto.partner.dao.AgentDao;
import org.helianto.partner.dao.ContactDao;
import org.helianto.partner.dao.CustomerDao;
import org.helianto.partner.dao.PartnerAssociationDao;
import org.helianto.partner.dao.PartnerAssociationFilterDao;
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

    private ProvinceDao provinceDao;
    private AddressDao addressDao;
    private ContactDao contactDao;
    private PartnerAssociationDao partnerAssociationDao;
    private PartnerAssociationFilterDao partnerAssociationFilterDao;
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

    public void writePartnerAssociation(PartnerAssociation partnerAssociation) {
        // TODO Auto-generated method stub
        
    }

    public void removePartnerAssociation(PartnerAssociation partnerAssociation) {
        // TODO Auto-generated method stub
        
    }

    public List<PartnerAssociation> findPartnerAssociation(String partnerAssociationSearchString) {
        // TODO Auto-generated method stub
        return null;
    }

    //- collaborators
    
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    public void setPartnerAssociationDao(PartnerAssociationDao partnerAssociationDao) {
        this.partnerAssociationDao = partnerAssociationDao;
    }
    public void setPartnerAssociationFilterDao(
            PartnerAssociationFilterDao partnerAssociationFilterDao) {
        this.partnerAssociationFilterDao = partnerAssociationFilterDao;
    }
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    public void setPartnerKeyDao(PartnerKeyDao partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
    
    //- init
    
    public void init() {
        if (provinceDao==null) {
            throw new IllegalArgumentException("ProvinceDao property required");
        }
        if (addressDao==null) {
            throw new IllegalArgumentException("AddressDao property required");
        }
        if (contactDao==null) {
            throw new IllegalArgumentException("ContactDao property required");
        }
        if (partnerAssociationDao==null) {
            throw new IllegalArgumentException("PartnerAssociationDao property required");
        }
        if (partnerAssociationFilterDao==null) {
            throw new IllegalArgumentException("PartnerAssociationFilterDao property required");
        }
        if (partnerDao==null) {
            throw new IllegalArgumentException("PartnerDao property required");
        }
        if (partnerKeyDao==null) {
            throw new IllegalArgumentException("PartnerKeyDao property required");
        }
        if (phoneDao==null) {
            throw new IllegalArgumentException("PhoneDao property required");
        }
        if (accountDao==null) {
            throw new IllegalArgumentException("AccountDao property required");
        }
        if (agentDao==null) {
            throw new IllegalArgumentException("AgentDao property required");
        }
        if (customerDao==null) {
            throw new IllegalArgumentException("CustomerDao property required");
        }
        if (supplierDao==null) {
            throw new IllegalArgumentException("SupplierDao property required");
        }
    }

}
