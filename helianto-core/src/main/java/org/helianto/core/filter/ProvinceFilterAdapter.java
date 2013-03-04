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
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Province filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 * @see ProvinceFormFilterAdapter
 */
public class ProvinceFilterAdapter extends AbstractRootFilterAdapter<Province> implements ParentFilter {
	
	private static final long serialVersionUID = 1L;
	private Class<? extends Province> clazz;
	private Province parent;
	private char discriminator;
	
	/**
	 * Default constructor.
	 */
	public ProvinceFilterAdapter(Province province) {
		super(province);
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
	
	@Override
	public String getOrderByString() {
		return "provinceCode";
	}
	
	/**
	 * Filter reset.
	 */
	public void reset() {
		getForm().setProvinceName("");
	}

	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getForm().getProvinceCode().length()>0;
	}
	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
			connect = true;
		}
		if (getParent()!=null) {
			appendEqualFilter("parent.id", getParentId(), mainCriteriaBuilder);
			connect = true;
		}
		return connect;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("provinceCode", getForm().getProvinceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("provinceName", getForm().getProvinceName(), mainCriteriaBuilder);
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
	 * Parent filter.
	 */
	@SuppressWarnings("unchecked")
	public Province getParent() {
		return parent;
	}
	public void setParent(Province parent) {
		this.parent = parent;
	}
	
	public long getParentId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return 0;
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
