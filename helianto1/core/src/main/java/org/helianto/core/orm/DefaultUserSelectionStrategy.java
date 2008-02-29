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
import org.helianto.core.Identity;
import org.helianto.core.UserFilter;
import org.helianto.core.dao.UserSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Default implementation of <code>UserSelectionStrategy</code>
 * interface. Selects users from any <code>User</code> that
 * belongs to the same <code>Entity</code> as the current 
 * <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserSelectionStrategy  implements UserSelectionStrategy {

    public String createCriteriaAsString(UserFilter filter, String prefix) {
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(prefix);
        
        mainCriteriaBuilder.appendEntityFromUserBackedFilter(filter);
        appendIdentityPrincipalFilter(filter, mainCriteriaBuilder);
        appendExclusionsFilter(filter, mainCriteriaBuilder);
        appendUserState(filter, mainCriteriaBuilder);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
    
    /**
     * <code>identityPrincipal</code> filter segment (not case sensitive)
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendIdentityPrincipalFilter(UserFilter filter, CriteriaBuilder criteriaBuilder) {
        if (filter.getIdentityPrincipal().length()>0) {
            criteriaBuilder.appendAnd().appendSegment("identityPrincipal", "like", "lower")
            .appendLike(filter.getIdentityPrincipal().toLowerCase());
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
    
    /**
     * <code>userState</code> filter segment (not case sensitive)
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendUserState(UserFilter filter, CriteriaBuilder criteriaBuilder) {
        if (filter.getUserState()!=' ') { 
            criteriaBuilder.appendAnd().appendSegment("userState", "=")
            	.append(filter.getUserState());
       }
    }
    
    private static Log logger = LogFactory.getLog(DefaultUserSelectionStrategy.class);

}
