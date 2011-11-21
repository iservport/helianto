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

import java.util.List;

import org.helianto.core.filter.Filter;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;

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
public abstract class AbstractFormAction<T, F> extends AbstractAction<T> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Hook to the form creation.
	 * 
	 * @param attributes
	 */
	protected abstract F getForm(MutableAttributeMap attributes);
	
	/**
	 * Create a new item list in the attribute named after {@link #getListName()}
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String find(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return internalCallService(attributes, userDetails, getForm(attributes));
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
	protected String internalCallService(MutableAttributeMap attributes, PublicUserDetails userDetails, F form) {
		logger.debug("Using form {}.", form);
		List<T> itemList = doCallService(attributes, form, userDetails);
		put(attributes, itemList);
		return internalCallService(attributes, itemList);
	}
	
	/**
	 * Called after {@link #internalCallService(MutableAttributeMap, PublicUserDetails, Filter)} to auto 
	 * select a item list in the appropriate scope receiver.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected String internalCallService(MutableAttributeMap attributes, List<T> itemList) {
		@SuppressWarnings("unchecked") T target = (T) attributes.get(getTargetName());
		if (target==null && itemList!=null && itemList.size()>0) {
			attributes.put(getTargetName(), itemList.get(0));
			logger.debug("Auto selected: {}.", itemList.get(0));
			return "success";
		}
		return isNotAutoSelected(attributes, itemList);
	}
	
	/**
	 * Called after {@link #internalCallService(MutableAttributeMap, List)} to signal success.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected String isNotAutoSelected(MutableAttributeMap attributes, List<T> itemList) {
		return "success";
	}
		
	/**
	 * Called by {@link #internalCallService(MutableAttributeMap, PublicUserDetails, Object)} to put 
	 * the item list in the appropriate scoped attribute.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected void put(MutableAttributeMap attributes, List<T> itemList) {
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
	 * Subclasses may override this to customize call to the service layer.
	 * 
	 * <p>
	 * Default implementation delegates to {@link #doCallService(MutableAttributeMap, Filter)}.
	 * </p>
	 * 
	 * @param attributes
	 * @param filter
	 * @param userDetails
	 */
	protected List<T> doCallService(MutableAttributeMap attributes, F form, PublicUserDetails userDetails) {
		return doCallService(attributes, form);
	}

	/**
	 * Subclasses may override this to customize call to the service layer.
	 * 
	 * <p>
	 * Default implementation delegates to {@link #doCallService(F)}.
	 * </p>
	 * 
	 * @param attributes
	 * @param form
	 */
	protected List<T> doCallService(MutableAttributeMap attributes, F form) {
		return doCallService(form);
	}

	/**
	 * Hook to the actual service call action.
	 * 
	 * @param form
	 */
	protected abstract List<T> doCallService(F form);

	static final Logger logger = LoggerFactory.getLogger(AbstractFormAction.class);
	
}
