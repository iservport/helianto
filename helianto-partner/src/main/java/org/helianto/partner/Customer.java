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

import org.helianto.core.Entity;


/**
 * <p>
 * Represents a relationship to a Customer. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class Customer extends Partner implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param customer
     */
    public static Customer customerFactory(PartnerRegistry partnerRegistry) {
        return internalPartnerFactory(Customer.class, partnerRegistry);
    }

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Customer() {
    	super();
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public Customer(PartnerRegistry partnerRegistry) {
    	this();
    	setPartnerRegistry(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Customer(Entity entity, String partnerAlias) {
    	this();
    	setPartnerRegistry(new PartnerRegistry(entity, partnerAlias));
    }

    /**
     * Entity constructor.
     * 
	 * <p>
	 * Create a backing {@link PartnerRegistry} and associate a new Customer to it.
	 * </p>
	 * 
     * @param entity
     */
    public Customer(Entity entity) {
    	this(new PartnerRegistry(entity));
    }

    /**
     * Partner constructor.
     * 
	 * <p>
	 * Read the backing {@link PartnerRegistry} from a partner to associate the new instance to it.
	 * </p>
	 * 
     * @param partner
     */
    public Customer(Partner partner) {
    	this(partner.getPartnerRegistry());
    	if (partner instanceof Customer) {
    		throw new IllegalArgumentException("Not allowed to create a partner from this source.");
    	}
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Customer) ) return false;
         return super.equals(other);
   }
   
}


