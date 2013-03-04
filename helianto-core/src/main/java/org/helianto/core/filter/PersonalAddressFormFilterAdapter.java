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

	public void reset() {
		getForm().reset();
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getIdentity()!=null && getForm().getIdentity().getId()>0 && getForm().getAddressType()!=' ';
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("identity.id", getForm().getIdentity().getId(), mainCriteriaBuilder);
		appendEqualFilter("addressType", getForm().getAddressType(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getIdentity()!=null && getForm().getIdentity().getId()>0) {
			appendEqualFilter("identity.id", getForm().getIdentity().getId(), mainCriteriaBuilder);
		}
		appendLikeFilter("province.provinceName", getForm().getCityName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "addressType";
	}
}
