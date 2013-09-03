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
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.form.AssociationForm;
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
	 * Find a user group named "USER", expected to hold all users in the given entity.
	 * 
	 * @param entity
	 */
	UserGroup findRootGroup(Entity entity);
	
	/**
	 * Find a user group named "ADMIN", expected to hold administrators for the given entity.
	 * 
	 * @param entity
	 */
	UserGroup findAdminGroup(Entity entity);
	
	/**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     * 
     * @param form
     */
    List<? extends UserGroup> findUsers(UserGroupForm form);
    
    /**
     * Find users sharing the same user key.
     * 
     * @param userKey
     */
    List<? extends UserGroup> findUsers(String userKey);
    
    /**
     * Find users sharing the same user key.
     * 
     * @param parent
     * @param userKey
     */
    List<User> findUsers(String parent, String userKey);
    
    /**
     * Find users sharing the same user key.
     * 
     * @param parent
     * @param userKey
     * @param entityType
     */
    List<User> findUsers(String parent, String userKey, char entityType);

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
    UserGroup storeUserGroup(UserGroup userGroup);
    
    /**
     * Find user associations.
     * 
     * @param form
     */
    List<UserAssociation> findUserAssociations(AssociationForm form);
    
    /**
     * Find user associations.
     * 
     * @param parent
     */
    List<UserAssociation> findUserAssociations(UserGroup parent);
    
    /**
     * Install user.
     * 
     * @param parent
     * @param identity
     * @param accountNonExpired
     */
    UserAssociation installUser(UserGroup parent, Identity identity, boolean accountNonExpired);
    
    /**
     * <p>Create <code>UserAssociation</code> with a new credential.</p>
     * 
     * @param parent
     * @param credential
     * @param accountNonExpired
     */
    UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired);
    
    /**
     * <p>Store <code>UserAssociation</code> and return a managed instance.</p>
     * 
     * @param parentAssociation
     */
    UserAssociation storeUserAssociation(UserAssociation parentAssociation);
    
    /**
     * Find a list of parents for a given <code>UserGroup</code>, including itself.
     * 
     * @param userGroup
     */
    List<UserGroup> findParentChain(UserGroup userGroup);
    
    /**
     * Store <code>UserLog<code>.
     * 
     * @param user
     * @param date
     */
	UserLog storeUserLog(User user, Date date);

	/**
	 * Install an UserGroup, if does not exist.
	 * 
	 * @param defaultEntity
	 * @param userGroupName
	 * @param reinstall
	 */
	UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall);

	/**
	 * Find <code>UserRole</code>(s).
	 * 
	 * @param userRoleFilter
	 * @deprecated
	 * @see #findUserRoles(UserRoleForm)
	 */
	List<UserRole> findUserRoles(Filter userRoleFilter);
	
	/**
	 * Find <code>UserRole</code>(s).
	 * 
	 * @param form
	 */
	List<UserRole> findUserRoles(UserRoleForm form);
	
	/**
	 * Install an UserRole, if does not exist.
	 * 
	 * @param userGroup
	 * @param service
	 * @param extension
	 */
	UserRole installUserRole(UserGroup userGroup, Service service, String extension);

	/**
	 * Store <code>UserRole</code> to the data store.
	 * 
	 * @param userRole
	 */
	UserRole storeUserRole(UserRole userRole);

	/**
	 * Remove <code>UserRole</code> from the data store.
	 * 
	 * @param userRole
	 * @param userGroup
	 */
	void removeUserRole(UserRole userRole, UserGroup userGroup);

}
