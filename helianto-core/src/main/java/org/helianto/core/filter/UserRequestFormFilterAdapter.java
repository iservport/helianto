package org.helianto.core.filter;

import org.helianto.core.Entity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractControlFilterAdapter;
import org.helianto.core.filter.form.UserRequestForm;

/**
 * Login request filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRequestFormFilterAdapter extends AbstractControlFilterAdapter<UserRequestForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public UserRequestFormFilterAdapter(UserRequestForm form) {
		super(form);
	}
	
	/**
	 * The entity.
	 */
	public Entity getEntity() {
		if (getForm().getUserGroup()!=null) {
			return getForm().getUserGroup().getEntity();
		}
		return super.getEntity();
	}
	
	@Override
	protected void appendEntityFilter(Entity entity, OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("userGroup.entity.id", "=").append(entity.getId());
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getUserGroup()!=null 
				&& getForm().getUserGroup().getId()>0
				&& getForm().getInternalNumber()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userGroup.id", getForm().getUserGroup().getId(), mainCriteriaBuilder);
		appendEqualFilter("internalNumber", getForm().getInternalNumber(), mainCriteriaBuilder);
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
