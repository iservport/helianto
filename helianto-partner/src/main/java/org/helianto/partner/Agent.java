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

import javax.persistence.DiscriminatorValue;


/**
 * <p>
 * Represents a relationship to an Agent, like a sales representative. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("A")
public class Agent extends Partner implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param partnerRegistry
     */
    public static Agent agentFactory(PartnerRegistry partnerRegistry) {
        return internalPartnerFactory(Agent.class, partnerRegistry);
    }

    private static final long serialVersionUID = 1L;
    private float agentComission;

	/**
	 *  Empty constructor
	 */
    public Agent() {
    	super();
    }

    /**
     * Agent comission.
     */
    public float getAgentComission() {
        return agentComission;
    }
    public void setAgentComission(float agentComission) {
        this.agentComission = agentComission;
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Agent) ) return false;
         return super.equals(other);
   }
   
}


