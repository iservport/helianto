package org.helianto.core.filter.base;

import org.helianto.core.Identity;
import org.helianto.core.PersonalEntity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require an <code>Identity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractPersonalFilterAdapter <F extends PersonalEntity> extends AbstractFilterAdapter<F> {

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
	 * The identity.
	 */
	public Identity getIdentity() {
		return getForm().getIdentity();
	}

	/**
	 * Restrict selection to a given identity, if any. 
	 */
	@Override
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getIdentity()!=null) {
			appendEqualFilter("identity.id", getIdentity().getId(), mainCriteriaBuilder);
			logger.debug("Filter constraint set to {}.", getIdentity());
		}
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(AbstractPersonalFilterAdapter.class);
	
}
