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

/**
 * Base implementation for <code>BasicDao</code>.
 * 
 * @author Mauricio Fernandes de Castro.
 */
public abstract class AbstractBasicDao<T> implements BasicDao<T> {
	
	public abstract Class<? extends T> getClazz();

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
	 * Subclasses may override to customize persistent object alias.
	 */
	public String getObjectAlias() {
        return getClazz().getSimpleName().toLowerCase();
	}

	/**
	 * Subclasses may override to customize select clause creation.
	 * 
	 * @param objectName
	 */
	protected StringBuilder getSelectBuilder() {
		StringBuilder selectClause = new StringBuilder("select ");
		return selectClause
			.append(getObjectAlias())
			.append(" from ")
			.append(getObjectName())
			.append(" ")
			.append(getObjectAlias())
			.append(" ");
	}
	
	public Collection<T> find(StringBuilder selectClause, String whereClause) {
        return find(selectClause, whereClause, new Object[0]);
	}

	protected abstract Collection<T> find(StringBuilder selectClause, String whereClause, Object... values);

	public T findUnique(Object... keys) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique with "+keys.length+" parameter(s)");
        }
		Collection<T> uniqueList = find(getSelectBuilder(), getWhereClauseBuilder(getParams()).toString(), keys);
		try {
			if (uniqueList!=null && uniqueList.size()==0) {
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

	/**
	 * Default keys are entity.id and internalNumber
	 */
	protected String[] getParams() {
		return new String[] { "entity", "internalNumber" };
	}
	
    private static final Log logger = LogFactory.getLog(AbstractBasicDao.class);

}
