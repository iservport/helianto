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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class UserAssociation implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private UserGroup parent;
    private UserGroup child;

    /** default constructor */
    public UserAssociation() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="associationId")
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Parent getter.
     */
    @ManyToOne()
    @JoinColumn(name="parentId", nullable=true)
    public UserGroup getParent() {
        return this.parent;
    }
    /**
     * Parent setter.
     */
    public void setParent(UserGroup parent) {
        this.parent = parent;
    }

    /**
     * Child getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="childId", nullable=true)
    public UserGroup getChild() {
        return this.child;
    }
    /**
     * Child setter.
     */
    public void setChild(UserGroup child) {
        this.child = child;
    }

    /**
     * <code>UserAssociation</code> factory.
     * 
     * @param parent
     * @param child
     */
    public static UserAssociation userAssociationFactory(UserGroup parent, UserGroup child) {
        UserAssociation userAssociation = new UserAssociation();
        userAssociation.setParent(parent);
        userAssociation.setChild(child);
        parent.getChildAssociations().add(userAssociation);
        child.getParentAssociations().add(userAssociation);
        return userAssociation;
    }

    /**
     * <code>UserAssociation</code> natural id query.
     */
    @Transient
    public static String getUserAssociationNaturalIdQueryString() {
        return "select userAssociation from UserAssociation userAssociation where userAssociation.parent = ? and userAssociation.child = ? ";
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("parent").append("='").append(getParent()).append("' ");
        buffer.append("child").append("='").append(getChild()).append("' ");
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
         if ( !(other instanceof UserAssociation) ) return false;
         UserAssociation castOther = (UserAssociation) other; 
         
         return ((this.getParent()==castOther.getParent()) || ( this.getParent()!=null && castOther.getParent()!=null && this.getParent().equals(castOther.getParent()) ))
             && ((this.getChild()==castOther.getChild()) || ( this.getChild()!=null && castOther.getChild()!=null && this.getChild().equals(castOther.getChild()) ));
   }
   
   /**
    * hashCode
    */
    @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getParent() == null ? 0 : this.getParent().hashCode() );
         result = 37 * result + ( getChild() == null ? 0 : this.getChild().hashCode() );
         return result;
   }   

}
