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

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.PartnerRole;
import org.helianto.core.Supplier;
import org.helianto.core.creation.PartnerCreator;
import org.helianto.core.dao.PartnerDao;
import org.helianto.core.junit.AbstractPartnerTest;
import org.springframework.dao.DataIntegrityViolationException;

public class PartnerDaoImplTests extends AbstractPartnerTest {
    
    // class under test
    private PartnerDao partnerDao;
    
    /*
     * Partner tests 
     */
    
    public void testPersistPartner() {
        //write
        Partner partner = createAndPersistPartner(partnerDao);
        hibernateTemplate.flush();
        //read
        assertEquals(partner,  partnerDao.findPartnerByEntityAndAlias(partner.getEntity(), partner.getAlias()));
    }
    
    public void testFindPartner() {
        // write list
        int i = 10;
        int e = 2; //entitites
        List<Partner> partnerList = createAndPersistPartnerList(hibernateTemplate, i, e);
        assertEquals(i*e, partnerList.size());
        // read
        Partner partner = partnerList.get((int) Math.random()*i);
//        assertEquals(partner.getEntity(),  partnerDao.findPartnerByEntity(partner.getEntity()));
    }

    public void testPartnerErrors() {
        try {
             partnerDao.persistPartner(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testPartnerDuplicate() {
        // write
        Partner partner = createAndPersistPartner(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemovePartner() {
        // bulk write
        int i = 10;
        int e = 2; //entitites
        List<Partner> partnerList = createAndPersistPartnerList(hibernateTemplate, i, e);
        assertEquals(i*e, partnerList.size());
        // remove
        Partner partner = partnerList.get((int) Math.random()*i);
        partnerDao.removePartner(partner);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Partner> all = (ArrayList<Partner>) hibernateTemplate.find("from Partner");
        assertEquals(i*e-1, all.size());
        assertFalse(all.contains(partner));
    }

    /*
     * PartnerRole tests 
     */
    
    public void testPersistPartnerRole() {
        //write
        PartnerRole partnerRole = createAndPersistPartnerRole( partnerDao);
        hibernateTemplate.flush();
        //read
//        assertEquals(partnerRole,  partnerDao.findPartnerRoleByEntity(partnerRole.getPartner().getEntity()));
//        assertEquals(partnerRole,  partnerDao.findPartnerRoleByPartner(partnerRole.getPartner()));
    }
    
    public void testFindPartnerRole() {
//        // write list
//        int i = 10;
//        int e = 2; //entities
//        List<PartnerRole> partnerRoleList = createAndPersistPartnerRoleList(hibernateTemplate, i, e);
//        assertEquals(i*e, partnerRoleList.size());
//        // read
//        PartnerRole partnerRole = partnerRoleList.get((int) Math.random()*i*e);
////        assertEquals(partnerRole,  partnerDao.findPartnerRoleByEntity(partnerRole.getPartner().getEntity()));
////        assertEquals(partnerRole,  partnerDao.findPartnerRoleByPartner(partnerRole.getPartner()));
    }

    public void testPartnerRoleErrors() {
        try {
            partnerDao.persistPartnerRole(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           partnerDao.removePartnerRole(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testPartnerRoleDuplicate() {
        // write
        PartnerRole partnerRole = createAndPersistPartnerRole(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partnerRole); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemovePartnerRole() {
//        // bulk write
//        int i = 10;
//        int e = 2; //entities
//        List<PartnerRole> partnerRoleList = createAndPersistPartnerRoleList(hibernateTemplate, i, e);
//        assertEquals(i*e, partnerRoleList.size());
//        // remove
//        PartnerRole partnerRole = partnerRoleList.get((int) Math.random()*i*e);
//        partnerDao.removePartnerRole(partnerRole);
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        // read
//        List<PartnerRole> all = (ArrayList<PartnerRole>) hibernateTemplate.find("from PartnerRole");
//        assertEquals(i*e-1, all.size());
//        assertFalse(all.contains(partnerRole));
    }


    
    
    ////////////////////
    
    public void testCreateCustomer() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Customer customer = PartnerCreator.customerFactory(partner);
        partnerDao.persistPartnerRole(customer);
        hibernateTemplate.flush();
        List<Customer> customerList = partnerDao.findCustomerByEntity(partner.getEntity());
        assertEquals(1, customerList.size());
//        assertSame(partner.getEntity(), partnerDao.findCustomerByEntity
        
        hibernateTemplate.flush();
        customerList = partnerDao.findCustomerByEntity(partner.getEntity());
        assertEquals(1, customerList.size());
        for (Customer c: customerList) {
//            assertEquals(entity, c.getEntity());
        }
    }
    
    public void testDuplicateCustomer() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Customer customer = PartnerCreator.customerFactory(partner);
        partnerDao.persistPartnerRole(customer);
        hibernateTemplate.flush();
        
        // create second
        Customer duplicate = PartnerCreator.customerFactory(partner);
        
        try {
            partnerDao.persistPartnerRole(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateSupplier() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Supplier supplier = PartnerCreator.supplierFactory(partner);
        partnerDao.persistPartnerRole(supplier);
        hibernateTemplate.flush();
        List<Supplier> supplierList = partnerDao.findSupplierByEntity(partner.getEntity());
        assertEquals(1, supplierList.size());
//        assertEquals(alias, supplierList.get(0).getAlias());
        
        hibernateTemplate.flush();
        supplierList = partnerDao.findSupplierByEntity(partner.getEntity());
        assertEquals(1, supplierList.size());
        for (Supplier s: supplierList) {
//            assertEquals(entity, s.getEntity());
        }
    }
    
    public void testDuplicateSupplier() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Supplier supplier = PartnerCreator.supplierFactory(partner);
        partnerDao.persistPartnerRole(supplier);
        hibernateTemplate.flush();
        
        // create second
        Supplier duplicate = PartnerCreator.supplierFactory(partner);
        
        try {
            partnerDao.persistPartnerRole(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateDivision() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Division division = PartnerCreator.divisionFactory(partner);
        partnerDao.persistPartnerRole(division);
        hibernateTemplate.flush();
        List<Division> divisionList = partnerDao.findDivisionByEntity(partner.getEntity());
        assertEquals(1, divisionList.size());
//        assertEquals(alias, divisionList.get(0).getAlias());
        
        hibernateTemplate.flush();
        divisionList = partnerDao.findDivisionByEntity(partner.getEntity());
        assertEquals(1, divisionList.size());
        for (Division d: divisionList) {
//            assertEquals(entity, d.getEntity());
        }
    }
    
    public void testDuplicateDivision() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Division division = PartnerCreator.divisionFactory(partner);
        partnerDao.persistPartnerRole(division);
        hibernateTemplate.flush();
        
        // create second
        Division duplicate = PartnerCreator.divisionFactory(partner);
        
        try {
            partnerDao.persistPartnerRole(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateBank() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Bank bank = PartnerCreator.bankFactory(partner);
        partnerDao.persistPartnerRole(bank);
        hibernateTemplate.flush();
        List<Bank> bankList = partnerDao.findBankByEntity(partner.getEntity());
        assertEquals(1, bankList.size());
//        assertEquals(alias, bankList.get(0).getAlias());
        
        hibernateTemplate.flush();
        bankList = partnerDao.findBankByEntity(partner.getEntity());
        assertEquals(1, bankList.size());
        for (Bank b: bankList) {
//            assertEquals(entity, b.getEntity());
        }
    }
    
    public void testDuplicateBank() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Bank bank = PartnerCreator.bankFactory(partner);
        partnerDao.persistPartnerRole(bank);
        hibernateTemplate.flush();
        
        // create second
        Bank duplicate = PartnerCreator.bankFactory(partner);
        
        try {
            partnerDao.persistPartnerRole(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateAgent() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Agent agent = PartnerCreator.agentFactory(partner);
        partnerDao.persistPartnerRole(agent);
        hibernateTemplate.flush();
        List<Agent> agentList = partnerDao.findAgentByEntity(partner.getEntity());
        assertEquals(1, agentList.size());
//        assertEquals(alias, agentList.get(0).getAlias());
        
        hibernateTemplate.flush();
        agentList = partnerDao.findAgentByEntity(partner.getEntity());
        assertEquals(1, agentList.size());
        for (Agent a: agentList) {
//            assertEquals(entity, a.getEntity());
        }
    }
    
    public void testDuplicateAgent() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Agent agent = PartnerCreator.agentFactory(partner);
        partnerDao.persistPartnerRole(agent);
        hibernateTemplate.flush();
        
        // create second
        Agent duplicate = PartnerCreator.agentFactory(partner);
        
        try {
            partnerDao.persistPartnerRole(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateManufacturer() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Manufacturer manufacturer = PartnerCreator.manufacturerFactory(partner);
        partnerDao.persistPartnerRole(manufacturer);
        hibernateTemplate.flush();
        List<Manufacturer> manufacturerList = partnerDao.findManufacturerByEntity(partner.getEntity());
        assertEquals(1, manufacturerList.size());
//        assertEquals(alias, manufacturerList.get(0).getAlias());
        
        hibernateTemplate.flush();
        manufacturerList = partnerDao.findManufacturerByEntity(partner.getEntity());
        assertEquals(1, manufacturerList.size());
        for (Manufacturer m: manufacturerList) {
//            assertEquals(entity, m.getEntity());
        }
    }
    
    public void testDuplicateManufacturer() {
        
        //create one
        Partner partner = createAndPersistPartner(partnerDao);
        Manufacturer manufacturer = PartnerCreator.manufacturerFactory(partner);
        partnerDao.persistPartnerRole(manufacturer);
        hibernateTemplate.flush();
        
        // create second
        Manufacturer duplicate = PartnerCreator.manufacturerFactory(partner);
        
        try {
            partnerDao.persistPartnerRole(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
                
    }
    
    //
    
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

}
