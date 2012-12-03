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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.helianto.core.BusinessAddress;
import org.helianto.core.def.PhoneType;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Phone;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.number.Sequenceable;

/**
 * Partner registry, a common class to represent Customers, Suppliers and other parties that relate to the owning
 * entity.
 * 
 * @author Mauricio Fernandes de Castro
 */

@javax.persistence.Entity
@DiscriminatorValue("R")

public class PrivateEntity2 

	extends PublicEntity 
	
	implements 
	  Sequenceable
	, BusinessAddress 

{

	/**
	 * Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'R';
	}

    private static final long serialVersionUID = 1L;
//    private Entity entity; superclasse
    private PublicEntity publicEntity;
    private boolean autoNumber = false;
//    private String partnerAlias; superclasse
    private long internalNumber;
//    private String partnerName; superclasse
//    private Phone mainPhone;
//    private String mainEmail;
//    private String nature; superclasse
    private String parsedContent;
    private Set<Partner> partners = new HashSet<Partner>(0);
    private Set<PrivateAddress> addresses = new HashSet<PrivateAddress>(0);
    private Set<PrivateEntityKey> partnerRegistryKeys = new HashSet<PrivateEntityKey>(0);
    private Set<PartnerPhone> phones = new HashSet<PartnerPhone>(0);
    private Set<ContactGroup> contactGroups = new HashSet<ContactGroup>(0);

    /** 
     * Empty constructor.
     */
    public PrivateEntity2() {
    	super();
    	setEntityAlias("");
    	setEntityName("");
		setMainPhone(new Phone());
		setMainEmail("");
//		setInternalNumber(-1);
    }

    /** 
     * Entity constructor.
     * 
     * @param entity
     */
    public PrivateEntity2(Entity entity) {
    	this();
    	setEntity(entity);
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param partnerAlias
     */
    public PrivateEntity2(Entity entity, String partnerAlias) {
    	this(entity);
    	setEntityAlias(partnerAlias);
    }

//    /**
//     * Entity.
//     */
//    @ManyToOne
//    @JoinColumn(name="entityId", nullable=true)
//    public Entity getEntity() {
//        return this.entity;
//    }
//    public void setEntity(Entity entity) {
//        this.entity = entity;
//    }
//
    @Transient
    public String getInternalNumberKey() {
    	return "PRVTENT";
    }
    
//    /**
//     * PartnerAlias.
//     * @deprecated use entityAlias instead.
//     */
//    @Transient
//    public String getPartnerAlias() {
//        return getEntityAlias();
//    }
//    public void setPartnerAlias(String partnerAlias) {
//        setEntityAlias(partnerAlias);
//    }
//
//    /** 
//     * Entity alias, copied from public entity if not null.
//     * 
//     * @see #getInternalEntityAlias()
//     */
//    @Column(length=20, name="partnerAlias")
//    public String getEntityAlias() {
//    	if (getPublicEntity()!=null) {
//    		return getPublicEntity().getEntityAlias();
//    	}
//        return getInternalEntityAlias();
//    }
//    public void setEntityAlias(String partnerAlias) {
//        this.partnerAlias = partnerAlias;
//    }
//    
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
    	if (isAutoNumber()) {
    		return new DecimalFormat("0").format(getInternalNumber());
    	}
    	return super.getInternalEntityAlias();
    }
    
    /**
     * True forces entityAlias to be generated from a sequence of numbers.
     */
    public boolean isAutoNumber() {
		return autoNumber;
	}
    public void setAutoNumber(boolean autoNumber) {
		this.autoNumber = autoNumber;
	}

    /**
     * Public entity.
     */
    @ManyToOne
    @JoinColumn(name="publicEntityId", nullable=true)
    public PublicEntity getPublicEntity() {
        return this.publicEntity;
    }
    public void setPublicEntity(PublicEntity publicEntity) {
        this.publicEntity = publicEntity;
    }
    
//    /**
//     * PartnerName.
//     * @deprecated use entityName instead.
//     */
//    @Transient
//    public String getPartnerName() {
//        return getEntityName();
//    }
//    public void setPartnerName(String partnerName) {
//        setEntityName(partnerName);
//    }
//
//    /**
//     * Entity name.
//     */
//    @Column(length=128, name="partnerName")
//    public String getEntityName() {
//    	if (getPublicEntity()!=null) {
//    		return getPublicEntity().getEntityName();
//    	}
//        return this.partnerName;
//    }
//    public void setEntityName(String partnerName) {
//        this.partnerName = partnerName;
//    }
//
//    /**
//     * PartnerName (short).
//     */
//    @Transient
//    public String getPartnerShortName() {
//    	if (getEntityName().length() > 20) {
//            return getEntityName().substring(0, 20)+"...";
//    	}
//        return getEntityName();
//    }
//    
    public long getInternalNumber() {
    	return internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
    	this.internalNumber = internalNumber;
    }
    
//    /**
//     * Main phone.
//     */
//    @Embedded
//    public Phone getMainPhone() {
//    	if (getPublicEntity()!=null) {
//    		return getPublicEntity().getMainPhone();
//    	}
//		return mainPhone;
//	}
//    public void setMainPhone(Phone mainPhone) {
//		this.mainPhone = mainPhone;
//	}
//    
//    // delegate phone
//    
//    /**
//     * Phone number.
//     */
//    @Transient
//    public String getPhoneNumber() {
//    	if (getMainPhone()!=null) {
//    		return getMainPhone().getPhoneNumber();
//    	}
//        return "";
//    }
//    public void setPhoneNumber(String phoneNumber) {
//    	if (getPublicEntity()==null && getMainPhone()!=null) {
//    		getMainPhone().setPhoneNumber(phoneNumber);
//    	}
//    }
//
//    /**
//     * Area code.
//     */
//    @Transient
//    public String getAreaCode() {
//    	if (getMainPhone()!=null) {
//    		return getMainPhone().getAreaCode();
//    	}
//        return "";
//    }
//    public void setAreaCode(String areaCode) {
//    	if (getPublicEntity()==null && getMainPhone()!=null) {
//    		getMainPhone().setAreaCode(areaCode);
//    	}
//    }
//    
//    /**
//     * Branch.
//     */
//    @Transient
//    public String getBranch() {
//    	if (getMainPhone()!=null) {
//    		return getMainPhone().getBranch();
//    	}
//        return "";
//	}
//    public void setBranch(String branch) {
//    	if (getPublicEntity()==null && getMainPhone()!=null) {
//    		getMainPhone().setBranch(branch);
//    	}
//	}
//
//    /**
//     * Phone type.
//     */
//    @Transient
//    public char getPhoneType() {
//    	if (getMainPhone()!=null) {
//    		return getMainPhone().getPhoneType();
//    	}
//        return PhoneType.MAIN.getValue();
//    }
//    public void setPhoneType(char phoneType) {
//    	if (getPublicEntity()==null && getMainPhone()!=null) {
//    		getMainPhone().setPhoneType(phoneType);
//    	}
//    }
//    public void setPhoneTypeAsEnum(PhoneType phoneType) {
//    	if (getPublicEntity()==null && getMainPhone()!=null) {
//    		getMainPhone().setPhoneTypeAsEnum(phoneType);
//    	}
//    }


    
//    /**
//     * Main e-mail.
//     */
//    @Column(length=40)
//    public String getMainEmail() {
//    	if (getPublicEntity()!=null) {
//    		return getPublicEntity().getMainEmail();
//    	}
//		return mainEmail;
//	}
//    public void setMainEmail(String mainEmail) {
//		this.mainEmail = mainEmail;
//	}
//    
//    /**
//     * Private entity nature determining Partners to be maintained as aggregates, as a keyword csv.
//     */
//    @Column(length=128)
//	public String getNature() {
//		return nature;
//	}
//	public void setNature(String nature) {
//		this.nature = nature;
//	}
//	
//	@Transient
//	public String[] getNatureAsArray() {
//		if (getNature()!=null && getNature().length()>0) {
//			return getNature().replace(" ", "").split(",");
//		}
//		return new String[] {};
//	}
//	public void setNatureAsArray(String[] natureArray) {
//		setNature(Arrays.deepToString(natureArray).replace("[", "").replace("]", "").replace(" ", ""));
//	}
    
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
    @OneToMany(mappedBy="privateEntity")
    public Set<Partner> getPartners() {
        return this.partners;
    }
    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    /**
     * Addresses.
     */
    @OneToMany(mappedBy="privateEntity")
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
    @OneToMany(mappedBy="privateEntity")
    public Set<PartnerPhone> getPhones() {
        return this.phones;
    }
    public void setPhones(Set<PartnerPhone> phones) {
        this.phones = phones;
    }
    
    /**
     * Contact groups.
     */
    @OneToMany(mappedBy="privateEntity")
    public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}
    public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

//	/**
//	 * Convenience to add a key type-value pair to the registry.
//	 * 
//	 * @param keyType
//	 * @param keyValue
//	 * @return true if added
//	 */
//	@Transient
//	public boolean addKeyValuePair(KeyType keyType, String keyValue) {
//		PrivateEntityKey partnerRegistryKey = new PrivateEntityKey(this, keyType, keyValue);
//		return getPartnerRegistryKeys().add(partnerRegistryKey);
//	}
//
	
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
         if ( !(other instanceof PrivateEntity2) ) return false;
         PrivateEntity2 castOther = (PrivateEntity2) other; 
         
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
