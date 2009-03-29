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
import org.helianto.core.Identity;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.IdentityFilter;
import org.springframework.stereotype.Repository;

/**
 * Identity data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("identityDao")
public class DefaultIdentityDao extends AbstractFilterDao<Identity, IdentityFilter> {

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * When entity is not null, restrict selection to identities already 
	 * associated to it.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		if (entity!=null) {
			mainCriteriaBuilder.appendAnd().appendSegment("id", "in")
		        .append("(select user.identity.id from User user where user.entity.id = ")
		        .append(entity.getId())
		        .append(") ");
		}
	}

	/**
	 * Default key is principalr
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "principal" };
	}

	@Override
	protected void doSelect(IdentityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        mainCriteriaBuilder.appendAnd().appendSegment("principal", "like", "lower")
            .appendLike(filter.getPrincipal().toLowerCase());
	}

	@Override
	protected void doFilter(IdentityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("optionalAlias", filter.getNameOrAliasSearch(), mainCriteriaBuilder);
	}

	@Override
	public Class<? extends Identity> getClazz() {
		return Identity.class;
	}

}
