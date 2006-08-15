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
import org.helianto.core.PartnerData;
import org.helianto.core.Supplier;
import org.helianto.core.dao.PartnerDao;
import org.helianto.core.test.PartnerTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class PartnerDaoImplTests extends PartnerTestSupport {

    // class under test
    private PartnerDao partnerDao;

    /*
     * PartnerData tests
     */

    public void testPersistPartnerData() {
        // write
        PartnerData partnerData = createAndPersistPartnerData(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(partnerData, partnerDao.findPartnerDataByNaturalId(
                partnerData.getEntity(), partnerData.getAlias()));
    }

    public void testFindPartnerData() {
        // write list
        int i = 10;
        int e = 2; // entitites
        List<PartnerData> partnerDataList = createAndPersistPartnerDataList(
                hibernateTemplate, i, e);
        assertEquals(i * e, partnerDataList.size());
        // read
        PartnerData partnerData = partnerDataList.get((int) Math.random() * i
                * e);
        List<PartnerData> list = partnerDao.findPartnerDataByEntity(partnerData
                .getEntity());
        for (PartnerData d : list) {
            assertEquals(partnerData.getEntity(), d.getEntity());
        }
    }

    public void testPartnerDataErrors() {
        try {
            partnerDao.persistPartnerData(null);
            fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testPartnerDataDuplicate() {
        // write
        PartnerData partnerData = createAndPersistPartnerData(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partnerData);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testRemovePartnerData() {
        // bulk write
        int i = 10;
        int e = 2; // entitites
        List<PartnerData> partnerDataList = createAndPersistPartnerDataList(
                hibernateTemplate, i, e);
        assertEquals(i * e, partnerDataList.size());
        // remove
        PartnerData partnerData = partnerDataList.get((int) Math.random() * i);
        partnerDao.removePartnerData(partnerData);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<PartnerData> all = (ArrayList<PartnerData>) hibernateTemplate
                .find("from PartnerData");
        assertEquals(i * e - 1, all.size());
        assertFalse(all.contains(partnerData));
    }

    /*
     * Partner tests
     */

    public void testPersistPartner() {
        // write
        Partner partner = createAndPersistPartner(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(partner, partnerDao.findPartnerByNaturalId(partner
                .getPartnerData(), partner.getSequence()));

        // write
        Customer customer = createAndPersistCustomer(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(customer, partnerDao.findPartnerByNaturalId(customer
                .getPartnerData(), customer.getSequence()));

        // write
        Supplier supplier = createAndPersistSupplier(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(supplier, partnerDao.findPartnerByNaturalId(supplier
                .getPartnerData(), supplier.getSequence()));

        // write
        Division division = createAndPersistDivision(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(division, partnerDao.findPartnerByNaturalId(division
                .getPartnerData(), division.getSequence()));

        // write
        Bank bank = createAndPersistBank(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(bank, partnerDao.findPartnerByNaturalId(bank
                .getPartnerData(), bank.getSequence()));

        // write
        Agent agent = createAndPersistAgent(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(agent, partnerDao.findPartnerByNaturalId(agent
                .getPartnerData(), agent.getSequence()));

        // write
        Manufacturer manufacturer = createAndPersistManufacturer(partnerDao);
        hibernateTemplate.flush();
        // read
        assertEquals(manufacturer, partnerDao.findPartnerByNaturalId(
                manufacturer.getPartnerData(), manufacturer.getSequence()));
    }

    public void testFindPartner() {
        // write list
        int i = 10;
        int d = 2; // partner data
        int e = 2; // entity
        List<Partner> partnerList = createAndPersistPartnerList(
                hibernateTemplate, i, d, e);
        assertEquals(i * d * e, partnerList.size());
        // read
        Partner partner = partnerList.get((int) Math.random() * i * d);
        List<Partner> list1 = partnerDao.findPartnerByEntity(partner
                .getPartnerData().getEntity());
        for (Partner p : list1) {
            assertEquals(partner.getPartnerData().getEntity(), p
                    .getPartnerData().getEntity());
        }
        List<Partner> list2 = partnerDao.findPartnerByPartnerData(partner
                .getPartnerData());
        for (Partner p : list2) {
            assertEquals(partner.getPartnerData(), p.getPartnerData());
        }
    }

    public void testPartnerErrors() {
        try {
            partnerDao.persistPartner(null);
            fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail();
        }
        try {
            partnerDao.removePartner(null);
            fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testPartnerDuplicate() {
        // write
        Partner partner = createAndPersistPartner(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testDuplicateCustomer() {
        // write
        Customer partner = createAndPersistCustomer(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testDuplicateSupplier() {
        // write
        Supplier partner = createAndPersistSupplier(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testDuplicateDivision() {
        // write
        Division partner = createAndPersistDivision(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testDuplicateBank() {
        // write
        Bank partner = createAndPersistBank(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testDuplicateAgent() {
        // write
        Agent partner = createAndPersistAgent(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testDuplicateManufacturer() {
        // write
        Manufacturer partner = createAndPersistManufacturer(partnerDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(partner);
            fail();
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            fail();
        }
    }

    public void testRemovePartner() {
        // write list
        int i = 10;
        int d = 2; // partner data
        int e = 2; // entity
        List<Partner> partnerList = createAndPersistPartnerList(
                hibernateTemplate, i, d, e);
        assertEquals(i * d * e, partnerList.size());
        // remove
        Partner partner = partnerList.get((int) Math.random() * partnerList.size());
        partnerDao.removePartner(partner);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Partner> all = (ArrayList<Partner>) hibernateTemplate
                .find("from Partner");
        assertEquals(partnerList.size() - 1, all.size());
        assertFalse(all.contains(partner));
    }

    //

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

}
