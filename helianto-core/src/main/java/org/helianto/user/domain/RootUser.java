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

package org.helianto.user.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.user.def.UserType;
/**
 * The root user account.
 * 
 * @author Mauricio Fernandes de Castro
 * 			
 */
@javax.persistence.Entity
@DiscriminatorValue("R")
public class RootUser 
	extends User 
{

	/**
	 * <<Transient>> Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'R';
	}

    private static final long serialVersionUID = 1L;

	/** 
	 * Empty constructor.
	 */
    public RootUser() {
    	super();
    	setUserType(UserType.SYSTEM.getValue());
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param credential
	 */
    public RootUser(Entity entity, Identity identity) {
    	this();
        setEntity(entity);
    	setIdentity(identity);
    }

	/** 
	 * Credential constructor.
	 * 
	 * <p>
	 * The credential is not used after its principal is read,
	 * although is here to force previous creation.
	 * </p>
	 * 
	 * @param entity
	 * @param credential
	 */
    public RootUser(Entity entity, Credential credential) {
    	this(entity, credential.getIdentity());
    }

	/** 
	 * Parent constructor.
	 * 
	 * @param parent
	 * @param childCredential
	 */
    public RootUser(UserGroup parent, Credential childCredential) {
    	this(parent.getEntity(), childCredential);
    	parent.getChildAssociations().add(new UserAssociation(parent, childCredential));
    }

}
