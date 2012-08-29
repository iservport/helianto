package org.helianto.message.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.filter.AbstractEventFilterAdapter;
import org.helianto.message.form.NotificationEventForm;

/**
 * Notification event form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class NotificationEventFormFilterAdapter 

	extends AbstractEventFilterAdapter<NotificationEventForm> 

{

	private static final long serialVersionUID = 1L;

	/**
	 * Required constructor.
	 * 
	 * @param form
	 */
	public NotificationEventFormFilterAdapter(NotificationEventForm form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getInternalNumber()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getForm().getInternalNumber(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "issueDate DESC";
	}

}
