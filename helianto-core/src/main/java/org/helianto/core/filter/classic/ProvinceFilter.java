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

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.User;
import org.helianto.core.criteria.OrmCriteriaBuilder;

/**
 * Province filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see ProvinceFilterAdapter
 */
public class ProvinceFilter extends AbstractUserBackedCriteriaFilter implements Serializable, PolymorphicFilter<Province> {
	
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
	private Class<? extends Province> clazz;
	private char discriminator;
	
	/**
	 * Default constructor.
	 */
	public ProvinceFilter() {
		super();
		setClazz(Province.class);
		reset();
	}
	
	/**
	 * Operator constructor.
	 * 
	 * @param operator
	 */
	public ProvinceFilter(Operator operator) {
		this();
		setOperator(operator);
	}
	
	/**
	 * Province code constructor.
	 * 
	 * @param operator
	 * @param provinceCode
	 */
	public ProvinceFilter(Operator operator, String provinceCode) {
		this(operator);
		setProvinceCode(provinceCode);
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
	protected void appendEntityFilter(Entity entity, OrmCriteriaBuilder mainCriteriaBuilder) {
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
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("provinceCode", getProvinceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
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
	
	/**
	 * Class filter.
	 */
	public Class<? extends Province> getClazz() {
		return this.clazz;
	}
	public void setClazz(Class<? extends Province> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Discriminator filter.
	 */
	public char getDiscriminator() {
		return this.discriminator; 
	}
	public void setDiscriminator(char discriminator) {
		this.discriminator = discriminator;
	}

}
