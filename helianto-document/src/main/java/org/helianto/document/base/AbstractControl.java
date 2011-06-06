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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.document.ControlState;
import org.helianto.document.ControlStateResolver;
import org.helianto.document.Controllable;
import org.helianto.document.Resolution;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Base class to entity records that control a date.
 *  
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractControl extends AbstractRepeatable implements Controllable {

    private static final long serialVersionUID = 1L;
    private Entity entity;
    private Date nextCheckDate;

    /** 
     * Default constructor.
     */
    public AbstractControl() {
    	super();
    	setNextCheckDate(new Date());
    }
    
    /** 
     * Entity constructor.
     * 
     * @param entity
     * @param internalNumber
     */
    public AbstractControl(Entity entity, long internalNumber) {
    	this();
    	setEntity(entity);
    	setInternalNumber(internalNumber);
    }
    
    /**
     * <<NaturalKey>> Entity owning the control.
     * @see {@link Entity}
     */
    @ManyToOne
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


