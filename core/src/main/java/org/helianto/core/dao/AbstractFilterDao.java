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


package org.helianto.core.dao;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.Filter;

/**
 * Base implementation to <code>FilterDao</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterDao<T, F extends Filter> 
    extends AbstractBasicDao<T> implements FilterDao<T, F> 
{

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
	 * Use the filter to create a where clause and delegate to
	 * the superclass.
	 */
	public Collection<T> find(F filter) {
		String whereClause = filter.createCriteriaAsString();
		return super.find(getSelectBuilder(), whereClause);
	}

    protected static Log logger = LogFactory.getLog(AbstractFilterDao.class);

}
