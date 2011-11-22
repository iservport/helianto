package org.helianto.core;

import org.helianto.core.Province;


/**
 * Address location interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AddressLocation {

    /**
     * Postal code.
     */
    public String getPostalCode();

    /**
     * Province or a descendant class like City.
     */
    public Province getProvince();

    /**
     * City name.
     */
    public String getCityName();

}
