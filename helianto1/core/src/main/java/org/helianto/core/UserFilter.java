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

import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserFilter extends AbstractUserBackedCriteriaFilter {

    private static final long serialVersionUID = 1L;
    private Identity identity;
    private String identityPrincipal;
    private char userState;
    private boolean orderByLastEventDesc = false;
	private Collection<Identity> exclusions;
    
    /**
     * Default constructor
     */
    public UserFilter() {
    	reset();
    }
    
    /**
     * Identity constructor
     */
    public UserFilter(Identity identity, boolean orderByLastEventDesc) {
    	super();
    	setIdentity(identity);
    	setOrderByLastEventDesc(orderByLastEventDesc);
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
     * Identity
     */
    public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
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
     * True if order by last event is required.
     */
    public boolean isOrderByLastEventDesc() {
		return orderByLastEventDesc;
	}
	public void setOrderByLastEventDesc(boolean orderByLastEventDesc) {
		this.orderByLastEventDesc = orderByLastEventDesc;
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

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("identity").append("='").append(getIdentity()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }
}
