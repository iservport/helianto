package org.helianto.core.filter;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.number.Sequenceable;

/**
 * Base class to filters that require both <code>Entity</code> and <code>internalNumber</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractSequenceFilterAdapter <T extends Sequenceable> extends AbstractDateRangeFilterAdapter<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractSequenceFilterAdapter(T filter) {
		super(filter);
	}
	
	@Override
	public boolean isSelection() { return getFilter().getInternalNumber()>0; }
	
	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getFilter().getInternalNumber(), mainCriteriaBuilder);
	}

}
