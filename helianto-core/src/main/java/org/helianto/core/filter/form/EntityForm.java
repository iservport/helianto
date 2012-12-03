package org.helianto.core.filter.form;

import org.helianto.core.Resettable;
import org.helianto.core.domain.type.RootEntity;

/**
 * Entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityForm extends RootEntity, Resettable {
	
	/**
	 * Entity alias.
	 */
	public String getEntityAlias();

	/**
	 * Entity alias like filter.
	 */
	public String getEntityAliasLike();
	
	
}
