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

import java.util.Calendar;

import javax.persistence.Transient;

import org.helianto.document.Repeatable;
import org.helianto.document.RepeatableStateResolver;
import org.helianto.document.TrackingMode;


/**
 * Base class to repeatable records.
 *  
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractRepeatable extends AbstractRecord implements Repeatable {

    private static final long serialVersionUID = 1L;
    private char trackingMode;
    private int frequency;
    private int frequencyType;

    /** 
     * Default constructor.
     */
    public AbstractRepeatable() {
    	super();
        setTrackingModeAsEnum(TrackingMode.END_ONLY);
    	setFrequency(0);
    	setFrequencyType(Calendar.DATE);
    }
    
    /**
     * Resolve control status.
     */
    @Transient
    public RepeatableStateResolver getState() {
    	return new RepeatableStateResolver(this);
    }

    /**
     * Tracking mode.
     */
    public char getTrackingMode() {
    	return getInternalTrackingMode();
    }
    @Transient
    protected char getInternalTrackingMode() {
    	return this.trackingMode;
    }
    public void setTrackingMode(char trackingMode) {
    	this.trackingMode = trackingMode;
    }
    public void setTrackingModeAsEnum(TrackingMode trackingMode) {
    	this.trackingMode = trackingMode.getValue();
    }
    
    /**
     * Frequency.
     */
    public int getFrequency() {
    	return getInternalFrequency();
    }
    @Transient
    protected int getInternalFrequency() {
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
   
    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractRepeatable) ) return false;
		 AbstractRepeatable castOther = ( AbstractRepeatable ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
}


