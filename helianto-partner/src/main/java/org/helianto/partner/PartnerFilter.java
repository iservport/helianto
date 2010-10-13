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

package org.helianto.partner;

import java.io.Serializable;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.PolymorphicFilter;

/**
 * Partner filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerFilter extends AbstractUserBackedCriteriaFilter implements Serializable, PolymorphicFilter<Partner> {
	
	private static final long serialVersionUID = 1L;
	private String partnerAlias;
	private String partnerNameLike;
	private char partnerState;
	private char priority = '0';
	private Class<? extends Partner> clazz = Partner.class;
	
	/**
	 * Default constructor.
	 */
	public PartnerFilter() {
		super();
		setPartnerAlias("");
		setPartnerNameLike("");
		setPartnerState(' ');
	}
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public PartnerFilter(Entity entity) {
		this();
		setEntity(entity);
	}

	/**
	 * Entity class constructor.
	 * 
	 * @param entity
	 * @param clazz
	 */
	public PartnerFilter(Entity entity, Class<? extends Partner> clazz) {
		this(entity);
		setClazz(clazz);
	}

	/**
	 * User constructor.
	 * 
	 * @param user
	 */
	public PartnerFilter(User user) {
		this();
		setUser(user);
	}

	/**
	 * User class constructor.
	 * 
	 * @param user
	 * @param clazz
	 */
	public PartnerFilter(User user, Class<? extends Partner> clazz) {
		this(user);
		setClazz(clazz);
	}

	/**
	 * Reset method.
	 */
	public void reset() {
		setPartnerNameLike("");
	}

	public boolean isSelection() {
		return getPartnerAlias().length()>0;
	}

	public String getObjectAlias() {
		return "partner";
	}

	/**AbstractHibernateFilterDao
	 * Read entity from the associated partner registry.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.entity.id", entity.getId(), mainCriteriaBuilder);
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.entityAlias", getPartnerAlias(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("privateEntity.entityName", getPartnerNameLike(), mainCriteriaBuilder);
		appendEqualFilter("partnerState", getPartnerState(), mainCriteriaBuilder);
		appendEqualFilter("priority", getPriority(), mainCriteriaBuilder);
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

	/**
	 * <<Chain>> Partner alias
	 */
	public String getPartnerAlias() {
		return partnerAlias;
	}
	public PartnerFilter setPartnerAlias(String partnerAlias) {
		this.partnerAlias = partnerAlias;
		return this;
	}

	/**
	 * Name like
	 */
	public String getPartnerNameLike() {
		return partnerNameLike;
	}
	public void setPartnerNameLike(String partnerNameLike) {
		this.partnerNameLike = partnerNameLike;
	}

	/**
	 * Partner state
	 */
	public char getPartnerState() {
		return partnerState;
	}
	public void setPartnerState(char partnerState) {
		this.partnerState = partnerState;
	}

	/**
	 * Priority
	 */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}

}
