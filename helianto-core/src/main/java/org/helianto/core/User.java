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
public class User extends UserGroup implements PersonalEntity {

	/**
	 * <<Transient>> Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'U';
	}

    private static final long serialVersionUID = 1L;
    private Identity identity;
    private char userType;
    private char privacyLevel;
	private Set<UserLog> userLogs = new HashSet<UserLog>(0);

	/** 
	 * Empty constructor.
	 */
    public User() {
    	super();
        setAccountNonExpired(false);
    	setUserTypeAsEnum(UserType.INTERNAL);
    	setPrivacyLevel('0');
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param credential
	 */
    public User(Entity entity, Identity identity) {
    	this();
        setEntity(entity);
    	setIdentity(identity);
    }

	/** 
	 * Credential constructor.
	 * 
	 * <p>
	 * The credential is not used after its principal is read,
	 * although is here to force previous creation.
	 * </p>
	 * 
	 * @param entity
	 * @param credential
	 */
    public User(Entity entity, Credential credential) {
    	this(entity, credential.getIdentity());
    }

	/** 
	 * Parent constructor.
	 * 
	 * @param parent
	 * @param childCredential
	 */
    public User(UserGroup parent, Credential childCredential) {
    	this(parent.getEntity(), childCredential);
    	parent.getChildAssociations().add(new UserAssociation(parent, childCredential));
    }

    /**
     * Overridden to obtain the user key from the identity principal.
     */
    @Transient
    protected String getInternalUserKey() {
    	if (getIdentity()!=null && getIdentity().getPrincipal()!=null && getIdentity().getPrincipal().length()>0) {
    		return getIdentity().getPrincipal();
    	}
        return super.getInternalUserKey();
    }
    
    /**
     * Identity.
     */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    /**
     * Setting the identity also sets the user key.
     * 
     * @param identity
     */
    public void setIdentity(Identity identity) {
        this.identity = identity;
    	setUserKey(getInternalUserKey());
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
     * "<<Transient>> User principal name.
     */
    @Transient
    public String getUserPrincipalName() {
    	if (getIdentity()!=null) {
    		return getIdentity().getPrincipalName();
    	}
        return "";
    }
    
    /**
     * "<<Transient>> Set user principal name as userName.
     */
    @Transient
    @Override
    protected String getInternalUserName() {
    	return getUserPrincipalName();
    }
    /**
     * User principal domain.
     */
    @Transient
    public String getUserPrincipalDomain() {
    	if (getIdentity()!=null) {
    		return getIdentity().getPrincipalDomain();
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
    public void setUserTypeAsEnum(UserType userType) {
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
	
}
