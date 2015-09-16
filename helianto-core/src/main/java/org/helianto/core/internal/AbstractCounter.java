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

package org.helianto.core.internal;

import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class to add counting methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractCounter
	extends AbstractTrunkEntity {

	private static final long serialVersionUID = 1L;
	
    @Transient
	private int countItems;
	
    @Transient
	private int countAlerts;
	
    @Transient
	private int countWarnings;
	
    @Transient
	private int countOthers;
	
    /** 
     * Empty constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractCounter() {
    	super();
    }

	/**
	 * True if base class matches id.
	 * 
	 * @param counter
	 */
    @JsonIgnore
	protected boolean match(SimpleCounter counter) {
		return counter.getBaseClass()!=null && counter.getBaseClass().equals(getId());
	}
	
    /**
     * Count items.
     */
    public int getCountItems() {
		return countItems;
	}
    public void setCountItems(int countItems) {
		this.countItems = countItems;
	}
    
    /**
     * Count items.
     * 
     * @param counterList
     */
    @JsonIgnore
	public AbstractCounter setCountItems(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountItems((int) counter.getItemCount());
				break;
			}
		}
		return this;
	}
	
    /**
     * Count alerts.
     */
    public int getCountAlerts() {
		return countAlerts;
	}
    public void setCountAlerts(int countAlerts) {
		this.countAlerts = countAlerts;
	}
    
    /**
     * Count alerts.
     * 
     * @param counterList
     */
    @JsonIgnore
	public AbstractCounter setCountAlerts(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountAlerts((int) counter.getItemCount());
				break;
			}
		}
		return this;
	}
	
    /**
     * Count warnings.
     */
    public int getCountWarnings() {
		return countWarnings;
	}
    public void setCountWarnings(int countWarnings) {
		this.countWarnings = countWarnings;
	}
    
    /**
     * Count warnings.
     * 
     * @param counterList
     */
    @JsonIgnore
	public AbstractCounter setCountWarnings(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountWarnings((int) counter.getItemCount());
				break;
			}
		}
		return this;
	}
	
    /**
     * Count others.
     */
    public int getCountOthers() {
		return countOthers;
	}
    public void setCountOthers(int countOthers) {
		this.countOthers = countOthers;
	}
    
    /**
     * Count others.
     * 
     * @param counterList
     */
    @JsonIgnore
	public AbstractCounter setCountOthers(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountOthers((int) counter.getItemCount());
				break;
			}
		}
		return this;
	}
    
}
