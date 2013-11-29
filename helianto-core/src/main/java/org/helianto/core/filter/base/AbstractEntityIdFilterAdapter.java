package org.helianto.core.filter.base;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.form.EntityIdForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filter adapters that require an <code>EntityIdForm</code>.
 * 
 * <p>
 * This base class is the preferred way to create filters to constrain result 
 * sets according to a given entity. Only the entity id must be provided through 
 * an <code>EntityIdForm</code>. In addition, if the class being filtered does not
 * have a direct reference to an entity, a different constraint string may be provided
 * using the method {@link #getEntityConstraint()}, as in "someParent.entity.id".
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEntityIdFilterAdapter <F extends EntityIdForm> 
	extends AbstractFilterAdapter<F> 
{

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param form
	 */
	public AbstractEntityIdFilterAdapter(F form) {
		super(form);
	}
	
	/**
	 * The entity id.
	 */
	public int getEntityId() {
		if (getForm()!=null) {
			return getForm().getEntityId();
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
     * @param entityId
	 * @param mainCriteriaBuilder
     */
	protected void appendEntityFilter(int entityId, OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment(getEntityConstraint(), "=").append(entityId);
    }
    
    /**
     * <code>Entity</code> constraint used by {@link #appendEntityFilter(int, OrmCriteriaBuilder)}, defaults to "entity.id".
     */
	protected String getEntityConstraint() {
		return "entity.id";
    }
    
    private static Logger logger = LoggerFactory.getLogger(AbstractEntityIdFilterAdapter.class);

}
