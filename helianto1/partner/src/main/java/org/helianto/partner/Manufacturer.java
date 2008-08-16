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
 * Represents a relationship to a Manufacturer. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("M")
public class Manufacturer extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public Manufacturer() {
    }

    /**
     * <code>Manufacturer</code> factory.
     * 
     * @param partnerRegistry
     */
    public static Manufacturer manufacturerFactory(PartnerRegistry partnerRegistry) {
        return internalPartnerFactory(Manufacturer.class, partnerRegistry);
    }

    /**
     * <code>Manufacturer</code> query.
     */
    @Transient
    public static StringBuilder getManufacturerQueryStringBuilder() {
    	return new StringBuilder("select manufacturer from Manufacturer manufacturer ");
    }   

    /**
     * <code>Manufacturer</code> natural id query.
     */
    @Transient
    public static String getManufacturerNaturalIdQueryString() {
    	return getManufacturerQueryStringBuilder().append("where manufacturer.partnerRegistry = ? and manufacturer.class = 'M' ").toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Manufacturer) ) return false;
         return super.equals(other);
   }
   
}


