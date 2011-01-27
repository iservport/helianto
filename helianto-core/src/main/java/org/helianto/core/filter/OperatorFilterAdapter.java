package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Operator filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class OperatorFilterAdapter extends AbstractFilterAdapter<Operator> {

	private static final long serialVersionUID = 1L;
	private boolean uniqueName = true;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public OperatorFilterAdapter(Operator filter) {
		super(filter);
	}

	/**
	 * Key constructor.
	 * 
	 * @param operatorName
	 */
	public OperatorFilterAdapter(String operatorName) {
		super(new Operator(operatorName));
	}

	/**
	 * Convenience constructor.
	 * 
	 * @param operatorName
	 * @param unique
	 */
	public OperatorFilterAdapter(String operatorName, boolean unique) {
		super(new Operator(operatorName));
		setUniqueName(unique);
	}

	public void reset() { }
	
	@Override
	public boolean isSelection() {
		return isUniqueName() && getFilter().getOperatorName().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("operatorName", getFilter().getOperatorName(), mainCriteriaBuilder);		
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) { 
		appendLikeFilter("operatorName", getFilter().getOperatorName(), mainCriteriaBuilder);
	}
	
	/**
	 * Uniqueness filter.
	 */
	public boolean isUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(boolean uniqueName) {
		this.uniqueName = uniqueName;
	}

}
