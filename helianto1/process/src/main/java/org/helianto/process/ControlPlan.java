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
import org.helianto.core.Sequenceable;
import org.helianto.document.AbstractDocument;

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


    /** default constructor */
    public ControlPlan() {
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
    public void setPhase(ProcessPhase phase) {
        this.phase = phase.getValue();
    }
    
    /**
     * <code>ControlPlan</code> factory.
     * 
     * @param entity
     * @param internalNumber
     */
    public static ControlPlan controlPlanFactory(Entity entity, long internalNumber) {
    	ControlPlan controlPlan = documentFactory(ControlPlan.class, entity, "");
    	controlPlan.setInternalNumber(internalNumber);
    	return controlPlan;
    }

    /**
     * <code>ControlPlan</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getControlPlanQueryStringBuilder() {
        return new StringBuilder("select controlPlan from ControlPlan controlPlan ");
    }

    /**
     * <code>ControlPlan</code> natural id query.
     */
    @Transient
    public static String getControlPlanNaturalIdQueryString() {
        return getControlPlanQueryStringBuilder().append("where controlPlan.entity = ? and controlPlan.docCode = ? ").toString();
    }
    
    public boolean equals(Object other) {
		 if ( !(other instanceof ControlPlan) ) return false;
		 return super.equals(other);
    }
 
}
