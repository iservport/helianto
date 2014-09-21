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
import org.helianto.core.domain.type.ResolvableEntity;
import org.springframework.format.annotation.DateTimeFormat;

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
	implements ResolvableEntity
{

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name="ownerId", nullable=true)
    private Identity owner;
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    
    private char resolution = Resolution.PRELIMINARY.getValue();
    
    private char privacyLevel = PrivacyLevel.PUBLIC.getValue();
    
    @Transient
    private int interval;

    /** 
     * Empty constructor
	 */
    public AbstractEvent() {
        super();
        setIssueDate(new Date());
    }
    
    /**
     * Locale.
     */
//    @Transient
    public Locale getLocale() {
    	return Locale.getDefault();
    }

    /**
     * Record owner.
     * @see {@link Identity}
     */
	public Identity getOwner() {
		return owner;
	}
    
	@Transient
	public int getOwnerId() {
		if (getOwner()!=null) {
			return getOwner().getId();
		}
		return 0;
	}

    /**
     * Safe presentation record owner alias.
     * @deprecated
     */
//    @Transient
	public String getOwnerOptionalAlias() {
        return getOwner()==null ? "" : owner.getDisplayName();
	}
    
    /**
     * Safe presentation record owner display name.
     */
    @Transient
	public String getOwnerDisplayName() {
        return getOwner()==null ? "" : owner.getDisplayName();
	}
    
    /**
     * Safe presentation record owner name.
     */
//    @Transient
	public String getOwnerName() {
        return getOwner()==null ? "" : owner.getIdentityName();
	}
	public void setOwner(Identity owner) {
		this.owner = owner;
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

    public char getResolution() {
        return validateResolution(this.resolution);
    }
    public void setResolution(char resolution) {
        this.resolution = resolution;
    }
    @JsonIgnore
    public void setResolution(String resolution) {
        this.resolution = resolution.charAt(0);
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
    public char getPrivacyLevel() {
        return this.privacyLevel;
    }
    public void setPrivacyLevel(char privacyLevel) {
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
    
}


