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
import javax.persistence.Version;



import org.helianto.core.Entity;
/**
 * <p>
 * A class to hold last value for internal number lists.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_internalenum",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "typeName"})}
)
public class InternalEnumerator implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String typeName;

    private int version;

    private long lastNumber;

    /** default constructor */
    public InternalEnumerator() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
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
     * TypeName getter.
     */
    @Column(length=12)
    public String getTypeName() {
        return this.typeName;
    }
    /**
     * TypeName setter.
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Version getter.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    /**
     * Version setter.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * LastNumber getter.
     */
    public long getLastNumber() {
        return this.lastNumber;
    }
    /**
     * LastNumber setter.
     */
    public void setLastNumber(long lastNumber) {
        this.lastNumber = lastNumber;
    }

    /**
     * <code>InternalEnumerator</code> factory.
     * 
     * @param entity
     * @param typeName
     */
    public static InternalEnumerator internalEnumeratorFactory(Entity entity, String typeName) {
        InternalEnumerator internalEnumerator = new InternalEnumerator();
        internalEnumerator.setEntity(entity);
        internalEnumerator.setTypeName(typeName);
        return internalEnumerator;
    }

    /**
     * <code>InternalEnumerator</code> natural id query.
     */
    @Transient
    public static String getInternalEnumeratorNaturalIdQueryString() {
        return "select internalEnumerator from InternalEnumerator internalEnumerator where internalEnumerator.entity = ? and internalEnumerator.typeName = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("typeName").append("='").append(getTypeName()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof InternalEnumerator) ) return false;
         InternalEnumerator castOther = (InternalEnumerator) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getTypeName() == null ? 0 : this.getTypeName().hashCode() );
         return result;
   }   

}
