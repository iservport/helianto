package org.helianto.inventory.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.inventory.domain.Tax;

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
		return getForm().getKeyType()!=null;
	}

	public void reset() { }
	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getForm().getProcessAgreement()!=null) {
			appendEqualFilter("processAgreement.id", getForm().getProcessAgreement().getId(), mainCriteriaBuilder);
			connect = true;
		}
		return connect;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("keyType.id", getForm().getKeyType().getId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
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
