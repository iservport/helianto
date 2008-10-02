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

package org.helianto.process.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.helianto.core.Association;
import org.helianto.core.Node;
import org.helianto.core.service.AbstractNode;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.ProcessDocument;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessNode extends AbstractNode<ProcessDocument> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param payLoad
	 */
	public ProcessNode(ProcessDocument payLoad) {
		super(payLoad.getId(), payLoad, 0, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param payLoad
	 * @param level
	 * @param sequence
	 */
	public ProcessNode(long id, ProcessDocument payLoad, int level, int sequence) {
		super(id, payLoad, level, sequence);
	}

	public String getCaption() {
		return getPayLoad().getDocCode();
	}

	public List<Node> getChildList() {
		List<Node> childList = new ArrayList<Node>();
		Set<DocumentAssociation> associationSet = getPayLoad().getChildAssociations();
		for (DocumentAssociation documentAssociation: associationSet) {
			childList.add(childNodeFactory((Association<ProcessDocument, ProcessDocument>) documentAssociation));
		}
		Collections.sort(childList);
		return childList;
	}
	
	private Node childNodeFactory(Association<ProcessDocument, ProcessDocument> association) {
		Node node = new ProcessNode(association.getId(), association.getChild(), getLevel()+1, association.getSequence());
		return node;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ProcessDocumentNode)) return false;
		return super.equals(other);
	}

}
