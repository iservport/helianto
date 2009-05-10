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

import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle objects using flow scope as a repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractModelFormAction<T> extends FormAction {
	
	/**
	 * Default constructor.
	 */
	public AbstractModelFormAction() {
		super();
	}

	/**
	 * Subclasses must override to use a specific name.
	 */
	public abstract String getTargetAttributeName();
	
    /**
     * Create the target and put in flow scope following {@link #getTargetAttributeName()}.
     */
	public final Event createTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- createTarget\n");
        }
        try {
        	T target = doCreateTarget(context);
            if (logger.isDebugEnabled()) {
                logger.debug("Created "+target);
            }
            put(context, target);
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to create target ", e);
            return error();
        }
    }
	
	/**
	 * Hook to the actual target creation.
	 * 
	 * @param context
	 * @return a new target
	 * @throws Exception
	 */
    protected abstract T doCreateTarget(RequestContext context) throws Exception;
    
    /**
     * Prepare the target.
     */
	@SuppressWarnings("unchecked")
	public final Event prepareTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- prepareTarget\n");
        }
        try {
            T preparedTarget = doPrepareTarget(context, (T) get(context));
            put(context, preparedTarget);
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to prepare target ", e);
            return error();
        }
    }
    
	/**
	 * Hook to the actual target preparation.
	 * 
	 * @param context
	 * @param target
	 * @return the prepared target
	 * @throws Exception
	 */
	protected abstract T doPrepareTarget(RequestContext context, T target) throws Exception;
    
	/**
	 * Return the target from flow scope.
	 * 
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	public Object get(RequestContext context) {
		T target = (T) context.getFlowScope().get(getTargetAttributeName());
		if (logger.isDebugEnabled()) {
			logger.debug("Get model object '"+getTargetAttributeName()+"'="+target);
		}
		return target;
	}
	
	/**
	 * Put the target in flow scope.
	 * 
	 * @param context
	 * @param target
	 */
	public void put(RequestContext context, T target) {
		context.getFlowScope().put(getTargetAttributeName(), target);
		if (logger.isDebugEnabled()) {
			logger.debug("Put model object '"+getTargetAttributeName()+"'="+target);
		}
	}
	
	/**
	 * Extract from a list.
	 * 
	 * @param context
	 * @param name
	 * @param reference
	 */
	protected void list(RequestContext context, String name, List<?> reference) {
		String listName = new StringBuilder(name).append("List").toString();
		if (reference!=null) {
			context.getFlowScope().put(listName, reference);
			if (logger.isDebugEnabled()) {
				logger.debug("List '"+listName+"' of size "+reference.size()+" set in flow scope.");
			}
		}
		else {
			logger.warn("List '"+listName+"' is null.");
		}
	}
	
    /**
     * Select the target and make it available in the model.
     */
	public Event selectTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- selectTarget\n");
        }
        try {
        	T target = doSelectTarget(context);
    		if (target!=null) {
                put(context, target);
                if (postProcessSelectTarget(context, target)) {
                    return success();
                }
    		}
    		else {
    			logger.warn("Null target selected");
    		}
        } catch (Exception e) {
            logger.warn("Unable to select target ", e);
        }
        return error();
    }
    
    protected abstract T doSelectTarget(RequestContext context) throws Exception;
    
    protected boolean postProcessSelectTarget(RequestContext context, T target) throws Exception {
    	return true;
    }
    
    /**
     * Clear the target.
     */
	public Event clearTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- clearTarget\n");
        }
        try {
            put(context, null);
            return success();
        } catch (Exception e) {
            logger.warn("Unable to clear target ", e);
            return error();
        }
    }
    
    @SuppressWarnings("unchecked")
	protected <P> P getFromList(RequestContext context, String listPrefix) {
    	String listName = new StringBuilder(listPrefix).append("List").toString();
        final List<P> itemList = (ArrayList<P>) context.getFlowScope().get(listName);
        return getFromList(context, listPrefix, itemList);
    }
    
	protected <P> P getFromList(RequestContext context, String listPrefix, List<P> itemList) {
    	String listIndex = new StringBuilder(listPrefix).append("Index").toString();
    	if (logger.isDebugEnabled()) {
    		logger.debug("Trying to bind parameter '"+listIndex+"'.");
    	}
        ParameterMap parameters = context.getRequestParameters();
        if (!parameters.contains(listIndex)) {
        	return null;
        }
        try {
            int index = Integer.parseInt(parameters.get(listIndex));
            return itemList.get(index);
        }
        catch (Exception e) {
        	logger.warn("Unable to find '"+listIndex+"'.", e);
        	return null;
        }
    }
    
}
