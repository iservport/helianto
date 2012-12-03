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

package org.helianto.user;

import java.util.Date;
import java.util.List;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.AssociationForm;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserLog;
import org.helianto.user.domain.UserRole;
import org.helianto.user.form.UserGroupForm;
import org.helianto.user.form.UserRoleForm;

/**
 * Default user service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserMgr {
	
    /**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     * 
     * @param form
     */
    public List<? extends UserGroup> findUsers(UserGroupForm form);
    
    /**
     * Find users sharing the same user key.
     * 
     * @param userKey
     */
    public List<? extends UserGroup> findUsers(String userKey);

//    /**
//     * Find users sharing the same identity.
//     * 
//     * @param identity
//     */
//    public List<? extends UserGroup> findUsers(Identity identity);
//
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
    public List<UserAssociation> findUserAssociations(AssociationForm form);
    
    /**
     * <p>Create <code>UserAssociation</code> with a new credential.</p>
     * 
     * @param parent
     * @param credential
     * @param accountNonExpired
     */
    public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired);
    
    /**
     * <p>Store <code>UserAssociation</code> and return a managed instance.</p>
     * 
     * @param parentAssociation
     */
    public UserAssociation storeUserAssociation(UserAssociation parentAssociation);
    
    /**
     * Find a list of parents for a given <code>UserGroup</code>, including itself.
     * 
     * @param userGroup
     */
    public List<UserGroup> findParentChain(UserGroup userGroup);
    
    /**
     * Store <code>UserLog<code>.
     * 
     * @param user
     * @param date
     */
	public UserLog storeUserLog(User user, Date date);

	/**
	 * Install an UserGroup, if does not exist.
	 * 
	 * @param defaultEntity
	 * @param userGroupName
	 * @param reinstall
	 */
	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall);

	/**
	 * Find <code>UserRole</code>(s).
	 * 
	 * @param userRoleFilter
	 * @deprecated
	 * @see #findUserRoles(UserRoleForm)
	 */
	public List<UserRole> findUserRoles(Filter userRoleFilter);
	
	/**
	 * Find <code>UserRole</code>(s).
	 * 
	 * @param form
	 */
	public List<UserRole> findUserRoles(UserRoleForm form);
	
	/**
	 * Install an UserRole, if does not exist.
	 * 
	 * @param userGroup
	 * @param service
	 * @param extension
	 */
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension);

	/**
	 * Store <code>UserRole</code> to the data store.
	 * 
	 * @param userRole
	 */
	public UserRole storeUserRole(UserRole userRole);

	/**
	 * Remove <code>UserRole</code> from the data store.
	 * 
	 * @param userRole
	 * @param userGroup
	 */
	public void removeUserRole(UserRole userRole, UserGroup userGroup);

}
