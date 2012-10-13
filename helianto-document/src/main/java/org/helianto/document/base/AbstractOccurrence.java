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

import org.helianto.core.SimpleStateResolver;
import org.helianto.core.def.ControlState;
import org.helianto.core.def.Resolution;
import org.helianto.document.Occurrence;

/**
 * Base class to represent an occurrence.
 *  
 * <p>
 * Occurrences are events that hold resolution information.
 * </p>
 * 
 * @deprecated
 * @author Mauricio Fernandes de Castro
 */

@javax.persistence.MappedSuperclass

public abstract class AbstractOccurrence 

	extends AbstractEvent 
	
	implements Occurrence 

{

    private static final long serialVersionUID = 1L;

    /** 
     * Default constructor.
     */
    public AbstractOccurrence() {
    	this(Resolution.PRELIMINARY.getValue());
    }
    
    /** 
     * Resolution constructor.
     * 
     * @param resolution
     */
    public AbstractOccurrence(char resolution) {
    	super();
    	setResolution(resolution);
    }
    
    /**
     * Resolve control status.
     */
    @Transient
    public SimpleStateResolver getState() {
    	return new SimpleStateResolver(this);
    }

    /**
     * Evaluate the control state.
     */
    @Transient
    public char getControlState() {
    	if (getResolution()==Resolution.DONE.getValue()) {
    		return ControlState.FINISHED.getValue();
    	}
		if (getResolution()==Resolution.CANCELLED.getValue()
				|| getResolution()==Resolution.WAIT.getValue()) {
			return ControlState.UNFINISHED.getValue();
		}
    	return ControlState.LATE.getValue();
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("]");
    	return buffer.toString();
     }

    public String toStringShort() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("#").append(getId()).append(" [");
    	return buffer.toString();
     }

}


