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

import org.helianto.core.hibernate.filter.UserBackedFilter;


/**
 * A strategy to create selection filters based on <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserBackedSelectionStrategy {

    /**
     * Creates a criteria builder if a <code>User</code> is present, or throws 
     * <code>IllegalArgumentException</code>.
     * 
     * @param filter
     * @param prefix
     */
    public StringBuilder createFilter(UserBackedFilter filter, String prefix);

}
