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


package org.helianto.core.orm;

import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.dao.AbstractHibernateFilterDao;
import org.springframework.stereotype.Repository;

/**
 * Identity data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("identityDao")
public class DefaultIdentityDao extends AbstractHibernateFilterDao<Identity, IdentityFilter> {

	/**
	 * Default key is principal
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "principal" };
	}

	@Override
	public Class<? extends Identity> getClazz() {
		return Identity.class;
	}

}
