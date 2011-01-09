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

import org.helianto.core.Entity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractTrunkFilterAdapter;
import org.helianto.partner.Agent;
import org.helianto.partner.Customer;
import org.helianto.partner.Division;
import org.helianto.partner.Laboratory;
import org.helianto.partner.Manufacturer;
import org.helianto.partner.Partner;
import org.helianto.partner.Supplier;

/**
 * Partner filter adapter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerFilterAdapter extends AbstractTrunkFilterAdapter<Partner> {
	
	private static final long serialVersionUID = 1L;
	private Class<? extends Partner> clazz = Partner.class;
	
	/**
	 * Default constructor.
	 * 
	 * @param partner
	 */
	public PartnerFilterAdapter(Partner partner) {
		super(partner);
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
	 * Reset method.
	 */
	public void reset() { }

	public boolean isSelection() {
		return getFilter().getEntityAlias().length()>0;
	}

	/**
	 * Read entity from the associated partner registry.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.entity.id", entity.getId(), mainCriteriaBuilder);
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.entityAlias", getFilter().getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("privateEntity.entityName", getFilter().getEntityName(), mainCriteriaBuilder);
		appendEqualFilter("partnerState", getFilter().getPartnerState(), mainCriteriaBuilder);
		appendEqualFilter("priority", getFilter().getPriority(), mainCriteriaBuilder);
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
	}

}
