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

import org.helianto.core.AbstractAssociation;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractAssociationFormAction<A extends AbstractAssociation<P, C>, P, C> extends AbstractEditAggregateFormAction<A, P> {
	
	private AbstractAssociationStack<P, C> stack;
	
	/**
	 * Default constructor.
	 */
	public AbstractAssociationFormAction() {
		super();
		stack = new AbstractAssociationStack<P, C>() {
			private static final long serialVersionUID = 1L;
		};
	}

    /**
     * Push the target into the stack.
     */
	public final Event pushTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- pushTarget\n");
        }
        try {
    		A target = (A) get(context);
            if (target!=null) {
            	if (stack.isEmpty()) {
                	P parent = target.getParent();
                	stack.setRootInstance(parent);
            	}
            	stack.push(target);
                if (logger.isDebugEnabled()) {
                    logger.debug("Pushed target is "+target);
                }
            	context.getFlowScope().put(getParentAttributeName(), target.getParent());
            	context.getFlowScope().put(getChildAttributeName(), target.getChild());
            	put(context, target);
            }
            else {
                logger.warn("Target not pushed: null");
            }
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to push target ", e);
            return error();
        }
    }
    
    /**
     * Pop the target from the stack.
     */
	@SuppressWarnings("unchecked")
	public final Event popTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- popTarget\n");
        }
        try {
            if (stack.isEmpty()) {
            	A target = get(context);
            	P parent = target.getParent();
            	stack.setRootInstance(parent);
            	context.getFlowScope().put(getParentAttributeName(), parent);
            	context.getFlowScope().put(getChildAttributeName(), null);
            	put(context, null);
                if (logger.isDebugEnabled()) {
                    logger.debug("Parent updated");
                }
            }
            else {
        		A lastTarget = (A) stack.pop();
                if (logger.isDebugEnabled()) {
                    logger.debug("Popped target was "+lastTarget);
                }
                if (stack.isEmpty()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Stack is empty");
                    }
                	context.getFlowScope().put(getParentAttributeName(), stack.getRootInstance());
                	context.getFlowScope().put(getChildAttributeName(), null);
                	put(context, null);
                   if (logger.isDebugEnabled()) {
                        logger.debug("Target pushed in stack");
                    }
                }
                else {
            		A target = (A) stack.peek();
                    if (logger.isDebugEnabled()) {
                        logger.debug("Peeked target is "+target);
                    }
                	context.getFlowScope().put(getParentAttributeName(), target.getParent());
                	context.getFlowScope().put(getChildAttributeName(), target.getChild());
                	put(context, target);
                    if (logger.isDebugEnabled()) {
                        logger.debug("Target pushed in stack");
                    }
                }
            }
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to push target ", e);
            return error();
        }
    }
    
	@Override
	protected A doPrepareTarget(RequestContext context, A target) throws Exception {
		return target;
	}

    public abstract String getChildAttributeName();
    
	/**
	 * Retrieve the managed parent.
	 * 
	 * @param managedAssociation
	 * @throws Exception
	 */
    protected P getManagedParent(A managedAssociation) throws Exception {
    	return managedAssociation.getParent();
    }
 
	/**
	 * Retrieve the managed child.
	 * 
	 * @param managedAssociation
	 * @throws Exception
	 */
    protected C getManagedChild(A managedAssociation) throws Exception {
    	return managedAssociation.getChild();
    }
 
	protected void postProcessStoreTarget(RequestContext context, A managedAssociation) throws Exception {
		super.postProcessStoreTarget(context, managedAssociation);
    	C managedChild = getManagedChild(managedAssociation);
    	if (managedChild!=null) {
        	context.getFlowScope().put(getChildAttributeName(), managedChild);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed child updated");
            }
    	}
    }
    
}
