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

package org.helianto.control;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.core.Sequenceable;
import org.helianto.core.TopLevelNumberedEntity;



/**
 * Base class to entity records that control a date.
 *  
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractControl extends AbstractRecord implements Serializable, Sequenceable, Control, TopLevelNumberedEntity {

    /**
     * Factory method.
     * 
     * @param <T>
     * @param clazz
     * @param entity
     * @param internalNumber
     */
    public static <T extends AbstractControl> T abstractControlFactory(Class<T> clazz, Entity entity, long internalNumber) {
        T control = AbstractRecord.abstractRecordFactory(clazz, internalNumber);
        control.setEntity(entity);
        return control;
    }
    
    private static final long serialVersionUID = 1L;
    private Entity entity;
    private Date nextCheckDate;
    private char trackingMode;
    private int frequency;
    private int frequencyType;

    /** default constructor */
    public AbstractControl() {
    	super();
        setTrackingMode(TrackingMode.END_ONLY);
    	setFrequency(0);
    	setFrequencyType(Calendar.DATE);
    }
    
    /**
     * <<NaturalKey>> Entity owning the control.
     * @see {@link Entity}
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Date to be controlled.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getNextCheckDate() {
        return this.nextCheckDate;
    }
    /**
     * Safe presentation control date and time.
     */
    @Transient
    public String getNextCheckDateTimeAsString() {
        return getNextCheckDate()==null ? "" : 
        	format(TemporalType.TIMESTAMP).format(getNextCheckDate());
    }
    /**
     * Safe presentation control date.
     */
    @Transient
    public String getNextCheckDateAsString() {
        return getNextCheckDate()==null ? "" : 
        	format(TemporalType.DATE).format(getNextCheckDate());
    }
    /**
     * Safe presentation control time.
     */
    @Transient
    public String getNextCheckTimeAsString() {
        return getNextCheckDate()==null ? "" : 
        	format(TemporalType.TIME).format(getNextCheckDate());
    }
    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }
    @Transient
    public boolean isCheckDatePast() {
//    	if (!isActive()) return false;
    	if (nextCheckDate==null) return true;
        return (nextCheckDate.before(new Date()));
    }
    @Transient
    public char getCheckDatePastAsChar() {
        return isCheckDatePast() ? 'Y' : 'N';
    }

    /**
     * Tracking mode.
     */
    public char getTrackingMode() {
    	return this.trackingMode;
    }
    public void setTrackingMode(char trackingMode) {
    	this.trackingMode = trackingMode;
    }
    public void setTrackingMode(TrackingMode trackingMode) {
    	this.trackingMode = trackingMode.getValue();
    }
    
    /**
     * Frequency.
     */
    public int getFrequency() {
    	return this.frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    /**
     * Frequency type.
     * 
     * @see Calendar
     */
    public int getFrequencyType() {
        return this.frequencyType;
    }
    public void setFrequencyType(int frequencyType) {
        this.frequencyType = frequencyType;
    }

	public TopLevelNumberedEntity setKey(Entity entity, long internalNumber) {
        this.setEntity(entity);
        this.setInternalNumber(internalNumber);
		return this;
	}

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractControl) ) return false;
		 AbstractControl castOther = ( AbstractControl ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
}


