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
 * Partner registry filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerRegistryFilter extends AbstractUserBackedCriteriaFilter implements Serializable {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static PartnerRegistryFilter partnerRegistryFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(PartnerRegistryFilter.class, user);
	}
	
	private static final long serialVersionUID = 1L;
	private String partnerAlias;
	private String partnerNameLike;

	/**
	 * Default constructor.
	 */
	public PartnerRegistryFilter() {
		setPartnerAlias("");
		setPartnerNameLike("");
	}

	/**
	 * Reset method.
	 */
	public void reset() {
		setPartnerAlias("");
		setPartnerNameLike("");
	}

    @Override
	public boolean isSelection() {
		return getPartnerAlias().length()>0;
	}

	/**
     * PartnerAlias.
     */
    public String getPartnerAlias() {
        return this.partnerAlias;
    }
    public void setPartnerAlias(String partnerAlias) {
        this.partnerAlias = partnerAlias;
    }

	/**
	 * Name like.
	 */
	public String getPartnerNameLike() {
		return partnerNameLike;
	}
	public void setPartnerNameLike(String partnerNameLike) {
		this.partnerNameLike = partnerNameLike;
	}

}
