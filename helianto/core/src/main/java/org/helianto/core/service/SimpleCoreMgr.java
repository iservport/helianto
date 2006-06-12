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

import java.util.List;
import java.util.Locale;

import org.helianto.core.Credential;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.PublicUserDetailsSwitcher;
import org.helianto.core.security.SecureUserDetails;

/**
 * A service layer interface extension for the simple user use case.
 * 
 * <p>A simple core service will aggregate a default entity to an
 * user domain object without human interaction. In such scenario, 
 * there is no need to create a credential first, as it may be 
 * automatically associated to the user.</p>
 * 
 * <p>This is only appropriate if the application is in
 * control of one single entity (two or three are also acceptable). 
 * If there are more entities, please refer to the full core service.<p>
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface SimpleCoreMgr {
    
    /**
     * <p>A default entity will be required to create a simple user.</p>
     */
    public DefaultEntity createDefaultEntity(String alias);

    /**
     * <p>Persist the DefaultEntity.</p>
     */
    public void persistDefaultEntity(DefaultEntity defaultEntity);
    
    /**
     * <p>A cooresponding default <code>Division</code> will be required 
     * to work with the <code>DefaultEntity</code>.</p>
     */
    public Division createDefaultDivision(DefaultEntity defaultEntity);

    /**
     * <p>Create a <code>Division</code>.</p>
     */
    public Division createDivision(Entity entity, String alias);
    
    /**
     * <p>Persist a <code>Division</code>.</p>
     */
    public void persistDivision(Division division);
    
    /**
     * <p>
     * The preferred way to create and persist the DefaultEntity.
     * </p>
     * <p>
     * Additionally, a <code>Division</code> with the same
     * alias will be automatically created and persisted.
     * </p>
     */
    public DefaultEntity installDefaultEntity(String alias);
    
    /**
     * <p>Change an entity to default.</p>
     */
    public void changeEntityToDefault(Entity entity);
    
    /**
     * <p>Find the default entity with priority zero.</p>
     */
    public Entity findDefaultEntity();
    
    /**
     * <p>An empty <code>Credential</code> to be 
     * submitted to the presentation layer.</p>
     */
    public Credential createEmptyCredential();
    
    /**
     * <p>A simple <code>User</code> creation that hides an empty <code>Credential</code>
     * creation behind its implementation and automatically
     * aggregates the default <code>Entity</code>.</p>
     */
    public User createSimpleUser();
    
    /**
     * <p>A simple <code>User</code> creation that hides an empty <code>Credential</code>
     * creation behind its implementation.</p>
     */
    public User createSimpleUser(Entity entity);
    
    /**
     * <p>A simple <code>User</code> creation given a <code>Credential</code>.</p>
     */
    public User createSimpleUser(Credential credential);
    
    /**
     * <p>Full <code>User</code> creation.</p>
     */
    public User createUser(Credential credential, Entity entity);
    
    /**
     * <p>List <code>User</code> by <code>Entity</code>.</p>
     */
    public List<User> findUserByEntity(Entity entity);
    
    /**
     * <p>The <code>java.util.Locale</code> 
     * for the given <code>Home</code>.</p>
     * 
     * <p>If a <code>Locale</code> can not be set
     * from the <code>language</code> and <code>country</code>
     * instance members of <code>Home</code>, return the 
     * default <code>Locale</code>.
     */
    public Locale getLocale(Home home);
    
    
    /**
     * <p>A method to prevent unique key violation for
     * the <code>Credential</code> object contained in <code>User</code>.</p>
     * <p>The principal is converted to lower case before
     * uniqueness check.<p>
     */
    public boolean isPrincipalUnique(User user);
    
    /**
     * <p>A method to prevent unique key violation for
     * the <code>Credential</code> object.</p>
     * <p>The principal is converted to lower case before
     * uniqueness check.<p>
     */
    public boolean isPrincipalUnique(Home home, Credential credential);
    
    /**
     * <p>Persist the <code>User</code>.</p>
     */
    public void persistUser(User user);
    
    /**
     * <p>Retrieve the <code>PublicUserDetails</code> available in a
     * secure context.</p>
     */
    public PublicUserDetails findSecureUser();
    
    /**
     * <p>Switch to an <code>User</code> with the same authentication.</p>
     * 
     */
    public boolean switchAuthorizedUser(PublicUserDetailsSwitcher secureUser, String entityAlias);
    
    /**
     * <p>Delegate to persistence layer to save <code>PersonalData</code>.</p>
     */
    public void persistPersonalData(SecureUserDetails secureUserDetails);
    
}
