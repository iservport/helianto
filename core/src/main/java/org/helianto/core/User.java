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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
/**
 * <p>
 * The user account.
 * </p>
 * <p>
 * An user account represents the association between an <code>Identity</code>
 * and an <code>Entity</code>. Such association allows for a singly identified 
 * actor, i.e. a person or any other organizational <code>Identity</code>, to keep
 * a single authentication scheme and have multiple authorization schemes, one per
 * <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 */
@javax.persistence.Entity
@DiscriminatorValue("U")
public class User extends UserGroup implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param entity
     */
    public static User userFactory(Entity entity) {
    	User user = UserGroup.internalUserGroupFactory(User.class, entity, "");
    	user.setIdentity(Identity.identityFactory(""));
    	return user;
    }

    /**
     * Factory method.
     * 
     * @param userAssociation
     */
    public static User userFactory(UserAssociation userAssociation) {
    	User user = UserGroup.internalUserGroupFactory(User.class, userAssociation.getParent().getEntity(), "");
    	userAssociation.setChild(user);
    	user.getParentAssociations().add(userAssociation);
    	user.setIdentity(Identity.identityFactory(""));
    	return user;
    }

    /**
     * Factory method.
     * 
     * @param entity
     * @param identity
     */
    public static User userFactory(Entity entity, Identity identity) {
        User user = UserGroup.internalUserGroupFactory(User.class, entity, identity.getPrincipal());
        user.setIdentity(identity);
        return user;
    }

    private static final long serialVersionUID = 1L;
    private Identity identity;
    private char userType;
    private char privacyLevel;
	private Set<UserLog> userLogs = new HashSet<UserLog>(0);

	/** default constructor */
    public User() {
    	super();
        setAccountNonExpired(false);
    	setUserType(UserType.INTERNAL);
    	setPrivacyLevel('0');
    }

    @Transient
    protected String resolveUserKey(String userKey) {
    	if (getIdentity()!=null && getIdentity().getPrincipal()!=null && getIdentity().getPrincipal().length()>0) {
    		return getIdentity().getPrincipal();
    	}
        return userKey;
    }
    
    /**
     * Identity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    /**
     * User principal.
     */
    @Transient
    public String getUserPrincipal() {
    	if (getIdentity()==null) {
    		return "";
    	}
        return getIdentity().getPrincipal();
    }
    /**
     * User principal name.
     */
    @Transient
    public String getUserPrincipalName() {
    	int position = getUserPrincipal().indexOf("@");
    	if (position>0) {
    		return getUserPrincipal().substring(0, position);
    	}
        return getUserPrincipal();
    }
    /**
     * User principal domain.
     */
    @Transient
    public String getUserPrincipalDomain() {
    	int position = getUserPrincipal().indexOf("@");
    	if (position>0) {
    		return getUserPrincipal().substring(position);
    	}
        return "";
    }
    /**
     * UserName.
     */
    @Transient
    public String getUserName() {
    	if (getIdentity()!=null) {
            return getIdentity().getIdentityName();
    	}
        return "";
    }

    /**
     * UserType getter.
     */
    public char getUserType() {
        return this.userType;
    }
    public void setUserType(char userType) {
        this.userType = userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType.getValue();
    }

    /**
     * Privacy level
     */
    public char getPrivacyLevel() {
        return this.privacyLevel;
    }
    public void setPrivacyLevel(char privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

	/**
	 * A collection of user logs.
	 */
	@OneToMany(mappedBy="user")
	public Set<UserLog> getUserLogs() {
		return userLogs;
	}
	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}
	
    /**
     * <code>UserLog</code> factory mehod.
     * 
     * @param eventType
     */
    public UserLog userLogFactory(EventType eventType) {
    	return UserLog.userLogFactory(this, new Date(), eventType);
    }
    
}
