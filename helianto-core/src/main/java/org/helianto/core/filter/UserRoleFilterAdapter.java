package org.helianto.core.filter;

import java.util.List;

import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;

/**
 * User role filter adapter.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 * @see UserRoleFormFilterAdapter
 */
public class UserRoleFilterAdapter extends AbstractFilterAdapter<UserRole> {

	private static final long serialVersionUID = 1L;
	private List<UserGroup> parentList;

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

	public void reset() { 
		getForm().setActivityState(' ');
	}
	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getParentList()!=null && getParentList().size()>0) {
			mainCriteriaBuilder.appendAnd().appendSegment("userGroup.id", "in");
			int[] parentIdArray = new int[getParentList().size()];
			int i = 0;
			for (UserGroup parent: getParentList()) {
				parentIdArray[i++] = parent.getId();
			}
			mainCriteriaBuilder.append(parentIdArray);
			connect = true;
		}
		return connect;
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getUserGroup()!=null 
		    && getForm().getService()!=null 
		    && getForm().getServiceExtension()!=null 
		    && getForm().getServiceExtension().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userGroup.id", getForm().getUserGroup().getId(), mainCriteriaBuilder);
		appendEqualFilter("service.id", getForm().getService().getId(), mainCriteriaBuilder);
		appendEqualFilter("serviceExtension", getForm().getServiceExtension(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getUserGroup()!=null) {
			appendEqualFilter("userGroup.id", getForm().getUserGroup().getId(), mainCriteriaBuilder);
		}
		if (getForm().getService()!=null) {
			appendEqualFilter("service.id", getForm().getService().getId(), mainCriteriaBuilder);
		}
		appendLikeFilter("serviceExtension", getForm().getServiceExtension(), mainCriteriaBuilder);
		appendEqualFilter("activityState", getForm().getActivityState(), mainCriteriaBuilder);
	}
	
	/**
	 * List of parent user groups.
	 */
	public List<UserGroup> getParentList() {
		return parentList;
	}
	public void setParentList(List<UserGroup> parentList) {
		this.parentList = parentList;
	}

}
