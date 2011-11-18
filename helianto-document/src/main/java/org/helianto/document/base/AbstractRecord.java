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

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.helianto.core.ControlStateResolver;
import org.helianto.core.def.ControlState;
import org.helianto.core.def.Resolution;
import org.helianto.document.Record;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Base class to represent a record.
 *  
 * <p>
 * Records are events that hold resolution information.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractRecord extends AbstractEvent implements Record {

    private static final long serialVersionUID = 1L;
    private int complete;
    private char resolution;
    private Date nextCheckDate;

    /** 
     * Default constructor.
     */
    public AbstractRecord() {
    	this(Resolution.PRELIMINARY.getValue());
    	setNextCheckDate(new Date());
    }
    
    /** 
     * Resolution constructor.
     * 
     * @param resolution
     */
    public AbstractRecord(char resolution) {
    	super();
    	setResolution(resolution);
    }
    
    public void reset() {
        setResolution(' ');
        setComplete(-1);
        setNextCheckDate(null);
    }
    
    public char getResolution() {
        return validateResolution(this.resolution);
    }
    public void setResolution(char resolution) {
        this.resolution = resolution;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution.charAt(0);
    }
    public void setResolutionAsEnum(Resolution resolution) {
        this.resolution = resolution.getValue();
    }
    
    /**
     * Do the actual resolution validation.
     */
    protected char validateResolution(char resolution) {
    	return resolution;
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
     * Date to be controlled.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getNextCheckDate() {
        return this.nextCheckDate;
    }
    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }
    
    /**
     * Resolve control status.
     */
    @Transient
    public ControlStateResolver getState() {
    	return new ControlStateResolver(this);
    }

    /**
     * Evaluate the control state.
     */
    @Transient
    public char getControlState() {
    	Date now = new Date();
    	if (getResolution()==Resolution.DONE.getValue()) {
    		return ControlState.FINISHED.getValue();
    	}
		if (getResolution()==Resolution.CANCELLED.getValue()
				|| getResolution()==Resolution.WAIT.getValue()) {
			return ControlState.UNFINISHED.getValue();
		}
    	if (getNextCheckDate()==null) return ' ';
    	if (getNextCheckDate().after(now)) {
    		return ControlState.RUNNING.getValue();
    	}
    	return ControlState.LATE.getValue();
    }
    
}


