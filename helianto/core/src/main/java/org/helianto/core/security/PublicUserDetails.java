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

package org.helianto.core.security;

import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;

/**
 * An interface to reveal public user details.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface PublicUserDetails {
    
    /**
     * Set the current <code>User</code>.
     */
    public void setUser(User user);
    
    /**
     * @return the current <code>Entity</code>.
     */
    public Entity getEntity();
    
    /**
     * @return the current <code>Credential</code> id.
     */
    public Long getCredentialId();
    
    /**
     * @return Personal data.
     */
    public PersonalData getPersonalData();
    
}
