package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.filter.form.IdentityForm;

/**
 * Identity form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFormFilterAdapter extends AbstractFilterAdapter<IdentityForm>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public IdentityFormFilterAdapter(IdentityForm form) {
		super(form);
	}

	public void reset() {
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getPrincipal()!=null && getForm().getPrincipal().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualLessCaseFilter("principal", getForm().getPrincipal(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("personalData.firstName", getForm().getFirstName(), mainCriteriaBuilder);
	}

}
