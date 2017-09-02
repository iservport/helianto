package org.helianto.classic.install;


import org.helianto.core.domain.City;

/**
 * State and city installer interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CityInstaller {

	/**
	 * Do install.
	 * 
	 * @param contextName
	 * @param countryCode
	 */
	City installStatesAndCities(String contextName, String countryCode);
	
}
