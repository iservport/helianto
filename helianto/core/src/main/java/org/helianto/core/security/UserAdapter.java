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

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.Role;

/**
 * Models core user information retieved by an 
 * {@link org.acegisecurity.userdetails.UserDetailsService} 
 * as an adapter class to keep coupling from the Acegi-security 
 * package as narrow as possible.
 * 
 * <p>
 * A new <code>UserAdapter</code> must be built from a single
 * {@link org.helianto.core.Credential}. As such <code>Credential</code>
 * may be connected to zero or more {@link org.helianto.core.User}s, 
 * the authorities granted are exposed only after at least one corresponding 
 * <code>User</code> is selected as current. This is
 * is a design choice made to allow multiple entities to share
 * the same set of credentials and still keep individual sets of
 * authorities. 
 * </p>
 * 
 * <p>
 * To avoid further processing after authentication 
 * for environments where all credentials can be restricted to 
 * a single entity, i.e. one <code>User</code> per <code>Credential</code>,
 * the constructor silently tries to do the selection.
 * </p>
 * 
 * <p>
 * Service layer code should retrieve <code>UserAdapter</code> through the 
 * {@link org.helianto.core.security.PublicUserDetails} interface 
 * to prevent unintended access to the embedded <code>User</code> and 
 * <code>Credential</code> instances. A convenient static method 
 * {@link #retrievePublicUserDetailsFromSecurityContext} 
 * is supplied to perform this task. <code>PublicUserDetails</code>  
 * provides a method to set the current <code>User</code> after any 
 * <code>Entity</code> selection.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * @see org.acegisecurity.providers.dao.User org.acegisecurity.providers.dao.User
 */
public final class UserAdapter extends AbstractUserDetails implements Serializable, PublicUserDetails {
    
    private static final long serialVersionUID = 4017521054529203449L;

    /**
     * Static method suitable to retrieve the <code>UserAdapter</code>
     * instance mantained in the <code>SecurityContext</code>.
     */
    public static PublicUserDetails retrievePublicUserDetailsFromSecurityContext() {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Retriving public user details ...");
        }
        PublicUserDetails pud = (PublicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Done.");
        }
        return pud;
    }
    
    public UserAdapter(Credential cred) {
        super(cred);
    }

    public GrantedAuthority[] getAuthorities() {
        Set roles = user.getRoles();
        if (roles!=null && roles.size()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Roles found: "+roles.size());
            }
            GrantedAuthority[] authorities = new GrantedAuthority[roles.size()];
            int i = 0;
            for (Iterator it = roles.iterator(); it.hasNext();) {
                authorities[i++] =  new GrantedAuthorityImpl(((Role) it.next()).getRoleName());
            }
            return authorities;
        }
        return new GrantedAuthority[] {new GrantedAuthorityImpl("ROLE_USER")};
    }

    public Entity getEntity() {
        return user.getEntity();
    }
    
    public Long getCredentialId() {
        return user.getCredential().getId();
    }
    
    public PersonalData getPersonalData() {
        return user.getCredential().getPersonalData();
    }

}
