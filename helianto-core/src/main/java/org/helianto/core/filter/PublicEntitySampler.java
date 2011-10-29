package org.helianto.core.filter;

import org.helianto.core.Entity;
import org.helianto.core.PublicEntity;

/**
 * Simple extension to <code>PublicEntity</code> to act as a sample to
 * filter adapters.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public class PublicEntitySampler extends PublicEntity {

	private static final long serialVersionUID = 1L;
	private String entityAlias;
	private String entityAliasLike;
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 */
	public PublicEntitySampler(Entity entity) {
		super(entity);
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

}
