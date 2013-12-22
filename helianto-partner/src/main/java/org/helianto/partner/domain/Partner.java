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

package org.helianto.partner.domain;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Address;
import org.helianto.core.EntityAddress;
import org.helianto.core.domain.City;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Phone;
import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.partner.PartnerState;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
/**
 * Base class to represent the relationship between the organization and other entities.
 * 
 * <p>
 * Tipical <code>Partner</code> extensions are Customers, Suppliers, etc. Every <code>Partner</code>
 * (or subclass) instance imposes the existence of a <code>PrivateEntity</code> instance where default registry 
 * data is stored. Subclasses are then free to override the default registry, delegated from its owning
 * <code>PrivateEntity</code>.
 * </p>
 * <p>
 * A transient {@link #newEntityAlias} field is provided to allow the service layer to create a new <code>Partner</code>
 * and a new owning <code>PrivateEntity</code> in one single call.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_partner",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "type"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("P")

public class Partner 
	implements 
	  TrunkEntity
	, EntityAddress {

    private static final long serialVersionUID = 1L;
    private int id;
    private PrivateEntity privateEntity;
    private String newEntityAlias;
    private Account account;
    private char priority;
    private char partnerState;
    private char taxClass;
    private Set<PartnerKey> partnerKeys = new HashSet<PartnerKey>(0);
    private Set<PartnerCategory> partnerCategories = new HashSet<PartnerCategory>(0);
    
    /**
     * <<Transient>> Discriminator.
     */
    @Transient
    public char getDiscriminator() {
    	return 'P';
    }

	/**
	 *  Empty constructor
	 */
    public Partner() {
    	setNewEntityAlias("");
        setPartnerState(PartnerState.ACTIVE.getValue());
        setPriority('0');
        setTaxClass('3');
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public Partner(PrivateEntity partnerRegistry) {
    	this();
    	setPrivateEntity(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Partner(Entity entity, String partnerAlias) {
    	this();
    	setPrivateEntity(new PrivateEntity(entity, partnerAlias));
    }

    /**
     * Entity constructor.
     * 
	 * <p>
	 * Create a backing {@link PrivateEntity} and associate a new Customer to it.
	 * </p>
	 * 
     * @param entity
     */
    public Partner(Entity entity) {
    	this(new PrivateEntity(entity));
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
     * Private entity.
     * 
     * <p>
     * Never null.
     * </p>
     */
    @JsonBackReference("privateEntity")
    @ManyToOne
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PrivateEntity getPrivateEntity() {
    	if (this.privateEntity==null) {
    		this.privateEntity = new PrivateEntity();
    	}
        return this.privateEntity;
    }
    public void setPrivateEntity(PrivateEntity privateEntity) {
        this.privateEntity = privateEntity;
    }
    
	/**
	 * <<Transient>> Should be used only to install a new PrivateEntity.
	 */
	@Transient
	public String getNewEntityAlias() {
		return this.newEntityAlias;
	}
	public void setNewEntityAlias(String newEntityAlias) {
		this.newEntityAlias = newEntityAlias;
	}
	
    /**
     * <<Transient>> Convenience to retrieve <code>Entity</code> from parent
     * <code>PrivateEntity</code>.
     */
    @Transient
    public Entity getEntity() {
    	return getPrivateEntity().getEntity();
    }
    
	/**
	 * <<Transient>> True if the owning <code>PrivateEntity</code> has alias.
	 */
	@Transient
	public boolean isPrivateEntityValid() {
		return getEntityAlias().length()>0;
	}
	
	/**
	 * <<Transient>> Should be used only to create a new <code>PrivateEntity</code>.
	 */
	@Transient
	public boolean isNewPrivateEntityRequested(Entity entity) {
		if (!isPrivateEntityValid() && getNewEntityAlias().length()>0) {
			getPrivateEntity().setEntity(entity);
			getPrivateEntity().setEntityAlias(getNewEntityAlias());
			setNewEntityAlias("");
			return true;
		}
		return false;
	}
	
    // Implementation of the BusinessUnit interface
    // future implementations may choose from addresses on the owning public entity.

    @Transient
    public String getEntityAlias() {
    	return getPrivateEntity().getEntityAlias();
    }
    
    @Transient
    public String getEntityName() {
    	return getPrivateEntity().getEntityName();
    }

    @Transient
    public String getAddress1() {
        return this.getPrivateEntity().getAddress1();
    }
    
    @Transient
    public String getAddressClassifier() {
        return this.getPrivateEntity().getAddressClassifier();
    }
    
    @Transient
    public String getAddressNumber() {
    	return this.getPrivateEntity().getAddressNumber();
    }
    
    @Transient
    public String getAddressDetail() {
    	return this.getPrivateEntity().getAddressDetail();
    }

    @Transient
    public String getAddress2() {
        return this.getPrivateEntity().getAddress2();
    }

    @Transient
    public String getAddress3() {
        return this.getPrivateEntity().getAddress3();
    }
    
    @Transient
    public String getPostOfficeBox() {
    	return this.getPrivateEntity().getPostOfficeBox();
    }

    @Transient
    public String getPostalCode() {
        return this.getPrivateEntity().getPostalCode();
    }

    @Transient
    public City getCity() {
        return this.getPrivateEntity().getCity();
    }
    
    @Transient
    public String getCityName() {
    	return this.getPrivateEntity().getCityName();
    }
    
    @Transient
    public String getShortAddress() {
    	return this.getPrivateEntity().getShortAddress();
    }
    
    @Transient
    public Phone getMainPhone() {
    	return this.getPrivateEntity().getMainPhone();
    }

    @Transient
    public String getMainEmail() {
    	return this.getPrivateEntity().getMainEmail();
    }

    // 
    
    /**
     * <<Transient>> Return the current addressee.
     * 
     * <p>
     * Current implementation defines the current addressee as the parent
     * partner registry.
     * </p>
     */
    @Transient
    protected Address getAddresse() {
    	return getAddressee(-1);
    }

    /**
     * <<Transient>> Return the addressee selected by an index.
     * 
     * <p>
     * Current implementation allways returns the parent
     * partner registry.
     * </p>
     * 
     * @param index
     */
    @Transient
    protected Address getAddressee(int index) {
    	return getPrivateEntity();
    }

    // setter methods rely on getAddresse to update its contents.

    /**
     * Set the current addressee address1.
     * 
     * @param address1
     */
    public void setAddress1(String address1) {
        getPrivateEntity().setAddress1(address1);
    }
    
    /**
     * Set the current addressee addressNumber.
     * 
     * @param addressNumber
     */
    public void setAddressNumber(String addressNumber) {
        getPrivateEntity().setAddressNumber(addressNumber);
    }
    
    /**
     * Set the current addressee addressDetail.
     * 
     * @param addressDetail
     */
    public void setAddressDetail(String addressDetail) {
        getPrivateEntity().setAddressDetail(addressDetail);
    }

    /**
     * Set the current addressee address2.
     * 
     * @param address2
     */
    public void setAddress2(String address2) {
        getPrivateEntity().setAddress2(address2);
    }

    /**
     * Set the current addressee address3.
     * 
     * @param address3
     */
    public void setAddress3(String address3) {
        getPrivateEntity().setAddress3(address3);
    }
    
    /**
     * Set the current addressee postOfficeBox.
     * 
     * @param postOfficeBox
     */
    public void setPostOfficeBox(String postOfficeBox) {
        getPrivateEntity().setPostOfficeBox(postOfficeBox);
    }

    /**
     * Set the current addressee postalCode.
     * 
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        getPrivateEntity().setPostalCode(postalCode);
    }

    /**
     * Set the current addressee city.
     * 
     * @param city
     */
    public void setCity(City city) {
        getPrivateEntity().setCity(city);
    }
    
    /**
     * Set the current addressee cityName.
     * 
     * @param cityName
     */
    public void setCityName(String cityName) {
        getPrivateEntity().setCityName(cityName);
    }
    
    /**
     * <<Transient>> Convenience to add address.
     * 
     * @param address
     */
	@Transient
    public boolean addAddress(PrivateAddress address) {
    	return getPrivateEntity().getAddresses().add(address);
    }
	
    /**
     * Account.
     */
    @JsonBackReference("account")
    @ManyToOne
    @JoinColumn(name="accountId", nullable=true)
    public Account getAccount() {
        return this.account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Priority.
     */
    public char getPriority() {
        return this.priority;
    }
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * Partner state.
     */
    public char getPartnerState() {
        return this.partnerState;
    }
    public void setPartnerState(char partnerState) {
        this.partnerState = partnerState;
    }
    public void setPartnerStateAsEnum(PartnerState partnerState) {
        this.partnerState = partnerState.getValue();
    }
    
    /**
     * Class related to tax requirements.
     */
    public char getTaxClass() {
		return taxClass;
	}
    public void setTaxClass(char taxClass) {
		this.taxClass = taxClass;
	}

    /**
     * Partner keys.
     */
    @JsonManagedReference 
    @OneToMany(mappedBy="partner")
    public Set<PartnerKey> getPartnerKeys() {
		return partnerKeys;
	}
	public void setPartnerKeys(Set<PartnerKey> partnerKeys) {
		this.partnerKeys = partnerKeys;
	}
	
    /**
     * Partner categories.
     */
    @JsonManagedReference("partner") 
    @OneToMany(mappedBy="partner")
	public Set<PartnerCategory> getPartnerCategories() {
		return partnerCategories;
	}
	public void setPartnerCategories(Set<PartnerCategory> partnerCategories) {
		this.partnerCategories = partnerCategories;
	}
	
	/**
	 * Convenience to add a key type-value pair to the partner.
	 * 
	 * @param keyType
	 * @param keyValue
	 * @return true if added
	 */
	@Transient
	public boolean addKeyValuePair(KeyType keyType, String keyValue) {
		PartnerKey partnerKey = new PartnerKey(this, keyType);
		partnerKey.setKeyValue(keyValue);
		return getPartnerKeys().add(partnerKey);
	}

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerRegistry").append("='").append(getPrivateEntity()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Partner) ) return false;
         Partner castOther = (Partner) other; 
         
         return ((this.getPrivateEntity()==castOther.getPrivateEntity()) || ( this.getPrivateEntity()!=null && castOther.getPrivateEntity()!=null && this.getPrivateEntity().equals(castOther.getPrivateEntity()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPrivateEntity() == null ? 0 : this.getPrivateEntity().hashCode() );
         return result;
   }

}
