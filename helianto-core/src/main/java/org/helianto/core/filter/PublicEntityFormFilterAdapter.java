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
public class PublicEntityFormFilterAdapter 
	extends AbstractRootFilterAdapter<PublicEntityForm> 
{

	private static final long serialVersionUID = 1L;
	
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
		logger.debug("Filter constraint set to {}.", getOperator());
	}
	
	@Override
	public boolean isSelection() {
		return false;
	}
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getOperator()!=null) {
			appendEqualFilter("entity.operator.id", getOperator().getId(), mainCriteriaBuilder);
		}
		else {
			appendEqualFilter("entity.operator.id", getForm().getContextId(), mainCriteriaBuilder);
		}
		appendEqualFilter("entity.id", getForm().getEntityId(), mainCriteriaBuilder);
		appendEqualFilter("entityAlias", getForm().getEntityAlias(), mainCriteriaBuilder);
		appendLikeFilter("entityName", getForm().getEntityName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "entity.alias";
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFormFilterAdapter.class);
	
}
