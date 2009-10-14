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
    
    @SuppressWarnings("unchecked")
	protected P getParent(RequestContext context) {
    	P parent = (P) getFormObjectScope().getScope(context).get(getParentAttributeName());
        if (logger.isDebugEnabled()) {
            logger.debug("Parent "+parent+" retrieved from "+getFormObjectScope());
        }
        return parent;
    }
    
	/**
	 * Override simple target creation and delegate to a member requiring a parent.
	 * 
	 * @param context
	 * @return a new target
	 * @throws Exception
	 */
	protected final T doCreateTarget(RequestContext context) throws Exception {
    	P parent = getParent(context);
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
    
	protected T doSelectTarget(RequestContext context) throws Exception {
    	ParameterMap parameters = context.getRequestParameters();
    	if (parameters.contains("target_index")) {
    		int index = parameters.getInteger("target_index");
    		P parent = getParent(context);
        	List<T> aggregateList = getAggregateList(context, parent);
        	if (aggregateList==null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Attempt to get aggregate list from parent failed");
                }
        	}
    		T target = aggregateList.get(index);
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
        
	protected void postProcessStoreTarget(RequestContext context, T managedTarget) throws Exception {
    	P managedParent = getManagedParent(managedTarget);
    	if (managedParent!=null) {
    		getFormObjectScope().getScope(context).put(getParentAttributeName(), managedParent);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed parent updated");
            }
    	}
    }
    
	/**
	 * Hook to retrieve the managed parent.
	 * 
	 * @param managedTarget
	 * @throws Exception
	 */
    protected abstract P getManagedParent(T managedTarget) throws Exception;
 
}
