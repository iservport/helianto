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

package org.helianto.partner;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
/**
 * <p>
 * A simplified class to represent the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_registry",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "partnerAlias"})}
)
@SecondaryTable(name="prtnr_partner",
  pkJoinColumns=@PrimaryKeyJoinColumn(name="partnerRegistryId"))
public class SimplePartnerRegistry implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String partnerAlias;
    private String partnerName;

    private char type;
    private char priority;
    private char partnerState;

    /** default constructor */
    public SimplePartnerRegistry() {
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
     * Entity.
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
     * PartnerAlias getter.
     */
    @Column(length=20)
    public String getPartnerAlias() {
        return this.partnerAlias;
    }
    public void setPartnerAlias(String partnerAlias) {
        this.partnerAlias = partnerAlias;
    }

    /**
     * PartnerName.
     */
    @Column(length=64)
    public String getPartnerName() {
        return this.partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    /**
     * PartnerName (short).
     */
    @Transient
    public String getPartnerShortName() {
    	if (this.partnerName.length() > 20) {
            return this.partnerName.substring(0, 20)+"...";
    	}
        return this.partnerName;
    }

    /**
     * Type getter.
     */
    @Column(table="prtnr_partner")
    public char getType() {
    	return type;
    }

    public void setType(char type) {
    	this.type = type;
    }   

    /**
     * Priority getter.
     */
    @Column(table="prtnr_partner")
    public char getPriority() {
        return this.priority;
    }
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * PartnerState getter.
     */
    @Column(table="prtnr_partner")
    public char getPartnerState() {
        return this.partnerState;
    }
    public void setPartnerState(char partnerState) {
        this.partnerState = partnerState;
    }

    /**
     * <code>PartnerRegistry</code> query.
     */
    @Transient
    public static StringBuilder getSimplePartnerRegistryQueryStringBuilder() {
    	return new StringBuilder("select partnerRegistry from SimplePartnerRegistry partnerRegistry ");
    }   

    /**
     * <code>PartnerRegistry</code> natural id query.
     */
    @Transient
    public static String getSimplePartnerRegistryNaturalIdQueryString() {
    	return getSimplePartnerRegistryQueryStringBuilder().append("where partnerRegistry.entity = ? and partnerRegistry.partnerAlias = ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerAlias").append("='").append(getPartnerAlias()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof SimplePartnerRegistry) ) return false;
         SimplePartnerRegistry castOther = (SimplePartnerRegistry) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getPartnerAlias()==castOther.getPartnerAlias()) || ( this.getPartnerAlias()!=null && castOther.getPartnerAlias()!=null && this.getPartnerAlias().equals(castOther.getPartnerAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerAlias() == null ? 0 : this.getPartnerAlias().hashCode() );
         return result;
   }

}
