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

package org.helianto.document.base;

import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.helianto.core.Identity;
import org.helianto.core.Privacy;
import org.helianto.core.PrivacyLevel;
import org.helianto.document.Event;
import org.springframework.format.annotation.DateTimeFormat;



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
public abstract class AbstractEvent implements Privacy, Event {

    private static final long serialVersionUID = 1L;
    private int id;
    private Integer version;
    private long internalNumber;
    private Identity owner;
    private Date issueDate;
    private char privacyLevel;
    // transient, convenience for filters
    private int interval;

    /** 
     * Empty constructor
	 */
    public AbstractEvent() {
        setPrivacyLevel(PrivacyLevel.PUBLIC.getValue());
        setIssueDate(new Date());
    }
    
    /**
     * Locale.
     */
    @Transient
    public Locale getLocale() {
    	return Locale.getDefault();
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
     * Version.
     */
    @Version
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * <<NaturalKey>> Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }

    /**
     * Issue date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getIssueDate() {
        return this.issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Record owner.
     * @see {@link Identity}
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="ownerId", nullable=true)
	public Identity getOwner() {
		return owner;
	}
    /**
     * Safe presentation record owner alias.
     */
    @Transient
	public String getOwnerOptionalAlias() {
        return getOwner()==null ? "" : owner.getOptionalAlias();
	}
    /**
     * Safe presentation record owner name.
     */
    @Transient
	public String getOwnerName() {
        return getOwner()==null ? "" : owner.getIdentityName();
	}
	public void setOwner(Identity owner) {
		this.owner = owner;
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
    public void setPrivacyLevel(PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel.getValue();
    }
    
    /**
     * <<Transient>> Convenience to pass date intervals when used as a filter.
     */
    @Transient
    public int getInterval() {
		return interval;
	}
    public void setInterval(int interval) {
		this.interval = interval;
	}
    
//    protected Format format(TemporalType temporalType) {
//    	switch (temporalType) {
//    	case TIMESTAMP: 
//    		return SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, getLocale());
//    	case DATE: 
//    		return SimpleDateFormat.getDateInstance(DateFormat.SHORT, getLocale());
//    	case TIME: 
//    		return SimpleDateFormat.getTimeInstance(DateFormat.SHORT, getLocale());
//    	}
//        return null;
//    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("internalNumber").append("='").append(getInternalNumber()).append("' ");			
    	buffer.append("]");
    	return buffer.toString();
     }

    public String toStringShort() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("#").append(getId()).append(" [");
    	buffer.append(getInternalNumber()).append("] ");			
    	return buffer.toString();
     }

    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }

}


