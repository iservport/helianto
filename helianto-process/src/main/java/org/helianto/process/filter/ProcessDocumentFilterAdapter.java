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

package org.helianto.process.filter;

import java.util.Collection;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractFilterAdapter;
import org.helianto.process.Characteristic;
import org.helianto.process.ControlMethod;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class ProcessDocumentFilterAdapter extends AbstractFilterAdapter<ProcessDocument>{

    private static final long serialVersionUID = 1L;
	private Class<? extends ProcessDocument> clazz;
	private ProcessDocument parent;
    private Collection<? extends ProcessDocument> exclusions;
    
    /**
     * Default constructor
     */
    public ProcessDocumentFilterAdapter(ProcessDocument processDocument) {
    	super(processDocument);
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    }
    	
	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (!getClazz().equals(ProcessDocument.class)) {
	        logger.debug("Document class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}
	
	@Override
	public boolean isSelection() {
		return getFilter().getDocCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getFilter().getDocCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
//		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("inheritanceType", getFilter().getInheritanceType(), mainCriteriaBuilder);
		appendEqualFilter("priority", getFilter().getPriority(), mainCriteriaBuilder);
		appendOrderBy("docCode", mainCriteriaBuilder);
	}

	/**
	 * Subclass
	 */
	public Class<? extends ProcessDocument> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends ProcessDocument> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Discriminator
	 */
	public char getDiscriminator() {
		if (clazz.equals(ControlMethod.class)) {
			return 'S'; 
		}
		if (clazz.equals(Characteristic.class)) {
			return 'K'; 
		}
		if (clazz.equals(Operation.class)) {
			return 'O'; 
		}
		if (clazz.equals(Process.class)) {
			return 'P'; 
		}
		return ' ';
	}

	public void setDiscriminator(char discriminator) {
		if (discriminator=='S') {
			clazz = ControlMethod.class; 
		}
		if (discriminator=='K') {
			clazz = Characteristic.class; 
		}
		if (discriminator=='O') {
			clazz = Operation.class; 
		}
		if (discriminator=='P') {
			clazz = Process.class; 
		}
	}

	/**
	 * Parent
	 */
	public ProcessDocument getParent() {
		return parent;
	}
	public void setParent(ProcessDocument parent) {
		this.parent = parent;
	}
	
	/**
	 * Exclusions.
	 */
	public Collection<? extends ProcessDocument> getExclusions() {
		return exclusions;
	}
	public void setExclusions(Collection<? extends ProcessDocument> exclusions) {
		this.exclusions = exclusions;
	}

	private static Logger logger = LoggerFactory.getLogger(ProcessDocument.class);

}