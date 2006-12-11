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

package org.helianto.core.hibernate;

import org.helianto.core.dao.IdentityFilter;
import org.helianto.core.dao.IdentitySelectionStrategy;

/**
 * Default implementation of <code>IdentitySelectionStrategy</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentitySelectionStrategy implements
        IdentitySelectionStrategy {

	public String createCriteriaAsString(IdentityFilter filter) {
		return createCriteriaAsString(filter, "identity");
    }

	public String createCriteriaAsString(IdentityFilter filter, String prefix) {
        StringBuilder criteria = new StringBuilder();
        if (!filter.getPrincipalSearch().equals("")) {
            criteria.append("lower("+prefix+".principal) like '%")
            .append(filter.getPrincipalSearch().toLowerCase())
            .append("%' ");
        }
        if (!filter.getNameOrAliasSearch().equals("")) {
            String nameOrAliasSearch = filter.getNameOrAliasSearch().toLowerCase();
            if (!criteria.toString().equals("")) {
                criteria.append("or ");
            }
            criteria.append("lower("+prefix+".optionalAlias) like '%")
            .append(nameOrAliasSearch)
            .append("%' ")
            .append("or ")
            .append("lower("+prefix+".firstName) like '%")
            .append(nameOrAliasSearch)
            .append("%' ")
            .append("or ")
            .append("lower("+prefix+".lastName) like '%")
            .append(nameOrAliasSearch)
            .append("%' ");
        }
        if (criteria.toString().equals("")) {
            return "";
        }
        return criteria.insert(0, "and (").append(")").toString();
	}

}
