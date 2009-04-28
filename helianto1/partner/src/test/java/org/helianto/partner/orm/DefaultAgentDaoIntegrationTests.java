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
import org.helianto.partner.Agent;
import org.helianto.partner.Partner;
import org.helianto.partner.test.AgentTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultAgentDaoIntegrationTests extends AbstractPartnerDaoImplConfig {
	
	public DefaultAgentDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		Agent agent = AgentTestSupport.createAgent();
		partnerDao.persist(agent);
		assertEquals(agent, partnerDao.findUnique(agent.getPartnerRegistry(), 'A'));
	}

    //- collabs

    private BasicDao<Partner> partnerDao;
    
    public void setPartnerDao(BasicDao<Partner> partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}