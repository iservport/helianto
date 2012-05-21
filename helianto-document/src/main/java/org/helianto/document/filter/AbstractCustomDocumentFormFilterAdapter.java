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
import org.helianto.document.form.CustomDocumentForm;

/**
 * Base to custom document form filter adapters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractCustomDocumentFormFilterAdapter<T extends CustomDocumentForm> extends AbstractDocumentFormFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
    
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
    public AbstractCustomDocumentFormFilterAdapter(T form) {
		super(form);
	}
    
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		if (getForm().getSeries()!=null) {
			appendEqualFilter("series.id", getForm().getSeries().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("series.contentType", getForm().getContentType(), mainCriteriaBuilder);
		appendEqualFilter("series.builderCode", getForm().getFolderCode(), mainCriteriaBuilder);
	}
	
}

