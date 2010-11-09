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

import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.core.collection.ParameterMap;

/**
 * Base class to presentation logic.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractAction<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name used as key to retrieve the target from attribute maps.
	 */
	protected String getTargetName() {
		return actionNamingConventionStrategy.getObjectName(this);
	}

	/**
	 * Create a new target in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String create(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		if (hasTarget(attributes)) {
			return "browse";
		}
		T target = doCreate(attributes, userDetails);
		attributes.put(getTargetName(), target);
		logger.debug("Created {}.", target);
		return "edit";
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
	
	// TODO test the following code for convenience
	
	public String select(MutableAttributeMap attributes, ParameterMap parameters) {
		return select(attributes, parameters, getItemIndexParameterName(getTargetName()));
	}
	
	public String select(MutableAttributeMap attributes, ParameterMap parameters, String itemName) {
		List<?> itemList = null;
		String itemListName = getItemListName(itemName);
		logger.debug("Looking for {}", itemListName);
		if (attributes.contains(itemListName)) {
			itemList = (List<?>) attributes.get(itemListName);
		}
		else {
			return "not_a_list";
		}
    	int index = 0;
    	if (itemList!=null) {
        	if (itemList.size()>1 && parameters.contains(itemName)) {
        		index = parameters.getInteger(itemName);
        	}
    		Object target = itemList.get(index);
    		if (target!=null) {
                logger.debug("Item #{} selected {}.", index, target);
                attributes.put(itemName, target);
    		}
    	}
		return "success";
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
	 * True if the atribute map has a not null target.
	 * 
	 * @param attributes
	 */
	protected boolean hasTarget(MutableAttributeMap attributes) {
		if (attributes.contains(getTargetName()) && attributes.get(getTargetName())!=null) {
			return true;
		}
		return false;
	}
	
	protected Object getReturnTarget(MutableAttributeMap attributes) {
		if (hasReturnTarget(attributes)) {
			return attributes.get(getReturnTargetName());
		}
		throw new IllegalArgumentException("Return target is not an attribute");
	}
	
	protected String getReturnTargetName() {
		return "returnTarget";
	}
	
	protected boolean hasReturnTarget(MutableAttributeMap attributes) {
		if (attributes.contains(getReturnTargetName()) && attributes.get(getReturnTargetName())!=null) {
			return true;
		}
		return false;
	}
	
	protected String getItemListName() {
		return getItemListName(getTargetName());
	}
	
	protected String getItemListName(String itemName) {
		return new StringBuilder(itemName).append("List").toString();
	}
	
	protected String getItemIndexParameterName(String itemName) {
		return new StringBuilder(itemName).append("_index").toString();
	}
	
	// collabs
	
	private NamingConventionStrategy actionNamingConventionStrategy;
	
	@Resource
	public void setActionNamingConventionStrategy(NamingConventionStrategy actionNamingConventionStrategy) {
		this.actionNamingConventionStrategy = actionNamingConventionStrategy;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractAction.class);
	
}
