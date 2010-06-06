package org.helianto.partner;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Public entity filter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityFilter extends AbstractUserBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;
	private String entityAliasLike;
	
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
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
	}

	public String getObjectAlias() {
		// create expressions like "select publicentity from PublicEntity publicentity where ..."
		return "publicentity";
	}

	public void reset() {
	}

}
