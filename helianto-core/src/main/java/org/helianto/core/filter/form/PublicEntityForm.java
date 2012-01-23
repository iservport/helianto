package org.helianto.core.filter.form;

import org.helianto.core.PublicEntity2;
import org.helianto.core.Resettable;
import org.helianto.core.RootEntity;
import org.helianto.core.TrunkEntity;

/**
 * Public entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityForm extends ClassForm<PublicEntity2>, TrunkEntity, RootEntity, Resettable {
	
	/**
	 * Entity name.
	 */
	public String getEntityName();
	
	/**
	 * Public entity type.
	 */
	public char getPublicEntityType();

}
