package org.helianto.core.domain.type;

import org.helianto.core.domain.Province;


/**
 * Address location interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface LocationEntity {

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
