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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.filter.UserBackedFilter;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserFilter implements Serializable , UserBackedFilter {

    private static final long serialVersionUID = 1L;
    private User user;
    private String identityPrincipal;
    private char userState;
    private Collection<Identity> exclusions;
    
    /**
     * Default constructor
     */
    public UserFilter() {
    }
    
    /**
     * Factory method.
     * @param user
     */
    public static UserFilter userFilterFactory(User user) {
    	UserFilter userFilter = new UserFilter();
    	userFilter.setUser(user);
    	return userFilter;
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    	setIdentityPrincipal("");
    	setExclusions(new HashSet<Identity>(0));
    	setUserState(UserState.ACTIVE.getValue());
    }
    
    /**
     * User tom meet <code>UserBackedFilter</code> interface requirements.
     */
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Identity principal criterion field
     */
    public String getIdentityPrincipal() {
        return identityPrincipal;
    }
    public void setIdentityPrincipal(String identityPrincipal) {
        this.identityPrincipal = identityPrincipal;
    }
    
    /**
     * User state criterion field
     */
	public char getUserState() {
		return userState;
	}
	public void setUserState(char userState) {
		this.userState = userState;
	}

    /**
     * Collection of identities to exclude on result set.
     */
    public Collection<Identity> getExclusions() {
        return exclusions;
    }
    public void setExclusions(Collection<Identity> exclusions) {
        this.exclusions = exclusions;
    }


}
