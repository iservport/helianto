package org.helianto.core.filter.base;

import org.helianto.core.Entity;
import org.helianto.core.TrunkEntity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filter adapters that require an <code>Entity</code> form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractTrunkFilterAdapter <F extends TrunkEntity> extends AbstractFilterAdapter<F> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param form
	 */
	public AbstractTrunkFilterAdapter(F form) {
		super(form);
	}
	
	/**
	 * The entity.
	 */
	public Entity getEntity() {
		return getForm().getEntity();
	}
	
	/**
	 * Restrict selection to a given entity, if any. 
	 */
	@Override
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
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
	protected void appendEntityFilter(Entity entity, OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("entity.id", "=").append(entity.getId());
    }
    
    private static Logger logger = LoggerFactory.getLogger(AbstractTrunkFilterAdapter.class);

}
