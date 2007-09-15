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
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * 			
 * <p>
 * The user group.
 * </p>
 * <p>
 * An user account represents the association between an <code>Identity</code>
 * and an <code>Entity</code>. If authorization fails at user level, the authorization 
 * service will look-up the user hierarchy roles as well.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_user",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "identityId"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("G")
public class UserGroup implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String userKey;
    private Identity identity;
    private char userState;
    private Set<UserAssociation> parentAssociations = new HashSet<UserAssociation>();
    private Set<UserAssociation> childAssociations = new HashSet<UserAssociation>();
    private Set<UserRole> roles = new HashSet<UserRole>();

    /** default constructor */
    public UserGroup() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userId")
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Entity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    /**
     * Entity setter.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * UserKey getter.
     */
    @Column(length=32)
    public String getUserKey() {
        return this.userKey;
    }
    /**
     * UserKey setter.
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * Identity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    /**
     * Identity setter.
     */
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    
    /**
     * UserPrincipal getter.
     */
    @Transient
    public String getUserPrincipal() {
        return this.identity.getPrincipal();
    }

    /**
     * UserState getter.
     */
    public char getUserState() {
        return this.userState;
    }
    /**
     * UserState setter.
     */
    public void setUserState(char userState) {
        this.userState = userState;
    }

    /**
     * ParentAssociations getter.
     */
    @OneToMany(mappedBy="child", fetch=FetchType.EAGER)
    public Set<UserAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    /**
     * ParentAssociations setter.
     */
    public void setParentAssociations(Set<UserAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * ChildAssociations getter.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    public Set<UserAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    /**
     * ChildAssociations setter.
     */
    public void setChildAssociations(Set<UserAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }

    /**
     * Roles getter.
     */
    @OneToMany(mappedBy="userGroup", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public Set<UserRole> getRoles() {
        return this.roles;
    }
    /**
     * Roles setter.
     */
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    /**
     * Internal <code>UserGroup</code> factory.
     * 
     * @param clazz
     * @param entity
     * @param identity
     */
    protected static <T extends UserGroup> T internalUserGroupFactory(Class<T> clazz, Entity entity, Identity identity) {
        try {
            T userGroup = clazz.newInstance();
            userGroup.setEntity(entity);
            if (identity==null) {
                identity = Identity.identityFactory("", "");
            }
            userGroup.setIdentity(identity);
            userGroup.setUserState(ActivityState.ACTIVE.getValue());
            return userGroup;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create class "+clazz, e);
        }
    }
    
    /**
     * <code>UserGroup</code> factory.
     * 
     * @param entity
     */
    public static UserGroup userGroupFactory(Entity entity) {
        return internalUserGroupFactory(UserGroup.class, entity, null);
    }

    /**
     * <code>UserGroup</code> factory.
     * 
     * @param entity
     */
    public static UserGroup userGroupFactory(Entity entity, String userKey) {
    	Identity identity = Identity.identityFactory(userKey, userKey);
    	identity.setIdentityType(IdentityType.GROUP.getValue());
    	UserGroup userGroup = internalUserGroupFactory(UserGroup.class, entity, identity);
    	userGroup.setUserKey(userKey);
        return userGroup;
    }

    /**
     * <code>UserGroup</code> factory.
     * 
     * @param entity
     * @param identity
     */
    public static UserGroup userGroupFactory(Entity entity, Identity identity) {
        return internalUserGroupFactory(UserGroup.class, entity, identity);
    }

    /**
     * <code>UserGroup</code> factory.
     * 
     * @param parent
     */
    public static UserGroup userGroupFactory(UserGroup parent) {
        return userGroupFactory(parent, null);
    }

    /**
     * <code>UserGroup</code> factory.
     * 
     * @param parent
     * @param identity
     */
    public static UserGroup userGroupFactory(UserGroup parent, Identity identity) {
        UserGroup userGroup = userGroupFactory(parent.getEntity(), identity);
        UserAssociation.userAssociationFactory(parent, userGroup);
        return userGroup;
    }

    /**
     * <code>UserGroup</code> natural id query.
     */
    public static StringBuilder getUserGroupQueryStringBuilder() {
        return new StringBuilder("select userGroup from UserGroup userGroup ");
    }

    /**
     * <code>UserGroup</code> natural id query.
     */
    public static String getUserGroupNaturalIdQueryString() {
        return getUserGroupQueryStringBuilder().append("where userGroup.entity = ? and userGroup.identity = ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
        buffer.append("identity").append("='").append(getIdentity()).append("' ");
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
             && ((this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ));
   }
   
   /**
    * hashCode
    */
    @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         return result;
   }   

}
