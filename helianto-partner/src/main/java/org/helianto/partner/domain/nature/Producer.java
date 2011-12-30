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

import org.helianto.core.Entity;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;


/**
 * Represents a relationship to a Producer, likely a rural producer (farmer). 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("R")
public class Producer extends Partner {

    private static final long serialVersionUID = 1L;

	/**
	 *  Empty constructor
	 */
    public Producer() {
    	super();
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public Producer(PrivateEntity partnerRegistry) {
    	this();
    	setPrivateEntity(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Producer(Entity entity, String partnerAlias) {
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
    public Producer(Partner partner) {
    	this(partner.getPrivateEntity());
    	if (partner.getClass().isAssignableFrom(getClass())) {
    		throw new IllegalArgumentException("Not allowed to create a partner from this source.");
    	}
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof Producer) ) return false;
         return super.equals(other);
   }
   
}


