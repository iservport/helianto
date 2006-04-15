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

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.PersonalData;

/**
 * An interface to reveal public user details.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface PublicUserDetails {
    
    /**
     * Return <code>PersonalData</code> from 
     * the secured <code>User</code>.
     */
    public PersonalData getPersonalData();
    
    /**
     * Return the <code>Credential</code> principal from 
     * the secured <code>User</code>.
     */
    public String getPrincipal();
    
    /**
     * Return the current <code>Entity</code>.
     */
    public Entity getCurrentEntity();
    
    /**
     * Retrieve the last login date for the current secure
     * <code>User</code>.
     */
    public Date getLastLogin();
    
}
