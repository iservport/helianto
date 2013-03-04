package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.core.form.PrivateSequenceForm;

public class PrivateSequenceFilterAdapter 
	extends AbstractTrunkFilterAdapter<PrivateSequenceForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PrivateSequenceFilterAdapter(PrivateSequenceForm form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getTypeName()!=null && getForm().getTypeName().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("typeName", getForm().getTypeName(), mainCriteriaBuilder);
		
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		// nop
	}
	
	@Override
	protected String[] getFieldNames() {
		return new String[] { "typeName" };
	}
	
	@Override
	public String getOrderByString() {
		return "typeName";
	}

}
