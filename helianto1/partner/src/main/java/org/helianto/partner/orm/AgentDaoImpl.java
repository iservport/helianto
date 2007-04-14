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

import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.Agent;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.dao.AgentDao;

/**
 * Default implementation of <code>Agent</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AgentDaoImpl extends GenericDaoImpl implements AgentDao {
     
    public Agent findAgentByNaturalId(PartnerAssociation partnerAssociation, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique agent with partnerAssociation='"+partnerAssociation+"' and sequence='"+sequence+"' ");
        }
        return (Agent) findUnique(Agent.getAgentNaturalIdQueryString(), partnerAssociation, sequence);
    }
    
    
}
