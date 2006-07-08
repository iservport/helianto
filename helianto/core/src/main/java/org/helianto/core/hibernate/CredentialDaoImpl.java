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

package org.helianto.core.hibernate;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.dao.CredentialDao;
import org.springframework.util.Assert;

/**
 * Hibernate implementation for <code>CredentialDao</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CredentialDaoImpl extends GenericDaoImpl implements CredentialDao {

	/*
	 * Persist, remove and find identity
	 */
	public void persistIdentity(Identity identity) {
		Assert.notNull(identity);
        merge(identity);
    }

    public void removeIdentity(Identity identity) {
        remove(identity);
    }

    public Identity findIdentityByPrincipal(String principal) {
        Identity identity = (Identity) findUnique(IDENTITY_QRY, principal);
        return identity;
    }

    static final String IDENTITY_QRY = 
        "from Identity identity " +
        "where identity.principal = ?";
    
	/*
	 * Persist, remove and find credential
	 */
    public void persistCredential(Credential credential) {
    	Assert.notNull(credential);
        merge(credential);
    }

    public void removeCredential(Credential credential) {
    	Assert.notNull(credential);
        remove(credential);
    }

    public Credential findCredentialByIdentity(Identity requiredIdentity) {
    	Assert.notNull(requiredIdentity, "An identity is required");
        return (Credential) findUnique(CREDENTIAL_QRY, requiredIdentity.getId());
    }
        
    static final String CREDENTIAL_QRY = 
        "from Credential credential " +
        "where credential.identity.id = ?";
    
}
