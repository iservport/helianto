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


package org.helianto.process.filter.classic;

import java.io.Serializable;
import java.util.Collection;

import org.helianto.core.Entity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.classic.PolymorphicFilter;
import org.helianto.document.filter.classic.AbstractDocumentFilter;
import org.helianto.partner.Partner;
import org.helianto.process.Characteristic;
import org.helianto.process.ControlMethod;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Process document filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public class ProcessDocumentFilter extends AbstractDocumentFilter implements Serializable, PolymorphicFilter<ProcessDocument> {

    private static final long serialVersionUID = 1L;
	private Class<? extends ProcessDocument> clazz;
	private char inheritanceType;
	private char priority;
	private ProcessDocument parent;
	private Partner partner;
    private Collection<? extends ProcessDocument> exclusions;
	
	/**
	 * Key constructor.
     * 
     * @param entity
     * @param docCode
	 */
	public ProcessDocumentFilter(Entity entity, String docCode) {
		super(entity, docCode);
		setClazz(ProcessDocument.class);
		setInheritanceType(' ');
		setPriority(' ');
	}
	
	/**
	 * Parent constructor.
     * 
     * @param parent
	 */
	public ProcessDocumentFilter(ProcessDocument parent) {
		super(parent.getEntity(), "");
		setParent(parent);
	}
	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (!getClazz().equals(ProcessDocument.class)) {
	        logger.debug("Document class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
			return true;
		}
		return false;
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("inheritanceType", getInheritanceType(), mainCriteriaBuilder);
		appendEqualFilter("priority", getPriority(), mainCriteriaBuilder);
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
	 * Inheritance type.
	 */
	public char getInheritanceType() {
		return inheritanceType;
	}
	public void setInheritanceType(char inheritanceType) {
		this.inheritanceType = inheritanceType;
	}

	/**
	 * Priority
	 */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
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
	 * Partner
	 */
	public Partner getPartner() {
		return this.partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
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

	/**
     * toString
     * @return String
     */
	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        builder.append("parent").append("='").append(getParent()).append("' ");
        builder.append("]");
        return builder.toString();
    }
	
	private static Logger logger = LoggerFactory.getLogger(ProcessDocument.class);

}
