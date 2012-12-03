package org.helianto.core.filter.form;

import org.helianto.core.Resettable;
import org.helianto.core.TrunkEntity;
import org.helianto.core.domain.type.RootEntity;

/**
 * Public entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityForm extends TypeForm, TrunkEntity, RootEntity, Resettable {
	
	/**
	 * Entity alias.
	 */
	public String getEntityAlias();
	
	/**
	 * Entity name.
	 */
	public String getEntityName();
	
}
