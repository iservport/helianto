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
import javax.persistence.Transient;


/**
 * <p>
 * Represents a relationship to a Laboratory. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("L")
public class Laboratory extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public Laboratory() {
    }

    /**
     * <code>Laboratory</code> factory.
     * 
     * @param partnerRegistry
     */
    public static Laboratory laboratoryFactory(PartnerRegistry partnerRegistry) {
        return internalPartnerFactory(Laboratory.class, partnerRegistry);
    }

    /**
     * <code>Laboratory</code> query.
     */
    @Transient
    public static StringBuilder getLaboratoryQueryStringBuilder() {
    	return new StringBuilder("select laboratory from Laboratory laboratory ");
    }   

    /**
     * <code>Laboratory</code> natural id query.
     */
    @Transient
    public static String getLaboratoryNaturalIdQueryString() {
    	return getLaboratoryQueryStringBuilder().append("where laboratory.partnerRegistry = ? and laboratory.class = 'L' ").toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Laboratory) ) return false;
         return super.equals(other);
   }
   
}


