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
import org.helianto.document.domain.DocumentFolder;
import org.helianto.document.form.DocumentFolderForm;

/**
 * Serializer form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentFolderFormFilterAdapter<T extends DocumentFolderForm> extends AbstractTrunkFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     * 
     * @param serializer
     */
    public DocumentFolderFormFilterAdapter(T serializer) {
		super(serializer);
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param builderCode
     */
    @SuppressWarnings("unchecked")
	public DocumentFolderFormFilterAdapter(Entity entity, String builderCode) {
    	super((T) new DocumentFolder(entity, builderCode));
    }

	public boolean isSelection() {
		return getForm().getFolderCode()!=null && getForm().getFolderCode().length()>0;
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("contentType", getForm().getContentType(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("folderCode", getForm().getFolderCode(), mainCriteriaBuilder);
	}

	@Override
	public String getOrderByString() {
		return "priority,folderCode";
	}
	
}