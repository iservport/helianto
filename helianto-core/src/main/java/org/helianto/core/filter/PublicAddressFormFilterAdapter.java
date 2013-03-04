package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.form.PublicAddressForm;

/**
 * Public address adapter database filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PublicAddressFormFilterAdapter extends AbstractRootFilterAdapter<PublicAddressForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PublicAddressFormFilterAdapter(PublicAddressForm form) {
		super(form);
	}
	
	/**
	 * True (return a single <code>PostalCodeAddressDatabase<code> instance) if
	 * the posta code filter has content.
	 */
	@Override
	public boolean isSelection() {
		return getForm().getPostalCode()!=null && getForm().getPostalCode().length()>0;
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getProvince()!=null) {
			appendEqualFilter("province.id", getForm().getProvince().getId(), mainCriteriaBuilder);
		}
		else {
			appendEqualFilter("province.provinceCode", getForm().getProvinceCode(), mainCriteriaBuilder);
		}
//		appendLikeFilter("address1", getAddressLike(), mainCriteriaBuilder);
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
		return "province.provinceCode ASC,address1 ASC ";
	}

}
