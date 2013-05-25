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

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.criteria.SelectFromBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.process.domain.ControlMethod;
import org.helianto.process.domain.Operation;
import org.helianto.process.domain.Process;
import org.helianto.process.domain.ProcessDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentFilterAdapter extends AbstractTrunkFilterAdapter<ProcessDocument> {

    private static final long serialVersionUID = 1L;
	private Class<? extends ProcessDocument> clazz;
	private ProcessDocument parent;
    private Collection<? extends ProcessDocument> exclusions;
    
    /**
     * Default constructor.
     * 
     * @param processDocument
     */
    public ProcessDocumentFilterAdapter(ProcessDocument processDocument) {
    	super(processDocument);
    	reset();
    }
    
    /**
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public ProcessDocumentFilterAdapter(Entity entity, String docCode) {
    	super(new ProcessDocument(entity, docCode));
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    	getForm().setInheritanceType(' ');
    	getForm().setPriority(' ');
    }
    
    @Override
    public String createSelectAsString() {
    	if (getParent()==null) {
        	return super.createSelectAsString();
    	}
		SelectFromBuilder builder = new SelectFromBuilder(ProcessDocument.class, getObjectAlias());
		builder.createSelectFrom().appendParentInnerJoin();
    	return builder.getAsString();
    }
    	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null && !getClazz().equals(ProcessDocument.class)) {
	        logger.debug("Document class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
			connect = true;
		}
		if (getParent()!=null) {
	        logger.debug("Document parent is: '{}'", getParent());
			mainCriteriaBuilder.appendAnd().append("parentAssociations.parent.id =").append(getParent().getId());
			connect = true;
		}
		return connect;
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getDocCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getForm().getDocCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("docName", getForm().getDocName(), mainCriteriaBuilder);
		appendEqualFilter("inheritanceType", getForm().getInheritanceType(), mainCriteriaBuilder);
		appendEqualFilter("priority", getForm().getPriority(), mainCriteriaBuilder);
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
	
	public long getParentId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return 0;
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