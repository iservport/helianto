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

package org.helianto.partner.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.nature.Agent;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
import org.helianto.partner.domain.nature.Laboratory;
import org.helianto.partner.domain.nature.Manufacturer;
import org.helianto.partner.domain.nature.Supplier;

/**
 * Partner filter adapter.
 * 
 * @author Maurício Fernandes de Castro
 * @deprecated
 * @see PartnerFormFilterAdapter
 */
public class PartnerFilterAdapter extends AbstractTrunkFilterAdapter<Partner> {
	
	private static final long serialVersionUID = 1L;
	private Class<? extends Partner> clazz;
	
	/**
	 * Default constructor.
	 * 
	 * @param partner
	 */
	public PartnerFilterAdapter(Partner partner) {
		super(partner);
		reset();
	}
	
	/**
	 * Partner class constructor.
	 * 
	 * @param partner
	 * @param clazz
	 */
	public PartnerFilterAdapter(Partner partner, Class<? extends Partner> clazz) {
		this(partner);
		setClazz(clazz);
	}

	/**
	 * Partner class constructor.
	 * 
	 * @param partner
	 * @param discriminator
	 */
	public PartnerFilterAdapter(Partner partner, char discriminator) {
		this(partner);
		setDiscriminator(discriminator);
	}

	/**
	 * Reset method.
	 */
	public void reset() { 
		getForm().setPartnerState(' ');
	}

	public boolean isSelection() {
		return getForm().getEntityAlias().length()>0;
	}

	/**
	 * Read entity from the associated partner registry.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.entity.id", entity.getId(), mainCriteriaBuilder);
	}

	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
			connect = true;
		}
		return connect;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.entityAlias", getForm().getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("privateEntity.entityName", getForm().getEntityName(), mainCriteriaBuilder);
		appendEqualFilter("partnerState", getForm().getPartnerState(), mainCriteriaBuilder);
		appendEqualFilter("priority", getForm().getPriority(), mainCriteriaBuilder);
		appendOrderBy("privateEntity.entityAlias", mainCriteriaBuilder);
	}

	/**
	 * Subclass
	 */
	public Class<? extends Partner> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends Partner> clazz) {
		this.clazz = clazz;
	}
	
	public char getDiscriminator() {
		if (clazz==null) {
			return ' '; 
		}
		if (clazz.equals(Agent.class)) {
			return 'A'; 
		}
		if (clazz.equals(Customer.class)) {
			return 'C'; 
		}
		if (clazz.equals(Division.class)) {
			return 'D'; 
		}
		if (clazz.equals(Manufacturer.class)) {
			return 'M'; 
		}
		if (clazz.equals(Laboratory.class)) {
			return 'L'; 
		}
		if (clazz.equals(Supplier.class)) {
			return 'S'; 
		}
		return ' ';
	}

	public void setDiscriminator(char discriminator) {
		if (discriminator=='A') {
			clazz = Agent.class; 
		}
		if (discriminator=='C') {
			clazz = Customer.class; 
		}
		if (discriminator=='D') {
			clazz = Division.class; 
		}
		if (discriminator=='M') {
			clazz = Manufacturer.class; 
		}
		if (discriminator=='L') {
			clazz = Laboratory.class; 
		}
		if (discriminator=='S') {
			clazz = Supplier.class; 
		}
		if (discriminator==' ') {
			clazz = null; 
		}
	}

}
