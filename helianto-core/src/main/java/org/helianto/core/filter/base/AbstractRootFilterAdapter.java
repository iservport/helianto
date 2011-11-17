package org.helianto.core.filter.base;

import org.helianto.core.Operator;
import org.helianto.core.RootEntity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filter adapters that require an <code>Operator</code> form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractRootFilterAdapter<F extends RootEntity> extends AbstractFilterAdapter<F> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param form
	 */
	public AbstractRootFilterAdapter(F form) {
		super(form);
	}
	
	/**
	 * The operator.
	 */
	public Operator getOperator() {
		return getForm().getOperator();
	}

	/**
	 * Restrict selection to a given operator, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getOperator()!=null) {
			appendEqualFilter("operator.id", getOperator().getId(), mainCriteriaBuilder);
			logger.debug("Filter constraint set to {}.", getOperator());
			return true;
		}
		return false;
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(AbstractRootFilterAdapter.class);
	
}
