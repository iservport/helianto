package org.helianto.core.filter.internal;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.InternalNumberForm;

/**
 * Internal number filter base class.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractInternalFilterAdapter<T extends InternalNumberForm> 
	extends AbstractDateFilterAdapter<T>
{

	private static final long serialVersionUID = 1L;

	public AbstractInternalFilterAdapter(T form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getInternalNumber() >0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getForm().getInternalNumber(), mainCriteriaBuilder);
	}

}
