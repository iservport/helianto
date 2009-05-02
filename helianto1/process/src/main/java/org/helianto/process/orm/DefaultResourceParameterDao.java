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


package org.helianto.process.orm;

import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterFilter;
import org.springframework.stereotype.Repository;

/**
 * Resource parameter data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceParameterDao")
public class DefaultResourceParameterDao extends AbstractFilterDao<ResourceParameter, ResourceParameterFilter> {

	@Override
	protected void doSelect(ResourceParameterFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("parameterCode", filter.getParameterCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(ResourceParameterFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("parameterName", filter.getParameterNameLike(), mainCriteriaBuilder);
		appendOrderBy("parameterCode", mainCriteriaBuilder);
	}

	@Override
	public Class<? extends ResourceParameter> getClazz() {
		return ResourceParameter.class;
	}

	/**
	 * Default keys are entity.id and parameterCode
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "entity", "parameterCode" };
	}

}
