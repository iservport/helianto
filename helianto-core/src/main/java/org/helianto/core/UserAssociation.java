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

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * 				
 * <p>
 * A class to hold user and user groups associations.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 		
 */
@javax.persistence.Entity
@Table(name="core_userassoc", 
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
public class UserAssociation extends AbstractAssociation<UserGroup, UserGroup> implements java.io.Serializable {

    /**
     * <code>UserAssociation</code> factory method.
     * 
     * @param parent
     */
    public static UserAssociation userAssociationFactory(UserGroup parent) {
        UserAssociation userAssociation = (UserAssociation) AbstractAssociation.associationFactory(UserAssociation.class, parent, null);
        parent.getChildAssociations().add(userAssociation);
        return userAssociation;
    }

    /**
     * <code>UserAssociation</code> factory method.
     * 
     * @param parent
     * @param child
     */
    public static UserAssociation userAssociationFactory(UserGroup parent, UserGroup child) {
        UserAssociation userAssociation = (UserAssociation) AbstractAssociation.associationFactory(UserAssociation.class, parent, child);
        parent.getChildAssociations().add(userAssociation);
        child.getParentAssociations().add(userAssociation);
        return userAssociation;
    }

    private static final long serialVersionUID = 1L;

    /** default constructor */
    public UserAssociation() {
    	super();
    }

    /**
     * Parent user group.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", nullable=true)
    public UserGroup getParent() {
        return this.parent;
    }

    /**
     * Child user group.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="childId", nullable=true)
    public UserGroup getChild() {
        return this.child;
    }

    @SuppressWarnings("unchecked")
	@Override
    protected int compareChild(AbstractAssociation other) {
    	if (this.getChild()!=null && other.getChild()!=null) {
    		return this.getChild().compareTo((UserGroup) other.getChild());
    	}
    	return 0;
    }
       
    /**
     * Natural key info.
     */
    @Transient
    public boolean isKeyEmpty() {
    	if (this.getChild()!=null) {
    		return this.getChild().isKeyEmpty();
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }

   /**
    * equals
    */
    @Override
   public boolean equals(Object other) {
         if ( !(other instanceof UserAssociation) ) return false;
         return super.equals(other);
   }

}
