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

import java.text.DecimalFormat;
import java.util.Arrays;
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

import org.helianto.core.BusinessAddress;
import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Phone;
import org.helianto.core.PublicAddress;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.number.Sequenceable;

/**
 * Partner registry, a common class to represent Customers, Suppliers and other parties that relate to the owning
 * entity.
 * 
 * @author Mauricio Fernandes de Castro
 */

@javax.persistence.Entity
@Table(name="prtnr_registry",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "partnerAlias"})}
)

public class PrivateEntity 

	extends AbstractAddress 
	
	implements 
	  Sequenceable
	, BusinessAddress 

{

    private static final long serialVersionUID = 1L;
    private Entity entity;
    private String partnerAlias;
    private long internalNumber;
    private String partnerName;
    private Phone mainPhone;
    private String mainEmail;
    private String nature;
    private String parsedContent;
    private Set<Partner> partners = new HashSet<Partner>(0);
    private Set<PrivateAddress> addresses = new HashSet<PrivateAddress>(0);
    private Set<PrivateEntityKey> partnerRegistryKeys = new HashSet<PrivateEntityKey>(0);
    private Set<PartnerPhone> phones = new HashSet<PartnerPhone>(0);
    private @Transient List<Partner> partnerList;
    private @Transient List<PrivateAddress> addressList;
    private @Transient List<PrivateEntityKey> partnerRegistryKeyList;

    /** 
     * Empty constructor.
     */
    public PrivateEntity() {
    	super();
    	setPartnerAlias("");
    	setPartnerName("");
		setMainPhone(new Phone());
		setMainEmail("");
		setInternalNumber(-1);
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

    @Transient
    public String getInternalNumberKey() {
    	return "PRVTENT";
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
        return getInternalEntityAlias();
    }
    public void setEntityAlias(String partnerAlias) {
        this.partnerAlias = partnerAlias;
    }
    
    /**
     * Subclasses may override this to customize automatic generation for the
     * entityAlias property (default behavior expects the user to provide a code).
     * 
     * <p>
     * Default implementation allows the text key property entityAlias to
     * be replaced by a numeric sequence if the property internalNumber
     * is set to 0 (default is -1) and the special symbol '#' is initially 
     * supplied as entityAlias.
     * </p>
     */
    @Transient
    protected String getInternalEntityAlias() {
    	if (partnerAlias!=null && partnerAlias.startsWith("#") && getInternalNumber()>0) {
    		partnerAlias = new DecimalFormat("0").format(getInternalNumber());
    	}
    	return partnerAlias;
    }
    
    /**
     * Convenience method to force initial conditions as required by the
     * method {@link #getInternalEntityAlias()} above to generate entityAlias
     * as a number sequence.
     * 
     * @param force
     */
    @Transient
    public void forceToNumber(boolean force) {
    	if (force) {
        	setEntityAlias("#");
        	setInternalNumber(0);
    	}
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
    
    public long getInternalNumber() {
    	return internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
    	this.internalNumber = internalNumber;
    }
    
    /**
     * Main phone.
     */
    @Embedded
    public Phone getMainPhone() {
		return mainPhone;
	}
    public void setMainPhone(Phone mainPhone) {
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
     * Private entity nature determining Partners to be maintained as aggregates, as a keyword csv.
     */
    @Column(length=128)
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	@Transient
	public String[] getNatureAsArray() {
		if (getNature()!=null && getNature().length()>0) {
			return getNature().replace(" ", "").split(",");
		}
		return new String[] {};
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(Arrays.deepToString(natureArray).replace("[", "").replace("]", "").replace(" ", ""));
	}
    
    /**
     * Text content to be parsed on binding to a custom form.
     */
    @Column(length=512)
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
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
    public Set<PrivateAddress> getAddresses() {
        return this.addresses;
    }
    public void setAddresses(Set<PrivateAddress> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * <<Transient>> Convenience to add address.
     * 
     * @param address
     */
	@Transient
    public boolean addAddress(PrivateAddress address) {
    	return getAddresses().add(address);
    }
	
    /**
     * <<Transient>> Convenience to hold ordered address list.
     */
	@Transient
    public List<PrivateAddress> getAddressList() {
    	return this.addressList;
    }
    public void setAddressList(List<PrivateAddress> addressList) {
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
    public Set<PartnerPhone> getPhones() {
        return this.phones;
    }
    public void setPhones(Set<PartnerPhone> phones) {
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
