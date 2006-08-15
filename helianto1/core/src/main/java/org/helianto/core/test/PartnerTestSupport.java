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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.PartnerData;
import org.helianto.core.Partner;
import org.helianto.core.Province;
import org.helianto.core.Supplier;
import org.helianto.core.creation.PartnerCreator;
import org.helianto.core.dao.PartnerDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PartnerTestSupport extends AbstractIntegrationTest {

    private static int testKey = 1;

    public static PartnerData createPartnerData(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        Province province = OperatorTestSupport.createProvince();
        PartnerData partnerData = PartnerCreator.partnerDataFactory(entity, province, generateKey(20, testKey ++));
        return partnerData;
    }

    public static PartnerData createAndPersistPartnerData(PartnerDao partnerDao) {
        PartnerData partnerData = createPartnerData();
        if (partnerDao!=null) {
            partnerDao.persistPartnerData(partnerData);
        }
        return partnerData;
    }

    public static List<PartnerData> createAndPersistPartnerDataList(HibernateTemplate hibernateTemplate, int i, int e) {
        List<PartnerData> partnerDataList = createPartnerDataList(i, e);
        for (PartnerData d: partnerDataList) {
            hibernateTemplate.merge(d);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return partnerDataList;
    }
    
    public static List<PartnerData> createPartnerDataList(int size, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        return createPartnerDataList(size, entityList);
    }
    
    public static List<PartnerData> createPartnerDataList(int size, List<Entity> entityList) {
        List<PartnerData> partnerDataList = new ArrayList<PartnerData>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                partnerDataList.add(createPartnerData(e));
            }
        }
        return partnerDataList ;
    }
    
    private static PartnerData resolvePartnerData(Object... args) {
        try {
            return (PartnerData) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            return createPartnerData();
        }
    }

    public static Partner createPartner(Object... args) {
        Partner partner = PartnerCreator.partnerFactory(resolvePartnerData(args));
        logger.info("+++ "+partner);
        return partner;
    }

    public static Customer createCustomer(Object... args) {
        Customer customer = PartnerCreator.customerFactory(resolvePartnerData(args));
        logger.info("+++ "+customer);
        return customer;
    }

    public static Supplier createSupplier(Object... args) {
        Supplier supplier = PartnerCreator.supplierFactory(resolvePartnerData(args));
        logger.info("+++ "+supplier);
        return supplier;
    }

    public static Division createDivision(Object... args) {
        Division division = PartnerCreator.divisionFactory(resolvePartnerData(args));
        logger.info("+++ "+division);
        return division;
    }

    public static Bank createBank(Object... args) {
        Bank bank = PartnerCreator.bankFactory(resolvePartnerData(args));
        logger.info("+++ "+bank);
        return bank;
    }

    public static Agent createAgent(Object... args) {
        Agent agent = PartnerCreator.agentFactory(resolvePartnerData(args));
        logger.info("+++ "+agent);
        return agent;
    }

    public static Manufacturer createManufacturer(Object... args) {
        Manufacturer manufacturer = PartnerCreator.manufacturerFactory(resolvePartnerData(args));
        logger.info("+++ "+manufacturer);
        return manufacturer;
    }

    public static Partner createAndPersistPartner(PartnerDao partnerDao) {
        Partner partner = createPartner();
        if (partnerDao!=null) {
            partnerDao.persistPartner(partner);
        }
        return partner;
    }

    public static Customer createAndPersistCustomer(PartnerDao partnerDao) {
        Customer customer = createCustomer();
        if (partnerDao!=null) {
            partnerDao.persistPartner(customer);
        }
        return customer;
    }

    public static Supplier createAndPersistSupplier(PartnerDao partnerDao) {
        Supplier supplier = createSupplier();
        if (partnerDao!=null) {
            partnerDao.persistPartner(supplier);
        }
        return supplier;
    }

    public static Division createAndPersistDivision(PartnerDao partnerDao) {
        Division division = createDivision();
        if (partnerDao!=null) {
            partnerDao.persistPartner(division);
        }
        return division;
    }

    public static Bank createAndPersistBank(PartnerDao partnerDao) {
        Bank bank = createBank();
        if (partnerDao!=null) {
            partnerDao.persistPartner(bank);
        }
        return bank;
    }

    public static Agent createAndPersistAgent(PartnerDao partnerDao) {
        Agent agent = createAgent();
        if (partnerDao!=null) {
            partnerDao.persistPartner(agent);
        }
        return agent;
    }

    public static Manufacturer createAndPersistManufacturer(PartnerDao partnerDao) {
        Manufacturer manufacturer = createManufacturer();
        if (partnerDao!=null) {
            partnerDao.persistPartner(manufacturer);
        }
        return manufacturer;
    }

    public static List<Partner> createAndPersistPartnerList(HibernateTemplate hibernateTemplate, int i, int p, int e) {
        List<Partner> partnerList = createPartnerList(i, p, e);
        for (Partner x: partnerList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return partnerList;
    }
    
    public static List<Partner> createPartnerList(int size, List<PartnerData> partnerDataList) {
        List<Partner> partnerList = new ArrayList<Partner>();
        for (PartnerData d: partnerDataList) {
            for (int i=0;i<size;i++) {
                partnerList.add(PartnerCreator.partnerFactory(d));
            }
        }
        return partnerList ;
    }

    public static List<Partner> createPartnerList(int size, int partnerDataListSize, int entityListSize) {
        List<PartnerData> partnerDataList = createPartnerDataList(partnerDataListSize, entityListSize);
        return createPartnerList(size, partnerDataList);
    }

}
