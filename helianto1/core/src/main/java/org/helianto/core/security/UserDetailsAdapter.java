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
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.springframework.util.Assert;

/**
 * Models core user information retieved by  
 * {@link org.acegisecurity.userdetails.UserDetailsService} 
 * as an adapter class to keep coupling from the Acegi-security 
 * package as narrow as possible.
 * 
 * <p>
 * A new <code>UserAdapter</code> must be created from a single
 * {@link org.helianto.core.User}. As a group of <code>User</code>s may be
 * connected by a common <code>Credential</code> it is desirable to switch 
 * <code>User</code>s inside the group at runtime as no new authentication is
 * be required (same credential). This is
 * is a design choice made to allow multiple entities to share
 * the same set of credentials and still keep individual sets of
 * authorities. 
 * </p>
 * 
 * <p>
 * Service layer code should retrieve <code>UserAdapter</code> through the 
 * {@link org.helianto.core.security.PublicUserDetails}  or
 * {@link org.helianto.core.security.PublicUserDetailsSwitcher} interfaces 
 * to prevent unintended access to the embedded <code>User</code> and 
 * <code>Credential</code> instances. A convenient static methods 
 * {@link org.helianto.core.security.AbstractUserAdapter#retrievePublicUserDetailsFromSecurityContext} and
 * {@link org.helianto.core.security.AbstractUserAdapter#retrievePublicUserDetailsSwitcherFromSecurityContext} 
 * are supplied to perform this task. <code>PublicUserDetailsSwitcher</code>  
 * provides a method to switch the current <code>User</code> based on an
 * <code>Entity</code> selection.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * @see org.acegisecurity.providers.dao.User org.acegisecurity.providers.dao.User
 */
public final class UserDetailsAdapter extends AbstractUserDetails implements Serializable, PublicUserDetailsSwitcher {
    
    private static final long serialVersionUID = 4017521054529203449L;
    
    public UserDetailsAdapter(User user, Credential credential) {
        super(user, credential);
    }

    public GrantedAuthority[] getAuthorities() {
        Set<UserRole> roles = getUser().getRoles();
        Assert.notNull(roles);
        GrantedAuthority[] authorities = new GrantedAuthority[roles.size()];
        int i = 0;
        for (UserRole r: roles) {
            String roleName = convertUserRoleToString(r);
            authorities[i++] =  new GrantedAuthorityImpl(roleName);
        }
        return authorities;
    }
    
    public String convertUserRoleToString(UserRole userRole) {
        StringBuilder sb = new StringBuilder();
        sb.append("ROLE_")
            .append(userRole.getService().getServiceName())
            .append("_").append(userRole.getServiceExtension());
        return sb.toString();
    }

    public Set<UserGroup> getUsers() {
        return getUser().getIdentity().getUsers();
    }

    public void setCurrentUser(User user) {
        Set<UserGroup> userSet = getUsers();
        boolean acceptable = false;
        for (UserGroup u: userSet) {
             if (u.equals(user)) {
                 acceptable = true;
                 setUser(user);
             }
        }
        Assert.isTrue(acceptable, "Unable to change to user "+user);
    }

}

