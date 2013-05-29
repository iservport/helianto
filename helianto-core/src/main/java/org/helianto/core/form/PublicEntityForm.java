package org.helianto.core.form;

import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Public entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityForm 
	extends TypeForm
	, TrunkEntity
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
