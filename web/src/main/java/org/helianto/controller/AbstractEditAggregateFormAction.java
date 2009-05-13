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
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle aggregate objects storage.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEditAggregateFormAction<T, P> extends AbstractEditTargetFormAction<T> {

    public abstract String getParentAttributeName();
    
    /**
     * Subclasses may override if the parent can produce a convenient
     * target list.
     * 
     * @param context
     * @param parent
     */
    protected List<T> getAggregateList(RequestContext context, P parent) {
    	return null;
    }
    
	/**
	 * Override simple target creation and delegate to a member requiring a parent.
	 * 
	 * @param context
	 * @return a new target
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
	protected final T doCreateTarget(RequestContext context) throws Exception {
    	P parent = (P) context.getFlowScope().get(getParentAttributeName());
        if (logger.isDebugEnabled()) {
            logger.debug("Ready to create aggregate for parent "+parent);
        }
    	return doCreateTarget(context, parent);
    }
    
	/**
	 * Hook to the actual target creation.
	 * 
	 * @param context
	 * @param parent
	 * @return a new target
	 * @throws Exception
	 */
    public abstract T doCreateTarget(RequestContext context, P parent) throws Exception;
    
	@SuppressWarnings("unchecked")
	protected final T doSelectTarget(RequestContext context) {
    	ParameterMap parameters = context.getRequestParameters();
    	if (parameters.contains("target_index")) {
    		int index = parameters.getInteger("target_index");
        	P parent = (P) context.getFlowScope().get(getParentAttributeName());
    		T target = getAggregateList(context, parent).get(index);
    		if (target!=null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Index "+index+" of "+getTargetAttributeName()+" list selected target "+target);
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
        
	protected final void postProcessStoreTarget(RequestContext context, T managedTarget) throws Exception {
    	P managedParent = getManagedParent(managedTarget);
    	if (managedParent!=null) {
        	context.getFlowScope().put(getParentAttributeName(), managedParent);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed parent updated");
            }
    	}
    }
    
    protected abstract P getManagedParent(T managedTarget);

    
}
