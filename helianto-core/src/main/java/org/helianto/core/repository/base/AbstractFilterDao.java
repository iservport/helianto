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


package org.helianto.core.repository.base;

import java.util.Collection;

import org.helianto.core.filter.Filter;
import org.helianto.core.filter.FormFilter;
import org.helianto.core.form.PageForm;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation to <code>FilterDao</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterDao<T> extends AbstractBasicDao<T> implements FilterDao<T> {

	/**
	 * Default constructor.
	 */
	public AbstractFilterDao() {
		super();
	}
	
	/**
	 * Class constructor.
	 * 
	 * @param clazz
	 */
	public AbstractFilterDao(Class<? extends T> clazz) {
		super(clazz);
	}
	
	/**
	 * Use the filter to create a where clause and delegate to the superclass.
	 * 
	 * <p>
	 * Starting on version 0.3.2, if the filter has a paging form, also provides paged data.
	 * </p>
	 * 
	 * @param filter
	 */
	public Collection<T> find(Filter filter) {
		String whereClause = filter.createCriteriaAsString();
		if (FormFilter.class.isAssignableFrom(filter.getClass()) 
				&& ((FormFilter<?>) filter).getForm().getClass().isAssignableFrom(PageForm.class)) {
			PageForm form = ((PageForm) ((FormFilter<?>) filter).getForm());
	    	if (form.getMaxRows()>0) {
	        	return super.find(form.getFirstRow(), form.getMaxRows(), getSelectBuilder(filter), whereClause, new Object[0]);
	    	}
		}
		return super.find(getSelectBuilder(filter), whereClause, new Object[0]);
	}

	/**
	 * Overload {@link #getSelectBuilder(String)} to allow select clause by filter.
	 * 
	 * @param filter
	 */
	protected StringBuilder getSelectBuilder(Filter filter) {
		if (filter.createSelectAsString()!=null) {
			return new StringBuilder(filter.createSelectAsString());
		}
		return super.getSelectBuilder(filter.getObjectAlias());
	}
	
    protected static Logger logger = LoggerFactory.getLogger(AbstractFilterDao.class);

}
