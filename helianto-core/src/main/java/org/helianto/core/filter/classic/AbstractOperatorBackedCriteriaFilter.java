package org.helianto.core.filter.classic;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractListFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that requires an <code>Operator</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see AbstractRootFilterAdapter
 */
@SuppressWarnings("serial")
public abstract class AbstractOperatorBackedCriteriaFilter extends AbstractListFilter {
	
    private Operator operator;
    
	/**
	 * Empty constructor.
	 */
	private AbstractOperatorBackedCriteriaFilter() {
		super();
	}
    
	/**
	 * Operator based constructor.
	 * 
	 * @param operator
	 */
	protected AbstractOperatorBackedCriteriaFilter(Operator operator) {
		this();
		setOperator(operator);
	}
    
	/**
	 * Operator alias filter.
	 */
    public Operator getOperator() {
        return operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

	/**
	 * Object alias to be used in query expressions.
	 */
	public String getObjectAlias() {
		String className =  getClass().getSimpleName();
		return className.substring(0, className.indexOf("Filter")).toLowerCase();
	}
	
	/**
	 * Restrict selection to a given operator, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getOperator()!=null) {
			appendEqualFilter("operator.id", getOperator().getId(), mainCriteriaBuilder);
			logger.debug("Will add '{}.operator.id = {}' to the beginning of any filter string", getObjectAlias(), operator.getId());
			return true;
		}
		return false;
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(AbstractOperatorBackedCriteriaFilter.class);
	
}
