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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.IdentityFilter;
import org.springframework.stereotype.Component;

/**
 * Default implementation of <code>IdentitySelectionStrategy</code>
 * interface. Selects identities from any <code>User</code> that
 * belongs to the same <code>Entity</code> as the current 
 * <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated in favour of DefalultIdentityDao
 */
@Component("identitySelectionStrategy")
public class DefaultIdentitySelectionStrategy  implements IdentitySelectionStrategy {

    public String createCriteriaAsString(IdentityFilter filter, String prefix) {
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(prefix);
        
        appendEntityFromUserBackedFilter(filter, mainCriteriaBuilder);
        appendPrincipalSearchFilter(filter, mainCriteriaBuilder);
        // TODO create second level criteria to optionalAlias, firstName and lastName (below)
        
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
    
    /**
     * Mandatory <code>Entity</code> filter segment.
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendEntityFromUserBackedFilter(IdentityFilter filter, CriteriaBuilder criteriaBuilder) {
        if (filter.getUser()==null) {
            throw new IllegalArgumentException("User required!");
        }
        criteriaBuilder.appendAnd().appendSegment("id", "in")
        .append("(select user.identity.id from User user where user.entity.id = ")
        .append(filter.getUser().getEntity().getId())
        .append(") ");
    }
    
    /**
     * <code>principalSearch</code> filter segment (not case sensitive)
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendPrincipalSearchFilter(IdentityFilter filter, CriteriaBuilder criteriaBuilder) {
        if (!filter.getPrincipal().equals("")) {
            criteriaBuilder.appendAnd().appendSegment("principal", "like", "lower")
            .appendLike(filter.getPrincipal().toLowerCase());
       }
    }
    
    /**
     * <code>optionalAlias</code> filter segment (not case sensitive)
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendOptionalAliasSearchFilter(IdentityFilter filter, CriteriaBuilder criteriaBuilder) {
        if (!filter.getNameOrAliasSearch().equals("")) {
            criteriaBuilder.appendAnd().appendSegment("optionalAlias", "like", "lower")
            .appendLike(filter.getNameOrAliasSearch().toLowerCase());
       }
    }
    
    /**
     * <code>firstName</code> filter segment (not case sensitive)
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendFirstNameSearchFilter(IdentityFilter filter, CriteriaBuilder criteriaBuilder) {
        if (!filter.getNameOrAliasSearch().equals("")) {
            criteriaBuilder.appendAnd().appendSegment("firstName", "like", "lower")
            .appendLike(filter.getNameOrAliasSearch().toLowerCase());
       }
    }
    
    /**
     * <code>lastName</code> filter segment (not case sensitive)
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendLastNameSearchFilter(IdentityFilter filter, CriteriaBuilder criteriaBuilder) {
        if (!filter.getNameOrAliasSearch().equals("")) {
            criteriaBuilder.appendAnd().appendSegment("lastName", "like", "lower")
            .appendLike(filter.getNameOrAliasSearch().toLowerCase());
       }
    }
    
    private static Log logger = LogFactory.getLog(DefaultIdentitySelectionStrategy.class);

}
