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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.helianto.core.EntityAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.number.Sequenceable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Partner registry, a common class to represent Customers, Suppliers and other parties that relate to the owning
 * entity.
 * 
 * @author Mauricio Fernandes de Castro
 */

@javax.persistence.Entity
@DiscriminatorValue("R")
public class PrivateEntity 
	extends PublicEntity
	implements 
	  Sequenceable
	, EntityAddress 
{

	/**
	 * Exposes the discriminator.
	 */
//	@Transient
	public char getDiscriminator() {
		return 'R';
	}

    private static final long serialVersionUID = 1L;
    
    @JsonBackReference("publicEntity")
    @ManyToOne
    @JoinColumn(name="publicEntityId", nullable=true)
    private PublicEntity publicEntity;
    
    private boolean autoNumber = false;
    
    private long internalNumber;
    
    @Column(length=512)
    private String parsedContent;
    
//    @JsonManagedReference("privateEntity")
//    @OneToMany(mappedBy="privateEntity")
//    private Set<Partner> partners = new HashSet<Partner>(0);
    
    @JsonManagedReference("privateEntity")
    @OneToMany(mappedBy="privateEntity")
    private Set<PrivateAddress> addresses = new HashSet<PrivateAddress>(0);
    
    @JsonManagedReference 
    @OneToMany(mappedBy="privateEntity", cascade=CascadeType.ALL)
    private Set<PrivateEntityKey> partnerRegistryKeys = new HashSet<PrivateEntityKey>(0);
    
    @JsonManagedReference 
    @OneToMany(mappedBy="privateEntity")
    private Set<PartnerPhone> phones = new HashSet<PartnerPhone>(0);
    
    @JsonManagedReference 
    @OneToMany(mappedBy="privateEntity")
    private Set<ContactGroup> contactGroups = new HashSet<ContactGroup>(0);

    /** 
     * Empty constructor.
     */
    public PrivateEntity() {
    	super();
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
    	setEntityAlias(partnerAlias);
    }

//    @Transient
    public String getInternalNumberKey() {
    	return "PRVTENT";
    }
    
//    @Transient
    public int getStartNumber() {
    	return 1;
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
//    @Transient
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
    public PublicEntity getPublicEntity() {
        return this.publicEntity;
    }
    public void setPublicEntity(PublicEntity publicEntity) {
        this.publicEntity = publicEntity;
    }
    
    public long getInternalNumber() {
    	return internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
    	this.internalNumber = internalNumber;
    }
    
    /**
     * Text content to be parsed on binding to a custom form.
     */
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
	}

//    /**
//     * Partners.
//     */
//    public Set<Partner> getPartners() {
//        return this.partners;
//    }
//    public void setPartners(Set<Partner> partners) {
//        this.partners = partners;
//    }

    /**
     * Addresses.
     */
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
//	@Transient
    public boolean addAddress(PrivateAddress address) {
    	return getAddresses().add(address);
    }
	
    /**
     * Partner registry keys.
     */
    public Set<PrivateEntityKey> getPartnerRegistryKeys() {
		return partnerRegistryKeys;
	}
	public void setPartnerRegistryKeys(Set<PrivateEntityKey> partnerRegistryKeys) {
		this.partnerRegistryKeys = partnerRegistryKeys;
	}
	
    /**
     * Phones.
     */
    public Set<PartnerPhone> getPhones() {
        return this.phones;
    }
    public void setPhones(Set<PartnerPhone> phones) {
        this.phones = phones;
    }
    
    /**
     * Contact groups.
     */
    public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}
    public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
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
