package org.helianto.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.filter.Listable;
import org.helianto.core.filter.Page;
import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.action.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Abstract page model builder.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <F>
 */
public abstract class AbstractPageModelBuilder<F> implements PageModelBuilder<F> {
	
	public String getModelName() {
		return new StringBuilder(
				modelBuilderNamingConventionStrategy.getConventionalName(this.getClass()))
		.append("Model").toString();
	}

	public PageModel<? extends F> createFormModel(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		F form = doCreateForm(attributes, userDetails);
		PageModel<F> model =  new PageModel<F>(form);
		attributes.put(getModelName(), model);
		logger.debug("Created attribute {}= {}.", getModelName(), model);
		return model;
	}
	
	/**
	 * Do the actual form creation.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	protected abstract F doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails);
	
	/**
	 * Convenient to retrieve the model.
	 * 
	 * @param attributes
	 */
	@SuppressWarnings("unchecked")
	public PageModel<F> getModel(MutableAttributeMap attributes) {
		PageModel<F> model = (PageModel<F>) attributes.get(getModelName());
		return model;
	}

	public F getForm(MutableAttributeMap attributes) {
		return (F) getModel(attributes).getForm();
	}

	public void addPage(MutableAttributeMap attributes, String pageName, List<?> list) {
		PageModel<F> model = getModel(attributes);
		model.addPage(pageName, new Page(list));
		logger.debug("Added page {} to {}.", list, pageName);
		if (list.size()>0) {
			attributes.put(pageName, list.get(0));
			logger.debug("Set attribute {}= {}.", pageName, list);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <L> List<L> getList(MutableAttributeMap attributes, String pageName) {
		PageModel<F> model = getModel(attributes);
		Listable page = model.getPage(pageName);
		if (page!=null) {
			return ((List<L>) page.getList());
		}
		logger.warn("Empty list!");
		return new ArrayList<L>();
	}

	// collabs
	
	private NamingConventionStrategy modelBuilderNamingConventionStrategy;
	
	@Resource
	public void setModelBuilderNamingConventionStrategy(NamingConventionStrategy modelBuilderNamingConventionStrategy) {
		this.modelBuilderNamingConventionStrategy = modelBuilderNamingConventionStrategy;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractPageModelBuilder.class);
	
}
