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
public abstract class AbstractTrunkFilterAdapter <F extends TrunkEntity> 

	extends AbstractFilterAdapter<F> 

{

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
	
	@Override
	public boolean isSelection() {
		return getForm().getEntity()!=null && getForm().getEntity().getId()>0;
	}
	
	/**
	 * Restrict selection to a given entity, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getEntity()!=null && !hasParentCriterion()) {
			mainCriteriaBuilder.appendAnd(connect);
			appendEntityFilter(getEntity(), mainCriteriaBuilder);
			logger.debug("Filter constraint set to {}.", getEntity());
			connect = true;
		}
		return connect;
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
