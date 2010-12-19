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

package org.helianto.core.filter.classic;

import org.helianto.core.Entity;
import org.helianto.core.filter.ListFilter;

/**
 * Extends the <code>Filter</code> interface to enforce the use
 * of <code>Entity</code> to build criteria.
 *  
 * @author Mauricio Fernandes de Castro
 * @deprecated see AbstractTrunkFilterAdapter
 */
public interface EntityBackedFilter extends ListFilter {
	
	/**
	 * Create criteria.
	 * 
	 * @param requireEntity raise unchecked exception if true and 
	 *        entity is not defined.
	 */
	public String createCriteriaAsString(boolean requireEntity);
	
	/**
	 * Entity filter property.
	 */
	public Entity getEntity();
	
}
