package org.helianto.core.filter;

import org.helianto.core.Entity;
import org.helianto.core.TrunkEntity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require an <code>Entity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractTrunkFilterAdapter <T extends TrunkEntity> extends AbstractFilterAdapter<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractTrunkFilterAdapter(T filter) {
		super(filter);
	}
	
	/**
	 * The entity.
	 */
	public Entity getEntity() {
		return getFilter().getEntity();
	}
	
	/**
	 * Restrict selection to a given entity, if any. 
	 */
	@Override
	public void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getEntity()!=null) {
			appendEntityFilter(getEntity(), mainCriteriaBuilder);
			logger.debug("Filter constraint set to {}.", getEntity());
		}
	}
	
    /**
     * <code>Entity</code> filter appender.
     * 
     * @param entity
	 * @param mainCriteriaBuilder
     */
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("entity.id", "=").append(entity.getId());
    }
    
    private static Logger logger = LoggerFactory.getLogger(AbstractTrunkFilterAdapter.class);

}
