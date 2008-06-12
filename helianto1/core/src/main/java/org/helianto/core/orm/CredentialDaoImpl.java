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

package org.helianto.core.orm;

import org.helianto.core.Credential;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.hibernate.GenericDaoImpl;



import org.helianto.core.Identity;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>Credential</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("credentialDao")
public class CredentialDaoImpl extends GenericDaoImpl implements CredentialDao {
     
    public void persistCredential(Credential credential) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+credential);
        }
        persist(credential);
    }
    
    public Credential mergeCredential(Credential credential) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+credential);
        }
        return (Credential) merge(credential);
    }
    
    public void removeCredential(Credential credential) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+credential);
        }
        remove(credential);
    }
    
    public Credential findCredentialByNaturalId(Identity identity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique credential with identity='"+identity+"' ");
        }
        return (Credential) findUnique(Credential.getCredentialNaturalIdQueryString(), identity);
    }
    
    public Credential findCredentialByPrincipal(String principal) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique credential with principal='"+principal+"' ");
        }
        return (Credential) findUnique(Credential.getCredentialPrincipalQueryString(), principal);
    }
    
}
