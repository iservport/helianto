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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
/**
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_registry",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "partnerAlias"})}
)
public class PartnerRegistry implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String partnerAlias;
    private String partnerName;
    private Set<Partner> partners = new HashSet<Partner>(0);
    private Set<Address> addresses = new HashSet<Address>(0);

    /** default constructor */
    public PartnerRegistry() {
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
     * PartnerAlias.
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
     * Partners getter.
     */
    @OneToMany(mappedBy="partnerRegistry", fetch=FetchType.EAGER)
    public Set<Partner> getPartners() {
        return this.partners;
    }
    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    /**
     * Addresses getter.
     */
    @OneToMany(mappedBy="partnerRegistry", fetch=FetchType.EAGER)
    public Set<Address> getAddresses() {
        return this.addresses;
    }
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * <code>PartnerAssociation</code> factory.
     * 
     * @param entity
     * @param partnerAlias
     */
    public static PartnerRegistry partnerRegistryFactory(Entity entity, String partnerAlias) {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        partnerRegistry.setEntity(entity);
        partnerRegistry.setPartnerAlias(partnerAlias);
        return partnerRegistry;
    }

    /**
     * <code>PartnerRegistry</code> query.
     */
    @Transient
    public static StringBuilder getPartnerRegistryQueryStringBuilder() {
    	return new StringBuilder("select partnerRegistry from PartnerRegistry partnerRegistry ");
    }   

    /**
     * <code>PartnerRegistry</code> natural id query.
     */
    @Transient
    public static String getPartnerRegistryNaturalIdQueryString() {
    	return getPartnerRegistryQueryStringBuilder().append("where partnerRegistry.entity = ? and partnerRegistry.partnerAlias = ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
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
         if ( !(other instanceof PartnerRegistry) ) return false;
         PartnerRegistry castOther = (PartnerRegistry) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getPartnerAlias()==castOther.getPartnerAlias()) || ( this.getPartnerAlias()!=null && castOther.getPartnerAlias()!=null && this.getPartnerAlias().equals(castOther.getPartnerAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getPartnerAlias() == null ? 0 : this.getPartnerAlias().hashCode() );
         return result;
   }   

}
