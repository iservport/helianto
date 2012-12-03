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

import java.io.Serializable;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractFilter;

/**
 * Country filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see ContryFilterAdapter
 */
public class CountryFilter extends AbstractFilter implements Serializable {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static CountryFilter filterFactory(Operator operator) {
		CountryFilter countryFilter = new CountryFilter(operator);
		countryFilter.reset();
		return countryFilter;
	}

	private static final long serialVersionUID = 1L;
	private Operator operator;
	private String countryCode;
	private String countryNameLike;
	
	/**
	 * Default constructor.
	 */
	public CountryFilter() {
		super();
		reset();
	}
	
	/**
	 * Operator constructor.
	 */
	public CountryFilter(Operator operator) {
		this();
		setOperator(operator);
	}
	
	/**
	 * Filter reset.
	 */
	public void reset() {
		setCountryCode("");
		setCountryNameLike("");
	}

	public String getObjectAlias() {
		return "country";
	}

	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getCountryCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("countryCode", getCountryCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("countryName", getCountryNameLike(), mainCriteriaBuilder);
	}

	/**
	 * Operator filter.
	 */
	public Operator getOperator() {
		return operator;
	}
	private void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * Property to filter country by code.
	 */
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Property to filter country by similar name.
	 */
	public String getCountryNameLike() {
		return countryNameLike;
	}
	public void setCountryNameLike(String countryNameLike) {
		this.countryNameLike = countryNameLike;
	}

}
