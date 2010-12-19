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

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Node;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.Errors;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.ScopeType;

/**
 * Presentation logic base class to handle object storage.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEditTargetFormAction<T> extends AbstractComplexModelFormAction<T> {

	private PropertyEditor targetPropertyEditor;
	private Class<T> clazz;
    
    /**
     * Make default scope FLOW.
     */
	public AbstractEditTargetFormAction() {
    	super();
		setFormObjectScope(ScopeType.FLOW);
	}
    
	protected T doSelectTarget(RequestContext context) throws Exception {
		return (T) get(context);
	}
        
    /**
     * Create a form object.
     */
	@Override
	protected AbstractTargetForm<T> createFormObject(RequestContext context) throws Exception {
        try {
    		AbstractTargetForm<T> formObject = doCreateForm();
            if (logger.isDebugEnabled()) {
                logger.debug("Created "+formObject);
            }
        	T target = (T) get(context);
        	if (target!=null) {
        		formObject.setTarget(target);
                if (logger.isDebugEnabled()) {
                    logger.debug("Target '"+getTargetAttributeName()+"'="+target+" set in form.");
                }
        	}
    		return formObject;
        } 
        catch (Exception e) {
        	logger.warn("Unable to create form object ", e);
        	throw new IllegalArgumentException("Unable to create form object");
        }
	}

	/**
	 * Default implementaion creates a <tt>DefaultTargetForm</tt>.
	 */
    protected AbstractTargetForm<T> doCreateForm() throws Exception {
    	return new DefaultTargetForm<T>();
    }
    
    @SuppressWarnings("unchecked")
    protected AbstractTargetForm<T> getForm(RequestContext context) throws Exception {
		return (AbstractTargetForm<T>) getFormObject(context);
    }
    
    /**
     * Delegate to {@link #doCreateTree(UserBackedFilter)}
     */
	@Override
    protected final List<Node> doCreateTree(RequestContext context) throws Exception {
    	T target = (T) get(context);
        if (logger.isDebugEnabled()) {
            logger.debug("Tree root: "+target);
        }
    	return doCreateTree(target);
    }
	
    /**
     * Subclasses should override if a target rooted tree is required.
     */
    protected List<Node> doCreateTree(T target) throws Exception {
    	return new ArrayList<Node>();
    }
	
    /**
     * Put the target in form to be edited.
     */
	public final Event editTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- editTarget\n");
        }
        try {
    		AbstractTargetForm<T> formObject = getForm(context);
    		T target = (T) get(context);
            if (target!=null) {
            	formObject.setTarget(target);
                if (logger.isDebugEnabled()) {
                    logger.debug("Target set in form");
                }
            }
            referenceData(context, target);
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to edit target ", e);
            return error();
        }
    }
    
	/**
	 * Hook to the actual target edition.
	 * 
	 * @param context
	 * @param target
	 * @throws Exception
	 */
	protected void referenceData(RequestContext context, T target) throws Exception {
	}
    
    /**
     * Put the target in form to be edited.
     */
	public final Event processReturnTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- processReturnTarget\n");
        }
        try {
            Object returnTarget =  getReturnTarget(context);
            if (returnTarget!=null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Processing return target "+returnTarget);
                }
        		T target = (T) get(context);
                if (!doProcessReturnTarget(target, returnTarget)) {
                	logger.warn("Invalid return target");
                	return error();
                }
            }
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to edit target ", e);
            return error();
        }
    }
    
    /**
     * Get a return target passed in request scope.
     * @param context
     */
    protected final Object getReturnTarget(RequestContext context) {
    	Object returnTarget =  context.getRequestScope().get("returnTarget");
		if (logger.isDebugEnabled()) {
			logger.debug("Return target is  "+returnTarget);
		}
		return returnTarget;
    }
        
    protected boolean doProcessReturnTarget(T detachedTarget, Object returnTarget) throws Exception {
    	return true;
    }
    
    /**
     * Store the target both in data store and in flow scope.
     */
	public final Event storeTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- storeTarget\n");
        }
        try {
            AbstractTargetForm<T> formObject = getForm(context);
            T target = formObject.getTarget();
            preProcessStoreTarget(context, target);
            T managedTarget = doStoreTarget(target);
            put(context, managedTarget);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed target stored");
            }
            postProcessStoreTarget(context, managedTarget);
            return success();
        }
        catch (DataIntegrityViolationException e) {
            logger.warn("Integrity violation ", e);
            getIntegrityViolationError().reject(getFormErrors(context));
            return error();
        }
        catch (Exception e) {
            logger.warn("Unable to store target ", e);
			doHandleError(getFormErrors(context), e);
            return error();
        }
    }
	
	/**
	 * Convert exception.
	 * @param lenient
	 * @return
	 */
	public Errors getFormErrors(RequestContext context) {
        try {
			return super.getFormErrors(context);
		} catch (Exception e1) {
            logger.warn("Unable to retrieve errors object ", e1);
            throw new IllegalArgumentException("Unable to retrieve errors object ", e1);
		}
	}
	
	/**
	 * Subclasses should override to provide custom integrity 
	 * violation messages.
	 */
	protected ErrorCodes getIntegrityViolationError() {
		return new ErrorCodes(getKeyField());
	}
	
	/**
	 * Subclasses should override to customize key field shown in a 
	 * standard integrity violation message.
	 */
	protected String getKeyField() {
		return "internalNumber";
	}
	
	/**
	 * Subclasses shoul override to customize store pre-processing.
	 * 
	 * @param context
	 * @param detachedTarget
	 * @throws Exception
	 */
	protected void preProcessStoreTarget(RequestContext context, T detachedTarget) throws Exception {
	}
    
	/**
	 * Hook to the actual target store.
	 * 
	 * @param detachedTarget
	 * @return
	 * @throws Exception
	 */
    protected abstract T doStoreTarget(T detachedTarget) throws Exception;
    
	/**
	 * Subclasses shoul override to customize store post-processing.
	 * 
	 * @param context
	 * @param detachedTarget
	 * @throws Exception
	 */
    protected void postProcessStoreTarget(RequestContext context, T managedTarget) throws Exception {
    }
    
	/**
	 * Subclasses should override to handle errors other than integrity violation.
	 * 
	 * @param the errors object
	 * @param the current exception
	 */
    protected void doHandleError(Errors errors, Exception e) {
    }
    
    /**
     * Remove the target.
     */
	public final Event removeTarget(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- removeTarget\n");
        }
        ParameterMap parameters = context.getRequestParameters();
        if (!parameters.contains("targetRemovalConfirmation")) {
            logger.warn("Not removed, user did not check confirmation");
            return new Event(this, "invalid");
        }
        try {
        	AbstractTargetForm<T> formObject = getForm(context);
            doRemoveTarget(formObject.getTarget());
            formObject.setTarget(null);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed target removed");
            }
            return success();
        }
        catch (Exception e) {
            logger.warn("Unable to remove target ", e);
            return error();
        }
    }
        
    protected void doRemoveTarget(T detachedTarget) throws Exception {
    	throw new IllegalStateException("Likely programming error: " +
    			"optional remove operation not actually implemented?");
    }
    
	@Override
	protected void registerPropertyEditors(PropertyEditorRegistry registry) {
		super.registerPropertyEditors(registry);
		if (clazz!=null) {
	        if (logger.isDebugEnabled()) {
	            logger.debug("Registering custom editor "+targetPropertyEditor);
	        }
	        registry.registerCustomEditor(clazz, "target", targetPropertyEditor);
		}
	}
	
    public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {    	
    }
    
    protected final PropertyEditor getTargetPropertyEditor() {
    	return this.targetPropertyEditor;
    }
    
    protected final void internalTargetPropertyEditorSetter(PropertyEditor targetPropertyEditor) {
    	this.targetPropertyEditor = targetPropertyEditor;
    }

    protected final void internalTargetPropertyEditorSetter(PropertyEditor targetPropertyEditor, Class<T> clazz) {
    	this.targetPropertyEditor = targetPropertyEditor;
    	this.clazz = clazz;
    }
    
    // subclasses
    
    protected class ErrorCodes {
    	
    	private String field;
    	private String errorCode;
 		private String defaultMessage = "ERROR";
    	private Object[] errorArgs;
    	
    	/**
    	 * Constructor
    	 */
    	public ErrorCodes(String field, String errorCode, String defaultMessage) {
    		this.field = new StringBuilder("target.")
    			.append(field)
    			.toString();
    		setNestedErrorCode(errorCode);
    		setDefaultMessage(defaultMessage);
    		setErrorArgs(new Object[0]);
    	}
    	
    	/**
    	 * Constructor to standard default message
    	 */
    	public ErrorCodes(String field, String errorCode) {
    		this(field, errorCode, "ERROR");
    	}
    	
    	/**
    	 * Constructor to integrity violation error
    	 */
    	public ErrorCodes(String field) {
    		this(field, "integrityViolation");
    	}
    	
    	/**
    	 * Do reject the value using errors object.
    	 * 
    	 * @param errors
    	 */
    	public void reject(Errors errors) {
    		if (errors!=null) {
    			if (errorArgs.length>0) {
            		errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
    			}
    			else {
            		errors.rejectValue(field, errorCode, defaultMessage);
    			}
    		}
    	}

    	/**
		 * ErrorCode to set in format ${targetAttributeName}.${errorCode}
		 */
		public ErrorCodes setNestedErrorCode(String errorCode) {
			this.errorCode = new StringBuilder(getTargetAttributeName())
			.append(".")
			.append(errorCode)
			.toString();
			return this;
		}

		/**
		 * DefaultMessage to set
		 */
		public ErrorCodes setDefaultMessage(String defaultMessage) {
			this.defaultMessage = defaultMessage;
			return this;
		}

		/**
		 * ErrorArgs to set
		 */
		public ErrorCodes setErrorArgs(Object[] errorArgs) {
			this.errorArgs = errorArgs;
			return this;
		}
		
		public String toString() {
			StringBuilder builder =  new StringBuilder("ErrorCodes [")
			.append(field).append(":")
			.append(errorCode).append(":")
			.append(defaultMessage);
			for (Object arg: errorArgs) {
				builder.append(", arg='").append(arg).append("'");
			}
			builder.append("]");
			return builder.toString();
		}

   }

}
