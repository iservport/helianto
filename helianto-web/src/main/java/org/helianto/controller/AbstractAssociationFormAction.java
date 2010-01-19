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
	
	private AbstractAssociationStack<P, C> internalStack;
	
	/**
	 * Default constructor.
	 */
	public AbstractAssociationFormAction() {
		super();
		internalStack = new AbstractAssociationStack<P, C>() {
			private static final long serialVersionUID = 1L;
		};
	}

	/**
	 * Internal stack.
	 */
	public AbstractAssociationStack<P, C> getInternalStack() {
		return internalStack;
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
            	if (internalStack.isEmpty()) {
            		internalStack.setRootInstance(target.getParent());
            	}
            	internalStack.push(target);
                if (logger.isDebugEnabled()) {
                    logger.debug("Pushed target is "+target);
                }
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
	 * On every selection of any association, prepare to replace the current parent
	 * target with the association child.
	 */
    @Override
    protected final boolean postProcessSelectTarget(RequestContext context, A target) throws Exception {
    	if (target.getChild()==null) {
            logger.warn("Association child is null after selection, parent target not replaced.");
            return postProcessParentTargetReplacement(context, target, false);
    	}
    	getFormObjectScope().getScope(context).put(getParentAttributeName(), target.getChild());
        if (logger.isDebugEnabled()) {
            logger.debug("Parent replaced by "+target.getChild());
        }
		return postProcessParentTargetReplacement(context, target, true);
	}
    
    /**
     * Subclasses may override to post process association selection
     * parent target replacement.
     *  
     * @param context
     * @param target
     * @param isReplacement
     * @return true if accepted
     */
    protected boolean postProcessParentTargetReplacement(RequestContext context, A target, boolean isReplacement) {
    	return true;
    }

	/**
     * Pop the target from the stack.
     * 
     * <p>Pop removes the top of the stack and sets the next object in
     * the row as target. Useful when editing an hierarchy and a backward step 
     * is requested.</p>
     */
	@SuppressWarnings("unchecked")
	public final Event popTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- popTarget\n");
        }
        if (internalStack.isEmpty()) {
        	throw new IllegalArgumentException("Unable to pop from an empty stack.");
        }
        try {
    		A lastTarget = (A) internalStack.pop();
            if (logger.isDebugEnabled()) {
                logger.debug("Popped target was "+lastTarget);
            }
            if (internalStack.isEmpty()) {
                // there is nothing left in the stack
                if (logger.isDebugEnabled()) {
                    logger.debug("Stack is empty");
                }
                // take the root as target
                put(context, internalStack.getRootInstance(), getParentAttributeName());
                if (logger.isDebugEnabled()) {
                    logger.debug("Parent replaced by "+lastTarget.getParent());
                }
            }
            else {
                // otherwise, remember the last pushed target
        		A target = (A) internalStack.peek();
            	put(context, target);
                if (logger.isDebugEnabled()) {
                    logger.debug("Peeked target is "+target);
                }
                put(context, lastTarget.getParent(), getParentAttributeName());
                if (logger.isDebugEnabled()) {
                    logger.debug("Parent replaced by "+lastTarget.getParent());
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
    public P getManagedParent(A managedAssociation) throws Exception {
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
    		getFormObjectScope().getScope(context).put(getChildAttributeName(), managedChild);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed child updated");
            }
    	}
    }
    
}
