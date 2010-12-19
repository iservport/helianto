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

package org.helianto.controller;

import org.helianto.core.filter.classic.UserBackedFilter;

/**
 * Base class to forms using filters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterForm<F extends UserBackedFilter, T> extends AbstractTargetForm<T> {

	private static final long serialVersionUID = 1L;
	private F filter;
	private char typeConstraint;
	
	/**
	 * The exposed filter.
	 */
	public F getFilter() {
		return filter;
	}
	public void setFilter(F filter) {
		this.filter = filter;
	}
	
	/**
	 * Type constraint.
	 */
	public char getTypeConstraint() {
		return typeConstraint;
	}
	public void setTypeConstraint(char typeConstraint) {
		this.typeConstraint = typeConstraint;
	}
    
	@Override
	public String toString() {
		return new StringBuilder()
		.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [")
    	.append("typeConstraint").append("='").append(getTypeConstraint()).append("' ")			
    	.append("filter").append("='").append(getFilter()).append("' ")			
    	.append("target").append("='").append(getTarget()).append("' ")			
    	.append("]")
    	.toString();
	}

}
