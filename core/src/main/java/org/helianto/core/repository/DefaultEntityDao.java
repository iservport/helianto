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


package org.helianto.core.repository;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.dao.AbstractHibernateFilterDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Entity data acess.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("entityDao")
@Transactional
public class DefaultEntityDao extends AbstractHibernateFilterDao<Entity, EntityFilter> {

	/**
	 * Keys are operator.id and alias
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "operator", "alias" };
	}

	@Override
	public Class<? extends Entity> getClazz() {
		return Entity.class;
	}

}
