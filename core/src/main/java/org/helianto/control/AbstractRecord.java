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

import javax.persistence.Transient;



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
public abstract class AbstractRecord extends AbstractEvent {

    /**
     * Factory method.
     * 
     * @param <T>
     * @param clazz
     * @param internalNumber
     */
    public static <T extends AbstractRecord> T abstractRecordFactory(Class<T> clazz, long internalNumber) {
        T event = null;
        try {
            event = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create record of class "+clazz, e);
        }
        event.setInternalNumber(internalNumber);
        return event;
    }
    
    private static final long serialVersionUID = 1L;
    private int complete;
    private char resolution;

    /** Default constructor */
    public AbstractRecord() {
    	super();
        setResolution(Resolution.PRELIMINARY.getValue());
    }
    
    /**
     * Resolution.
     * <p>
     * Optionally, validate the resolution in {@link #validateResolution(char)}.
     * </p>
     */
    public char getResolution() {
        return validateResolution(this.resolution);
    }
    /**
     * True if the resolution indicates state ACTIVE.
     */
    @Transient
    public boolean isActive() {
    	if (getResolution()==Resolution.PRELIMINARY.getValue()) return true;
    	if (getResolution()==Resolution.TODO.getValue()) return true;
    	if (getResolution()==Resolution.WAIT.getValue()) return true;
        return false;
    }
    /**
     * True if the resolution indicates state RUNNIG.
     */
    @Transient
    public boolean isRunning() {
    	if (getResolution()==Resolution.TODO.getValue()) return true;
    	if (getResolution()==Resolution.WAIT.getValue()) return true;
        return false;
    }
    /**
     * Do the actual resolution validation.
     */
    protected char validateResolution(char resolution) {
    	return resolution;
    }
    public void setResolution(char resolution) {
        this.resolution = resolution;
    }
    public void setResolution(Resolution resolution) {
        this.resolution = resolution.getValue();
    }
    
    /**
     * Percentual to indicate how much of the record is complete.
     */
    public int getComplete() {
    	return validateCompleteness();
    }
    public void setComplete(int complete) {
        this.complete = complete;
    }
    
    /**
     * Give subclasses a chance to validate completeness.
     */
    protected int validateCompleteness() {
    	return this.complete;
    }
    
}


