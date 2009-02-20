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
import org.springframework.webflow.core.collection.ParameterMap;
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
    }
    
	@Override
	public String getFormObjectName() {
		return "filter";
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
	public final Event setupForm(RequestContext context) throws Exception {
		return super.setupForm(context);
	}

	/**
	 * Simply delegates to <tt>setupForm</tt>.
	 */
	public final Event setupFilter(RequestContext context) throws Exception {
		return super.setupForm(context);
	}

	@Override
	protected final F createFormObject(RequestContext context) throws Exception {
		F formObject = doCreateFilter();
        if (logger.isDebugEnabled()) {
            logger.debug("Created "+formObject);
        }
        return formObject;
	}

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
     * Pre-process the selection and return yes if no other action is
     * required.
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
        getFilter(context).reset();
        return success();
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
                context.getFlowScope().put(getTargetListAttributeName(), targetList);
                context.getFlowScope().put(getTargetListSizeAttributeName(), targetList.size());
            	return success();
            }
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
    	return true;
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
