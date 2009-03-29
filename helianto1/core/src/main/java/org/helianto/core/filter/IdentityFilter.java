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

import org.helianto.core.User;



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
	
    private static final long serialVersionUID = 1L;
    private String principal = "";
    private String nameOrAliasSearch = "";

    public String getPrincipal() {
        return principal;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    
    @Override
	public boolean isSelection() {
		return (getEntity()==null && getPrincipal().length()>0);
	}
    
	public String getNameOrAliasSearch() {
        return nameOrAliasSearch;
    }
    public void setNameOrAliasSearch(String nameOrAliasSearch) {
        this.nameOrAliasSearch = nameOrAliasSearch;
    }

    public void reset() {
    	setPrincipal("");
    	setNameOrAliasSearch("");
    }

}
