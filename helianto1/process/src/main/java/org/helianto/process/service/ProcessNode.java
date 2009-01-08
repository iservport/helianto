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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.AbstractNode;
import org.helianto.core.Node;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.ProcessDocument;

/**
 * Implement <code>Node</code> interface to provide a process tree.
 * 
 * <p>
 * Process nodes wrap <code>DocumentAssociation</code>s.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessNode extends AbstractNode<DocumentAssociation> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param root
	 */
	public ProcessNode(ProcessDocument root) {
		this(new DocumentAssociation(), 0, 0, false);
		getPayLoad().setChild(root);
	}

	/**
	 * Constructor.
	 * 
	 * @param payLoad
	 * @param level
	 * @param sequence
	 */
	public ProcessNode(DocumentAssociation payLoad, int level, int sequence) {
		this(payLoad, level, sequence, true);
	}

	/**
	 * Constructor.
	 * 
	 * @param payLoad
	 * @param level
	 * @param sequence
	 * @param editable
	 */
	public ProcessNode(DocumentAssociation payLoad, int level, int sequence, boolean editable) {
		super(payLoad.getId(), payLoad, level, sequence, editable);
	}

	/**
	 * Child node factory.
	 * 
	 * @param childAssociation
	 * @param editable
	 */
	public final Node childNodeFactory(DocumentAssociation childAssociation, boolean editable) {
		Node node = new ProcessNode(childAssociation, getLevel()+1, childAssociation.getSequence(), editable);
		return node;
	}

	public final String getCaption() {
		return getPayLoad().getChild().getDocCode();
	}

	public final List<Node> getChildList() {
		List<Node> childList = new ArrayList<Node>();
		List<DocumentAssociation> associationList = getPayLoad().getChild().getChildAssociationList();
    	if (logger.isDebugEnabled()) {
    		logger.debug("Found "+associationList.size()+" association(s) under sequence "+getSequence());
    	}
		for (DocumentAssociation documentAssociation: associationList) {
			if (documentAssociation.getParent().equals(getPayLoad().getChild())) {
				childList.add(childNodeFactory(documentAssociation, true));
		    	if (logger.isDebugEnabled()) {
		    		logger.debug("Added "+documentAssociation+" as editable node");
		    	}
			}
			else {
				childList.add(childNodeFactory(documentAssociation, false));
		    	if (logger.isDebugEnabled()) {
		    		logger.debug("Added "+documentAssociation+" as not editable node");
		    	}
			}
		}
		Collections.sort(childList);
		return childList;
	}
	
	/**
	 * equals
	 */
	@Override
	public final boolean equals(Object other) {
		if (!(other instanceof ProcessDocumentNode)) return false;
		return super.equals(other);
	}
	
	private static final Log logger = LogFactory.getLog(ProcessNode.class);

}
