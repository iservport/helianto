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

package org.helianto.core.service;

import java.util.Collection;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.IdentityFilter;

/**
 * Default user service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserMgr extends CoreMgr {
	
    /**
     * Find <core>Identity</core> by principal.
     */
    public Identity findIdentityByPrincipal(String principal);
    
    /**
     * <p>Selects an <code>Identity</code> list.
     * 
     * @param filter
     * @param exclusions list to be removed after selection
     */
    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions);
    
    /**
     * Store the given <code>Identity</code> and return a managed object.
     * @param identity
     */
    public Identity storeIdentity(Identity identity);
    
    /**
     * Store the given <code>Credential</code> and return a managed object.
     * @param identity
     */
    public Credential storeCredential(Credential credential);
    
    /**
     * <p>A simple <code>User</code> creation given an <code>Endity</code>.</p>
     */
    public User createUser(Entity entity);
    
    /**
     * <p>Full <code>User</code> creation.</p>
     */
    public User createUser(Identity identity, Entity entity);
    
    /**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     */
    public List<User> findUsers(String criteria);
    
    /**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     */
    public List<User> findUsers(UserFilter userFilter);
    
    /**
     * <p>Store <code>UserGroup</code> and return a managed instance.</p>
     */
    public UserGroup storeUserGroup(UserGroup userGroup);
    
    /**
     * <p>Write <code>Identity</code> to datastore.</p>
     * @deprecated in favor of storeIdentity
     */
    public void writeIdentity(Identity identity);
    
    /**
     * <p>A <code>Credential</code> with an
     * associated new <code>Identity</code>.</p>
     * @deprecated in favor of storeCredential
     */
    public void writeCredential(Credential credential);
    
    /**
     * <p>Persist the <code>User</code>.</p>
     */
    @Deprecated
    public void persistUser(User user);
    
    /**
     * <p>Store <code>User</code> and return a managed instance.</p>
     * @deprecated use storeUser(UserGroup userGroup)
     */
    public User storeUser(User user);
    
    /**
     * <p>Write <code>User</code>.</p>
     * @deprecated use storeUser
     */
    public void writeUser(User user);
    
    /**
     * <p>Write <code>UserGroup</code>.</p>
     * @deprecated use storeUser
     */
    public void writeUser(UserGroup userGroup);
    
}
