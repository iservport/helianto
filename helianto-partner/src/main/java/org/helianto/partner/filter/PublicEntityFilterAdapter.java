package org.helianto.partner.filter;

import org.helianto.core.Entity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractRootFilterAdapter;
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
		getFilter().setEntityAlias("");
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
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
			logger.debug("Added class {} restriction.", getClazz());
		}
	}
	
	@Override
	public boolean isSelection() {
		return getFilter().getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.alias", getFilter().getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("entityName", getFilter().getEntityName(), mainCriteriaBuilder);
	}
	
	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFilterAdapter.class);
	
}
