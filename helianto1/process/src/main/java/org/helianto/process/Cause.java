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

package org.helianto.process;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Entity;
/**
 * <p>
 * Represents a <code>Cause</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_cause",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("C")
public class Cause implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private long internalNumber;
    private int version;
    private String causeDesc;

    /** default constructor */
    public Cause() {
    }

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
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Internal number
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }

    /**
     * Version getter.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Cause description getter.
     */
    @Column(length=512)
    public String getCauseDesc() {
        return this.causeDesc;
    }
    public void setCauseDesc(String causeDesc) {
        this.causeDesc = causeDesc;
    }

    /**
     * <code>Cause</code> factory.
     * 
     * @param entity
     * @param internalNumber
     */
    public static Cause causeFactory(Entity entity, long internalNumber) {
        return causeFactory(Cause.class, entity, internalNumber);
    }

    /**
     * <code>Cause</code> factory.
     * 
     * @param entity
     * @param internalNumber
     */
    public static <T extends Cause> T causeFactory(Class<T> clazz, Entity entity, long internalNumber) {
        T cause = null;
        try {
            cause = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create cause of class "+clazz);
        }
        cause.setEntity(entity);
        cause.setInternalNumber(internalNumber);
        return cause;
    }

    /**
     * <code>Cause</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getCauseQueryStringBuilder() {
        return new StringBuilder("select cause from Cause cause ");
    }

    /**
     * <code>Cause</code> natural id query.
     */
    @Transient
    public static String getCauseNaturalIdQueryString() {
        return getCauseQueryStringBuilder().append("where cause.entity = ? and cause.internalNumber= ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("internalNumber").append("='").append(getInternalNumber()).append("' ");
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
         if ( !(other instanceof Cause) ) return false;
         Cause castOther = (Cause) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }   

}
