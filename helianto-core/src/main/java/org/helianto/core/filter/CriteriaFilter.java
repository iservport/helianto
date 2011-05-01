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

package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;


/**
 * Filters implementing this interface are enabled to create 
 * string criteria to collaborate with the persistence strategy.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface CriteriaFilter extends Filter {
	
	/**
	 * Create criteria.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public String createCriteriaAsString(OrmCriteriaBuilder mainCriteriaBuilder);

	/**
	 * Reset filter.
	 */
    public void reset();
	
}
