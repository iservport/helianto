package org.helianto.partner;

import org.helianto.core.Operator;
import org.helianto.core.filter.AbstractOperatorBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Public entity filter.
 * 
 * @author mauriciofernandesdecastro
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
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
			logger.debug("Added class {} restriction.", getClazz());
		}
	}
	
	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("entity.alias", getEntityAliasLike(), mainCriteriaBuilder);
	}
	
	@Override
	public boolean isSelection() {
		return getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity.alias", getEntityAlias(), mainCriteriaBuilder);
	}

	public String getObjectAlias() {
		// create expressions like "select publicentity from PublicEntity publicentity where ..."
		return "publicentity";
	}

	public void reset() {
	}

	private static final Logger logger  = LoggerFactory.getLogger(PublicEntityFilter.class);
	
}
