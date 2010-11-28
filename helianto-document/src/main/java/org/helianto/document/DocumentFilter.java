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

import org.helianto.core.Entity;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Document filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentFilter extends AbstractDocumentFilter {

	private static final long serialVersionUID = 1L;
	private String builderCode;
    private char contentType;
    
    /**
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public DocumentFilter(Entity entity, String docCode) {
    	super(entity, docCode);
    	setBuilderCode("");
    	setContentType(' ');
    }

    /**
     * Reset.
     */
	public void reset() {
		super.reset();
		setContentType(' ');
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("documentCodeBuilder.contentType", getContentType(), mainCriteriaBuilder);
		appendEqualFilter("documentCodeBuilder.builderCode", getBuilderCode(), mainCriteriaBuilder);
	}
	
	/**
	 * Content type filter.
	 */
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Builder code filter.
	 */
    public String getBuilderCode() {
        return this.builderCode;
    }
    public void setBuilderCode(String builderCode) {
        this.builderCode = builderCode;
    }

}
