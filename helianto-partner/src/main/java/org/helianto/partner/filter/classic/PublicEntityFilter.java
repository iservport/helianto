package org.helianto.partner.filter.classic;

import org.helianto.core.Operator;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.classic.AbstractOperatorBackedCriteriaFilter;
import org.helianto.partner.PublicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Public entity filter.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public class PublicEntityFilter extends AbstractOperatorBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;
	private Class<? extends PublicEntity> clazz;
	private String entityAlias;
	private String entityAliasLike;
	
	/**
	 * Default constructor.
	 * 
	 * @param operator
	 */
	public PublicEntityFilter(Operator operator) {
		super(operator);
		setOrderByString("entity.alias");
		setEntityAlias("");
		setEntityAliasLike("");
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
	 * Entity alias filter.
	 */
	public String getEntityAlias() {
		return entityAlias;
	}
	public void setEntityAlias(String entityAlias) {
		this.entityAlias = entityAlias;
	}

	/**
	 * Entity alias filter.
	 */
	public String getEntityAliasLike() {
		return entityAliasLike;
	}
	public void setEntityAliasLike(String entityAliasLike) {
		this.entityAliasLike = entityAliasLike;
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
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("entity.alias", getEntityAliasLike(), mainCriteriaBuilder);
	}
	
	@Override
	public boolean isSelection() {
		return getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.alias", getEntityAlias(), mainCriteriaBuilder);
	}

	public void reset() {
	}

	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFilter.class);
	
}
