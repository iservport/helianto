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

import org.helianto.core.Entity;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Province data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("provinceDao")
@Transactional
public class DefaultProvinceDao extends AbstractFilterDao<Province, ProvinceFilter> {

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * Filter provinces using same operator as the current entity.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("operator.id", "=")
			.append(entity.getOperator().getId());
	}

	/**
	 * Selection criterion.
	 */
	@Override
	protected boolean isSelection(ProvinceFilter filter) {
		return filter.getProvinceCode().length()>0;
	}

	@Override
	protected void doSelect(ProvinceFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("provinceCode", filter.getProvinceCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(ProvinceFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("provinceName", filter.getProvinceNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected String[] getParams() {
		return new String[] { "operator", "provinceCode" };
	}

	@Override
	public Class<? extends Province> getClazz() {
		return Province.class;
	}
    
}
