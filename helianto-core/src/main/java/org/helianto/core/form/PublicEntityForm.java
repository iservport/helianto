package org.helianto.core.form;

import org.helianto.core.domain.type.RootEntity;

/**
 * Public entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityForm 
	extends TypeForm
	, EntityIdForm
	, RootEntity
{
	
	/**
	 * Entity alias.
	 */
	public String getEntityAlias();
	
	/**
	 * Entity name.
	 */
	public String getEntityName();
	
}
