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
import java.util.Date;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserAssociationFilter;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;

/**
 * Default user service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserMgr {
	
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
     * 
     * @param identity
     */
    public Identity storeIdentity(Identity identity);
    
    /**
     * Create and prepare <code>UserGroup</code> in session.
     */
    public UserGroup prepareNewUserGroup(Entity entity);
    
    /**
     * Prepare <code>UserGroup</code> in session.
     */
    public UserGroup prepareUserGroup(UserGroup userGroup);
    
    /**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     */
    public List<UserGroup> findUsers(UserFilter userFilter);
    
    /**
     * Find users sharing the same identity.
     */
    public List<UserGroup> findUsers(Identity identity);

    /**
     * <p>Store <code>UserGroup</code> and return a managed instance.</p>
     */
    public UserGroup storeUserGroup(UserGroup userGroup);
    
    /**
     * Find user associations.
     */
    public List<UserAssociation> findUserAssociations(UserAssociationFilter userAssociationFilter);
    
    /**
     * <p>Create <code>UserAssociation</code>.</p>
     */
    public UserAssociation prepareNewUserAssociation(UserGroup parent);
    
    /**
     * <p>Create <code>UserAssociation</code> with a new identity.</p>
     */
    public UserAssociation installUser(UserGroup parent, String principal);
    
    /**
     * <p>Store <code>UserAssociation</code> and return a managed instance.</p>
     */
    public UserAssociation storeUserAssociation(UserAssociation parentAssociation);
    
    /**
     * Store <code>UserLog<code>.
     * 
     * @param user
     * @param date
     */
	public UserLog storeUserLog(User user, Date date);
	
}
