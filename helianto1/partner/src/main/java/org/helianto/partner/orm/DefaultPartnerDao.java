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

import org.helianto.core.Entity;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.springframework.stereotype.Repository;

/**
 * Partner data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("partnerDao")
public class DefaultPartnerDao extends AbstractFilterDao<Partner, PartnerFilter> {

	/**
	 * Read entity from the associated partner registry.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("partnerRegistry.entity.id", entity.getId(), mainCriteriaBuilder);
	}

	@Override
	protected void preProcessFilter(PartnerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (filter.getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(filter.getClazz());
		}
	}

	@Override
	protected void doSelect(PartnerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("partnerRegistry.partnerAlias", filter.getPartnerAlias(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(PartnerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("partnerRegistry.partnerName", filter.getPartnerNameLike(), mainCriteriaBuilder);
		appendEqualFilter("partnerState", filter.getPartnerState(), mainCriteriaBuilder);
		appendEqualFilter("priority", filter.getPriority(), mainCriteriaBuilder);
		appendOrderBy("partnerRegistry.partnerAlias", mainCriteriaBuilder);
	}

	@Override
	public Class<? extends Partner> getClazz() {
		return Partner.class;
	}

	@Override
	protected String[] getParams() {
		return new String[] { "partnerRegistry", "class" };
	}

}
