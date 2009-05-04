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

import org.helianto.core.Unit;
import org.helianto.core.UnitFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Unit data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("unitDao")
public class DefaultUnitDao extends AbstractFilterDao<Unit, UnitFilter> {

	@Override
	protected void preProcessFilter(UnitFilter filter, CriteriaBuilder mainCriteriaBuilder) {
    	if (filter.getCategoryGroup()!=null) {
        	appendEqualFilter("category.categoryGroup", filter.getCategoryGroup().getValue(), mainCriteriaBuilder);
    	}
	}

	@Override
	protected void doFilter(UnitFilter filter, CriteriaBuilder mainCriteriaBuilder) {
    	if (filter.getCategory()!=null) {
        	appendEqualFilter("category.id", filter.getCategory().getId(), mainCriteriaBuilder);
    	}
    	appendLikeFilter("unitNameLike", filter.getUnitNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(UnitFilter filter, CriteriaBuilder mainCriteriaBuilder) {
    	appendEqualFilter("unitCode", filter.getUnitCode(), mainCriteriaBuilder);
	}

	@Override
	protected String[] getParams() {
		return new String[] { "entity", "unitCode" };
	}

	@Override
	public Class<? extends Unit> getClazz() {
		return Unit.class;
	}

}