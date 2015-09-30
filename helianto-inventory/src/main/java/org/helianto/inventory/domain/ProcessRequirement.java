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

package org.helianto.inventory.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.internal.AbstractRequirement;


/**
 * A base class to represent orders, invoices, quality.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_requirement",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class ProcessRequirement 
	extends AbstractRequirement {

	private static final long serialVersionUID = 1L;

	public String getInternalNumberKey() {
		return "PROCREQ";
	}
	
    public int getStartNumber() {
    	return 1;
    }

	/**
	 * Empty constructor.
	 */
	public ProcessRequirement() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	public ProcessRequirement(Entity entity, long internalNumber) {
		super();
		setEntity(entity);
		setInternalNumber(internalNumber);
	}

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ProcessRequirement) {
			return super.equals(other);
		}
		return false;
    }
    
}
