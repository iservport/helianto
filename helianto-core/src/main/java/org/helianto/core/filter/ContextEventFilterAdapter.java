package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.form.ContextEventForm;

/**
 * Context filter.
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextEventFilterAdapter 
	extends AbstractRootFilterAdapter<ContextEventForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * 
	 * @param form
	 */
	public ContextEventFilterAdapter(ContextEventForm form) {
		super(form);
	}
	
	public boolean isSelection() {
		return super.isSelection() && getForm().getPublicNumber()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("publicNumber", getForm().getPublicNumber(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resolution", getForm().getResolution(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "issueDate DESC";
	}

}
