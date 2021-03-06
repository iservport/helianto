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

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.helianto.core.domain.enums.ControlState;
import org.helianto.core.domain.enums.Resolution;
import org.helianto.core.domain.enums.ResolutionExtended;

/**
 * Base class to represent a record.
 *  
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractEventControl 
	extends AbstractEvent
{

    private static final long serialVersionUID = 1L;
    
    private long internalNumber;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextCheckDate;
    
    private int frequency;
    
    private int frequencyType;
    
    private int complete;

    /** 
     * Default constructor.
     */
    public AbstractEventControl() {
    	this(Resolution.PRELIMINARY.getValue());
    	setNextCheckDate(new Date());
    }
    
    /** 
     * Resolution constructor.
     * 
     * @param resolution
     */
    public AbstractEventControl(char resolution) {
    	super();
    	setResolution(resolution);
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
     * Date to be controlled.
     */
    public Date getNextCheckDate() {
        return validateNextCheckDate(this.nextCheckDate);
    }
    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }
    
    /**
     * Give subclasses a chance to validate next check date.
     */
    protected Date validateNextCheckDate(Date nextCheckDate) {
    	return nextCheckDate;
    }
    
    /**
     * Evaluate the control state.
     */
    public char getControlState() {
    	Date now = new Date();
    	if (getResolution()==ResolutionExtended.DONE.getValue()) {
    		return ControlState.FINISHED.getValue();
    	}
		if (getResolution()==ResolutionExtended.CANCELLED.getValue()
				|| getResolution()==ResolutionExtended.WAIT.getValue()) {
			return ControlState.UNFINISHED.getValue();
		}
    	if (getNextCheckDate()==null) return ' ';
    	if (getNextCheckDate().after(now)) {
    		return ControlState.RUNNING.getValue();
    	}
    	return ControlState.LATE.getValue();
    }
    
    /**
     * Frequency.
     */
    public int getFrequency() {
    	return getInternalFrequency();
    }
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
   
    public int getComplete() {
    	return validateCompleteness(this.complete);
    }
    public void setComplete(int complete) {
        this.complete = complete;
    }
    
    /**
     * Give subclasses a chance to validate completeness.
     */
    protected int validateCompleteness(int complete) {
    	return complete;
    }
    
    /**
     * Merger.
     * 
     * @param command
     */
    public void merge(AbstractEventControl command) {
    	super.merge(command);
		setNextCheckDate(command.getNextCheckDate());
		setFrequency(command.getFrequency());
		setFrequencyType(command.getFrequencyType());
		setComplete(command.getComplete());
    }
    
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

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractEventControl) ) return false;
		 AbstractEventControl castOther = ( AbstractEventControl ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
}


