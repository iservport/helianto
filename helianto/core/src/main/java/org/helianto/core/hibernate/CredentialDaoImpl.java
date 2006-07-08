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

/**
 * Hibernate implementation for <code>CredentialDao</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CredentialDaoImpl extends GenericDaoImpl implements CredentialDao {

    public void persistIdentity(Identity identity) {
        merge(identity);
    }

    public void removeIdentity(Identity identity) {
        remove(identity);
    }

    public void persistCredential(Credential credential) {
        merge(credential);
    }

    public void removeCredential(Credential credential) {
        remove(credential);
    }

    public Identity findIdentityByPrincipal(String principal) {
        Identity identity = (Identity) findUnique(IDENTITY_QRY, principal);
        if (logger.isDebugEnabled()) {
            logger.debug("Found identity "+identity);
        }
        return identity;
    }

    static final String IDENTITY_QRY = 
        "from Identity identity " +
        "where identity.principal = ?";
    
    public int countIdentityByPrincipal(String principal) {
        return find(IDENTITY_QRY, principal).size();
    }

    public Credential findCredentialByIdentity(Identity identity) {
        logger.info("*** Finding "+identity);
        return (Credential) findUnique(CREDENTIAL_QRY, identity.getId());
    }
        
    static final String CREDENTIAL_QRY = 
        "from Credential credential " +
        "where credential.identity.id = ?";
    
}
