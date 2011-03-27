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

package org.helianto.process;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.base.AbstractDocument;

/**
 * <p>
 * A control plan.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_controlplan")
public class ControlPlan extends AbstractDocument implements Sequenceable {

	private static final long serialVersionUID = 1L;
    protected long internalNumber;
    private int phase;


    /** 
     * Document constructor.
     */
    public ControlPlan() {
    	super();
    	setPhaseAsEnum(ProcessPhase.PRODUCTION);
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public ControlPlan(Entity entity, String docCode) {
    	this();
    	setEntity(entity);
    	setDocCode(docCode);
    }
    /**
     * Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
        StringBuilder prefix = new StringBuilder("CTP#");
        setDocCode(prefix.append(internalNumber).toString());
    }
    @Transient
	public String getInternalNumberKey() {
		return "CTRLPLN";
	}
    
    /**
     * Phase.
     */
    public int getPhase() {
        return this.phase;
    }
    public void setPhase(int phase) {
        this.phase = phase;
    }
    public void setPhaseAsEnum(ProcessPhase phase) {
        this.phase = phase.getValue();
    }
    
    public boolean equals(Object other) {
		 if ( !(other instanceof ControlPlan) ) return false;
		 return super.equals(other);
    }
 
}
