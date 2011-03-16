package org.helianto.web.action;

import java.io.Serializable;

import javax.annotation.Resource;

import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Business delegate (action) base class designed to collaborate with a {@link FormModel}
 * based flow.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractModelAction<F> implements Serializable, ModelAction {
	
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
	 * Create a new model and form in the attribute map.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	public String createModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		FormModel<F> formModel = getModel(attributes);
		if (formModel==null) {
			formModel = doCreateModel(attributes, userDetails); 
			putModel(attributes, formModel);
		}
		F form = doCreateForm(attributes, userDetails, formModel);
		formModel.setForm(form);
		return "success";
	}
	
	/**
	 * Default model is an instance of {@link PageModel}.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	protected FormModel<F> doCreateModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new PageModel<F>(); 
	}
	
	@SuppressWarnings("unchecked")
	public FormModel<F> getModel(MutableAttributeMap attributes) {
		return (FormModel<F>) attributes.get(getModelName());
	}
	
	protected void putModel(MutableAttributeMap attributes, FormModel<F> formModel) {
		if (formModel!=null) {
			attributes.put(getModelName(), formModel);
		}
	}
	
	/**
	 * Hook to the actual form creation.
	 * 
	 * @param attributes
	 * @param userDetails
	 * @param formModel
	 */
	protected abstract F doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails, FormModel<F> formModel);
	
	// collabs
	
	private NamingConventionStrategy modelActionNamingConventionStrategy;
	
	@Resource
	public void setModelActionNamingConventionStrategy(NamingConventionStrategy modelActionNamingConventionStrategy) {
		this.modelActionNamingConventionStrategy = modelActionNamingConventionStrategy;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractModelAction.class);
	
}
