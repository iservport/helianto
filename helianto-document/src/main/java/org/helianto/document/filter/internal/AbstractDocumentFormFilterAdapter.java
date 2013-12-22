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

package org.helianto.document.filter.internal;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.internal.AbstractDateFilterAdapter;
import org.helianto.document.form.DocumentForm;

/**
 * Base to document form filter adapters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDocumentFormFilterAdapter<T extends DocumentForm> 
	extends AbstractDateFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
    
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
    public AbstractDocumentFormFilterAdapter(T form) {
		super(form);
	}
    
	/**
	 * True when filter must select a distinct document.
	 */
	public boolean isSelection() {
		return getForm().getDocCode()!=null && getForm().getDocCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getForm().getDocCode(), mainCriteriaBuilder);
	}
	
	@Override
	protected String[] getFieldNames() {
		return new String[] {"docCode", "docName" };
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendLikeFilter("docName", getForm().getDocName(), mainCriteriaBuilder);
		appendPriority(mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "docCode";
	}
	
	/**
	 * Subclasses may override to allow priority range filter.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void appendPriority(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("priority", getForm().getPriority(), mainCriteriaBuilder);
	}

}

