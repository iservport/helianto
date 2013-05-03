package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractPersonalFilterAdapter;
import org.helianto.core.form.PersonalAddressForm;

/**
 * Personal address form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PersonalAddressFormFilterAdapter extends AbstractPersonalFilterAdapter<PersonalAddressForm> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PersonalAddressFormFilterAdapter(PersonalAddressForm form) {
		super(form);
	}

	@Override
	public boolean isSelection() {
		return getForm().getIdentityId()>0 && getForm().getAddressType()!=' ';
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("addressType", getForm().getAddressType(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendLikeFilter("province.provinceName", getForm().getCityName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "addressType";
	}
}
