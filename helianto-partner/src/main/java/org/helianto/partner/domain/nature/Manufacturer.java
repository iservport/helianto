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
 * Represents a relationship to a Manufacturer. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("M")
public class Manufacturer extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * <<Transient>> Discriminator.
     */
    @Transient
    public char getDiscriminator() {
    	return 'M';
    }

    /**
     * Default constructor.
     */
    public Manufacturer() {
    	super();
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public Manufacturer(PrivateEntity partnerRegistry) {
    	this();
    	setPrivateEntity(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Manufacturer(Entity entity, String partnerAlias) {
    	this();
    	setPrivateEntity(new PrivateEntity(entity, partnerAlias));
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
    public Manufacturer(Partner partner) {
    	this(partner.getPrivateEntity());
    	if (partner.getClass().isAssignableFrom(getClass())) {
    		throw new IllegalArgumentException("Not allowed to create a partner from this source.");
    	}
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Manufacturer) ) return false;
         return super.equals(other);
   }
   
}


