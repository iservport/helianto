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

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * <code>Identity</code> filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFilter extends AbstractUserBackedCriteriaFilter {

	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static IdentityFilter identityFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(IdentityFilter.class, user);
	}
	
	/**
	 * Factory method.
	 * 
	 * @param principal
	 */
	public static IdentityFilter identityFilterFactory(String principal) {
		IdentityFilter identityFilter = new IdentityFilter();
		identityFilter.setPrincipal(principal);
		return identityFilter;
	}
	
    private static final long serialVersionUID = 1L;
    private String principal;
    private String nameOrAliasSearch;
    
    /**
     * Default constructor.
     */
    public IdentityFilter() {
    	setPrincipal("");
    	setNameOrAliasSearch("");
    }

    /**
     * Reset.
     */
    public void reset() {
    	setPrincipal("");
    	setNameOrAliasSearch("");
    }

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * When entity is not null, restrict selection to identities already 
	 * associated to it.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		if (entity!=null) {
			mainCriteriaBuilder.appendAnd().appendSegment("id", "in")
		        .append("(select user.identity.id from User user where user.entity.id = ")
		        .append(entity.getId())
		        .append(") ");
		}
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("optionalAlias", getNameOrAliasSearch(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("principal", getPrincipal(), mainCriteriaBuilder);
	}

	public String getObjectAlias() {
		return "identity";
	}

    /**
     * Principal filter.
     */
    public String getPrincipal() {
        return principal;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    
	public boolean isSelection() {
		return (getEntity()==null && getPrincipal().length()>0);
	}
    
    /**
     * Name or alias filter.
     */
	public String getNameOrAliasSearch() {
        return nameOrAliasSearch;
    }
    public void setNameOrAliasSearch(String nameOrAliasSearch) {
        this.nameOrAliasSearch = nameOrAliasSearch;
    }

}
