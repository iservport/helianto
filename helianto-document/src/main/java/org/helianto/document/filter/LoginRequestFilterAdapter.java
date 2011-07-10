package org.helianto.document.filter;

import org.helianto.core.Entity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.LoginRequest;

/**
 * Login request filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class LoginRequestFilterAdapter extends AbstractControlFilterAdapter<LoginRequest> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public LoginRequestFilterAdapter(LoginRequest form) {
		super(form);
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	public LoginRequestFilterAdapter(Entity entity, long internalNumber) {
		super(new LoginRequest(entity, internalNumber));
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendLikeFilter("principal", getForm().getPrincipal(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "internalNumber";
	}

}
