package org.helianto.inventory.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.inventory.form.TaxForm;

/**
 * Tax filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class TaxFilterAdapter extends AbstractFilterAdapter<TaxForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public TaxFilterAdapter(TaxForm form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getProcessAgreementId()>0 && getForm().getKeyTypeId()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("processAgreement.id", getForm().getProcessAgreementId(), mainCriteriaBuilder);
		appendEqualFilter("keyType.id", getForm().getKeyTypeId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendEqualFilter("keyType.keyCode", getForm().getKeyCode(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "keyCode";
	}
	
}
