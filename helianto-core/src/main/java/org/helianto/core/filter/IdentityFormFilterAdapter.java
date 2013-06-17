package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.form.IdentityForm;

/**
 * Identity form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFormFilterAdapter extends AbstractFilterAdapter<IdentityForm>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public IdentityFormFilterAdapter(IdentityForm form) {
		super(form);
	}

	public void reset() {
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getPrincipal()!=null && getForm().getPrincipal().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualLessCaseFilter("principal", getForm().getPrincipal(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("personalData.firstName", getForm().getFirstName(), mainCriteriaBuilder);
		appendLikeFilter("personalData.lastName", getForm().getLastName(), mainCriteriaBuilder);
		appendEqualFilter("personalData.gender", getForm().getGender(), mainCriteriaBuilder);
		appendEqualFilter("identityType", getForm().getIdentityType(), mainCriteriaBuilder);
		appendEqualFilter("notification", getForm().getNotification(), mainCriteriaBuilder);
	}
	
	@Override
	public boolean isSearch() {
		return getForm().getSearchString()!=null 
				&& getForm().getSearchString().length()>0
				&& getForm().getSearchMode()!=' ';
	}
	
	@Override
	protected String[] getFieldNames() {
		if (getForm().getSearchString().contains("@")) {
			return new String[] { "principal" };
		}
		return new String[] { "principal", "displayName", "personalData.firstName", "personalData.lastName" };
	}
	
}
