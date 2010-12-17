package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.RootEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require an <code>Operator</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractRootFilterAdapter<T extends RootEntity> extends AbstractFilterAdapter<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractRootFilterAdapter(T filter) {
		super(filter);
	}
	
	/**
	 * The operator.
	 */
	public Operator getOperator() {
		return getFilter().getOperator();
	}

	/**
	 * Restrict selection to a given operator, if any. 
	 */
	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getOperator()!=null) {
			appendEqualFilter("operator.id", getOperator().getId(), mainCriteriaBuilder);
			logger.debug("Filter constraint set to {}.", getOperator());
		}
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(AbstractRootFilterAdapter.class);
	
}
