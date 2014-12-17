package org.helianto.partner.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.partner.form.ContactGroupForm;

/**
 * Contact group form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ContactGroupFormFilterAdapter extends AbstractFilterAdapter<ContactGroupForm> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public ContactGroupFormFilterAdapter(ContactGroupForm form) {
		super(form);
	}
	
	/**
	 * Force to filter by class.
	 */
	protected boolean hasPolimorphicCriterion() {
		return true;
	}
	
	@Override
	public void preProcessPolimorphicFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("class", 'C', mainCriteriaBuilder);
		mainCriteriaBuilder.addSegmentCount(1);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.id", getForm().getPrivateEntityId(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "userKey";
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		throw new UnsupportedOperationException();
	}
}
