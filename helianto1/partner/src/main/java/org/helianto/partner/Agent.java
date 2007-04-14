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

package org.helianto.partner;

import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p>
 * Represents a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_agent")
public class Agent extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private float agentComission;

    public Agent() {
    }

    /**
     * AgentComission getter.
     */
    public float getAgentComission() {
        return agentComission;
    }

    /**
     * AgentComission setter.
     */
    public void setAgentComission(float agentComission) {
        this.agentComission = agentComission;
    }

    /**
     * <code>Agent</code> factory.
     * 
     * @param partnerAssociation
     * @param sequence
     */
    public static Agent agentFactory(PartnerAssociation partnerAssociation, int sequence) {
        Agent agent = new Agent();
        agent.setPartnerAssociation(partnerAssociation);
        agent.setSequence(sequence);
        partnerAssociation.getPartners().add(agent);
        return agent;
    }

    /**
     * <code>Agent</code> natural id query.
     */
    @Transient
    public static String getAgentNaturalIdQueryString() {
        return "select agent from Agent agent where agent.partnerAssociation = ? and agent.sequence = ? ";
    }

}


