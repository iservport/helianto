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

import org.helianto.core.domain.Entity;
import org.helianto.partner.def.DivisionType;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;


/**
 * Represents a division inside a organization. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("D")
public class Division 
	extends Partner
{

    private static final long serialVersionUID = 1L;
    
    private char divisionType = DivisionType.HEADQUARTER.getValue();

    /**
     * <<Transient>> Discriminator.
     */
//    @Transient
    public char getDiscriminator() {
    	return 'D';
    }

	/**
     * Default constructor.
     */
    public Division() {
    	super();
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public Division(PrivateEntity partnerRegistry) {
    	this();
    	setPrivateEntity(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Division(Entity entity, String partnerAlias) {
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
    public Division(Partner partner) {
    	this(partner.getPrivateEntity());
    	if (partner.getClass().isAssignableFrom(getClass())) {
    		throw new IllegalArgumentException("Not allowed to create a partner from this source.");
    	}
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


