package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.filter.form.KeyTypeForm;

/**
 * Key type form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class KeyTypeFormFilterAdapter extends AbstractRootFilterAdapter<KeyTypeForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public KeyTypeFormFilterAdapter(KeyTypeForm form) {
		super(form);
	}

	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getKeyCode()!=null && getForm().getKeyCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("keyCode", getForm().getKeyCode(), mainCriteriaBuilder);		
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { 
		appendLikeFilter("keyName", getForm().getKeyName(), mainCriteriaBuilder);
	}
	
}
