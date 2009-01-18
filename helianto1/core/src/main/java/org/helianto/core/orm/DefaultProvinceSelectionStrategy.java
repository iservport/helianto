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
import org.helianto.core.ProvinceFilter;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Component;

/**
 * Selection strategy parametrized to <code>ProvinceFilter</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("provinceSelectionStrategy")
public class DefaultProvinceSelectionStrategy extends AbstractSelectionStrategy<ProvinceFilter> {

	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("operator.id", "=")
        .append(entity.getOperator().getId());
    }

	@Override
	protected boolean isSelection(ProvinceFilter filter) {
		return (filter.getProvinceCode().length()>0);
	}
	
	@Override
	protected void doSelect(ProvinceFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("provinceCode", filter.getProvinceCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(ProvinceFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("provinceName", filter.getProvinceNameLike(), mainCriteriaBuilder);
	}

}
