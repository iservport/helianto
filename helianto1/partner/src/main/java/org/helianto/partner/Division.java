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
 * Represents a division inside a organization. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("D")
public class Division extends Partner implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param customer
     */
    public static Division divisionFactory(PartnerRegistry partnerRegistry) {
        return internalPartnerFactory(Division.class, partnerRegistry);
    }

    private static final long serialVersionUID = 1L;
    private char divisionType;

	/**
     * Default constructor.
     */
    public Division() {
    	super();
    }

	/**
     * Division type.
     */
    public char getDivisionType() {
		return divisionType;
	}
	public void setDivisionType(char divisionType) {
		this.divisionType = divisionType;
	}
	public void setDivisionType(DivisionType divisionType) {
		this.divisionType = divisionType.getValue();
	}

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Division) ) return false;
         return super.equals(other);
   }
   
}


