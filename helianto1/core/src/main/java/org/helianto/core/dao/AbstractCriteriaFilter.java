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

package org.helianto.core.dao;

/**
 * Base class to criteria filters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractCriteriaFilter implements
    IdentitySelectionStrategy {
    
    /**
     * Creates the criteria builder.
     */
    protected StringBuilder createCriteria() {
        return new StringBuilder();
    }
    
    /**
     * Performs usual prefix cocatenation.
     * 
     * @param criteria
     * @param prefix
     * @param field
     * @param sqlOperator
     */
    protected StringBuilder concatenate(StringBuilder criteria, String prefix, String field, String sqlOperator) {
        criteria.append(prefix)
            .append(".")
            .append(field)
            .append(" ")
            .append(sqlOperator)
            .append(" ");
        return criteria;
    }

}
