package org.helianto.inventory.filter;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractFilterAdapter;
import org.helianto.inventory.Tax;

/**
 * Tax filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class TaxFilterAdapter extends AbstractFilterAdapter<Tax> {

	private static final long serialVersionUID = 1L;
	private String keyCode;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public TaxFilterAdapter(Tax filter) {
		super(filter);
		setKeyCode("");
	}
	
	/**
	 * Key code constructor.
	 * 
	 * @param keyCode
	 * @param filter
	 */
	public TaxFilterAdapter(String keyCode, Tax filter) {
		super(filter);
		setKeyCode(keyCode);
	}
	
	@Override
	public boolean isSelection() {
		return getFilter().getKeyType()!=null;
	}

	public void reset() { }
	
	@Override
	public void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getFilter().getProcessAgreement()!=null) {
			appendEqualFilter("processAgreement.id", getFilter().getProcessAgreement().getId(), mainCriteriaBuilder);
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("keyType.id", getFilter().getKeyType().getId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("keyType.keyCode", getKeyCode(), mainCriteriaBuilder);
	}
	
	/**
	 * Key code filter.
	 */
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

}
