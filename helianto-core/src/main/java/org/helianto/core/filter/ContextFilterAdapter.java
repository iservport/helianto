package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.form.ContextForm;

/**
 * Context filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextFilterAdapter 
	extends AbstractFilterAdapter<ContextForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public ContextFilterAdapter(ContextForm form) {
		super(form);
	}

	@Override
	public boolean isSelection() {
		return getForm().getContextName()!=null && getForm().getContextName().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("operatorName", getForm().getContextName(), mainCriteriaBuilder);		
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { 
		appendLikeFilter("operatorName", getForm().getContextName(), mainCriteriaBuilder);
	}
	
}
