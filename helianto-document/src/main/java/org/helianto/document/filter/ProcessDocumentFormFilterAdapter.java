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

package org.helianto.document.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.criteria.SelectFromBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.form.ProcessDocumentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentFormFilterAdapter extends AbstractTrunkFilterAdapter<ProcessDocumentForm> {

    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     * 
     * @param processDocumentForm
     */
    public ProcessDocumentFormFilterAdapter(ProcessDocumentForm processDocumentForm) {
    	super(processDocumentForm);
    }
    
    @Override
    public String createSelectAsString() {
    	if (getForm().getParent()==null) {
        	return super.createSelectAsString();
    	}
		SelectFromBuilder builder = new SelectFromBuilder(ProcessDocument.class, getObjectAlias());
		builder.createSelectFrom().appendParentInnerJoin();
    	return builder.getAsString();
    }
    	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getForm().getClazz()!=null && !getForm().getClazz().equals(ProcessDocument.class)) {
	        logger.debug("Document class is: '{}'", getForm().getClazz());
			mainCriteriaBuilder.appendAnd().append(getForm().getClazz());
			connect = true;
		}
		if (getForm().getParent()!=null) {
	        logger.debug("Document parent is: '{}'", getForm().getParent());
			mainCriteriaBuilder.appendAnd().append("parentAssociations.parent.id =").append(getForm().getParent().getId());
			connect = true;
		}
		return connect;
	}
	
	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getDocCode()!=null && getForm().getDocCode().length()>0;
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

	private static Logger logger = LoggerFactory.getLogger(ProcessDocument.class);

}