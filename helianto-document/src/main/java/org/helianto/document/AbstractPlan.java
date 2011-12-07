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

package org.helianto.document;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.helianto.document.base.AbstractJournal;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Plan base class.
 *  
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractPlan extends AbstractJournal implements Plan {

    private static final long serialVersionUID = 1L;
    private Date scheduledStartDate;
    private Date scheduledEndDate;
    private long scheduledDuration;


    /** 
     * Default constructor.
     */
    protected AbstractPlan() {
    	super();
    	setScheduledStartDate(getIssueDate());
    }
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduledStartDate() {
        return this.scheduledStartDate;
    }
    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduledEndDate() {
        return this.scheduledEndDate;
    }
    public void setScheduledEndDate(Date scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    /**
     * Duração programada em mili-segundos.
     */
    public long getScheduledDuration() {
        return this.scheduledDuration;
    }
    public void setScheduledDuration(long scheduledDuration) {
        this.scheduledDuration = scheduledDuration;
    }
}


