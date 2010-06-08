package org.helianto.partner;

import org.helianto.core.Operator;
import org.helianto.core.filter.AbstractOperatorBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Public entity filter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityFilter extends AbstractOperatorBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;
	private String entityAlias;
	private String entityAliasLike;
	
	/**
	 * Default constructor.
	 * 
	 * @param operator
	 */
	public PublicEntityFilter(Operator operator) {
		super(operator);
		setEntityAlias("");
		setEntityAliasLike("");
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

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("entity.alias", getEntityAliasLike(), mainCriteriaBuilder);
		appendOrderBy("entity.alias", mainCriteriaBuilder);
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

}
