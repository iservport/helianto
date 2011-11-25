package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.filter.form.PublicEntityForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Public entity form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityFormFilterAdapter extends AbstractRootFilterAdapter<PublicEntityForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PublicEntityFormFilterAdapter(PublicEntityForm form) {
		super(form);
	}
	
	public void reset() {
		getForm().reset();
	}
	
	/**
	 * Restrict selection to a given operator, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getForm().getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getForm().getClazz());
			logger.debug("Added class {} restriction.", getForm().getClazz());
			connect = true;
		}
		return connect;
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getOperator()!=null && getForm().getEntity()!=null;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.id", getForm().getEntity().getId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getOperator()==null && getForm().getEntity()!=null) {
			appendEqualFilter("entity.id", getForm().getEntity().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("publicEntityType", getForm().getPublicEntityType(), mainCriteriaBuilder);
		appendLikeFilter("entityName", getForm().getEntityName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "entity.alias";
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFormFilterAdapter.class);
	
}
