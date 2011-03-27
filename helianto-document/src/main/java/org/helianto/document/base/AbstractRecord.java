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

import javax.persistence.Transient;

import org.helianto.document.RecordStateResolver;
import org.helianto.document.Recordable;
import org.helianto.document.Resolution;

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
public abstract class AbstractRecord extends AbstractEvent implements Recordable {

    private static final long serialVersionUID = 1L;
    private int complete;
    private char resolution;

    /** 
     * Default constructor.
     */
    public AbstractRecord() {
    	this(Resolution.PRELIMINARY.getValue());
    }
    
    /** 
     * Resolution constructor.
     * 
     * @param resolution
     */
    public AbstractRecord(char resolution) {
    	super();
        setResolution(resolution);
        setComplete(-1);
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

    /**
     * Resolve record status.
     */
    @Transient
    public RecordStateResolver getState() {
    	return new RecordStateResolver(this);
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
    
}


