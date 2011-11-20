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

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.PersistenceStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation for <code>BasicDao</code>.
 * 
 * @author Mauricio Fernandes de Castro.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractBasicDao<T> implements BasicDao<T> {
	
	private Class<? extends T> clazz;
	private String objectAlias;
	private String[] params;
	private String selectClause;
	
	/**
	 * Default constructor.
	 */
	public AbstractBasicDao() {
		this(null);
	}
	
	/**
	 * Class constructor.
	 * 
	 * @param clazz
	 */
	public AbstractBasicDao(Class<? extends T> clazz) {
		super();
		setClazz(clazz);
		setObjectAlias("alias");
	}
	
	/**
	 * The class of objects to be persisted.
	 */
	public Class<? extends T> getClazz() {
		return this.clazz;
	}
	public void setClazz(Class<? extends T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Default keys are entity.id and internalNumber
	 */
	protected String[] getParams() {
		return this.params;
	}
	public void setParams(String... params) {
		this.params =  params;
	}
	
	public Collection<T> find(String whereClause) {
        return find(getSelectBuilder(), whereClause);
	}

	/**
	 * Subclasses may override to customize persistent object name.
	 */
	public String getObjectName() {
        return getClazz().getSimpleName();
	}

	/**
	 * Object alias.
	 */
	public String getObjectAlias() {
		return objectAlias;
	}
	public void setObjectAlias(String objectAlias) {
		this.objectAlias = objectAlias;
	}

	/**
	 * Subclasses may override to customize select clause creation.
	 */
	protected StringBuilder getSelectBuilder() {
		return getSelectBuilder(getObjectAlias());
	}
	
	/**
	 * Subclasses may override to customize select clause creation.
	 * 
	 * <p>
	 * If the select clause is set, use to create the query, 
	 * otherwise build it like 'select ${objectAlias} from
	 * ${objectName} ${objectAlias}'.
	 * </p>
	 * 
	 * @param objectName
	 */
	protected StringBuilder getSelectBuilder(String objectAlias) {
		if (getSelectClause()!=null && getSelectClause().length()>0) {
			return new StringBuilder(getSelectClause());
		}
		StringBuilder selectClause = new StringBuilder("select ");
		return selectClause
			.append(objectAlias)
			.append(" from ")
			.append(getObjectName())
			.append(" ")
			.append(objectAlias)
			.append(" ");
	}
	
	/**
	 * Optional select clause.
	 */
	public String getSelectClause() {
		return selectClause;
	}
	public void setSelectClause(String selectClause) {
		this.selectClause = selectClause;
	}

	public Collection<T> find(StringBuilder selectClause, String whereClause) {
        return find(selectClause, whereClause, new Object[0]);
	}

	public T findUnique(Object... keys) {
        logger.debug("Finding unique with {} parameter(s)", keys.length);
		Collection<T> uniqueList = find(getSelectBuilder(), getWhereClauseBuilder(getParams()).toString(), keys);
		try {
			if (uniqueList!=null && uniqueList.size()==0) {
				logger.info("Finding unique RETURNING NULL");
				return null;
			}
			if (uniqueList!=null && uniqueList.size()==1) {
				return uniqueList.iterator().next();
			}
			throw new IllegalArgumentException("Unable to find unique result: found "+uniqueList.size());
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to find unique result", e);
		}
	}

	/**
	 * @return
	 */
	protected StringBuilder getWhereClauseBuilder(String[] fields) {
		StringBuilder whereClauseBuilder = new StringBuilder();
		if (fields==null) {
			return whereClauseBuilder;
		}
		for (int i = 0;i<fields.length;i++) {
			if (i>0) {
				whereClauseBuilder.append(" AND ");
			}
			whereClauseBuilder.append(getObjectAlias()).append(".");
			whereClauseBuilder.append(fields[i]).append(" = ?");
		}
		whereClauseBuilder.append(" ");
		return whereClauseBuilder;
	}
	
	public Collection<T> find(StringBuilder selectClause, String whereClause, Object... values) {
        if (!whereClause.equals("")) {
        	selectClause.append("where ").append(whereClause);
        }
        String query = selectClause.toString();
        logger.debug("Query {}", query);
		return (Collection<T>) getPersistenceStrategy().find(query, values);
	}

	// persistence strategy implementation

	public Collection<T> find(int firstRow, String query, Object... values) {
		return (Collection<T>) getPersistenceStrategy().find(firstRow, query, values);
	}

	public Collection<T> find(int firstRow, int maxRows, String query, Object... values) {
		return (Collection<T>) getPersistenceStrategy().find(firstRow, maxRows, query, values);
	}

	public Collection<T> find(String query, Object... values) {
		return (Collection<T>) getPersistenceStrategy().find(query, values);
	}

	public T merge(T object) {
		return (T) getPersistenceStrategy().merge(object);
	}

	public void persist(T managedObject) {
        getPersistenceStrategy().persist(managedObject);
	}
	
	public T load(T managedObject) {
		return (T) getPersistenceStrategy().load(managedObject);
	};
	
	public T load(Class<? extends T> clazz, Serializable id) {
		return (T) getPersistenceStrategy().load(clazz, id);
	}

	public void saveOrUpdate(T managedObject) {
        getPersistenceStrategy().saveOrUpdate(managedObject);
	}

	public void remove(T object) {
        getPersistenceStrategy().remove(object);
	}

	public void refresh(T object) {
        getPersistenceStrategy().refresh(object);
	}

	public void evict(T object) {
        getPersistenceStrategy().evict(object);
	}
	
	public void flush() {
        getPersistenceStrategy().flush();
	}
	
	public void clear() {
        getPersistenceStrategy().clear();
	}

	// collabs
    
	private PersistenceStrategy persistenceStrategy;
    
    protected PersistenceStrategy getPersistenceStrategy() {
		return this.persistenceStrategy;
	}

    @Resource
	public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
		this.persistenceStrategy = persistenceStrategy;
	}

    private static final Logger logger = LoggerFactory.getLogger(AbstractBasicDao.class);

}
