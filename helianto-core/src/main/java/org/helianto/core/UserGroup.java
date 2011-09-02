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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * 			
 * An user account (or group) represents a set of roles within an <code>Entity</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_user",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "userKey"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("G")
public class UserGroup implements TrunkEntity, Comparable<UserGroup>, NaturalKeyInfo {
	
	/**
	 * <<Transient>> Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'G';
	}

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String userKey;
    private Date lastEvent;
    private char userState;
    private boolean accountNonExpired;
    private char createIdentity;
    private Set<UserAssociation> parentAssociations = new HashSet<UserAssociation>();
    private Set<UserAssociation> childAssociations = new HashSet<UserAssociation>();
    private List<UserAssociation> childAssociationList = new ArrayList<UserAssociation>();
	private Set<UserRole> roles = new HashSet<UserRole>();
    private List<UserRole> roleList = new ArrayList<UserRole>();
    
	/** 
	 * Empty constructor.
	 */
    public UserGroup() {
    	setUserKey("");
    	setLastEvent(new Date());
    	setUserStateAsEnum(UserState.ACTIVE);
    	setAccountNonExpired(true);
    	setCreateIdentity(CreateIdentity.REJECT);
    }

	/** 
	 * Entity constructor.
	 * 
	 * @param entity
	 */
    public UserGroup(Entity entity) {
    	this();
    	setEntity(entity);
    }

	/** 
	 * User group constructor.
	 * 
	 * @param entity
	 * @param userKey
	 */
    public UserGroup(Entity entity, String userKey) {
    	this(entity);
    	setUserKey(userKey);
    }

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userId")
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Entity.
     */
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    @Transient
    public Locale getLocale() {
		return getEntity().getLocale();
	}
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * <<Transient>> Convenience to return Operator.
     */
    @Transient
    public Operator getOperator() {
		return getEntity().getOperator();
	}

    /**
     * User key.
     */
    @Column(length=40)
    public String getUserKey() {
        return getInternalUserKey();
    }
    
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    @Transient
    public boolean isUserKeyEmpty() {
    	return getUserKey().length()==0;
    }

    /**
     * <<Transient>> Subclasses may override to customize userKey creation.
     */
    @Transient
    protected String getInternalUserKey() {
    	return this.userKey;
    }

    /**
     * Last event
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastEvent() {
		return lastEvent;
	}
	public void setLastEvent(Date lastEvent) {
		this.lastEvent = lastEvent;
	}

    /**
     * UserState getter.
     */
    public char getUserState() {
        return this.userState;
    }
    public void setUserState(char userState) {
        this.userState = userState;
    }
    public void setUserStateAsEnum(UserState userState) {
        this.userState = userState.getValue();
    }

    /**
     * AccountNonExpired getter.
     */
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

	/**
     * <<Transient>> May be used by the presentation layer to
     * signal automatic identity creation"
     */
    @Transient
    public char getCreateIdentity() {
		return createIdentity;
	}
	public void setCreateIdentity(char createIdentity) {
		this.createIdentity = createIdentity;
	}
	public void setCreateIdentity(CreateIdentity createIdentity) {
		this.createIdentity = createIdentity.getValue();
	}
	
	/**
     * Parent associations.
     */
    @OneToMany(mappedBy="child")
    public Set<UserAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    public void setParentAssociations(Set<UserAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * Child associations.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.ALL})
    public Set<UserAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    public void setChildAssociations(Set<UserAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }

    /**
     * <<Transient>> Child association list.
     */
    @Transient
    public List<UserAssociation> getChildAssociationList() {
		return childAssociationList;
	}
	public void setChildAssociationList(List<UserAssociation> childAssociationList) {
		this.childAssociationList = childAssociationList;
	}

    /**
     * Roles for this group.
     */
    @OneToMany(mappedBy="userGroup")
    public Set<UserRole> getRoles() {
        return this.roles;
    }
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    /**
     * <<Transient>> User role list.
     */
    @Transient
	public List<UserRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}

	public int compareTo(UserGroup other) {
		if (getUserKey()!=null && other!=null && other.getUserKey()!=null) {
			return getUserKey().compareTo(other.getUserKey());
		}
		return 1;
	}   

    /**
     * Internal <code>UserGroup</code> factory.
     * 
     * @param clazz
     * @param entity
     * @param userKey
     */
    protected static <T extends UserGroup> T internalUserGroupFactory(Class<T> clazz, Entity entity, String userKey) {
        try {
            T userGroup = clazz.newInstance();
            userGroup.setEntity(entity);
            userGroup.setUserKey(userKey);
            userGroup.setUserState(ActivityState.ACTIVE.getValue());
            return userGroup;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create class "+clazz, e);
        }
    }
    
    /**
     * Natural key info.
     */
    @Transient
    public boolean isKeyEmpty() {
    	if (this.getUserKey()!=null) {
    		return getUserKey().length()==0;
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("userKey").append("='").append(getUserKey()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof UserGroup) ) return false;
         UserGroup castOther = (UserGroup) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getUserKey()==castOther.getUserKey()) || ( this.getUserKey()!=null && castOther.getUserKey()!=null && this.getUserKey().equals(castOther.getUserKey()) ));
   }
   
   /**
    * hashCode
    */
    @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getUserKey() == null ? 0 : this.getUserKey().hashCode() );
         return result;
   }

}
