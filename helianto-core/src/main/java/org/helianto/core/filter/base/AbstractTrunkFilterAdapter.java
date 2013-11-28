package org.helianto.core.filter.base;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.TrunkEntity;
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
	 * 
	 * @deprecated
	 * @see #getEntityId()
	 */
	public Entity getEntity() {
		return getForm().getEntity();
	}
	
	/**
	 * The entity id.
	 */
	public int getEntityId() {
		if (getForm()!=null && getForm().getEntity()!=null) {
			return getForm().getEntity().getId();
		}
		return 0;
	}
	
	@Override
	public boolean isSelection() {
		return getEntityId()>0;
	}
	
	/**
	 * Restrict selection to a given entity, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getEntityId()>0 && !hasParentCriterion()) {
			mainCriteriaBuilder.appendAnd(connect);
			appendEntityFilter(getEntityId(), mainCriteriaBuilder);
			logger.debug("Filter constraint entity.id set to {}.", getEntityId());
			connect = true;
		}
		return connect;
	}
	
    /**
     * <code>Entity</code> filter appender.
     * 
     * @param entity
	 * @param mainCriteriaBuilder
	 * @deprecated
     */
	protected void appendEntityFilter(Entity entity, OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEntityFilter(entity.getId(), mainCriteriaBuilder);
    }
    
    /**
     * <code>Entity</code> filter appender.
     * 
     * @param entityId
	 * @param mainCriteriaBuilder
     */
	protected void appendEntityFilter(int entityId, OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("entity.id", "=").append(entityId);
    }
    
    private static Logger logger = LoggerFactory.getLogger(AbstractTrunkFilterAdapter.class);

}
