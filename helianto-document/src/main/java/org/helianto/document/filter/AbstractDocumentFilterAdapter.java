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

import org.helianto.core.Entity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractTrunkFilterAdapter;
import org.helianto.document.Document;

/**
 * Base to document filter adapters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDocumentFilterAdapter extends AbstractTrunkFilterAdapter<Document> {

	private static final long serialVersionUID = 1L;
    
    public AbstractDocumentFilterAdapter(Document target) {
		super(target);
	}
    
    /**
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public AbstractDocumentFilterAdapter(Entity entity, String docCode) {
    	super(new Document(entity, docCode));
    }

    /**
     * Reset filter.
     */
	public void reset() {
		getFilter().setDocName("");
	}
	
	/**
	 * True when filter must select a distinct document.
	 */
	public boolean isSelection() {
		return getFilter().getDocCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getFilter().getDocCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docName", getFilter().getDocName(), mainCriteriaBuilder);
		appendLikeFilter("docName", getFilter().getDocName(), mainCriteriaBuilder);
	}

}

