/* Copyright 2005-2016 I Serv Consultoria Empresarial Ltda.
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.helianto.core.domain.City;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Phone;
import org.helianto.partner.domain.enums.PartnerState;
import org.helianto.partner.domain.enums.PartnerType;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Base class to represent the relationship between the organization and other 
 * entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="p_partner",
    uniqueConstraints = {@UniqueConstraint(columnNames={"privateEntityId", "partnerType"})}
)
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="privateEntityId", nullable=true)
    private PrivateEntity privateEntity;
    
    @Transient
	private Integer privateEntityId;

    @Enumerated(EnumType.STRING) @Column(length = 20)
	private PartnerType partnerType;
    
    @Transient
    private String newEntityAlias = "";
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="accountId", nullable=true)
    private Account account;
    
    @Transient
	private Integer accountId;
    
	private Integer priority = 0;
    
    @Enumerated @Column(length=20)
    private PartnerState partnerState = PartnerState.ACTIVE;
    
	private char taxClass = '3';
    
	/**
	 *  Empty constructor
	 */
    public Partner() {
    	super();
    }

	/**
     * Private entity constructor.
     * 
     * @param privateEntity
     */
    public Partner(PrivateEntity privateEntity) {
    	this();
    	setPrivateEntity(privateEntity);
    }

	/**
     * Key constructor.
     * 
     * @param privateEntity
     * @param partnerType
     */
    public Partner(PrivateEntity privateEntity, PartnerType partnerType) {
    	this(privateEntity);
    	setPartnerType(partnerType);
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
     * Primary key getter.
     */
    public int getId() {
		return id;
	}
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Private entity.
     */
    public PrivateEntity getPrivateEntity() {
        return this.privateEntity;
    }
    public void setPrivateEntity(PrivateEntity privateEntity) {
        this.privateEntity = privateEntity;
    }
    
    public Integer getPrivateEntityId() {
    	if (getPrivateEntity()!=null) {
    		return getPrivateEntity().getId();
    	}
		return privateEntityId;
	}
    public void setPrivateEntityId(Integer privateEntityId) {
		this.privateEntityId = privateEntityId;
	}
    
	/**
	 * <<Transient>> True if the owning <code>PrivateEntity</code> has alias.
	 */
	public boolean isPrivateEntityValid() {
		return getEntityAlias().length()>0;
	}
	
    public String getEntityAlias() {
    	return getPrivateEntity().getEntityAlias();
    }
    
    public String getEntityName() {
    	return getPrivateEntity().getEntityName();
    }

    public String getAddress1() {
        return this.getPrivateEntity().getAddress1();
    }
    
    public String getAddressClassifier() {
        return this.getPrivateEntity().getAddressClassifier();
    }
    
    public String getAddressNumber() {
    	return this.getPrivateEntity().getAddressNumber();
    }
    
    public String getAddressDetail() {
    	return this.getPrivateEntity().getAddressDetail();
    }

    public String getAddress2() {
        return this.getPrivateEntity().getAddress2();
    }

    public String getAddress3() {
        return this.getPrivateEntity().getAddress3();
    }
    
    public String getPostOfficeBox() {
    	return this.getPrivateEntity().getPostOfficeBox();
    }

    public String getPostalCode() {
        return this.getPrivateEntity().getPostalCode();
    }

    public City getCity() {
        return this.getPrivateEntity().getCity();
    }
    
    public String getCityName() {
    	return this.getPrivateEntity().getCityName();
    }
    
    public String getShortAddress() {
    	return this.getPrivateEntity().getShortAddress();
    }
    
    public Phone getMainPhone() {
    	return this.getPrivateEntity().getMainPhone();
    }

    public String getMainEmail() {
    	return this.getPrivateEntity().getMainEmail();
    }

    public PartnerType getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerType partnerType) {
        this.partnerType = partnerType;
    }

    /**
     * Account.
     */
    public Account getAccount() {
        return this.account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Integer getAccountId() {
		if (getAccount()!=null) {
			return getAccount().getId();
		}
		return accountId;
	}
    public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

    public String getNewEntityAlias() {
        return this.newEntityAlias;
    }
    public void setNewEntityAlias(String newEntityAlias) {
        this.newEntityAlias = newEntityAlias;
    }

    public Integer getPriority() {
        return this.priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public PartnerState getPartnerState() {
        return this.partnerState;
    }
    public void setPartnerState(PartnerState partnerState) {
        this.partnerState = partnerState;
    }

    public char getTaxClass() {
        return this.taxClass;
    }
    public void setTaxClass(char taxClass) {
        this.taxClass = taxClass;
    }

    public Partner merge(Partner command) {
        setPriority(command.getPriority());
        setPartnerState(command.getPartnerState());
        setTaxClass(command.getTaxClass());
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Partner)) return false;
        final Partner other = (Partner) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$privateEntityId = this.getPrivateEntityId();
        final Object other$privateEntityId = other.getPrivateEntityId();
        if (this$privateEntityId == null ? other$privateEntityId != null : !this$privateEntityId.equals(other$privateEntityId))
            return false;
        final Object this$partnerType = this.getPartnerType();
        final Object other$partnerType = other.getPartnerType();
        if (this$partnerType == null ? other$partnerType != null : !this$partnerType.equals(other$partnerType))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $privateEntityId = this.getPrivateEntityId();
        result = result * PRIME + ($privateEntityId == null ? 43 : $privateEntityId.hashCode());
        final Object $partnerType = this.getPartnerType();
        result = result * PRIME + ($partnerType == null ? 43 : $partnerType.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Partner;
    }

    public String toString() {
        return "org.helianto.partner.domain.Partner(id=" + this.getId() + ", privateEntity=" + this.getPrivateEntity() + ", privateEntityId=" + this.getPrivateEntityId() + ", partnerType=" + this.getPartnerType() + ", newEntityAlias=" + this.newEntityAlias + ", account=" + this.getAccount() + ", accountId=" + this.getAccountId() + ", priority=" + this.priority + ", partnerState=" + this.partnerState + ", taxClass=" + this.taxClass + ")";
    }

}
