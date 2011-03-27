package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;

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
	 * @param form
	 */
	public OperatorFilterAdapter(Operator form) {
		super(form);
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
		return isUniqueName() && getForm().getOperatorName().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("operatorName", getForm().getOperatorName(), mainCriteriaBuilder);		
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) { 
		appendLikeFilter("operatorName", getForm().getOperatorName(), mainCriteriaBuilder);
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
