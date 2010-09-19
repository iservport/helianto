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

import org.helianto.core.Credential;
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
     * 
     * @param principal
     */
    public Identity findIdentityByPrincipal(String principal);
    
    /**
     * Load <core>Identity</core> by id.
     * 
     * @param id
     */
    public Identity loadIdentity(long id);
    
    /**
     * Load the <core>Identity</core> photo.
     * 
     * @param identity
     */
    public byte[] loadIdentityPhoto(Identity identity);
    
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
     * 
     * @param entity
     */
    public UserGroup prepareNewUserGroup(Entity entity);
    
    /**
     * Prepare <code>UserGroup</code> in session.
     * 
     * @param userGroup
     */
    public UserGroup prepareUserGroup(UserGroup userGroup);
    
    /**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     * 
     * @param userFilter
     */
    public List<UserGroup> findUsers(UserFilter userFilter);
    
    /**
     * Find users sharing the same identity.
     * 
     * @param identity
     */
    public List<UserGroup> findUsers(Identity identity);

    /**
     * <p>Store <code>UserGroup</code> and return a managed instance.</p>
     * 
     * @param userGroup
     */
    public UserGroup storeUserGroup(UserGroup userGroup);
    
    /**
     * Find user associations.
     * 
     * @param userAssociationFilter
     */
    public List<UserAssociation> findUserAssociations(UserAssociationFilter userAssociationFilter);
    
    /**
     * <p>Create <code>UserAssociation</code>.</p>
     * 
     * @param parent
     */
    public UserAssociation prepareNewUserAssociation(UserGroup parent);
    
    /**
     * <p>Create <code>UserAssociation</code> with a new credential.</p>
     * 
     * @param parent
     * @param credential
     * @param accountNonExpired
     */
    public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired);
    
    /**
     * <p>Create <code>Credential</code> and <code>Identity</code>.</p>
     * 
     * @param principal
     */
    public Credential installIdentity(String principal);
    
    /**
     * <p>Create <code>Credential</code>.</p>
     * 
     * @param identity
     */
    public Credential installCredential(Identity identity);
    
    /**
     * <p>Store <code>UserAssociation</code> and return a managed instance.</p>
     * 
     * @param parentAssociation
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
