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

import org.helianto.core.Service;
import org.helianto.core.ServiceFilter;
import org.helianto.core.dao.AbstractHibernateFilterDao;
import org.springframework.stereotype.Repository;

/**
 * Service data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("serviceDao")
public class DefaultServiceDao extends AbstractHibernateFilterDao<Service, ServiceFilter> {

	/**
	 * Keys are operator.id and serviceName
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "operator", "serviceName" };
	}

	@Override
	public Class<? extends Service> getClazz() {
		return Service.class;
	}

}
