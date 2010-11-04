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

package org.helianto.document;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Base to document filters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractDocumentFilter extends AbstractUserBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;
    private String docCode;
    private String docName;
    private String docNameLike;
    
    /**
     * Default constructor.
     */
    public AbstractDocumentFilter() {
    	super();
		reset();
    }

    /**
     * Reset filter.
     */
	public void reset() {
		setDocCode("");
		setDocNameLike("");
	}
	
	/**
	 * True when filter must select a distinct document.
	 */
	public boolean isSelection() {
		return getDocCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getDocCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docName", getDocName(), mainCriteriaBuilder);
		appendLikeFilter("docName", getDocNameLike(), mainCriteriaBuilder);
	}

	/**
	 * Document code filter.
	 */
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	
	/**
	 * Document name filter.
	 */
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	/**
	 * Document name like filter.
	 */
	public String getDocNameLike() {
		return docNameLike;
	}
	public void setDocNameLike(String docNameLike) {
		this.docNameLike = docNameLike;
	}
	
}

