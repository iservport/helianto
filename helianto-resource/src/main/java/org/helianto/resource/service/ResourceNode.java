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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.Node;
import org.helianto.core.base.AbstractNode;
import org.helianto.resource.domain.ResourceAssociation;

/**
 * Implement <code>Node</code> interface to provide a resource tree.
 * 
 * <p>
 * Process nodes wrap <code>ResourceAssociation</code>s.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceNode extends AbstractNode<ResourceAssociation> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param payLoad
	 * @param level
	 * @param sequence
	 * @param editable
	 */
	public ResourceNode(ResourceAssociation payLoad, int level, int sequence, boolean editable) {
		super(payLoad.getId(), payLoad, level, sequence, editable);
	}

	/**
	 * Null constructor.
	 * 
	 */
	protected ResourceNode() {
		super(0, null, 0, 0, false);
	}

	/**
	 * Child node factory.
	 * 
	 * @param childAssociation
	 */
	protected Node childNodeFactory(ResourceAssociation childAssociation) {
		boolean editable = false;
		if (childAssociation.getParent()!=null && childAssociation.getParent().equals(getContent().getChild())) {
			editable = true;
		}
		Node node = new ResourceNode(childAssociation, getLevel()+1, childAssociation.getSequence(), editable);
    	logger.debug("Added {} as {} editable node", childAssociation, (editable ? "" : "not "));
		return node;
	}

	public String getCaption() {
		return getContent().getChild().getResourceCode();
	}

	public List<Node> getChildList() {
		List<Node> childList = new ArrayList<Node>();
		List<ResourceAssociation> associationList = getContent().getChild().getChildAssociationList();
		for (ResourceAssociation resourceAssociation: associationList) {
			childList.add(childNodeFactory(resourceAssociation));
		}
		Collections.sort(childList);
		return childList;
	}
	
	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ResourceNode)) return false;
		return super.equals(other);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceNode.class);

}
