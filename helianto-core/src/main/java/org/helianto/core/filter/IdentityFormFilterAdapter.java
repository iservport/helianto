package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.filter.form.IdentityForm;

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
		appendNameLikeFilter(mainCriteriaBuilder);
	}
	
	/**
	 * Case insensitive search for name or principal.
	 * 
	 * <p>
	 * The name is split and searched for in fields 'firstName' and 'lastName'
	 * unless it constains '@', when 'principal' used for searching.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void appendNameLikeFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getNameLike()==null) {
			return;
		}
		OrmCriteriaBuilder nameCriteria = new OrmCriteriaBuilder(mainCriteriaBuilder.getAlias());
		String[] names = getForm().getNameLike().split(" ");
		for (String name: names) {
			if (name.contains("@")) {
				nameCriteria.appendOr()
				.appendSegment("personalData.principal", "like", "lower")
				.appendLike(name.trim().toLowerCase());
			}
			else {
				nameCriteria.appendOr()
				.appendSegment("personalData.firstName", "like", "lower")
				.appendLike(name.trim().toLowerCase()).appendOr()
				.appendSegment("personalData.lastName", "like", "lower")
				.appendLike(name.trim().toLowerCase());
			}
		}
		mainCriteriaBuilder.append(nameCriteria);
	}

}
