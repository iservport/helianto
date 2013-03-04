package org.helianto.user.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.user.form.UserRoleForm;

/**
 * User role filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRoleFormFilterAdapter extends AbstractFilterAdapter<UserRoleForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public UserRoleFormFilterAdapter(UserRoleForm filter) {
		super(filter);
	}

	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (hasUserGroupParentIdArray()) {
			mainCriteriaBuilder.appendAnd().appendSegment("userGroup.id", "in");
			mainCriteriaBuilder.append(getForm().getUserGroupParentIdArray());
			connect = true;
		}
		return connect;
	}
	
	/**
	 * Convenient to check if there is a non-empty parent user group id array.
	 */
	protected boolean hasUserGroupParentIdArray() {
		return getForm().getUserGroupParentIdArray()!=null && getForm().getUserGroupParentIdArray().length>0;
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getUserGroupParentId()>0 
		    && getForm().getServiceId()>0 
		    && getForm().getServiceExtension()!=null 
		    && getForm().getServiceExtension().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userGroup.id", getForm().getUserGroupParentId(), mainCriteriaBuilder);
		appendEqualFilter("service.id", getForm().getServiceId(), mainCriteriaBuilder);
		appendEqualFilter("serviceExtension", getForm().getServiceExtension(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (!hasUserGroupParentIdArray()) {
			appendEqualFilter("userGroup.id", getForm().getUserGroupParentId(), mainCriteriaBuilder);
		}
		appendEqualFilter("service.id", getForm().getServiceId(), mainCriteriaBuilder);
		appendLikeFilter("serviceExtension", getForm().getServiceExtension(), mainCriteriaBuilder);
		appendEqualFilter("activityState", getForm().getActivityState(), mainCriteriaBuilder);
	}
	
}
