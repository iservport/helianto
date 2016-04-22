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

package org.helianto.document.internal;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.helianto.core.internal.AbstractEventControl;
import org.helianto.document.Journal;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Extends <code>AbstractControl</code> to add actual start and end dates..
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractJournal 
	extends AbstractEventControl 
	implements Journal {

    private static final long serialVersionUID = 1L;
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualStartDate;
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualEndDate;
    
    private char priority;

    /** 
     * Default constructor.
     */
    public AbstractJournal() {
    	super();
        setPriority('0');
    }
    
    /**
     * Summary.
     */
    public abstract String getSummary();
    
    /**
     * Actual start date.
     */
    public Date getActualStartDate() {
        return internalActualStartDate();
    }
    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }
    
    /**
     * Convenience to allow subclasses to change how actual start 
     * date is handled.
     */
    protected Date internalActualStartDate() {
    	return this.actualStartDate;
    }

    /**
     * Actual end date.
     */
    public Date getActualEndDate() {
        return internalActualEndDate();
    }
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    /**
     * Convenience to allow subclasses to change how actual end 
     * date is handled.
     */
    protected Date internalActualEndDate() {
    	return this.actualEndDate;
    }

    /**
     * <<Transient>> Actual duration in milliseconds.
     */
    public long getActualDuration() {
    	if (getActualStartDate()!=null && getActualEndDate()!=null) {
    		return getActualEndDate().getTime() - getActualStartDate().getTime();
    	}
    	return 1;
    }

    /**
     * Priority.
     */
    public char getPriority() {
        return this.priority;
    }
    public void setPriority(char priority) {
        this.priority = priority;
    }
    
    /**
     * Merger.
     * 
     * @param command
     */
    public void merge(AbstractJournal command) {
    	super.merge(command);
    	setActualStartDate(command.getActualStartDate());
    	setActualEndDate(command.getActualEndDate());
    	setPriority(command.getPriority());
    }
    
    @Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractJournal) ) return false;
		 return super.equals(other);
    }
   
}


