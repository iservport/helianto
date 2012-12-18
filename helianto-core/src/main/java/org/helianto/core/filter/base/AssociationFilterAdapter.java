package org.helianto.core.filter.base;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.form.AssociationForm;

/**
 * Association form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class AssociationFilterAdapter extends AbstractFilterAdapter<AssociationForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public AssociationFilterAdapter(AssociationForm form) {
		super(form);
	}
	
	/**
	 * Sublcasses must override to give the parent field a name different than "parent.id".
	 */
	protected String getParentFieldId() {
		return "parent.id";
	}
	
	/**
	 * Sublcasses must override to give the parent field a name different than "parent.id".
	 */
	protected String getChildFieldId() {
		return "child.id";
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getParentId()>0 && getForm().getChildId()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter(getParentFieldId(), getForm().getParentId(), mainCriteriaBuilder);
		appendEqualFilter(getChildFieldId(), getForm().getChildId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter(getParentFieldId(), getForm().getParentId(), mainCriteriaBuilder);
		appendEqualFilter(getChildFieldId(), getForm().getChildId(), mainCriteriaBuilder);
	}

}
