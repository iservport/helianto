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
import org.helianto.core.filter.PolymorphicFilter;

/**
 * Resource group polimorphic filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFilter extends AbstractUserBackedCriteriaFilter implements PolymorphicFilter<ResourceGroup>{

	/**
	 * Create the filter for a given type.
	 * 
	 * @param user
	 */
	public static ResourceGroupFilter resourceGroupFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(ResourceGroupFilter.class, user);
	}

	/**
	 * Create the filter for a given type.
	 * 
	 * @param user
	 * @param clazz
	 */
	public static ResourceGroupFilter resourceGroupFilterFactory(User user, Class<? extends ResourceGroup> clazz) {
		ResourceGroupFilter filter = ResourceGroupFilter.resourceGroupFilterFactory(user);
		filter.setClazz(clazz);
		return filter;
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

    private static final long serialVersionUID = 1L;
    private String resourceCode;
	private String resourceNameLike;
	private char resourceType;
	private Class<? extends ResourceGroup> clazz = ResourceGroup.class;
	
	/**
	 * Default constructor.
	 */
	public ResourceGroupFilter() {
		setResourceCode("");
		setResourceNameLike("");
		setResourceType(' ');
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		setResourceCode("");
		setResourceNameLike("");
	}

	@Override
	public boolean isSelection() {
		return getResourceCode().length()>0;
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
	 * Discriminator
	 */
	public char getDiscriminator() {
		if (clazz.equals(ResourceGroup.class)) {
			return 'G'; 
		}
		if (clazz.equals(Resource.class)) {
			return 'R'; 
		}
		return ' ';
	}
	public void setDiscriminator(char discriminator) {
		if (discriminator=='G') {
			clazz = ResourceGroup.class; 
		}
		if (discriminator=='R') {
			clazz = Resource.class; 
		}
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
