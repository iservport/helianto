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


package org.helianto.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Node;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Stateless implementation for <code>TreeBuilder</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Scope("prototype")
@Component("treeBuilder")
public class TreeBuilderImpl implements TreeBuilder {
	
	private List<Node> tree;
	
	public void reset() {
		if (logger.isDebugEnabled()) {
			logger.debug("Tree reset.");
		}
		tree = new ArrayList<Node>();
	}

	public void buildTree(Node root) {
		reset();
		addNode(root);
	}

	public void addNode(Node node) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding node "+node);
		}
		getTree().add(node);
//		if (node.isExpanded()) {
		if (true) {
			List<Node> childList = node.getChildList();
			if (logger.isDebugEnabled()) {
				logger.debug("Expanding node with "+childList.size()+" child(ren)");
			}
			for (Node childNode: childList) {
				addNode(childNode);
			}
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug("Node is collapsed.");
			}
			
		}
	}

	public List<Node> getTree() {
		return tree;
	}
	
	private static final Log logger = LogFactory.getLog(TreeBuilderImpl.class);}
