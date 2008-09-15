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

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * Partner filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerFilter extends AbstractUserBackedCriteriaFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Class<? extends Partner> clazz = Partner.class;
	private String partnerNameLike;
	private char partnerState;
	private char priority = '0';

	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static PartnerFilter partnerFilterFactory(User user) {
		PartnerFilter partnerFilter = new PartnerFilter();
		partnerFilter.setUser(user);
		return partnerFilter;
	}
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 * @param clazz
	 */
	public static PartnerFilter partnerFilterFactory(User user, Class<? extends Partner> clazz) {
		PartnerFilter partnerFilter = new PartnerFilter();
		partnerFilter.setUser(user);
		partnerFilter.setClazz(clazz);
		return partnerFilter;
	}
	
	/**
	 * Reset method.
	 */
	public void reset() {
		setPartnerNameLike("");
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
