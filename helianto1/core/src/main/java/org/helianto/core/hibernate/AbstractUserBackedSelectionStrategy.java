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

import org.helianto.core.dao.UserBackedSelectionStrategy;
import org.helianto.core.hibernate.filter.UserBackedFilter;

/**
 * Base class to selection strategies using an 
 * <code>UserBackedFilter</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractUserBackedSelectionStrategy extends AbstractSelectionStrategy implements
        UserBackedSelectionStrategy {

    /**
     * Creates a criteria builder if a <code>User</code> is present, or throws 
     * <code>IllegalArgumentException</code>.
     * 
     * @param filter
     * @param prefix
     */
    public StringBuilder createFilter(UserBackedFilter filter, String prefix) {
        StringBuilder criteria = new StringBuilder();
        
        if (filter.getUser()==null) {
            throw new IllegalArgumentException("An user must be specified on any filter!");
        }
        concatenate(criteria, prefix, "id", "in")
        .append("(select user.identity.id from User user where user.entity.id = ")
        .append(filter.getUser().getEntity().getId())
        .append(") ");
        return criteria;
    }

}
