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
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.document.base.AbstractDocument;

/**
 * Base to document filter adapters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDocumentFilterAdapter<T extends AbstractDocument> extends AbstractTrunkFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
    
	/**
	 * Default constructor.
	 * 
	 * @param target
	 */
    public AbstractDocumentFilterAdapter(T target) {
		super(target);
		reset();
		setOrderByString("docCode");
	}
    
    /**
     * Reset filter.
     */
	public void reset() {
		getFilter().setDocName("");
		getFilter().setPriority(' ');
	}
	
	/**
	 * True when filter must select a distinct document.
	 */
	public boolean isSelection() {
		return getFilter().getDocCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getFilter().getDocCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("docName", getFilter().getDocName(), mainCriteriaBuilder);
		appendPriority(mainCriteriaBuilder);
	}
	
	/**
	 * Subclasses may override to allow priority range filter.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void appendPriority(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("priority", getFilter().getPriority(), mainCriteriaBuilder);
	}

}

