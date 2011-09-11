package org.helianto.core.filter;

import org.helianto.core.UserGroup;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.filter.form.UserRoleForm;

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

	public void reset() { 
		getForm().reset();
	}
	
	@Override
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getForm().getParentList()!=null && getForm().getParentList().size()>0) {
			mainCriteriaBuilder.appendAnd().appendSegment("userGroup.id", "in");
			int[] parentIdArray = new int[getForm().getParentList().size()];
			int i = 0;
			for (UserGroup parent: getForm().getParentList()) {
				parentIdArray[i++] = parent.getId();
			}
			mainCriteriaBuilder.append(parentIdArray);
		}
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getParent()!=null 
		    && getForm().getService()!=null 
		    && getForm().getServiceExtension()!=null 
		    && getForm().getServiceExtension().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userGroup.id", getForm().getParent().getId(), mainCriteriaBuilder);
		appendEqualFilter("service.id", getForm().getService().getId(), mainCriteriaBuilder);
		appendEqualFilter("serviceExtension", getForm().getServiceExtension(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getParent()!=null && (getForm().getParentList()==null || getForm().getParentList().isEmpty())) {
			appendEqualFilter("userGroup.id", getForm().getParent().getId(), mainCriteriaBuilder);
		}
		if (getForm().getService()!=null) {
			appendEqualFilter("service.id", getForm().getService().getId(), mainCriteriaBuilder);
		}
		appendLikeFilter("serviceExtension", getForm().getServiceExtension(), mainCriteriaBuilder);
		appendEqualFilter("activityState", getForm().getActivityState(), mainCriteriaBuilder);
	}
	
}
