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

import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.hibernate.filter.IdentityFilter;

/**
 * Identity and Credential data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface AuthenticationDao {
    
    /**
     * Persists <code>Identity</code>.
     */
    public void persistIdentity(Identity identity);

    /**
     * Merges <code>Identity</code>.
     */
    public Identity mergeIdentity(Identity identity);

    /**
     * Removes <code>Identity</code>.
     */
    public void removeIdentity(Identity identity);

    /**
     * Finds <code>Identity</code> by principal.
     */
    public Identity findIdentityByPrincipal(String principal);

    /**
     * Finds <code>Identity</code> list by <code>IdentityFilter</code>.
     */
    public List<Identity> findIdentityByCriteria(IdentityFilter filter);

    /**
     * Persists <code>Credential</code>.
     */
    public void persistCredential(Credential credential);

    /**
     * Merges <code>Credential</code>.
     */
    public Credential mergeCredential(Credential credential);

    /**
     * Removes <code>Credential</code>.
     */
    public void removeCredential(Credential credential);

    /**
     * Finds <code>Credential</code> by <code>Identity</code>.
     */
    public Credential findCredentialByIdentity(Identity identity);

}
