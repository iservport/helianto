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

package org.helianto.core;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;
/**
 * A contact group.
 * 
 * @author Mauricio Fernandes de Castro
 * 			
 */
@javax.persistence.Entity
@DiscriminatorValue("C")
public class ContactGroup extends UserGroup {

	/**
	 * <<Transient>> Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'C';
	}

    private static final long serialVersionUID = 1L;

	/** 
	 * Empty constructor.
	 */
    public ContactGroup() {
    	super();
    }

	/** 
	 * Entity constructor.
	 * 
	 * @param entity
	 */
    public ContactGroup(Entity entity) {
    	this();
    	setEntity(entity);
    }

	/** 
	 * User group constructor.
	 * 
	 * @param entity
	 * @param userKey
	 */
    public ContactGroup(Entity entity, String userKey) {
    	this(entity);
    	setUserKey(userKey);
    }

}
