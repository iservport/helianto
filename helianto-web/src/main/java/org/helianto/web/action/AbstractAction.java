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

package org.helianto.web.action;

import java.io.Serializable;

import javax.annotation.Resource;

import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.core.collection.ParameterMap;

/**
 * Base class to presentation logic based on spring webflow.
 * 
 * <p>
 * Exposes operations: create, prepare, store and remove.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractAction<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name used as key to retrieve the target from attribute maps.
	 */
	protected String getTargetName() {
		return getTargetName(this.getClass());
	}

	/**
	 * Name used as key to retrieve the target from attribute maps.
	 * 
	 * @param clazz
	 */
	protected String getTargetName(Class<?> clazz) {
		return actionNamingConventionStrategy.getConventionalName(clazz);
	}

	/**
	 * Name used as key to retrieve the model from attribute maps.
	 * 
	 * <p>
	 * Default behavior is to append "Model"to the target name.
	 * </p>
	 */
	protected String getModelName() {
		return new StringBuilder(getTargetName()).append("Model").toString();
	}

	/**
	 * Create a new target in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String create(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		T target = doCreate(attributes, userDetails);
		attributes.put(getTargetName(), target);
		logger.debug("Created {} with name {}.", target, getTargetName());
		return "success";
	}
	
	/**
	 * Subclasses must override this to assure the actual target creation.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	protected abstract T doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails);

	/**
	 * Replace the target with a prepared one.
	 * 
	 * @param attributes
	 * @deprecated
	 */
	public String prepare(MutableAttributeMap attributes) {
		if (!hasTarget(attributes)) {
			return "noTarget";
		}
		T target = getTarget(attributes);
		attributes.put(getTargetName(), doPrepare(target, attributes));
		logger.debug("Prepared {}.", target);
		return "success";
	}
	
	/**
	 * Subclasses override this to assure the actual target preparation.
	 * 
	 * @param target
	 * @param attributes
	 * @deprecated
	 */
	protected T doPrepare(T target, MutableAttributeMap attributes) {
		return target;
	}
	
	/**
	 * Store the target received in the attribute map.
	 * 
	 * @param attributes
	 */
	public String store(MutableAttributeMap attributes) {
		if (!hasTarget(attributes)) {
			return "noTarget";
		}
		attributes.put(getTargetName(), doStore(getTarget(attributes)));
		return "success";
	}
	
	/**
	 * Subclasses must override this to assure the actual target persistence.
	 * 
	 * @param attributes
	 */
	protected abstract T doStore(T target);
	
	/**
	 * Remove the target received in the attribute map.
	 * 
	 * @param attributes
	 * @param parameters
	 */
	public String remove(MutableAttributeMap attributes, ParameterMap parameters) {
		if (!hasTarget(attributes)) {
			return "noTarget";
		}
		String confirmRemoval = parameters.get("confirmRemoval");
		logger.debug("Removal confirmation is: '{}'.", confirmRemoval);
		if (confirmRemoval!=null && confirmRemoval.equals("R")) {
			String result = doRemove(attributes, getTarget(attributes));
			if (result.equals("success")) {
				attributes.put(getTargetName(), null);
				logger.debug("Removed: {}.", getTargetName());
			}
			return result;
		}
		logger.debug("Not removed: {}.", getTargetName());
		return "failure";
	}
		
	/**
	 * Default behavior throws an exception.
	 * 
	 * @param attributes
	 * @param target
	 */
	protected String doRemove(MutableAttributeMap attributes, T target) {
		return doRemove(target);
	}
		
	/**
	 * Default behavior throws an exception.
	 * 
	 * @param target
	 */
	protected String doRemove(T target) {
		throw new UnsupportedOperationException("Removal not supported");
	}
		
	/**
	 * Create a new Model in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String createModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Object model = doCreateModel(attributes, userDetails);
		attributes.put(getModelName(), model);
		logger.debug("Created {} with name {}.", model, getModelName());
		return "success";
	}
	
	/**
	 * Default model is of type <code>SimpleModel</code>.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected SimpleModel<?> doCreateModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Object form = getTarget(attributes);
		return new SimpleModel(form, userDetails.getUser());
	}

	// convenience methods
	
	/**
	 * Get target from atribute map.
	 * 
	 * @param attributes
	 */
	@SuppressWarnings("unchecked")
	protected T getTarget(MutableAttributeMap attributes) {
		if (hasTarget(attributes)) {
			return (T) attributes.get(getTargetName());
		}
		throw new IllegalArgumentException("Target is not an attribute");
	}
	
	/**
	 * Subclasses may override this to customize model retrieval.
	 * 
	 * @param attributes
	 * @param <M>
	 */
	@SuppressWarnings("unchecked")
	protected <M> M getModel(MutableAttributeMap attributes) {
		M model = null;
		if (hasModel(attributes)) {
			model = (M) attributes.get(getModelName());
			logger.debug("Retrieved {}.", model);
		}
		return model;
	}

	/**
	 * True if the attribute map has a not null model.
	 * 
	 * @param attributes
	 */
	protected boolean hasModel(MutableAttributeMap attributes) {
		if (attributes.contains(getModelName()) && attributes.get(getModelName())!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * True if the atribute map has a not null target.
	 * 
	 * @param attributes
	 */
	protected boolean hasTarget(MutableAttributeMap attributes) {
		if (attributes.contains(getTargetName()) && attributes.get(getTargetName())!=null) {
			logger.debug("The current scope has a target named: {}.", getTargetName());
			return true;
		}
		return false;
	}
	
	// collabs
	
	private NamingConventionStrategy actionNamingConventionStrategy;
	
	@Resource
	public void setActionNamingConventionStrategy(NamingConventionStrategy actionNamingConventionStrategy) {
		this.actionNamingConventionStrategy = actionNamingConventionStrategy;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractAction.class);
	
}
