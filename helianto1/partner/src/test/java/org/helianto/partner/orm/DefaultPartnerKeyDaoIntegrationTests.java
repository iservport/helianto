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
import org.helianto.partner.PartnerKey;
import org.helianto.partner.test.PartnerKeyTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerKeyDaoIntegrationTests extends AbstractPartnerDaoImplConfig {
	
	public DefaultPartnerKeyDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		PartnerKey partnerKey = PartnerKeyTestSupport.createPartnerKey();
		partnerKeyDao.persist(partnerKey);
		assertEquals(partnerKey, partnerKeyDao.findUnique(partnerKey.getPartnerRegistry(), partnerKey.getKeyType()));
	}

    //- collabs

    private BasicDao<PartnerKey> partnerKeyDao;
    
    public void setPartnerKeyDao(BasicDao<PartnerKey> partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    
}