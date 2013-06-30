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

package org.helianto.core;

import java.util.List;
import java.util.Set;

import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.form.IdentitySecurityForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;

/**
 * Default security service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SecurityMgr {
	
	/**
	 *  Find IdentitySecurity.
	 *  
	 *  @param form
	 */
	List<IdentitySecurity> findIdentitySecurity(IdentitySecurityForm form);
	
	/**
	 * Find IdentitySecurity by consumerKey.
	 * 
	 * @param consumerKey
	 */
	IdentitySecurity findIdentitySecurity(String consumerKey);
	
	/**
	 * Store IdentitySecurity, optionally verifying the password.
	 * 
	 * @param identitySecurity
	 * @param verify
	 */
	
	IdentitySecurity storeIdentitySecurity(IdentitySecurity identitySecurity, boolean verify);
	
	/**
	 * Load <core>IdentitySecurity</core>.
	 * 
	 * @param identitySecurityId
	 */
	IdentitySecurity loadIdentitySecurity(PublicUserDetails userDetails);
	
    /**
     * Find user roles by user group.
     * 
     * @param userGroup
     * @param recursively
     */
    Set<UserRole> findRoles(UserGroup userGroup, boolean recursively);
    
    /**
     * Used to authenticate an instance of <code>UserDetailsAdapter</code>s.
     * 
     * @param userDetails
     */
    void authenticate(UserDetailsAdapter userDetails);
	
    /**
     * Clear the current authentication.
     */
	void clearAuthentication();
	
	/**
	 * Find the authenticated user.
	 */
	PublicUserDetails findAuthenticatedUser();
	
    /**
     * Find <core>Credential</core> by <core>Identity</core>.
     * 
     * @param identity
     * @deprecated
     */
	Credential findCredentialByIdentity(Identity identity);
	
    /**
     * Find <core>Credential</core> by principal.
     * 
     * @param principal
     * @deprecated
     */
	Credential findCredentialByPrincipal(String principal);
	
    /**
     * Re-attach <core>Credential</core> to a session.
     * 
     * @param credential
     * @deprecated
     */
	Credential loadCredential(Credential credential);
	
    /**
     * Store the given <code>Credential</code> and return a managed object.
     * 
     * @param identity
     * @deprecated
     * @exception PasswordNotVerifiedException
     */
    Credential storeCredential(Credential credential) 
        throws PasswordNotVerifiedException;
    
    /**
     * Used to authenticate a known <code>User</code> with a set of <code>UserRole</code>s.
     * 
     * @param user
     * @param roles
     * @deprecated 
     */
    void authenticate(User user, Set<UserRole> roles);
    
}
