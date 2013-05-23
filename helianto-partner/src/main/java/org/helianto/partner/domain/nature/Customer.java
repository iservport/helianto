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

package org.helianto.partner.domain.nature;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;


/**
 * <p>
 * Represents a relationship to a Customer. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class Customer extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * <<Transient>> Discriminator.
     */
    @Transient
    public char getDiscriminator() {
    	return 'C';
    }

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
    public Customer(PrivateEntity partnerRegistry) {
    	this();
    	setPrivateEntity(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Customer(Entity entity, String partnerAlias) {
    	this();
    	setPrivateEntity(new PrivateEntity(entity, partnerAlias));
    }

    /**
     * Entity constructor.
     * 
	 * <p>
	 * Create a backing {@link PrivateEntity} and associate a new Customer to it.
	 * </p>
	 * 
     * @param entity
     */
    public Customer(Entity entity) {
    	this(new PrivateEntity(entity));
    }

    /**
     * Partner constructor.
     * 
	 * <p>
	 * Read the backing {@link PrivateEntity} from a partner to associate the new instance to it.
	 * </p>
	 * 
     * @param partner
     */
    public Customer(Partner partner) {
    	this(partner.getPrivateEntity());
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


