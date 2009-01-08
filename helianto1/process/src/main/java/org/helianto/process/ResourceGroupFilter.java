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


package org.helianto.process;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFilter extends AbstractUserBackedCriteriaFilter {

    private static final long serialVersionUID = 1L;
    private String resourceCode = "";
	private String resourceNameLike = "";
	private char resourceType = ' ';
	private Class<? extends ResourceGroup> clazz = ResourceGroup.class;
	
	
	public ResourceGroupFilter() {
	}
	
	/**
	 * Create the filter for a given type.
	 * 
	 * @param user
	 * @param clazz
	 */
	public static ResourceGroupFilter resourceGroupFilterFactory(User user, Class<? extends ResourceGroup> clazz) {
		ResourceGroupFilter resourceGroupFilter = new ResourceGroupFilter();
		resourceGroupFilter.setUser(user);
		resourceGroupFilter.setClazz(clazz);
		return resourceGroupFilter;
	}

	/**
	 * Create the filter for a hierarchy.
	 * 
	 * @param user
	 * @param root
	 */
	public static ResourceGroupFilter resourceGroupFilterFactory(User user, ResourceGroup root) {
		ResourceGroupFilter resourceGroupFilter = ResourceGroupFilter.resourceGroupFilterFactory(user, root.getClass());
		return resourceGroupFilter;
	}

	public void reset() {
		setResourceCode("");
		setResourceNameLike("");
	}

	/**
	 * Resource code.
	 */
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	/**
	 * Subclass
	 */
	public Class<? extends ResourceGroup> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends ResourceGroup> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Resource name like.
	 */
    public String getResourceNameLike() {
		return resourceNameLike;
	}
	public void setResourceNameLike(String resourceNameLike) {
		this.resourceNameLike = resourceNameLike;
	}

	/**
	 * Resource type.
	 */
	public char getResourceType() {
		return resourceType;
	}
	public void setResourceType(char resourceType) {
		this.resourceType = resourceType;
	}

}
