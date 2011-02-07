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
import org.helianto.core.filter.Listable;
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
	 * Default model is of type <code>SimpleModel</code>.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected SimpleModel<?> doCreateModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Filter filter = getFilter(attributes, userDetails);
		return new SimpleModel(filter, userDetails.getUser());
	}

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
		Filter filter = getFilter(attributes, userDetails);
		logger.debug("Using filter {}.", filter);
		List<T> itemList = doFilter(attributes, filter);
		put(attributes, itemList);
		return "success";
	}
	
	/**
	 * Called after doFilter to put the item list in the appropriate scope receiver.
	 * 
	 * </p>
	 * By default, try to use the filter as a <code>Listable</code> receiver.
	 * </p>
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected void put(MutableAttributeMap attributes, List<T> itemList) {
		Listable model = getModel(attributes);
		logger.debug("Updating model {}.", model);
		model.setList(itemList);
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
	protected Filter getFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Filter filter = getFilter(attributes);
		if (filter==null) {
			filter = doCreateFilter(attributes, userDetails);
			filter.setObjectAlias(getTargetName());
			logger.debug("Created {} with object alias {}.", filter, filter.getObjectAlias());
			attributes.put(getFilterName(), filter);
		}
		return filter;
	}

	/**
	 * Subclasses may override this to customize filter retrieval.
	 * 
	 * @param attributes
	 */
	protected Filter getFilter(MutableAttributeMap attributes) {
		Filter filter = null;
		if (hasFilter(attributes)) {
			filter = (Filter) attributes.get(getFilterName());
			logger.debug("Retrieved {}.", filter);
		}
		return filter;
	}
	
	/**
	 * Hook to the actual filter creation.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	protected abstract Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails);

	/**
	 * Subclasses may override this to customize filter action.
	 * 
	 * <p>
	 * Default implementation delegates to {@link #doFilter(Filter)}.
	 * </p>
	 * 
	 * @param attributes
	 * @param filter
	 */
	protected List<T> doFilter(MutableAttributeMap attributes, Filter filter) {
		return doFilter(filter);
	}

	/**
	 * Hook to the actual filter action.
	 * 
	 * @param filter
	 */
	protected abstract List<T> doFilter(Filter filter);

	/**
	 * True if the attribute map has a not null filter.
	 * 
	 * @param attributes
	 */
	protected boolean hasFilter(MutableAttributeMap attributes) {
		if (attributes.contains(getFilterName()) && attributes.get(getFilterName())!=null) {
			return true;
		}
		return false;
	}
	
	static final Logger logger = LoggerFactory.getLogger(AbstractFilterAction.class);
	
}
