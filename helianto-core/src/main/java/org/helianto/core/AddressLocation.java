package org.helianto.core;

import org.helianto.core.domain.City;


/**
 * Address location interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AddressLocation {

    /**
     * City.
     */
    City getCity();

    /**
     * Postal code.
     */
    String getPostalCode();

}
