package org.helianto.core.form;

import org.helianto.core.domain.type.RootEntity;

/**
 * Entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityForm extends RootEntity {
	
	/**
	 * Entity alias.
	 */
	String getEntityAlias();

	/**
	 * Entity alias like filter.
	 */
	String getEntityAliasLike();
	
	/**
	 * Activity state filter.
	 */
	char getActivityState();
	
	/**
	 * Nature filter.
	 */
	char getNature();
	
	
}
