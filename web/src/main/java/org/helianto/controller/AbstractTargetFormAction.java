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

import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle object creation and storage.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractTargetFormAction<T> extends AbstractEditTargetFormAction<T> {

	
	/**
	 * Extends abstract method in superclass with a null
	 * implementation to allow target creation to be handled by 
	 * other methods.
	 */
	@Override
	protected T doCreateTarget(RequestContext context) throws Exception {
		return null;
	}

	/**
     * Pre-process the association selection subflow.
     */
	public final Event preProcessSubflow(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- preProcessAssociationSelectionSubflow\n");
        }
        try {
        	if (!doPreProcessSubflow(context)) {
                logger.warn("Association selection subflow pre-process interrupted ");
                return error();
        	}
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to pre-process association selection subflow ", e);
            return error();
        }
    }
	
    protected boolean doPreProcessSubflow(RequestContext context) throws Exception {
    	return true;
    }

    /**
     * Post-process the association selection subflow; if the target is null,
     * invoke target creation hook.
     */
	@SuppressWarnings("unchecked")
	public final Event postProcessSubflow(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- postProcessAssociationSelectionSubflow\n");
        }
        try {
        	Object returnTarget =  getReturnTarget(context);
        	T target = (T) get(context);
       		target = doPostProcessSubflow(context, target, returnTarget);
            if (target!=null) {
            	put(context, target);
            	return success();
            }
            logger.warn("Unable to post-process association selection subflow ");
            return error();
        }
        catch (Exception e) {
            logger.warn("Unable to post-process association selection subflow ", e);
            return error();
        }
    }
	
	/**
	 * Hook to do the actual subflow post-processing.
	 */
    protected T doPostProcessSubflow(RequestContext context, T target, Object returnTarget) throws Exception {
    	return null;
    }
    
	public Object getTargetRequirement(RequestContext context) {
		return context.getRequestScope().get("targetRequirement");
	}
	
}
