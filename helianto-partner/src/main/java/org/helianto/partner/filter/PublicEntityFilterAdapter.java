package org.helianto.partner.filter;

import org.helianto.core.Entity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.partner.PublicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Public entity filter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityFilterAdapter extends AbstractRootFilterAdapter<PublicEntitySampler> {

	private static final long serialVersionUID = 1L;
	private Class<? extends PublicEntity> clazz;
	
	/**
	 * Default constructor.
	 * 
	 * @param operator
	 */
	public PublicEntityFilterAdapter(PublicEntitySampler sample) {
		super(sample);
		setOrderByString("entity.alias");
		reset();
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 */
	public PublicEntityFilterAdapter(Entity entity) {
		this(new PublicEntitySampler(entity));
	}
	
	public void reset() {
		getForm().setEntityAlias("");
		getForm().setPublicEntityType(' ');
	}
	
	/**
	 * Type filter.
	 */
	public Class<? extends PublicEntity> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends PublicEntity> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Restrict selection to a given operator, if any. 
	 */
	@Override
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
			logger.debug("Added class {} restriction.", getClazz());
		}
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.alias", getForm().getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.id", getForm().getEntity().getId(), mainCriteriaBuilder);
		appendEqualFilter("publicEntityType", getForm().getPublicEntityType(), mainCriteriaBuilder);
		appendLikeFilter("entityName", getForm().getEntityName(), mainCriteriaBuilder);
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFilterAdapter.class);
	
}
