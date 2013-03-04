package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.form.PublicEntityForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Public entity form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityFormFilterAdapter extends AbstractRootFilterAdapter<PublicEntityForm> {

	private static final long serialVersionUID = 1L;
	private static final String NON_PUBLIC_TYPES = "R";
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PublicEntityFormFilterAdapter(PublicEntityForm form) {
		super(form);
	}
	
	/**
	 * Operator pre-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public void preProcessOperatorFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.operator.id", getOperator().getId(), mainCriteriaBuilder);
		logger.debug("Filter constraint set to {}.", getOperator());
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getEntity()!=null
				&& getForm().getEntity().getId()>0
				&& getForm().getEntityAlias()!=null
				&& getForm().getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.id", getForm().getEntity().getId(), mainCriteriaBuilder);
		appendEqualFilter("entityAlias", getForm().getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getEntity()!=null && (getForm().getOperator()==null | NON_PUBLIC_TYPES.indexOf(getForm().getType())>=0)) {
			appendEqualFilter("entity.id", getForm().getEntity().getId(), mainCriteriaBuilder);
		}
		appendLikeFilter("entityName", getForm().getEntityName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "entity.alias";
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFormFilterAdapter.class);
	
}
