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
import org.helianto.core.filter.UserBackedFilter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle object selection using filters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterOnlyFormAction<F extends UserBackedFilter, T> extends AbstractComplexModelFormAction<T> {

	/**
     * Default constructor.
     */
    public AbstractFilterOnlyFormAction() {
    	super();
    }
    
	@Override
	public String getFormObjectName() {
		return "filter";
	}

	/**
	 * Simply delegates to <tt>setupForm</tt>.
	 */
	public final Event setupFilter(RequestContext context) throws Exception {
		return super.setupForm(context);
	}

	@Override
	protected final F createFormObject(RequestContext context) throws Exception {
		F formObject = doCreateFilter(context);
        if (logger.isDebugEnabled()) {
            logger.debug("Created "+formObject);
        }
        return formObject;
	}

	/**
	 * Optional filter creation requiring the context, default 
	 * implementation simply delegates to {@link #doCreateFilter()}.
	 * 
	 * @param context
	 */
    public F doCreateFilter(RequestContext context) throws Exception {
    	return doCreateFilter();
    }
    
    /**
     * Hook to filter creation.
     */
    public abstract F doCreateFilter() throws Exception;
    
	@SuppressWarnings("unchecked")
	protected final F getFilter(RequestContext context) throws Exception {
		return (F) getFormObject(context);
    }
	
    @Override
	protected T doCreateTarget(RequestContext context) throws Exception {
		// optional
		return null;
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
        	if (doPreProcess(getFilter(context), context)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Filter prepared");
                }
    	        return success();
        	}
        	else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Filter preparation interrupted");
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
     * <p>
     * Return true to signal the filter is ready (default).
     * Subclasses may read parameters from scope to set filter fields 
     * before filter processing. 
     * </p>
     * 
     * @throws Exception 
     */
    protected boolean doPreProcess(F filter, RequestContext context) throws Exception {
    	return true;
    }
        
    /**
     * Reset the filter
     * @throws Exception 
     */
    public final Event resetFilter(RequestContext context) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- resetFilter\n");
        }
        try {
        	if (doResetFilter(getFilter(context), context)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Filter reset");
                }
    	        return success();
        	}
        	else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Filter reset interrupted");
                }
        		return error();
        	}
		} catch (Exception e) {
			logger.warn("Unable to reset filter ", e);
            return error();
		}
    }
        
    /**
     * Subclasses may override to customize filter reset.
     * 
     * @throws Exception 
     */
    protected boolean doResetFilter(F filter, RequestContext context) throws Exception {
    	filter.reset();
    	return true;
    }
        
    /**
     * Apply the filter.
     */
    public final Event applyFilter(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- applyFilter\n");
        }
        try {
        	F filter = getFilter(context);
        	if (!beforeApplyFilter(context, filter)) {
        		return error();
        	}
            List<T> targetList = doApplyFilter(filter);
            if (logger.isDebugEnabled() && targetList!=null) {
                logger.debug("Target list found: "+targetList.size());
            }
            if (afterApplyFilter(context, filter, targetList)) {
            	getFormObjectScope().getScope(context).put(getTargetListAttributeName(), targetList);
            	getFormObjectScope().getScope(context).put(getTargetListSizeAttributeName(), targetList.size());
            	return success();
            }
        }
        catch (AccessDeniedException e) {
            logger.warn("Access denied ", e);
            return result("forbidden");
        }
        catch (Exception e) {
            logger.warn("Unable to apply filter ", e);
        }
        return error();
    }
    
    /**
     * Hook called before the target list is in flow scope.
     * 
     * @param context
     * @param filter
     * @param targetList
     * 
     */
    protected boolean beforeApplyFilter(RequestContext context, F filter) {
    	return true;
    }
    
    /**
     * Hook to do the actual filter processing.
     * 
     * <p>Subclasses should call the service layer to 
     * obtain the appropriate result list.
     * </p>
     * 
     * @param filter
     */
    protected abstract List<T> doApplyFilter(F filter);
    
    /**
     * Hook called after the target list is in flow scope.
     * 
     * @param context
     * @param filter
     * @param targetList
     * 
     */
    protected boolean afterApplyFilter(RequestContext context, F filter, List<T> targetList) {
    	return (targetList!=null && targetList.size()>0);
    }
    
    /**
     * Delegate to {@link #doCreateTree(UserBackedFilter)}
     */
    @Override
    protected final List<Node> doCreateTree(RequestContext context) throws Exception {
    	return doCreateTree(getFilter(context));
    }
	
    /**
     * Subclasses should override if a list rooted tree is required.
     */
    protected List<Node> doCreateTree(F filter) throws Exception {
    	return new ArrayList<Node>();
    }
	   
	@Override
	protected T doPrepareTarget(RequestContext context, T target) throws Exception {
		return target;
	}

}
