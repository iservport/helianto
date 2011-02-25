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
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.TrunkEntity;
/**
 * Only <code>PrivateEntity</code> instances may have rights to operate with the (owning) <code>Entity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_registry",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "partnerAlias"})}
)
public class PrivateEntity extends AbstractAddress implements TrunkEntity, BusinessUnit {

    private static final long serialVersionUID = 1L;
    private Entity entity;
    private String partnerAlias;
    private String partnerName;
    private AbstractPhone mainPhone;
    private String mainEmail;
    private Set<Partner> partners = new HashSet<Partner>(0);
    private Set<Address> addresses = new HashSet<Address>(0);
    private Set<PrivateEntityKey> partnerRegistryKeys = new HashSet<PrivateEntityKey>(0);
    private Set<Phone> phones = new HashSet<Phone>(0);
    private @Transient List<Partner> partnerList;
    private @Transient List<Address> addressList;
    private @Transient List<PrivateEntityKey> partnerRegistryKeyList;

    /** 
     * Empty constructor.
     */
    public PrivateEntity() {
    	super();
    	setPartnerAlias("");
    	setPartnerName("");
		setMainPhone(new AbstractPhone());
		setMainEmail("");
    }

    /** 
     * Entity constructor.
     * 
     * @param entity
     */
    public PrivateEntity(Entity entity) {
    	this();
    	setEntity(entity);
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param partnerAlias
     */
    public PrivateEntity(Entity entity, String partnerAlias) {
    	this(entity);
    	setPartnerAlias(partnerAlias);
    }

    /**
     * Entity.
     */
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * PartnerAlias.
     * @deprecated use entityAlias instead.
     */
    @Transient
    public String getPartnerAlias() {
        return getEntityAlias();
    }
    public void setPartnerAlias(String partnerAlias) {
        setEntityAlias(partnerAlias);
    }

    /**
     * Entity alias.
     */
    @Column(length=20, name="partnerAlias")
    public String getEntityAlias() {
        return this.partnerAlias;
    }
    public void setEntityAlias(String partnerAlias) {
        this.partnerAlias = partnerAlias;
    }

    /**
     * PartnerName.
     * @deprecated use entityName instead.
     */
    @Transient
    public String getPartnerName() {
        return getEntityName();
    }
    public void setPartnerName(String partnerName) {
        setEntityName(partnerName);
    }

    /**
     * Entity name.
     */
    @Column(length=64, name="partnerName")
    public String getEntityName() {
        return this.partnerName;
    }
    public void setEntityName(String partnerName) {
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
     * Main phone.
     */
    @Embedded
    public AbstractPhone getMainPhone() {
		return mainPhone;
	}
    public void setMainPhone(AbstractPhone mainPhone) {
		this.mainPhone = mainPhone;
	}
    
    /**
     * Main e-mail.
     */
    @Column(length=40)
    public String getMainEmail() {
		return mainEmail;
	}
    public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

    /**
     * Partners.
     */
    @OneToMany(mappedBy="privateEntity", fetch=FetchType.EAGER)
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
     * Partner registry keys.
     */
    @OneToMany(mappedBy="privateEntity", cascade=CascadeType.ALL)
    public Set<PrivateEntityKey> getPartnerRegistryKeys() {
		return partnerRegistryKeys;
	}
	public void setPartnerRegistryKeys(Set<PrivateEntityKey> partnerRegistryKeys) {
		this.partnerRegistryKeys = partnerRegistryKeys;
	}
	
    /**
     * Phones.
     */
    @OneToMany(mappedBy="partnerRegistry", fetch=FetchType.EAGER)
    public Set<Phone> getPhones() {
        return this.phones;
    }
    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
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
		PrivateEntityKey partnerRegistryKey = new PrivateEntityKey(this, keyType, keyValue);
		return getPartnerRegistryKeys().add(partnerRegistryKey);
	}

    /**
     * <<Transient>> Convenience to hold ordered partner key list.
     */
	@Transient
    public List<PrivateEntityKey> getPartnerRegistryKeyList() {
    	return this.partnerRegistryKeyList;
    }
    public void setPartnerRegistryKeyList(List<PrivateEntityKey> partnerRegistryKeyList) {
        this.partnerRegistryKeyList = partnerRegistryKeyList;
    }
    
	/**
	 * Update fields provided by <code>PublicAddress</code>.
	 * 
	 * @param publicAddress
	 */
	public void setPublicAddress(PublicAddress publicAddress) {
		if (publicAddress!=null) {
			setAddress1(publicAddress.getAddress1());
			setAddress2(publicAddress.getAddress2());
			setPostalCode(publicAddress.getPostalCode());
			setProvince(publicAddress.getProvince());
		}
	}
	
    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerAlias").append("='").append(getEntityAlias()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PrivateEntity) ) return false;
         PrivateEntity castOther = (PrivateEntity) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getEntityAlias()==castOther.getEntityAlias()) || ( this.getEntityAlias()!=null && castOther.getEntityAlias()!=null && this.getEntityAlias().equals(castOther.getEntityAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntityAlias() == null ? 0 : this.getEntityAlias().hashCode() );
         return result;
   }   

}
