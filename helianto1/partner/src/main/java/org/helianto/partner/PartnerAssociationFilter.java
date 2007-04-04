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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.User;
/**
 * <p>
 * Represents a filter to <code>PartnerAssociation</code>.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_partnerassocfilter",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "partnerAssociationFilterAlias"})}
)
public class PartnerAssociationFilter implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String partnerAssociationFilterAlias;
    private User user;
    private String partnerAssociationName;
    private char partnerType;

    /** default constructor */
    public PartnerAssociationFilter() {
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
     * PartnerAssociationFilterAlias getter.
     */
    @Column(length=20)
    public String getPartnerAssociationFilterAlias() {
        return this.partnerAssociationFilterAlias;
    }
    /**
     * PartnerAssociationFilterAlias setter.
     */
    public void setPartnerAssociationFilterAlias(String partnerAssociationFilterAlias) {
        this.partnerAssociationFilterAlias = partnerAssociationFilterAlias;
    }

    /**
     * User getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="userId", nullable=true)
    public User getUser() {
        return this.user;
    }
    /**
     * User setter.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * PartnerAssociationName getter.
     */
    @Column(length=32)
    public String getPartnerAssociationName() {
        return this.partnerAssociationName;
    }
    /**
     * PartnerAssociationName setter.
     */
    public void setPartnerAssociationName(String partnerAssociationName) {
        this.partnerAssociationName = partnerAssociationName;
    }

    /**
     * PartnerType getter.
     */
    public char getPartnerType() {
        return this.partnerType;
    }
    /**
     * PartnerType setter.
     */
    public void setPartnerType(char partnerType) {
        this.partnerType = partnerType;
    }

    /**
     * <code>PartnerAssociationFilter</code> factory.
     * 
     * @param entity
     * @param partnerAssociationFilterAlias
     */
    public static PartnerAssociationFilter partnerAssociationFilterFactory(Entity entity, String partnerAssociationFilterAlias) {
        PartnerAssociationFilter partnerAssociationFilter = new PartnerAssociationFilter();
        partnerAssociationFilter.setEntity(entity);
        partnerAssociationFilter.setPartnerAssociationFilterAlias(partnerAssociationFilterAlias);
        return partnerAssociationFilter;
    }

    /**
     * <code>PartnerAssociationFilter</code> natural id query.
     */
    @Transient
    public static String getPartnerAssociationFilterNaturalIdQueryString() {
        return "select partnerAssociationFilter from PartnerAssociationFilter partnerAssociationFilter where partnerAssociationFilter.entity = ? and partnerAssociationFilter.partnerAssociationFilterAlias = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
        buffer.append("partnerAssociationFilterAlias").append("='").append(getPartnerAssociationFilterAlias()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PartnerAssociationFilter) ) return false;
         PartnerAssociationFilter castOther = (PartnerAssociationFilter) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getPartnerAssociationFilterAlias()==castOther.getPartnerAssociationFilterAlias()) || ( this.getPartnerAssociationFilterAlias()!=null && castOther.getPartnerAssociationFilterAlias()!=null && this.getPartnerAssociationFilterAlias().equals(castOther.getPartnerAssociationFilterAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getPartnerAssociationFilterAlias() == null ? 0 : this.getPartnerAssociationFilterAlias().hashCode() );
         return result;
   }   

}
