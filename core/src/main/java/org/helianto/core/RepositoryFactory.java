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

package org.helianto.core;

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.repository.AbstractBasicDao;
import org.helianto.core.repository.AbstractFilterDao;
import org.helianto.core.repository.PersistenceStrategy;
import org.springframework.stereotype.Component;

/**
 * Create strongly typed DAOs and inject the appropriate persistence strategy.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component
@SuppressWarnings("unchecked")
public class RepositoryFactory {

	/**
	 * Return a new <code>BasicDao</code>.
	 * 
	 * @param <T>
	 * @param targetClazz
	 * @param filterClazz
	 * @param params
	 */
	public <T> AbstractBasicDao 
	basicDaoFactory(Class<T> targetClazz, String... params) 
	{
		AbstractBasicDao<T> basicDao =  
			new AbstractBasicDao(targetClazz) {
			public PersistenceStrategy<T> getPersistenceStrategy() {
				return persistenceStrategy;
			}
		};
		setParams(basicDao, params);
		return basicDao;
	}

	/**
	 * Return a new filter DAO.
	 * 
	 * @param <T>
	 * @param <F>
	 * @param targetClazz
	 * @param filterClazz
	 * @param params
	 */
	public <T, F extends Filter> AbstractFilterDao 
	filterDaoFactory(Class<T> targetClazz, Class<F> filterClazz, String... params) 
	{
		AbstractFilterDao<T, F> filterDao =  
			new AbstractFilterDao(targetClazz) {
			public PersistenceStrategy<T> getPersistenceStrategy() {
				return persistenceStrategy;
			}
		};
		setParams(filterDao, params);
		return filterDao;
	}
	
	/**
	 * Convenience to inject parameters.
	 * 
	 * @param <T>
	 * @param basicDao
	 * @param params
	 */
	protected <T> void setParams(AbstractBasicDao<T> basicDao, String... params) {
		if (params.length > 0) {
			basicDao.setParams(params);
		}
	}

	// collabs
    
    private PersistenceStrategy persistenceStrategy;
    
    @Resource
	public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
		this.persistenceStrategy = persistenceStrategy;
	}
    
}
