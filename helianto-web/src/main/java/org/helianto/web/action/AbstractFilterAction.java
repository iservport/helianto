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

import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Base class to presentation logic.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFilterAction<T> extends AbstractAction<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Name used as key to retrieve the filter from attribute maps.
	 * 
	 * <p>
	 * Default behavior is to append "Filter"to the target name.
	 * </p>
	 */
	protected String getFilterName() {
		return new StringBuilder(getTargetName()).append("Filter").toString();
	}

	/**
	 * Create a new item list in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String filter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		// also put the target name in the attribute map.
		if (!attributes.contains("targetName")) {
			attributes.put("targetName", getTargetName());
		}
		ListFilter filter = getFilter(attributes, userDetails);
		logger.debug("Using filter {}.", filter);
		List<T> itemList = doFilter(filter);
		filter.setList(itemList);
		logger.debug("Created list of size {} in filter.", itemList.size());
		return "success";
	}
	
	/**
	 * Subclasses may override this to customize filter retrieval.
	 * 
	 * <p>
	 * Default behavior is to create a new filter in the attribute map if does not 
	 * exist already.
	 * </p>
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	protected ListFilter getFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		if (hasFilter(attributes)) {
			return (ListFilter) attributes.get(getFilterName());
		}
		else {
			ListFilter filter = doCreateFilter(attributes, userDetails);
			logger.debug("Created {}.", filter);
			attributes.put(getFilterName(), filter);
			return filter;
		}
	}

	/**
	 * Hook to the actual filter creation.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	protected abstract ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails);

	/**
	 * Hook to the actual filter action.
	 * 
	 * @param filter
	 */
	protected abstract List<T> doFilter(ListFilter filter);

	// convenience methods
	
	/**
	 * True if the atribute map has a not null filter.
	 * 
	 * @param attributes
	 */
	protected boolean hasFilter(MutableAttributeMap attributes) {
		if (attributes.contains(getFilterName()) && attributes.get(getFilterName())!=null) {
			return true;
		}
		return false;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractFilterAction.class);
	
}
