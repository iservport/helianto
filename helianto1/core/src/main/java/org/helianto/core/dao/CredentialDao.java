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

package org.helianto.core.dao;

import org.helianto.core.Credential;
import org.helianto.core.Identity;

/**
 * <code>Credential</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CredentialDao extends CommonOrmDao {
     
    /**
     * Persist <code>Credential</code>.
     */
    public void persistCredential(Credential credential);
    
    /**
     * Merge <code>Credential</code>.
     */
    public Credential mergeCredential(Credential credential);
    
    /**
     * Remove <code>Credential</code>.
     */
    public void removeCredential(Credential credential);
    
    /**
     * Find <code>Credential</code> by <code>Identity</code>.
     */
    public Credential findCredentialByNaturalId(Identity identity);
    
    /**
     * Find <code>Credential</code> by principal.
     */
    public Credential findCredentialByPrincipal(String principal);
    
    
    
}
