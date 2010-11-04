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

import java.util.Set;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.security.SecureUserDetails;

/**
 * Default security service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SecurityMgr {
	
    /**
     * Find <core>Credential</core> by <core>Identity</core>.
     */
	public Credential findCredentialByIdentity(Identity identity);
	
    /**
     * Find <core>Credential</core> by principal.
     */
	public Credential findCredentialByPrincipal(String principal);
	
    /**
     * Store the given <code>Credential</code> and return a managed object.
     * 
     * @param identity
     * @exception PasswordNotVerifiedException
     */
    public Credential storeCredential(Credential credential) 
        throws PasswordNotVerifiedException;
    
    /**
     * Store the given <code>Credential</code> and update the secure user.
     * 
     * @param identity
     */
    public void storeCredential(SecureUserDetails secureUser);
    
    /**
     * Find user roles by user group.
     * 
     * @param userGroup
     * @param recursively
     */
    public Set<UserRole> findRoles(UserGroup userGroup, boolean recursively);
    
}
