package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.form.PublicAddressForm;

/**
 * Public address adapter database filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PublicAddressFilterAdapter extends AbstractRootFilterAdapter<PublicAddressForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PublicAddressFilterAdapter(PublicAddressForm form) {
		super(form);
	}
	
	/**
	 * True (return a single <code>PostalCodeAddressDatabase<code> instance) if
	 * the postal code filter has content.
	 */
	@Override
	public boolean isSelection() {
		return super.isSelection() && isEnabled(getForm().getPostalCode());
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("state.id", getForm().getStateId(), mainCriteriaBuilder);
		appendEqualFilter("state.stateCode", getForm().getStateCode(), mainCriteriaBuilder);
		appendEqualFilter("city.cityCode", getForm().getCityCode(), mainCriteriaBuilder);
	}
	
	/**
	 * Avoid wrong creation of polymorphic criterion.
	 */
	@Override
	protected boolean hasPolimorphicCriterion() {
		return false;
	}
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("postalCode", getForm().getPostalCode(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "province.postalCode ASC";
	}

}
