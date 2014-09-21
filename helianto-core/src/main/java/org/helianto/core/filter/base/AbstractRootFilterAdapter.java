package org.helianto.core.filter.base;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.type.RootEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filter adapters that require a context form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractRootFilterAdapter<F extends RootEntity> 
	extends AbstractFilterAdapter<F> 
{

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
	 * The context (operator).
	 */
	public Operator getOperator() {
		return getForm().getOperator();
	}

	/**
	 * The context id.
	 */
	public int getContextId() {
		if (getOperator()!=null) {
			return getOperator().getId();
		}
		if (getForm()!=null) {
			return getForm().getContextId();
		}
		return 0;
	}
	
	/**
	 * Restrict selection to a given operator, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (hasOperatorCriterion()) {
			preProcessOperatorFilter(mainCriteriaBuilder);
			connect = true;
		}
		return connect;
	}
	
	public boolean isSelection() {
		return getContextId()>0;
	}
	
	/**
	 * Operator pre-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public void preProcessOperatorFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("operator.id", getContextId(), mainCriteriaBuilder);
		logger.debug("Filter constraint set to {}.", getOperator());
	}
	
	/**
	 * True if there is a segment for operator criterion.
	 */
	protected boolean hasOperatorCriterion() {
		return getContextId()>0;
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(AbstractRootFilterAdapter.class);
	
}
