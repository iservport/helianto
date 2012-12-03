package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Identity;
import org.helianto.core.filter.base.AbstractFilterAdapter;

/**
 * Identity filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public class IdentityFilterAdapter extends AbstractFilterAdapter<Identity>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param operator
	 */
	public IdentityFilterAdapter(Identity identity) {
		super(identity);
		reset();
	}

	/**
	 * Key constructor.
	 * 
	 * @param principal
	 */
	public IdentityFilterAdapter(String principal) {
		this(new Identity(principal));
		reset();
	}

	public void reset() {
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getPrincipal()!=null && getForm().getPrincipal().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("principal", getForm().getPrincipal(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("personalData.firstName", getForm().getPersonalData().getFirstName(), mainCriteriaBuilder);
	}

}
