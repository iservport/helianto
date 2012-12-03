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


package org.helianto.document.filter.classic;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;
import org.helianto.user.domain.User;

/**
 * Serializer filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public class SerializerFilter extends AbstractUserBackedCriteriaFilter {

	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static SerializerFilter documentCodeBuilderFilterFactory(User user) {
		SerializerFilter documentCodeBuilderFilter = new SerializerFilter();
		documentCodeBuilderFilter.setUser(user);
		return documentCodeBuilderFilter;
	}
	
	private static final long serialVersionUID = 1L;
    private String builderCode;
	private char contentType;
    
    /**
     * Default constructor.
     */
    public SerializerFilter() {
		this(null, "");
    }

    /**
     * Entity constructor.
     * 
     * @param entity
     */
    public SerializerFilter(Entity entity) {
		this(entity, "");
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param builderCode
     */
    public SerializerFilter(Entity entity, String builderCode) {
    	super();
		setContentType(' ');
		setEntity(entity);
		setBuilderCode(builderCode);
    }

	public void reset() {
		setBuilderCode("");
	}

	public boolean isSelection() {
		return getBuilderCode().length()>0;
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("builderCode", getBuilderCode(), mainCriteriaBuilder);
		appendEqualFilter("contentType", getContentType(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("builderCode", getBuilderCode(), mainCriteriaBuilder);
	}

    /**
     * Builder code filter.
     */
	public String getBuilderCode() {
		return builderCode;
	}
	public void setBuilderCode(String builderCode) {
		this.builderCode = builderCode;
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

}
