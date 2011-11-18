package org.helianto.core.filter.form;

import org.helianto.core.Navigable;
import org.helianto.core.def.NavigationMode;


/**
 * Class form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NavigableForm extends Navigable {
	
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
