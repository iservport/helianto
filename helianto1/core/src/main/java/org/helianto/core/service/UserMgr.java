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
import org.helianto.core.UserGroup;
import org.helianto.core.filter.IdentityFilter;

/**
 * Default user service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserMgr extends CoreMgr {
	
    /**
     * <p>Write <code>Identity</code> to datastore.</p>
     */
    public void writeIdentity(Identity identity);
    
    /**
     * <p>Selects an <code>Identity</code> list.
     * 
     * @param filter
     * @param exclusions list to be removed after selection
     */
    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions);
    
    /**
     * <p>A <code>Credential</code> with an
     * associated new <code>Identity</code>.</p>
     */
    public void writeCredential(Credential credential);
    
    /**
     * <p>A simple <code>User</code> creation given an <code>Endity</code>.</p>
     */
    public User createUser(Entity entity);
    
    /**
     * <p>Full <code>User</code> creation.</p>
     */
    public User createUser(Identity identity, Entity entity);
    
    /**
     * <p>Persist the <code>User</code>.</p>
     */
    public void persistUser(User user);
    
    /**
     * <p>List <code>UserGroup</code> by <code>Entity</code>.</p>
     */
    public List<UserGroup> findUserByEntity(Entity entity);
    
    /**
     * <p>List <code>UserGroup</code> by string criteria.</p>
     */
    public List<User> findUsers(String criteria);
    
    
    /**
     * <code>UserGroup</code> will be unlocked by this action
     * if the <code>Credential</code> is active.
     */
    public void activateUser(UserGroup user, Credential credential);
    
    /**
     * <code>UserGroup</code> will be locked by this action.
     */
    public void cancelUser(UserGroup user);
    
    /**
     * <code>UserGroup</code> will be locked by this action. 
     * Unlike <code>cancelUser()</code>, this is supposed to be 
     * a temporary action.
     */
    public void suspendUser(UserGroup user);

}
