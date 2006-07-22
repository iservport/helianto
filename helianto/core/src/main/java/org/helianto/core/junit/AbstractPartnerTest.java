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

package org.helianto.core.junit;

import static org.helianto.core.type.UniqueKeyInfo.PARTNER_ALIAS_LENGTH;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.core.PartnerRole;
import org.helianto.core.creation.PartnerCreator;
import org.helianto.core.dao.PartnerDao;
import org.helianto.core.hibernate.EntityDaoImplTests;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AbstractPartnerTest extends AbstractIntegrationTest {

    public static Partner createAndPersistPartner(PartnerDao partnerDao) {
        Entity entity = EntityDaoImplTests.createAndPersistEntity(null);
        Partner partner = PartnerCreator.partnerFactory(entity, generateKey(PARTNER_ALIAS_LENGTH));
        if (partnerDao!=null) {
            partnerDao.persistPartner(partner);
        }
        return partner;
    }

    public static List<Partner> createAndPersistPartnerList(HibernateTemplate hibernateTemplate, int i, int e) {
        List<Partner> partnerList = createPartnerList(i, e);
        hibernateTemplate.saveOrUpdateAll(partnerList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return partnerList;
    }
    
    public static List<Partner> createPartnerList(int size, int entityListSize) {
        List<Entity> entityList = EntityDaoImplTests.createEntityList(entityListSize);
        return createPartnerList(size, entityList);
    }
    
    public static List<Partner> createPartnerList(int size, List<Entity> entityList) {
        List<Partner> partnerList = new ArrayList<Partner>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                partnerList.add(PartnerCreator.partnerFactory(e, generateKey(PARTNER_ALIAS_LENGTH, i)));
            }
        }
        return partnerList ;
    }

    public static PartnerRole createAndPersistPartnerRole(PartnerDao partnerDao) {
        Partner partner = createAndPersistPartner(partnerDao);
        PartnerRole partnerRole = PartnerCreator.partnerRoleFactory(partner);
        if (partnerDao!=null) {
            partnerDao.persistPartnerRole(partnerRole);
        }
        return partnerRole;
    }

    public static List<PartnerRole> createAndPersistPartnerRoleList(HibernateTemplate hibernateTemplate, int i, int p) {
        List<PartnerRole> partnerRoleList = createPartnerRoleList(i, p);
        hibernateTemplate.saveOrUpdateAll(partnerRoleList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return partnerRoleList;
    }
    
    public static List<PartnerRole> createPartnerRoleList(int size, List<Partner> partnerList) {
        List<PartnerRole> partnerRoleList = new ArrayList<PartnerRole>();
        for (Partner p: partnerList) {
            for (int i=0;i<size;i++) {
                partnerRoleList.add(PartnerCreator.partnerRoleFactory(p));
            }
        }
        return partnerRoleList ;
    }

    public static List<PartnerRole> createPartnerRoleList(int size, int partnerListSize) {
        int e = 2; //entities
        List<Partner> partnerList = createPartnerList(partnerListSize, e);
        return createPartnerRoleList(size, partnerList);
    }

}
