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

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;

/**
 * Default base service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ServerMgr {

    /**
     * Sets the system up to accept a manager.
     * 
     * @param managerIdentity
     * @return new manager <code>User</code>
     */
    public User prepareSystemConfiguration(Identity managerIdentity);
    
    /**
     * Creates an <code>Entity</code> with name "DEFAULT"
     */
    public Entity createDefaultEntity();
    
    /**
     * Creates an <code>Operator</code> with name "DEFAULT",
     * LOCAL mode and default <code>Locale</code>.
     */
    public Operator createLocalDefaultOperator();
    
    /**
     * <p>Finds or creates <code>UserGroup</code> by <code>Entity</code> and name.</p>
     */
    public UserGroup findOrCreateUserGroup(Entity entity, String groupName);
    
    /**
     * <p>Finds or creates <code>UserGroup</code> by <code>Entity</code> and name.</p>
     */
    public UserGroup findOrCreateUserGroup(Entity entity, String serviceName, String[] extensions);
    
    /**
     * A manager is an <code>User</code> associated to both, an <code>ADMIN</code>
     * group and an <code>USER</code> group.
     * 
     * @param entity
     * @param managerIdentity
     */
    public User createManager(Entity entity, Identity managerIdentity);
    
    /**
     * Persist the <code>Operator</code>.
     */
    public void persistOperator(Operator operator);

    /**
     * <p>Finds <code>Operator</code> list.</p>
     */
    public List<Operator> findOperator();

    /**
     * <p>Finds <code>Operator</code> by name.</p>
     */
    public Operator findOperatorByName(String operatorName);

    /**
     * Send a <code>Credential</code> confirmation mail using 
     * a <code>PasswordConfirmationMailForm</code>.
     */
    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm)
            throws MessagingException;

}