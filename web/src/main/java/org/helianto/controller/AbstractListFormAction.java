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
public abstract class AbstractListFormAction<T> extends AbstractComplexModelFormAction<T> {

	/**
     * Default constructor.
     */
    public AbstractListFormAction() {
    	super();
    }
    
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

	@Override
	protected T doPrepareTarget(RequestContext context, T target) throws Exception {
		throw new IllegalArgumentException("Likely programming error, method not implemented!");
	}

    @Override
	protected T doCreateTarget(RequestContext context) throws Exception {
		// optional
		return null;
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
