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
import java.util.List;
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
import org.helianto.core.KeyType;
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

    /**
     * Factory method.
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

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String partnerAlias;
    private String partnerName;
    private Set<Partner> partners = new HashSet<Partner>(0);
    private Set<Address> addresses = new HashSet<Address>(0);
    private Set<PartnerRegistryKey> partnerRegistryKeys = new HashSet<PartnerRegistryKey>(0);
    private @Transient List<Partner> partnerList;
    private @Transient List<Address> addressList;
    private @Transient Address mainAddress;
    private @Transient List<PartnerRegistryKey> partnerRegistryKeyList;

    /** default constructor */
    public PartnerRegistry() {
    }

    /**
     * Primary key.
     */
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
     * Partners.
     */
    @OneToMany(mappedBy="partnerRegistry", fetch=FetchType.EAGER)
    public Set<Partner> getPartners() {
        return this.partners;
    }
    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    /**
     * <<Transient>> Convenience to hold ordered partner list.
     */
	@Transient
    public List<Partner> getPartnerList() {
    	return this.partnerList;
    }
    public void setPartnerList(List<Partner> partnerList) {
        this.partnerList = partnerList;
    }
    
    /**
     * Addresses.
     */
    @OneToMany(mappedBy="partnerRegistry"
    	, cascade=CascadeType.ALL
    	, fetch=FetchType.EAGER)
    public Set<Address> getAddresses() {
        return this.addresses;
    }
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * <<Transient>> Convenience to add address.
     * 
     * @param address
     */
	@Transient
    public boolean addAddress(Address address) {
    	return getAddresses().add(address);
    }
	
    /**
     * <<Transient>> Convenience to hold ordered address list.
     */
	@Transient
    public List<Address> getAddressList() {
    	return this.addressList;
    }
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
    
    /**
     * <<Transient>> Convenience hold the main address.
     */
	@Transient
    public Address getMainAddress() {
    	return mainAddress;
    }
    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    /**
     * Partner registry keys.
     */
    @OneToMany(mappedBy="partnerRegistry")
    public Set<PartnerRegistryKey> getPartnerRegistryKeys() {
		return partnerRegistryKeys;
	}
	public void setPartnerRegistryKeys(Set<PartnerRegistryKey> partnerRegistryKeys) {
		this.partnerRegistryKeys = partnerRegistryKeys;
	}
	
	/**
	 * Convenience to add a key type-value pair to the registry.
	 * 
	 * @param keyType
	 * @param keyValue
	 * @return true if added
	 */
	@Transient
	public boolean addKeyValuePair(KeyType keyType, String keyValue) {
		PartnerRegistryKey partnerRegistryKey = PartnerRegistryKey.partnerRegistryKeyFactory(this, keyType);
		partnerRegistryKey.setKeyValue(keyValue);
		return getPartnerRegistryKeys().add(partnerRegistryKey);
	}

    /**
     * <<Transient>> Convenience to hold ordered partner key list.
     */
	@Transient
    public List<PartnerRegistryKey> getPartnerRegistryKeyList() {
    	return this.partnerRegistryKeyList;
    }
    public void setPartnerRegistryKeyList(List<PartnerRegistryKey> partnerRegistryKeyList) {
        this.partnerRegistryKeyList = partnerRegistryKeyList;
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
         result = 37 * result + ( getPartnerAlias() == null ? 0 : this.getPartnerAlias().hashCode() );
         return result;
   }   

}
