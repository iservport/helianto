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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.Role;
import org.helianto.core.User;
import org.helianto.core.UserLog;

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
    
    public UserDetailsAdapter(UserLog userLog) {
        super(userLog);
    }

    public GrantedAuthority[] getAuthorities() {
        Set roles = userLog.getUser().getRoles();
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

    public PersonalData getPersonalData() {
        return userLog.getUser().getCredential().getPersonalData();
    }

    public Entity getCurrentEntity() {
        return userLog.getUser().getEntity();
    }
    
    public List<Entity> getEntities() {
        List<Entity> entityList = new ArrayList<Entity>();
        if (logger.isDebugEnabled()) {
            logger.debug("Current entity is: "+getCurrentEntity());
        }
        for (User u: getUsers()) {
        	if (!u.getEntity().equals(getCurrentEntity())) {
                entityList.add(u.getEntity());
                if (logger.isDebugEnabled()) {
                    logger.debug("Added to entity list: "+u.getEntity());
                }
        	}
        }
        return entityList;
    }

    public Date getLastLogin() {
        return userLog.getLastLogin();
    }
    
    public Set<User> getUsers() {
        return userLog.getUser().getCredential().getUsers();
    }
    
    public void setUserLog(UserLog userLog) {
        this.userLog = userLog;
    }

    public void setEntity(Entity entity) {
        Set<User> userSet = userLog.getUser().getCredential().getUsers();
        User newUser = null;
        for (User u: userSet) {
             if (u.getEntity().equals(entity)) {
                 newUser = u;
             }
        }
        if (newUser==null) {
            throw new IllegalArgumentException("Unable to change to entity " +
                    entity.getAlias()+": there is no corresponding user for " +
                    "credential "+userLog.getUser().getCredential());
        } 
    }
    
}

