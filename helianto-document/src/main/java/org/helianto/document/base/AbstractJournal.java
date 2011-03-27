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

import org.springframework.format.annotation.DateTimeFormat;


/**
 * Extends <code>AbstractControl</code> to add actual start and end dates..
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractJournal extends AbstractControl {

    private static final long serialVersionUID = 1L;
    private Date actualStartDate;
    private Date actualEndDate;
    private int priority;

    /** 
     * Default constructor.
     */
    public AbstractJournal() {
    	super();
        setPriority(0);
    }
    
    /**
     * Summary.
     */
    @Transient
    public abstract String getSummary();
    
    /**
     * Actual start date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getActualStartDate() {
        return this.actualStartDate;
    }
    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    /**
     * Actual end date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getActualEndDate() {
        return this.actualEndDate;
    }
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    /**
     * <<Transient>> Actual duration in milliseconds.
     */
    @Transient
    public long getActualDuration() {
    	if (getActualStartDate()!=null && getActualEndDate()!=null) {
    		return getActualEndDate().getTime() - getActualStartDate().getTime();
    	}
    	return 1;
    }

    /**
     * Priority.
     */
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    @Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractJournal) ) return false;
		 return super.equals(other);
    }
   
}


