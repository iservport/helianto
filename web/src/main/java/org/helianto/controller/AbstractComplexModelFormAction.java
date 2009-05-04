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


package org.helianto.controller;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Node;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle complex models.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractComplexModelFormAction<T> extends AbstractAuthorizationFormAction<T> {

    /**
     * Create Tree.
     * @throws Exception 
     */
	public Event createTree(RequestContext context) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- createTree\n");
        }
        try {
            List<Node> tree = doCreateTree(context);
            if (logger.isDebugEnabled() && tree!=null) {
                logger.debug("Created tree of size: "+tree.size());
            }
            context.getFlowScope().put("tree", tree);
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to create tree ", e);
            return error();
        }
    }
    
    protected List<Node> doCreateTree(RequestContext context) throws Exception {
    	return new ArrayList<Node>();
    }
	
    /**
     * Select a node.
     */
	@SuppressWarnings("unchecked")
	public Event selectNode(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- selectNode\n");
        }
    	String nodeName = "node";
    	Object content = null;
        try {
        	ParameterMap parameters = context.getRequestParameters();
        	if (parameters.contains("node")) {
        		nodeName = parameters.get("node");
        	}
        	else if (context.getFlashScope().contains("nodeName")) {
        		nodeName = context.getFlashScope().getRequiredString("nodeName");
        	}
        	if (parameters.contains("index")) {
        		int index = parameters.getInteger("index");
        		content = doSelectNode(context, nodeName, index).getContent();
        	}
        	else {
				content = doSelectNode(context, nodeName, (T) get(context)).getContent();
        	}
        	if (content!=null) {
        		postProcessContent(context, content);
        		context.getFlowScope().put(nodeName, content);
                if (logger.isDebugEnabled()) {
                    logger.debug("Node content from '"+nodeName+"' "+content+" set in flow scope.");
                }
                return success();
        	}
        	else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Node content from '"+nodeName+"' is null.");
                }
                return error();
        	}
        } catch (Exception e) {
            logger.warn("Unable to select "+nodeName, e);
            return error();
        }
    }
    
    /**
     * Hook to do the actual node selection from a list.
     */
	@SuppressWarnings("unchecked")
	protected Node doSelectNode(RequestContext context, String nodeName, int index) {
    	List<Node> tree = (List<Node>) context.getFlowScope().get("tree");
    	Node node = tree.get(index);
        if (logger.isDebugEnabled()) {
            logger.debug("Selected node '"+nodeName+"'="+node);
        }
    	return node;
    }
        
    /**
     * Hook to do the actual node selection from the target.
     */
	protected Node doSelectNode(RequestContext context, String nodeName, T target) {
    	return null;
    }
        
    /**
     * Subclasses should override if node post-process is required.
     */
	protected void postProcessContent(RequestContext context, Object content) {
    }
        
    /**
     * Select a detail, either from a detail list or from the target.
     */
	@SuppressWarnings("unchecked")
	public Event prepareNode(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- prepareNode\n");
        }
    	String nodeName = "node";
    	Object node = null;
        try {
        	ParameterMap parameters = context.getRequestParameters();
        	if (parameters.contains("node")) {
        		nodeName = parameters.get("node");
        	}
        	else if (context.getFlashScope().contains("nodeName")) {
        		nodeName = context.getFlashScope().getRequiredString("nodeName");
        	}
        	if (parameters.contains("index")) {
        		int index = parameters.getInteger("index");
        		node = doSelectNode(context, nodeName, index);
        	}
        	else {
				node = doSelectNode(context, nodeName, (T) get(context));
        	}
        	if (node!=null) {
        		context.getFlowScope().put(nodeName, node);
                if (logger.isDebugEnabled()) {
                    logger.debug("Detail '"+nodeName+"'="+node+" set in flow scope.");
                }
                return success();
        	}
        	else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Detail '"+nodeName+"' is null.");
                }
                return error();
        	}
        } catch (Exception e) {
            logger.warn("Unable to select "+nodeName, e);
            return error();
        }
    }
    
}
