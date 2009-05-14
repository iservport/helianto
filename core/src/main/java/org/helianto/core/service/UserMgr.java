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

import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
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
     * @param identity
     */
    public Identity storeIdentity(Identity identity);
    
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
     * <p>Store <code>UserGroup</code> and return a managed instance.</p>
     * <p>Convenient to store a recently created user or group.</p>
     */
    public UserGroup storeUserGroup(UserAssociation parentAssociation);
    
    /**
     * <p><code>UserAssociation</code> creation that inherits privileges 
     * and resolves identity.</p>
     */
    public UserAssociation createUserAssociation(UserGroup parent, String principal);
    
    /**
     * <p>Store <code>UserAssociation</code> and return a managed instance.</p>
     */
    public UserAssociation storeUserAssociation(UserAssociation parentAssociation);
    
    /**
     * Write a new <code>UserLog<code> and update the <code>Identity</code>
     * last log date.
     * 
     * @param user
     * @param date
     */
	public UserLog storeUserLog(User user, Date date);
	
    /**
     * Find a <code>Province</code> list by <code>Operator</code>.
     */
    public List<Province> findProvinceByOperator(Operator operator);

}
