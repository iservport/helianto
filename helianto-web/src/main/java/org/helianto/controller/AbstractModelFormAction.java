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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	 * Hook to provide a target specific name.
	 */
	public abstract String getTargetAttributeName();
	
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
     * Target index attribute name.
     */
    public String getTargetIndexAttributeName() {
		return "target_index";
	}

	/**
     * Target list selection attribute name.
     */
    public String getTargetSubListAttributeName() {
		return "targetSubList";
	}

	/**
	 * Put the target in same scope as the form.
	 * 
	 * @param context
	 * @param target
	 */
	public void put(RequestContext context, T target) {
		put(context, target, getTargetAttributeName());
	}
	
	/**
	 * Put the target in same scope as the form.
	 * 
	 * @param <O>
	 * @param context
	 * @param object
	 * @param attributeName
	 */
	public <O> void put(RequestContext context, O object, String attributeName) {
		getFormObjectScope().getScope(context).put(attributeName, object);
		if (logger.isDebugEnabled()) {
			logger.debug("Put model object '"+attributeName+"'="+object);
		}
	}
	
	/**
	 * Return the target from flow scope.
	 * 
	 * @param context
	 */
	public T get(RequestContext context) {
		return get(context, getTargetAttributeName());
	}
	
	/**
	 * Return the target from flow scope.
	 * 
	 * @param context
	 * @param attributeName
	 */
	@SuppressWarnings("unchecked")
	public T get(RequestContext context, String attributeName) {
		T target = (T) getFormObjectScope().getScope(context).get(attributeName);
		if (logger.isDebugEnabled()) {
			logger.debug("Get model object '{}'={}", getTargetAttributeName(), target);
		}
		return target;
	}
	
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
	 * Extract from a list.
	 * 
	 * @param context
	 * @param name
	 * @param reference
	 */
	protected void list(RequestContext context, String name, List<?> reference) {
		String listName = new StringBuilder(name).append("List").toString();
		if (reference!=null) {
			put(context, reference, listName);
			if (logger.isDebugEnabled()) {
				logger.debug("List '"+listName+"' of size "+reference.size()+" set in "+getFormObjectScope()+" scope.");
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
    
    @SuppressWarnings("unchecked")
	protected T doSelectTarget(RequestContext context) throws Exception {
    	List<T> targetList = (List<T>) getFormObjectScope().getScope(context).get(getTargetListAttributeName());
    	ParameterMap parameters = context.getRequestParameters();
    	int index = 0;
    	if (targetList!=null) {
        	if (targetList.size()>1 && parameters.contains(getTargetIndexAttributeName())) {
        		index = parameters.getInteger(getTargetIndexAttributeName());
        	}
    		T target = targetList.get(index);
    		if (target!=null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Index "+index+" of "+getTargetListAttributeName()+" selected target "+target);
                }
        		return target;
    		}
    	}
        return null;
    }
        
    protected boolean postProcessSelectTarget(RequestContext context, T target) throws Exception {
    	return true;
    }
    
    /**
     * Select a target sub-list and make it available in the model.
     */
	public Event selectTargetSubList(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- selectTargetSubList\n");
        }
        try {
        	List<T> targetSubList = doSelectTargetSubList(context);
    		if (targetSubList!=null) {
    			put(context, targetSubList, getTargetSubListAttributeName());
                if (postProcessSelectTargetSubList(context, targetSubList)) {
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
    
    @SuppressWarnings("unchecked")
	protected List<T> doSelectTargetSubList(RequestContext context) throws Exception {
    	List<T> targetList = (List<T>) getFormObjectScope().getScope(context).get(getTargetListAttributeName());
    	List<T> targetSubList = new ArrayList<T>();
    	ParameterMap parameters = context.getRequestParameters();
    	String[] indexes = null;
    	if (targetList!=null) {
        	if (targetList.size()>1 && parameters.contains(getTargetIndexAttributeName())) {
        		indexes = parameters.getArray(getTargetIndexAttributeName());
        	}
        	if (indexes!=null && indexes.length>0) {
        		for (String index: indexes) {
            		T target = targetList.get(Integer.parseInt(index));
            		if (target!=null) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Index "+index+" of "+getTargetListAttributeName()+" added to target sublist");
                        }
                        targetSubList.add(target);
            		}
        		}
        		return targetSubList;
        	}
    	}
        return null;
    }
        
    protected boolean postProcessSelectTargetSubList(RequestContext context, List<T> target) throws Exception {
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
        	if (doClearTarget(context)) {
        		return success();
        	}
        } catch (Exception e) {
            logger.warn("Unable to clear target ", e);
        }
        return error();
    }
    
	/**
     * Subclasses may override to actually clear the target.
     * 
     * @throws Exception 
     */
    protected boolean doClearTarget(RequestContext context) throws Exception {
    	Object object = getFormObjectScope().getScope(context).remove(getTargetAttributeName());
        if (logger.isDebugEnabled()) {
            logger.debug("Object "+object+" removed from "+getFormObjectScope());
        }
    	return true;
    }
    
    @SuppressWarnings("unchecked")
	protected <P> P getFromList(RequestContext context, String listPrefix) {
    	String listName = new StringBuilder(listPrefix).append("List").toString();
        final List<P> itemList = (ArrayList<P>) getFormObjectScope().getScope(context).get(listName);
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
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractModelFormAction.class);
    
}
