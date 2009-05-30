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

import java.util.List;

import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle object selection.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractLoadFormAction<T, P> extends AbstractComplexModelFormAction<T> {

	/**
     * Default constructor.
     */
    public AbstractLoadFormAction() {
    	super();
    }
    
	/**
	 * Subclasses must override to use a specific name.
	 */
	public abstract String getParentAttributeName();
	
	/**
     * Target list attribute name.
     */
    public String getTargetListAttributeName() {
		return "targetList";
	}

	/**
     * Target list attribute name.
     */
    public String getTargetListSizeAttributeName() {
		return "targetListSize";
	}

	/**
     * Pre-process the selection.
     */
    public final Event preProcess(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- preProcess\n");
        }
        try {
        	if (doPreProcess(context)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Selection prepared");
                }
    	        return success();
        	}
        	else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Selection preparation interrupted");
                }
        		return error();
        	}
		} catch (Exception e) {
			logger.warn("Unable to pre-process the selection ", e);
            return error();
		}
    }
        
    /**
     * Hook to do any pre-processing.
     * 
     * @throws Exception 
     */
    protected boolean doPreProcess(RequestContext context) throws Exception {
    	return true;
    }
        
	@Override
	protected T doPrepareTarget(RequestContext context, T target) throws Exception {
		throw new IllegalArgumentException("Likely programming error, method not implemented!");
	}

	@Override
	public final Event setupForm(RequestContext context) throws Exception {
		return super.setupForm(context);
	}

    @Override
	protected T doCreateTarget(RequestContext context) throws Exception {
		// optional
		return null;
	}

    /**
     * List.
     */
    public final Event load(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- applyFilter\n");
        }
        try {
        	P parent = getParent(context);
        	if (!beforeList(context, parent)) {
        		return error();
        	}
        	
            List<T> targetList = doLoad(parent);
            if (logger.isDebugEnabled() && targetList!=null) {
                logger.debug("Target list found: "+targetList.size());
            }
            if (afterList(context, targetList)) {
                context.getFlowScope().put(getTargetListAttributeName(), targetList);
                context.getFlowScope().put(getTargetListSizeAttributeName(), targetList.size());
            	return success();
            }
        }
        catch (Exception e) {
            logger.warn("Unable to list ", e);
        }
        return error();
    }
    
    /**
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected P getParent(RequestContext context) {
		P parent = (P) context.getFlowScope().get(getParentAttributeName());
        if (logger.isDebugEnabled()) {
            logger.debug("Parent is "+parent);
        }
		return parent;
	}

	/**
     * Hook called before the target list is in flow scope.
     * 
     * @param context
     * @param parent
     * 
     */
    protected boolean beforeList(RequestContext context, P parent) {
    	return true;
    }
    
    /**
     * Hook to do the actual processing.
     * 
     * <p>Subclasses should call the service layer to 
     * obtain the appropriate result list.
     * </p>
     * 
     */
    protected abstract List<T> doLoad(P parent);
    
    /**
     * Hook called after the target list is in flow scope.
     * 
     * @param context
     * @param targetList
     * 
     */
    protected boolean afterList(RequestContext context, List<T> targetList) {
    	return true;
    }
    
    @SuppressWarnings("unchecked")
	protected final T doSelectTarget(RequestContext context) {
    	ParameterMap parameters = context.getRequestParameters();
    	if (parameters.contains("target_index")) {
    		int index = parameters.getInteger("target_index");
    		List<T> targetList = (List<T>) context.getFlowScope().get(getTargetListAttributeName());
    		T target = targetList.get(index);
    		if (target!=null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Index "+index+" of "+getTargetListAttributeName()+" selected target "+target);
                }
        		return target;
    		}
    		else {
    			logger.warn("Null target selected by index "+index);
    		}
            return null;
    	}
		logger.warn("No selection parameter found");
        return null;
    }
        
    /**
     * Post-process the selection .
     */
    @SuppressWarnings("unchecked")
	public final Event postProcess(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- postProcess\n");
        }
        try {
        	Object returnTarget =  get(context);
            if (doPostProcess(context, (T) get(context), returnTarget)) {
            	return success();
            }
        	else {
                logger.warn("Unable to post-process association selection subflow ");
                return error();
        	}
		} catch (Exception e) {
			logger.warn("Unable to pre-process the selection ", e);
            return error();
		}
    }
        
	/**
     * Hook to do any post-processing.
     * 
     * 
     * @throws Exception 
     */
    protected boolean doPostProcess(RequestContext context, T target, Object returnTarget) throws Exception {
    	return true;
    }
    
}
