package org.helianto.core.form;

import org.helianto.core.def.NavigationMode;
import org.helianto.core.domain.type.NavigableEntity;


/**
 * Class form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NavigableForm extends NavigableEntity {
	
    /**
     * Navigation mode.
     */
	public NavigationMode getNavigationMode();
	
	/**
	 * Navigation mode is a R/W property.
	 * 
	 * @param navigationMode
	 */
	public void setNavigationMode(NavigationMode navigationMode);
	

}
