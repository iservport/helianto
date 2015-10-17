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

package org.helianto.core.internal;

import java.util.Date;
import java.util.Locale;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.def.Resolution;
import org.helianto.core.def.ResolutionExtended;
import org.helianto.core.domain.Identity;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Base class to represent an event.
 *  
 * <p>
 * Events are numbered and are controlled by an owner.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractEvent 
	extends AbstractTrunkEntity
{

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ownerId", nullable=true)
    private Identity owner;
    
    @Transient
    private Integer ownerId = 0;
    
    @Transient
    private String ownerDisplayName = "";
    
    @Transient
    private String ownerFirstName = "";
    
    @Transient
    private String ownerLastName = "";
    
    @Transient
    private Character ownerGender = 'N';
    
    @Transient
    private String ownerImageUrl = "";
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    
    private Character resolution = Resolution.PRELIMINARY.getValue();
    
    private Character privacyLevel = PrivacyLevel.PUBLIC.getValue();
    
    @Transient
    private int interval;

    /** 
     * Empty constructor.
	 */
    public AbstractEvent() {
        super();
        setIssueDate(new Date());
    }
    
    /** 
     * Read constructor.
     * 
     * @param id
     * @param ownerId
     * @param issueDate
     * @param resolution
     */
    protected AbstractEvent(Integer id, Integer ownerId, Date issueDate, Character resolution) {
    	this();
    	setId(id);
    	setOwnerId(ownerId);
    	setIssueDate(issueDate);
    	setResolution(resolution);
    }
    
    /** 
     * Read composite constructor.
     * 
     * @param id
     * @param ownerId
     * @param ownerDisplayName
     * @param ownerFirstName
     * @param ownerLastName
     * @param ownerGender
     * @param ownerImageUrl
     * @param issueDate
     * @param resolution
     */
    protected AbstractEvent(Integer id, Integer ownerId, String ownerDisplayName
    		, String ownerFirstName, String ownerLastName, Character ownerGender
    		, String ownerImageUrl, Date issueDate, Character resolution) {
    	this(id, ownerId, issueDate, resolution);
    	setOwnerDisplayName(ownerDisplayName);
    	setOwnerFirstName(ownerFirstName);
    	setOwnerLastName(ownerLastName);
    	setOwnerGender(ownerGender);
    	setOwnerImageUrl(ownerImageUrl);
    }
    
    /**
     * Locale.
     */
    public Locale getLocale() {
    	return Locale.getDefault();
    }

    /**
     * Owner.
     */
	public Identity getOwner() {
		return owner;
	}
	public void setOwner(Identity owner) {
		this.owner = owner;
	}   
    
	/**
	 * <<Transient>> owner id.
	 */
	public Integer getOwnerId() {
		return getOwner()!=null ? getOwner().getId() : ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

    /**
     * <<Transient>> owner display name.
     */
	public String getOwnerDisplayName() {
        return getOwner()!=null ? owner.getDisplayName() : ownerDisplayName;
	}
	public void setOwnerDisplayName(String ownerDisplayName) {
		this.ownerDisplayName = ownerDisplayName;
	}
    
    /**
     * <<Transient>> owner first name.
     */
	public String getOwnerFirstName() {
        return getOwner()!=null ? owner.getIdentityFirstName() : ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
    
    /**
     * <<Transient>> owner last name.
     */
	public String getOwnerLastName() {
        return getOwner()!=null ? owner.getIdentityLastName() : ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
    
    /**
     * Safe presentation of owner name.
     */
	public String getOwnerName() {
        return getOwner()!=null ? owner.getIdentityName() : ownerFirstName.trim()+" "+ownerLastName;
	}
	
    /**
     * <<Transient>> owner gender.
     */
	public Character getOwnerGender() {
        return getOwner()!=null ? owner.getGender() : ownerGender;
	}
	public void setOwnerGender(Character ownerGender) {
		this.ownerGender = ownerGender;
	}
	
    /**
     * <<Transient>> owner image URL.
     */
	public String getOwnerImageUrl() {
        return getOwner()!=null ? owner.getImageUrl() : ownerImageUrl;
	}
	public void setOwnerImageUrl(String ownerImageUrl) {
		this.ownerImageUrl = ownerImageUrl;
	}

    /**
     * Issue date.
     */
    public Date getIssueDate() {
        return this.issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Character getResolution() {
        return validateResolution(this.resolution);
    }
    public void setResolution(Character resolution) {
        this.resolution = resolution;
    }
    public void setResolutionAsEnum(ResolutionExtended resolution) {
        this.resolution = resolution.getValue();
    }
    
    /**
     * Do the actual resolution validation.
     */
    protected char validateResolution(char resolution) {
    	return resolution;
    }

    /**
     * Privacy level.
     */
    public Character getPrivacyLevel() {
        return this.privacyLevel;
    }
    public void setPrivacyLevel(Character privacyLevel) {
        this.privacyLevel = privacyLevel;
    }
    public void setPrivacyLevelAsEnum(PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel.getValue();
    }
    
    /**
     * <<Transient>> Convenience to pass date intervals when used as a filter.
     */
    public int getInterval() {
		return interval;
	}
    public void setInterval(int interval) {
		this.interval = interval;
	}
    
    /**
     * Merger.
     * 
     * @param command
     */
    public void merge(AbstractEvent command) {
		setIssueDate(command.getIssueDate());
		setResolution(command.getResolution());
		setPrivacyLevel(command.getPrivacyLevel());
    }
    
}


