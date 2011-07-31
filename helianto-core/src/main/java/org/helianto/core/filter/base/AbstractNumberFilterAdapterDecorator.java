package org.helianto.core.filter.base;

import org.helianto.core.Operator;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.number.Numerable;

/**
 * Base class to filters that require both <code>Operator</code> and <code>internalNumber</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractNumberFilterAdapterDecorator <T extends Numerable> extends AbstractRootFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
	private AbstractFilter decoratedFilter;

	/**
	 * Key constructor.
	 * 
	 * @param operator
	 * @param internalNumber
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	public AbstractNumberFilterAdapterDecorator(final Operator operator, long internalNumber) {
		super((T) new AbstractNumber(operator, internalNumber) {});
	}
	
	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractNumberFilterAdapterDecorator(T filter) {
		super(filter);
	}
	
	/**
	 * Decorator constructor.
	 * 
	 * @param filter
	 * @param decoratedFilter
	 */
	public AbstractNumberFilterAdapterDecorator(T filter, AbstractFilter decoratedFilter) {
		super(filter);
		this.decoratedFilter = decoratedFilter;
	}
	
	@Override
	public boolean isSelection() { return getForm().getPublicNumber()>0; }
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("publicNumber", getForm().getPublicNumber(), mainCriteriaBuilder);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (decoratedFilter!=null) {
			decoratedFilter.doFilter(mainCriteriaBuilder);
		}
	}

}
