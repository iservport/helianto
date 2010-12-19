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


package org.helianto.resource;

import org.helianto.core.User;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;

/**
 * Resource parameter filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterFilter extends AbstractUserBackedCriteriaFilter {

	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static ResourceParameterFilter resourceParameterFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(ResourceParameterFilter.class, user);
	}

    private static final long serialVersionUID = 1L;
    private String parameterCode;
	private String parameterNameLike;
	
	/**
	 * Default constructor.
	 */
	public ResourceParameterFilter() {
		setParameterCode("");
		setParameterNameLike("");
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		setParameterCode("");
		setParameterNameLike("");
	}

	public boolean isSelection() {
		return getParameterCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("parameterCode", getParameterCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("parameterName", getParameterNameLike(), mainCriteriaBuilder);
		appendOrderBy("parameterCode", mainCriteriaBuilder);
	}

	/**
	 * Parameter code.
	 */
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	
	/**
	 * Parameter name like.
	 */
    public String getParameterNameLike() {
		return parameterNameLike;
	}
	public void setParameterNameLike(String parameterNameLike) {
		this.parameterNameLike = parameterNameLike;
	}

}
