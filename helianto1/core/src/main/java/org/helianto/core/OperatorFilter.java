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


package org.helianto.core;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * <code>Operator</code> filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorFilter extends AbstractUserBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;
	private String operatorName;
	
	public static OperatorFilter filterFactory(User user) {
		OperatorFilter operatorFilter = new OperatorFilter();
		operatorFilter.setUser(user);
		operatorFilter.reset();
		return operatorFilter;
	}

	public void reset() {
	}

	/**
	 * Property to filter operatorName.
	 */
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

}
