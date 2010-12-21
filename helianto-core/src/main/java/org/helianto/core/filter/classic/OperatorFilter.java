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
import org.helianto.core.User;
import org.helianto.core.criteria.CriteriaBuilder;

/**
 * <code>Operator</code> filter.
 * 
 * <p>
 * NOTE: Operator filters are not required to have a root entity or user
 * like most user backed criteria filters usually do.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorFilter extends AbstractUserBackedCriteriaFilter {

	/**
	 * Factory method.
	 */
	public static OperatorFilter filterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(OperatorFilter.class, user);
	}
	
	private static final long serialVersionUID = 1L;
	private String operatorName;
	private String operatorNameLike;
	
	/**
	 * Default constructor.
	 */
	public OperatorFilter() {
		setOperatorName("");
		setOperatorNameLike("");
	}

	/**
	 * Reset.
	 */
	public void reset() {
		setOperatorNameLike("");
	}

	public boolean isSelection() {
		return getOperatorName().length()>0;
	}

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * Overriden because default implementation does not allow other 
	 * entities to be selected.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("operatorName", getOperatorNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("operatorName", getOperatorName(), mainCriteriaBuilder);
	}

	/**
	 * Property to select operatorName.
	 */
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * Property to filter operatorName.
	 */
	public String getOperatorNameLike() {
		return operatorNameLike;
	}
	public void setOperatorNameLike(String operatorNameLike) {
		this.operatorNameLike = operatorNameLike;
	}

}
