package org.helianto.core.filter;

import org.helianto.core.Identity;
import org.helianto.core.PersonalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require an <code>Identity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractPersonalFilterAdapter <T extends PersonalEntity> extends AbstractFilterAdapter<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractPersonalFilterAdapter(T filter) {
		super(filter);
	}
	
	/**
	 * The identity.
	 */
	public Identity getIdentity() {
		return getFilter().getIdentity();
	}

	/**
	 * Restrict selection to a given identity, if any. 
	 */
	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getIdentity()!=null) {
			appendEqualFilter("identity.id", getIdentity().getId(), mainCriteriaBuilder);
			logger.debug("Filter constraint set to {}.", getIdentity());
		}
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(AbstractPersonalFilterAdapter.class);
	
}
