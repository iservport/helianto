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

package org.helianto.resource.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.helianto.core.Node;
import org.helianto.resource.domain.ResourceAssociation;
import org.helianto.resource.domain.ResourceGroup;

/**
 * Implement <code>Node</code> interface to provide a resource 
 * tree root level.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceRootNode extends ResourceNode {

	private static final long serialVersionUID = 1L;
	private List<ResourceGroup> resourceList;

	/**
	 * Constructor.
	 * 
	 * @param resourceList
	 */
	public ResourceRootNode(List<ResourceGroup> resourceList) {
		super();
		this.resourceList = resourceList;
	}

	/**
	 * Child node factory.
	 * 
	 * @param childAssociation
	 */
	public final Node childNodeFactory(ResourceGroup resourceGroup, int sequence) {
		ResourceAssociation resourceAssociation = new ResourceAssociation();
		resourceAssociation.setChild(resourceGroup);
		resourceAssociation.setSequence(sequence);
		return childNodeFactory(resourceAssociation);
	}

	public final String getCaption() {
		return "";
	}

	public final List<Node> getChildList() {
		List<Node> childList = new ArrayList<Node>();
		int i = 1;
		for (ResourceGroup resourceGroup: resourceList) {
			childList.add(childNodeFactory(resourceGroup, i++));
		}
		Collections.sort(childList);
		return childList;
	}
	
	/**
	 * equals
	 */
	@Override
	public final boolean equals(Object other) {
		if (!(other instanceof ResourceRootNode)) return false;
		return super.equals(other);
	}
	
}
