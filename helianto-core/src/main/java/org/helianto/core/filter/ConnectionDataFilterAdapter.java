package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.form.ConnectionDataForm;

/**
 * Connection data filter.
 * 
 * @author mauriciofernandesdecastro
 */
public class ConnectionDataFilterAdapter 
	extends AbstractFilterAdapter<ConnectionDataForm> {

	private static final long serialVersionUID = 1L;

	public ConnectionDataFilterAdapter(ConnectionDataForm form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getIdentityId()>0 && getForm().getProviderType()!=null;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		// TODO Auto-generated method stub
		
	}

}
