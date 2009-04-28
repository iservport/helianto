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


package org.helianto.partner.orm;

import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;
import org.springframework.stereotype.Repository;

/**
 * Partner registry data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("partnerRegistryDao")
public class DefaultPartnerRegistryDao extends AbstractFilterDao<PartnerRegistry, PartnerRegistryFilter> {

	@Override
	protected void doFilter(PartnerRegistryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("partnerName", filter.getPartnerNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(PartnerRegistryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("partnerAlias", filter.getPartnerAlias(), mainCriteriaBuilder);
	}

	@Override
	public Class<? extends PartnerRegistry> getClazz() {
		return PartnerRegistry.class;
	}

	@Override
	protected String[] getParams() {
		return new String[] { "entity", "partnerAlias" };
	}

}