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

package org.helianto.user.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.helianto.core.PersonalEntity;
import org.helianto.core.def.Appellation;
import org.helianto.core.def.Gender;
import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
//	@Transient
	public char getDiscriminator() {
		return 'U';
	}

    private static final long serialVersionUID = 1L;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="identityId", nullable=true)
    private Identity identity;
    
    @Column(length=4)
    private String initials;
    
    private char privacyLevel = PrivacyLevel.PUBLIC.getValue();
    
	@JsonManagedReference 
	@OneToMany(mappedBy="user")
	private Set<UserLog> userLogs = new HashSet<UserLog>(0);

	/** 
	 * Empty constructor.
	 */
    public User() {
    	super();
        setAccountNonExpired(false);
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
//    @Transient
    protected String getInternalUserKey() {
    	if (getIdentity()!=null && getIdentity().getPrincipal()!=null && getIdentity().getPrincipal().length()>0) {
    		return getIdentity().getPrincipal();
    	}
        return super.getInternalUserKey();
    }
    
    /**
     * Identity.
     */
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
     * <<Transient>> Safe user principal.
     */
//    @Transient
    public String getUserPrincipal() {
    	if (getIdentity()==null) {
    		return "";
    	}
        return getIdentity().getPrincipal();
    }
    
    /**
     * "<<Transient>> Safe user principal name.
     */
//    @Transient
    public String getUserPrincipalName() {
    	if (getIdentity()!=null) {
    		return getIdentity().getPrincipalName();
    	}
        return "";
    }
    
    /**
     * <<Transient>> Internal user full name.
     */
//    @Transient
    @Override
    protected String getInternalUserName() {
    	if (getIdentity()!=null) {
    		return getIdentity().getIdentityName();
    	}
    	return getUserPrincipalName();
    }
    
    /**
     * <<Transient>> Safe user principal domain.
     */
//    @Transient
    public String getUserPrincipalDomain() {
    	if (getIdentity()!=null) {
    		return getIdentity().getPrincipalDomain();
    	}
        return "";
    }
    
    /**
     * <<Transient>> Safe user optional alias.
     * @deprecated
     */
//    @Transient
    public String getUserOptionalAlias() {
    	if (getIdentity()!=null) {
    		return getIdentity().getDisplayName();
    	}
        return "";
    }
    
    /**
     * <<Transient>> Safe user display name.
     */
//    @Transient
    public String getUserDisplayName() {
    	if (getIdentity()!=null) {
    		return getIdentity().getDisplayName();
    	}
        return "";
    }
    
    /**
     * <<Transient>> Safe user first name.
     */
//    @Transient
    public String getUserFirstName() {
    	if (getIdentity()!=null) {
    		return getIdentity().getIdentityFirstName();
    	}
        return "";
    }
    
    /**
     * <<Transient>> Safe user last name.
     */
//    @Transient
    public String getUserLastName() {
    	if (getIdentity()!=null) {
    		return getIdentity().getIdentityLastName();
    	}
        return "";
    }
    
    /**
     * <<Transient>> Safe user gender.
     */
//    @Transient
    public char getUserGender() {
    	if (getIdentity()!=null) {
    		return getIdentity().getGender();
    	}
        return Gender.NOT_SUPPLIED.getValue();
    }
    
    /**
     * <<Transient>> Safe user appellation.
     */
//    @Transient
    public char getUserAppelation() {
    	if (getIdentity()!=null) {
    		return getIdentity().getAppellation();
    	}
        return Appellation.NOT_SUPPLIED.getValue();
    }
    
    /**
     * <<Transient>> Safe user birth date.
     */
//    @Transient
    public Date getUserBirthDate() {
    	if (getIdentity()!=null) {
    		return getIdentity().getBirthDate();
    	}
        return new Date();
    }
    
    /**
     * User initials (optional), like JFK, etc..
     */
    public String getInitials() {
		return initials;
	}
    public void setInitials(String initials) {
		this.initials = initials;
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
    public void setPrivacyLevelAsEnum(PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel.getValue();
    }

	/**
	 * A collection of user logs.
	 */
	public Set<UserLog> getUserLogs() {
		return userLogs;
	}
	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}
	
}
