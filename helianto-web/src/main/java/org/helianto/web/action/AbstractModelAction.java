package org.helianto.web.action;

import java.io.Serializable;

import javax.annotation.Resource;

import org.helianto.core.User;
import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Action to collaborate with a <code>PageModel</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractModelAction<F> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String modelName;
	
	/**
	 * Name used as key to retrieve the target from attribute maps.
	 */
	public String getModelName() {
		if (modelName==null) {
			setModelName(modelActionNamingConventionStrategy.getConventionalName(this.getClass()));
			logger.debug("Model name is {}.", getModelName());
		}
		return this.modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * Create a new model in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String createModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		User user = userDetails.getUser();
		PageModel<F> pageModel = getModel(attributes);
		if (pageModel==null) {
			pageModel = new PageModel<F>(user, "selection"); 
			putModel(attributes, pageModel);
		}
		F filter = doCreateFilter(attributes, pageModel);
		pageModel.setFilter(filter);
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<F> getModel(MutableAttributeMap attributes) {
		return (PageModel<F>) attributes.get(getModelName());
	}
	
	protected void putModel(MutableAttributeMap attributes, PageModel<F> pageModel) {
		if (pageModel!=null) {
			attributes.put(getModelName(), pageModel);
		}
	}
	
	/**
	 * Subclasses may use this method instead of {@link #doCreateFilter(MutableAttributeMap, User)}.
	 * 
	 * @param attributes
	 * @param pageModel
	 */
	protected F doCreateFilter(MutableAttributeMap attributes, PageModel<F> pageModel) {
		return doCreateFilter(attributes, pageModel.getUser());
	}
	
	/**
	 * Hook to the actual filter creation.
	 * 
	 * @param attributes
	 * @param user
	 */
	protected abstract F doCreateFilter(MutableAttributeMap attributes, User user);
	
	// collabs
	
	private NamingConventionStrategy modelActionNamingConventionStrategy;
	
	@Resource
	public void setModelActionNamingConventionStrategy(NamingConventionStrategy modelActionNamingConventionStrategy) {
		this.modelActionNamingConventionStrategy = modelActionNamingConventionStrategy;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractModelAction.class);
	
}
