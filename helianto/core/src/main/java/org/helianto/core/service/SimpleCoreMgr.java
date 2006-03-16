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

import org.helianto.core.Credential;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;

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
     * <p>A default entity will be required to create a simple user.</p>
     */
    public DefaultEntity createDefaultEntity(String alias, int priority);

    /**
     * <p>Change an entity to default.</p>
     */
    public DefaultEntity changeEntityToDefault(Entity entity, int priority);
    
    /**
     * <p>Find the default entity with priority zero.</p>
     */
    public DefaultEntity findDefaultEntity();
    
    /**
     * <p>Find a default entity with any priority.</p>
     */
    public DefaultEntity findDefaultEntity(int priority);

    /**
     * <p>A simple user creation that hides a <code>Credential</code>
     * creation behind its implementation and automatically
     * aggregates the highest priority <code>DefaultEntity</code>.</p>
     */
    public User createSimpleUser(String principal, PersonalData pd);
    
    /**
     * <p>A simple user creation that hides a <code>Credential</code>
     * creation behind its implementation.</p>
     */
    public User createSimpleUser(Entity entity, String principal, PersonalData pd);
    
    /**
     * <p>Check password against minimum requirements.</p>
     */
    public void validatePassowrd(Credential cred, String verification);
    
    /**
     * <p>Persist the DefaultEntity.</p>
     */
    public void persistDefaultEntity(DefaultEntity defaultEntity);
    
}
