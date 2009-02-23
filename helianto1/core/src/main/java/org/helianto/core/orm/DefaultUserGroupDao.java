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
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * <code>UserGroup</code> data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("userGroupDao")
public class DefaultUserGroupDao extends AbstractFilterDao<UserGroup, UserFilter> {

	/**
	 * Required to avoid exception when entity is not present.
	 */
	@Override
	protected String createCriteriaAsString(UserFilter filter) {
		return createCriteriaAsString(filter, false);
	}
	
	@Override
	protected void preProcessFilter(UserFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (filter.getClass()!=null) {
			mainCriteriaBuilder.appendAnd().append(filter.getClazz());
		}
	}

	@Override
	protected void doFilter(UserFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (filter.getIdentity()!=null) {
			appendEqualFilter("identity.id", filter.getIdentity().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("userState", filter.getUserState(), mainCriteriaBuilder);
        appendExclusionsFilter(filter, mainCriteriaBuilder);
		if (filter.isOrderByLastEventDesc()) {
			appendOrderBy("lastEvent DESC", mainCriteriaBuilder);
		}
	}

    /**
     * <code>exclusions</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendExclusionsFilter(UserFilter filter, CriteriaBuilder criteriaBuilder) {
        if (filter.getExclusions()!=null && filter.getExclusions().size() > 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("Found "+filter.getExclusions().size()+" exclusion(s).");
            }
            criteriaBuilder.appendAnd().appendSegment("identity.id", "not in")
            .append("(");
            String separator = "";
            for (Identity identity: filter.getExclusions()) {
            	if (identity!=null) {
                    criteriaBuilder.append(separator).append(identity.getId());
                    if (separator.equals("")) {
                        separator = ", ";
                    }
            	}
            }
            criteriaBuilder.append(")");
       }
    }
    
	@Override
	protected boolean isSelection(UserFilter filter) {
		return filter.getIdentityPrincipal()!=null && filter.getIdentityPrincipal().length() > 0;
	}

	@Override
	protected void doSelect(UserFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendAnd().appendSegment("identity.principal", "like", "lower").appendLike(
				filter.getIdentityPrincipal().toLowerCase());
	}

	@Override
	public Class<? extends UserGroup> getClazz() {
		return UserGroup.class;
	}

	@Override
	protected String[] getParams() {
		return new String[] { "entity", "identity" };
	}

}
