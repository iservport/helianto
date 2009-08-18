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

import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Operator data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("operatorDao")
@Transactional
public class DefaultOperatorDao extends AbstractFilterDao<Operator, OperatorFilter> {

	@Override
	public Class<? extends Operator> getClazz() {
		return Operator.class;
	}

	/**
	 * Default key is operatorName.
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "operatorName" };
	}

}
