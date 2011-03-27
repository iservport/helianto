package org.helianto.core.filter;

import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;

/**
 * User role filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRoleFilterAdapter extends AbstractFilterAdapter<UserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public UserRoleFilterAdapter(UserRole filter) {
		super(filter);
	}

	/**
	 * Key constructor.
	 * 
     * @param user
     * @param service
     * @param serviceExtension
	 */
	public UserRoleFilterAdapter(UserGroup user, Service service, String serviceExtension) {
		super(new UserRole(user, service, serviceExtension));
	}

	public void reset() { }
	
	@Override
	public boolean isSelection() {
		return getFilter().getUserGroup()!=null 
		    && getFilter().getService()!=null 
		    && getFilter().getServiceExtension()!=null 
		    && getFilter().getServiceExtension().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userGroup.id", getFilter().getUserGroup().getId(), mainCriteriaBuilder);
		appendEqualFilter("service.id", getFilter().getService().getId(), mainCriteriaBuilder);
		appendEqualFilter("serviceExtension", getFilter().getServiceExtension(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getFilter().getUserGroup()!=null) {
			appendEqualFilter("userGroup.id", getFilter().getUserGroup().getId(), mainCriteriaBuilder);
		}
		if (getFilter().getService()!=null) {
			appendEqualFilter("service.id", getFilter().getService().getId(), mainCriteriaBuilder);
		}
		appendLikeFilter("serviceExtension", getFilter().getServiceExtension(), mainCriteriaBuilder);
	}

}
