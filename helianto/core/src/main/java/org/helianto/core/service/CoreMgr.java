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

import javax.mail.MessagingException;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.User;

/**
 * A base service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface CoreMgr extends GenericService {

    /**
     * Persist a <code>Credential</code>.
     */
    public void persistCredential(Credential credential);

    /**
     * Persist an <code>Entity</code>
     */
    public void persistEntity(Entity entity);
    
    /**
     * Persist an <code>User</code>
     */
    public void persistUser(User user);
    
    /**
     * Load an <code>Entity</code>
     */
    public Entity loadEntity(Long key);
    
    /**
     * Load a <code>Credential</code>
     */
    public Credential loadCredential(Long key);
    
    /**
     * Find a <code>Credential</code> by principal.
     */
    public Credential findCredentialByPrincipal(String principal);
    
    /**
     * Find a <code>Home</code> or throw an Exception, if
     * none can't be found.
     * <p>This method performs successive calls
     * to the datastore, using different criteria, to cover all
     * possibilities to retrieve an supervisor.
     * </p>
     */
    public Home findRequiredHome(Object homeName);
    
    /**
     * Find an <code>Entity</code> by alias.
     */
    public Entity findEntityByAlias(String entityAlias);
    
    /**
     * Find all <code>User</code>s associated to a 
     * <code>Credential</code> by Id.
     */
    public List<User> findUserByCredentialId(Long credId);
    
    /**
     * Return the last available number to a number type
     * and assign a new one with increment 1.
     */
    public long currentNumber(Entity entity, String type);
    
    /**
     * Send a <code>Credential</code> registration using 
     * mail settings from <code>Supervisor</code>.
     */
    public void sendRegistrationNotification(Home home, Credential cred) throws MessagingException;
    
}