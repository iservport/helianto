package org.helianto.core.filter;

import org.helianto.core.UserGroup;
import org.helianto.core.UserRequest;
import org.helianto.core.filter.base.AbstractControlFilterAdapter;
import org.helianto.core.criteria.OrmCriteriaBuilder;

/**
 * Login request filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRequestFilterAdapter extends AbstractControlFilterAdapter<UserRequest> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public UserRequestFilterAdapter(UserRequest form) {
		super(form);
	}

	/**
	 * Key constructor.
	 * 
	 * @param userGroup
	 * @param internalNumber
	 */
	public UserRequestFilterAdapter(UserGroup userGroup, long internalNumber) {
		super(new UserRequest(userGroup, internalNumber));
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendLikeFilter("principal", getForm().getPrincipal(), mainCriteriaBuilder);
		appendEqualFilter("tempPassword", getForm().getTempPassword(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "internalNumber";
	}

}
