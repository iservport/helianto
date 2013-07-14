package org.helianto.core;

import org.helianto.core.domain.Province;


/**
 * Address location interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AddressLocation {

    /**
     * Postal code.
     */
    String getPostalCode();

    /**
     * Province or a descendant class like City.
     */
    Province getProvince();

    /**
     * City name.
     */
    String getCityName();

}
