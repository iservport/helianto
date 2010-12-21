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

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Province filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProvinceFilterAdapter extends AbstractRootFilterAdapter<Province> {
	
	private static final long serialVersionUID = 1L;
	private Class<? extends Province> clazz;
	private char discriminator;
	
	/**
	 * Default constructor.
	 */
	public ProvinceFilterAdapter(Province province) {
		super(province);
		setClazz(Province.class);
	}
	
	/**
	 * Province code constructor.
	 * 
	 * @param operator
	 * @param provinceCode
	 */
	public ProvinceFilterAdapter(Operator operator, String provinceCode) {
		this(new Province(operator,  provinceCode));
	}
	
	/**
	 * Filter reset.
	 */
	public void reset() {
		getFilter().setProvinceCode("");
		getFilter().setProvinceName("");
	}

	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getFilter().getProvinceCode().length()>0;
	}
	
	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("provinceCode", getFilter().getProvinceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("provinceName", getFilter().getProvinceName(), mainCriteriaBuilder);
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
