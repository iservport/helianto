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

import static org.helianto.core.creation.UniqueKeyInfo.ENTITY_ALIAS_LENGTH;
import static org.helianto.core.creation.UniqueKeyInfo.PARTNER_ALIAS_LENGTH;

import java.util.List;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Customer;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Supplier;
import org.helianto.core.creation.PartnerCreator;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.dao.PartnerDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;

public class PartnerDaoImplTests extends AbstractIntegrationTest {
    
    // class under test
    private PartnerDao partnerDao;
    
    // test size
    int testSize = 20;
    
    public void testCreateCustomer() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Customer customer = partnerCreator.customerFactory(entity, alias);
        partnerDao.persistCustomer(customer);
        hibernateTemplate.flush();
        List<Customer> customerList = partnerDao.findCustomerByEntity(entity);
        assertEquals(1, customerList.size());
        assertEquals(alias, customerList.get(0).getAlias());
        
        // create many
        for (int i = 1;i<testSize;i++) {
            partnerDao.persistCustomer(partnerCreator.customerFactory(entity, generateKey(PARTNER_ALIAS_LENGTH)));
        }
        hibernateTemplate.flush();
        customerList = partnerDao.findCustomerByEntity(entity);
        assertEquals(testSize, customerList.size());
        for (Customer c: customerList) {
            assertEquals(entity, c.getEntity());
        }
    }
    
    public void testDuplicateCustomer() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Customer customer = partnerCreator.customerFactory(entity, alias);
        partnerDao.persistCustomer(customer);
        hibernateTemplate.flush();
        
        // create second
        Customer duplicate = partnerCreator.customerFactory(entity, alias);
        
        try {
            partnerDao.persistCustomer(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateSupplier() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Supplier supplier = partnerCreator.supplierFactory(entity, alias);
        partnerDao.persistSupplier(supplier);
        hibernateTemplate.flush();
        List<Supplier> supplierList = partnerDao.findSupplierByEntity(entity);
        assertEquals(1, supplierList.size());
        assertEquals(alias, supplierList.get(0).getAlias());
        
        // create many
        for (int i = 1;i<testSize;i++) {
            partnerDao.persistSupplier(partnerCreator.supplierFactory(entity, generateKey(PARTNER_ALIAS_LENGTH)));
        }
        hibernateTemplate.flush();
        supplierList = partnerDao.findSupplierByEntity(entity);
        assertEquals(testSize, supplierList.size());
        for (Supplier s: supplierList) {
            assertEquals(entity, s.getEntity());
        }
    }
    
    public void testDuplicateSupplier() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Supplier supplier = partnerCreator.supplierFactory(entity, alias);
        partnerDao.persistSupplier(supplier);
        hibernateTemplate.flush();
        
        // create second
        Supplier duplicate = partnerCreator.supplierFactory(entity, alias);
        
        try {
            partnerDao.persistSupplier(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateDivision() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Division division = partnerCreator.divisionFactory(entity, alias);
        partnerDao.persistDivision(division);
        hibernateTemplate.flush();
        List<Division> divisionList = partnerDao.findDivisionByEntity(entity);
        assertEquals(1, divisionList.size());
        assertEquals(alias, divisionList.get(0).getAlias());
        
        // create many
        for (int i = 1;i<testSize;i++) {
            partnerDao.persistDivision(partnerCreator.divisionFactory(entity, generateKey(PARTNER_ALIAS_LENGTH)));
        }
        hibernateTemplate.flush();
        divisionList = partnerDao.findDivisionByEntity(entity);
        assertEquals(testSize, divisionList.size());
        for (Division d: divisionList) {
            assertEquals(entity, d.getEntity());
        }
    }
    
    public void testDuplicateDivision() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Division division = partnerCreator.divisionFactory(entity, alias);
        partnerDao.persistDivision(division);
        hibernateTemplate.flush();
        
        // create second
        Division duplicate = partnerCreator.divisionFactory(entity, alias);
        
        try {
            partnerDao.persistDivision(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateBank() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Bank bank = partnerCreator.bankFactory(entity, alias);
        partnerDao.persistBank(bank);
        hibernateTemplate.flush();
        List<Bank> bankList = partnerDao.findBankByEntity(entity);
        assertEquals(1, bankList.size());
        assertEquals(alias, bankList.get(0).getAlias());
        
        // create many
        for (int i = 1;i<testSize;i++) {
            partnerDao.persistBank(partnerCreator.bankFactory(entity, generateKey(PARTNER_ALIAS_LENGTH)));
        }
        hibernateTemplate.flush();
        bankList = partnerDao.findBankByEntity(entity);
        assertEquals(testSize, bankList.size());
        for (Bank b: bankList) {
            assertEquals(entity, b.getEntity());
        }
    }
    
    public void testDuplicateBank() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Bank bank = partnerCreator.bankFactory(entity, alias);
        partnerDao.persistBank(bank);
        hibernateTemplate.flush();
        
        // create second
        Bank duplicate = partnerCreator.bankFactory(entity, alias);
        
        try {
            partnerDao.persistBank(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateAgent() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Agent agent = partnerCreator.agentFactory(entity, alias);
        partnerDao.persistAgent(agent);
        hibernateTemplate.flush();
        List<Agent> agentList = partnerDao.findAgentByEntity(entity);
        assertEquals(1, agentList.size());
        assertEquals(alias, agentList.get(0).getAlias());
        
        // create many
        for (int i = 1;i<testSize;i++) {
            partnerDao.persistAgent(partnerCreator.agentFactory(entity, generateKey(PARTNER_ALIAS_LENGTH)));
        }
        hibernateTemplate.flush();
        agentList = partnerDao.findAgentByEntity(entity);
        assertEquals(testSize, agentList.size());
        for (Agent a: agentList) {
            assertEquals(entity, a.getEntity());
        }
    }
    
    public void testDuplicateAgent() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Agent agent = partnerCreator.agentFactory(entity, alias);
        partnerDao.persistAgent(agent);
        hibernateTemplate.flush();
        
        // create second
        Agent duplicate = partnerCreator.agentFactory(entity, alias);
        
        try {
            partnerDao.persistAgent(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    public void testCreateManufacturer() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Manufacturer manufacturer = partnerCreator.manufacturerFactory(entity, alias);
        partnerDao.persistManufacturer(manufacturer);
        hibernateTemplate.flush();
        List<Manufacturer> manufacturerList = partnerDao.findManufacturerByEntity(entity);
        assertEquals(1, manufacturerList.size());
        assertEquals(alias, manufacturerList.get(0).getAlias());
        
        // create many
        for (int i = 1;i<testSize;i++) {
            partnerDao.persistManufacturer(partnerCreator.manufacturerFactory(entity, generateKey(PARTNER_ALIAS_LENGTH)));
        }
        hibernateTemplate.flush();
        manufacturerList = partnerDao.findManufacturerByEntity(entity);
        assertEquals(testSize, manufacturerList.size());
        for (Manufacturer m: manufacturerList) {
            assertEquals(entity, m.getEntity());
        }
    }
    
    public void testDuplicateManufacturer() {
        
        //create one
        String alias = generateKey(PARTNER_ALIAS_LENGTH);
        Manufacturer manufacturer = partnerCreator.manufacturerFactory(entity, alias);
        partnerDao.persistManufacturer(manufacturer);
        hibernateTemplate.flush();
        
        // create second
        Manufacturer duplicate = partnerCreator.manufacturerFactory(entity, alias);
        
        try {
            partnerDao.persistManufacturer(duplicate);
            fail();
        } catch (DataIntegrityViolationException e) {
            logger.info("Expected exception");
        }
        
    }
    
    //
    
    private Entity entity;
    private PartnerCreator partnerCreator;
    private EntityDao entityDao;

    @Override
    public void onSetUpInTransaction() {
        DefaultEntity defaultEntity = partnerCreator.defaultEntityFactory(generateKey(ENTITY_ALIAS_LENGTH));
        entityDao.persistDefaultEntity(defaultEntity);
        entity = defaultEntity.getEntity();
    }

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    public void setPartnerCreator(PartnerCreator partnerCreator) {
        this.partnerCreator = partnerCreator;
    }

}
