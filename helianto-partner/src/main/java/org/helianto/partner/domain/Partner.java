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
import lombok.Data;
import org.helianto.core.EntityAddress;
import org.helianto.core.domain.City;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Phone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Base class to represent the relationship between the organization and other 
 * entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Data
@javax.persistence.Entity
@Table(name="p_partner",
    uniqueConstraints = {@UniqueConstraint(columnNames={"privateEntityId", "categoryId"})}
)
public class Partner 
	implements Serializable, EntityAddress {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="privateEntityId", nullable=true)
    private PrivateEntity privateEntity;
    
    @Transient
	private Integer privateEntityId;
    
	private Integer categoryId;
    
    @Transient
    private String newEntityAlias = "";
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="accountId", nullable=true)
    private Account account;
    
    @Transient
	private Integer accountId;
    
    @SuppressWarnings("unused")
	private Integer priority = 0;
    
    @Enumerated @Column(length=20)
    private PartnerState partnerState = PartnerState.ACTIVE;
    
    @SuppressWarnings("unused")
	private char taxClass = '3';
    
    @JsonIgnore 
    @OneToMany(mappedBy="partner")
    private Set<PartnerKey> partnerKeys = new HashSet<PartnerKey>(0);
    
    @JsonIgnore
    @OneToMany(mappedBy="partner")
    private Set<PartnerCategory> partnerCategories = new HashSet<PartnerCategory>(0);

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
     * @param categoryId
     */
    public Partner(PrivateEntity privateEntity, Integer categoryId) {
    	this(privateEntity);
    	setCategoryId(categoryId);
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

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

}
