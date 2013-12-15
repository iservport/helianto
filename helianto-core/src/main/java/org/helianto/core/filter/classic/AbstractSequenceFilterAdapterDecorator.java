package org.helianto.core.filter.classic;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.base.AbstractFilter;
import org.helianto.core.filter.base.AbstractSequence;
import org.helianto.core.number.Sequenceable;

/**
 * Base class to filters that require both <code>Entity</code> and <code>internalNumber</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractSequenceFilterAdapterDecorator <T extends Sequenceable> 
	extends AbstractDateIntervalFilterAdapter<T> 
{

	private static final long serialVersionUID = 1L;
	private AbstractFilter decoratedFilter;

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	public AbstractSequenceFilterAdapterDecorator(final Entity entity, long internalNumber) {
		super((T) new AbstractSequence(entity, internalNumber) {});
	}
	
	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractSequenceFilterAdapterDecorator(T filter) {
		super(filter);
	}
	
	/**
	 * Decorator constructor.
	 * 
	 * @param filter
	 * @param decoratedFilter
	 */
	public AbstractSequenceFilterAdapterDecorator(T filter, AbstractFilter decoratedFilter) {
		super(filter);
		this.decoratedFilter = decoratedFilter;
	}
	
	@Override
	public boolean isSelection() { return getForm().getInternalNumber()>0; }
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getForm().getInternalNumber(), mainCriteriaBuilder);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		if (decoratedFilter!=null) {
			decoratedFilter.doFilter(mainCriteriaBuilder);
		}
	}

}
