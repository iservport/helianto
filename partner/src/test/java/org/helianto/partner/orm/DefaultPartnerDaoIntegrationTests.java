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


package org.helianto.partner.orm;

import org.helianto.core.dao.BasicDao;
import org.helianto.partner.Partner;
import org.helianto.partner.test.PartnerTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerDaoIntegrationTests extends AbstractPartnerDaoImplConfig {
	
	public DefaultPartnerDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		Partner partner = PartnerTestSupport.createPartner();
		partnerDao.persist(partner);
		assertEquals(partner, partnerDao.findUnique(partner.getPartnerRegistry(), 'P'));
	}

    //- collabs

    private BasicDao<Partner> partnerDao;
    
    public void setPartnerDao(BasicDao<Partner> partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}