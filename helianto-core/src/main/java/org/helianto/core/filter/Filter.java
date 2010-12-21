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

import org.helianto.core.criteria.CriteriaBuilder;


/**
 * Filters implementing this interface are enabled to create 
 * string criteria to collaborate with the persistence strategy.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface Filter {
	
	/**
	 * Create criteria.
	 */
	public String createCriteriaAsString();

	/**
	 * Create criteria.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public String createCriteriaAsString(CriteriaBuilder mainCriteriaBuilder);

	/**
	 * Reset filter.
	 */
    public void reset();
	
    /**
     * If true, a unique result is expected, otherwise, a collection.
     * 
     * <p>
     * Convenient when filter properties correspond to the natural key.
     * </p>
     */
    public boolean isSelection();

	/**
	 * Object alias to be used in query expressions.
	 */
	public String getObjectAlias();
	
	/**
	 * Object alias to be used in query expressions.
	 */
	public void setObjectAlias(String objectAlias);
	
}
