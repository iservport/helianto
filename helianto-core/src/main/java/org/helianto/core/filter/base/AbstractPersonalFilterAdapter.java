package org.helianto.core.filter.base;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.form.IdentityIdForm;

/**
 * Base class to filters that require an <code>Identity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractPersonalFilterAdapter <F extends IdentityIdForm> 
	extends AbstractFilterAdapter<F> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param form
	 */
	public AbstractPersonalFilterAdapter(F form) {
		super(form);
	}
	
	/**
	 * Restrict selection to a given identity, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getIdentityId()>0) {
			appendEqualFilter("identity.id", getForm().getIdentityId(), mainCriteriaBuilder);
			return true;
		}
		return false;
	}
	
}
