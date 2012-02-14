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
import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.core.collection.ParameterMap;

/**
 * Extends {@link AbstractAction} to provide form based behavior to presentation logic.
 * 
 * <p>
 * Subclasses must provide implementation to the abstract method {@link #getForm(MutableAttributeMap)}. 
 * The method is expected to return the actual form to be presented to the service layer through 
 * {@link #doCallService(F)}. After that, the flow is ready to call the method 
 * {@link #find(MutableAttributeMap, PublicUserDetails)} to update the scoped attribute named after
 * {@link #getListName()} with a list of the resulting items of type &lt;T&gt;.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFormAction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Name to be used as key to retrieve the target from attribute maps.
	 */
	protected String getTargetName() {
		return getTargetName(this.getClass());
	}

	/**
	 * Name to be used as key to retrieve the target from attribute maps.
	 * 
	 * <p>
	 * Default implementation uses a <code>NamingConventionStrategy</code> collaborator 
	 * to resolve the name from a class.
	 * </p>
	 * 
	 * @param clazz
	 */
	protected String getTargetName(Class<?> clazz) {
		return actionNamingConventionStrategy.getConventionalName(clazz);
	}

	/**
	 * Get target from the attribute map.
	 * 
	 * @param attributes
	 * @exception if target name returned null from the attribute map
	 */
	protected Object getTarget(MutableAttributeMap attributes) throws IllegalArgumentException {
		if (attributes.contains(getTargetName()) && attributes.get(getTargetName())!=null) {
			return attributes.get(getTargetName());
		}
		logger.error("Target name {} returned null from the attribute map", getTargetName());
		throw new IllegalArgumentException("Target name returned null from the attribute map.");
	}
	
	/**
	 * Create a new target in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String create(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Object target = doCreate(attributes, userDetails);
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
	protected abstract Object doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails);

	/**
	 * Store the target received in the attribute map.
	 * 
	 * @param attributes
	 */
	public String store(MutableAttributeMap attributes) {
		if (attributes.contains(getTargetName()) && attributes.get(getTargetName())!=null) {
			attributes.put(getTargetName(), doStore(attributes, getTarget(attributes)));
			return "success";
		}
		logger.error("Target name {} returned null from the attribute map", getTargetName());
		throw new IllegalArgumentException("Target name returned null from the attribute map.");
	}
	
	/**
	 * Subclasses may override this to the actual target persistence.
	 * 
	 * @param attributes
	 * @param target
	 */
	protected Object doStore(MutableAttributeMap attributes, Object target) {
		return doStore(getTarget(attributes));
	}
	
	/**
	 * Subclasses must override this to assure the actual target persistence.
	 * 
	 * @param target
	 */
	protected abstract Object doStore(Object target);
	
	/**
	 * Name to be used as key to retrieve the form from the attribute map.
	 */
	protected String getFormName() {
		return "form";
	}
	
	/**
	 * Form object.
	 * 
	 * @param attributes
	 */
	protected Object getForm(MutableAttributeMap attributes) {
		return attributes.get(getFormName());
	}
	
	/**
	 * Create a new item list in the attribute named after {@link #getListName()}
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String filter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return internalFind(attributes, userDetails, getForm(attributes));
	}
	
	/**
	 * Called after {@link #find(MutableAttributeMap, PublicUserDetails)} to delegate the 
	 * actual update of the scoped attribute named after {@link #getListName()} to
	 * {@link #put(MutableAttributeMap, List)}.
	 * 
	 * @param attributes
	 * @param userDetails
	 * @param form
	 */
	protected String internalFind(MutableAttributeMap attributes, PublicUserDetails userDetails, Object form) {
		logger.debug("Using form {}.", form);
		Object internalForm = preProcessForm(attributes, form, userDetails);
		List<Object> itemList = doCallFinderService(internalForm);
		put(attributes, itemList);
		return internalFind(attributes, itemList);
	}
	
	/**
	 * Called after {@link #internalFind(MutableAttributeMap, PublicUserDetails, Filter)} to auto 
	 * select a item list in the appropriate scope receiver.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected String internalFind(MutableAttributeMap attributes, List<Object> itemList) {
		Object target = attributes.get(getTargetName());
		if (target==null && itemList!=null && itemList.size()>0) {
			attributes.put(getTargetName(), itemList.get(0));
			logger.debug("Auto selected: {}.", itemList.get(0));
			return "success";
		}
		return isNotAutoSelected(attributes, itemList);
	}
	
	/**
	 * Called after {@link #internalFind(MutableAttributeMap, List)} to signal success.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected String isNotAutoSelected(MutableAttributeMap attributes, List<Object> itemList) {
		return "success";
	}
		
	/**
	 * Called by {@link #internalFind(MutableAttributeMap, PublicUserDetails, Object)} to put 
	 * the item list in the appropriate scoped attribute.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected void put(MutableAttributeMap attributes, List<Object> itemList) {
		attributes.put(getListName(), itemList);
		logger.debug("Attribute {} updated with {}.", getListName(), itemList);
	}
	
	/**
	 * Default list name is ${targetName}List.
	 */
	protected String getListName() {
		return new StringBuilder(getTargetName()).append("List").toString();
	}
	
	/**
	 * Form pre-processing.
	 * 
	 * <p>
	 * Default implementation only returns the passed form unchanged.
	 * </p>
	 * 
	 * @param attributes
	 * @param form
	 * @param userDetails
	 */
	protected Object preProcessForm(MutableAttributeMap attributes, Object form, PublicUserDetails userDetails) {
		return form;
	}

	/**
	 * Hook to the actual service call action.
	 * 
	 * @param form
	 */
	protected abstract List<Object> doCallFinderService(Object form);
	
	/**
	 * Default index name is ${targetName}_index.
	 */
	protected String getIndexName() {
		return new StringBuilder(getTargetName()).append("_index").toString();
	}
	
	/**
	 * Get index using ${indexName} read first the parameter map and, if not contained, then the attribute map.
	 * 
	 * @param attributes
	 * @param parameters
	 */
	protected int getIndex(MutableAttributeMap attributes, ParameterMap parameters) {
		if (parameters.contains(getIndexName())) {
			return parameters.getInteger(getIndexName());
		}
		else if (attributes.contains(getIndexName())) {
			return attributes.getInteger(getIndexName());
		}
		return 0;
	}
	
	/**
	 * Selects the target from the selection list using the attribute ${targetName}_index.
	 * 
	 * <p>
	 * Default implementation looks for a 'step' parameter before delegating to {@link #select(MutableAttributeMap, ParameterMap, int)}
	 * </p>
	 * 
	 * @param attributes
	 * @param parameters
	 */
	public String select(MutableAttributeMap attributes, ParameterMap parameters) {
		if (parameters.contains("step")) {
			int step = parameters.getInteger("step");
			return select(attributes, parameters, step);
		}
		return select(attributes, parameters, 0);
	}

	/**
	 * Selects the target from the selection list using the attribute ${targetName}_index.
	 * 
	 * <p>
	 * Default implementation also saves the selected index as ${targetName}_index.
	 * </p>
	 * 
	 * @param attributes
	 * @param parameters
	 * @param step
	 */
	public String select(MutableAttributeMap attributes, ParameterMap parameters, int step) {
		int index = getIndex(attributes, parameters) + step;
		List<?> list = (List<?>) attributes.get(getListName());
		if (list!=null) {
			if (index>=0 && list.size()>index) {
				attributes.put(getTargetName(), list.get(index));
				attributes.put(getIndexName(), index);
				return "success";			
			}
		}
		return "ignored";
	}

	// collabs
	
	private NamingConventionStrategy actionNamingConventionStrategy;
	
	@Resource
	public void setActionNamingConventionStrategy(NamingConventionStrategy actionNamingConventionStrategy) {
		this.actionNamingConventionStrategy = actionNamingConventionStrategy;
	}
	
	static final Logger logger = LoggerFactory.getLogger(AbstractFormAction.class);
	
}
