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

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Province filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProvinceFilter extends AbstractUserBackedCriteriaFilter implements Serializable {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static ProvinceFilter filterFactory(User user) {
		ProvinceFilter provinceFilter = new ProvinceFilter();
		provinceFilter.setUser(user);
		provinceFilter.reset();
		return provinceFilter;
	}

	private static final long serialVersionUID = 1L;
	private Operator operator;
	private String provinceCode;
	private String provinceNameLike;
	
	/**
	 * Default constructor.
	 */
	public ProvinceFilter() {
		super();
		reset();
	}
	
	/**
	 * Operator constructor.
	 */
	public ProvinceFilter(Operator operator) {
		this();
		setOperator(operator);
	}
	
	/**
	 * Filter reset.
	 */
	public void reset() {
		setProvinceCode("");
		setProvinceNameLike("");
	}

	public String getObjectAlias() {
		return "province";
	}

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * Filter provinces using same operator as the current entity.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("operator.id", "=")
			.append(entity.getOperator().getId());
	}

	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getProvinceCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("provinceCode", getProvinceCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("provinceName", getProvinceNameLike(), mainCriteriaBuilder);
	}

	@Override
	public void setEntity(Entity entity) {
		super.setEntity(entity);
		if (entity!=null) {
			setOperator(entity.getOperator());
		}
	}

	/**
	 * Operator filter.
	 */
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * Property to filter province by code.
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * Property to filter province by similar name.
	 */
	public String getProvinceNameLike() {
		return provinceNameLike;
	}
	public void setProvinceNameLike(String provinceNameLike) {
		this.provinceNameLike = provinceNameLike;
	}

}
