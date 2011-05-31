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
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.document.Serializer;
import org.helianto.document.base.AbstractSerializer;

/**
 * Serializer filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SerializerFilterAdapter<T extends AbstractSerializer<?>> extends AbstractTrunkFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     * 
     * @param serializer
     */
    public SerializerFilterAdapter(T serializer) {
		super(serializer);
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param builderCode
     */
    @SuppressWarnings("unchecked")
	public SerializerFilterAdapter(Entity entity, String builderCode) {
    	super((T) new Serializer(entity, builderCode));
    }

	public void reset() { }

	public boolean isSelection() {
		return getForm().getBuilderCode().length()>0;
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("contentType", getForm().getContentType(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("builderCode", getForm().getBuilderCode(), mainCriteriaBuilder);
	}

}
