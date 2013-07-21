package org.helianto.core.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CityTests {

	@Test
    public void provinceEquals() {
        City city = new City();
        assertFalse(city.equals(null));
        
        City other = new City();
        assertTrue(city.equals(other));
        
        Operator context = new Operator("DEFAULT");
        
        city.setContext(context);
        assertFalse(city.equals(other));
        city.setCityCode("TEST");
        assertFalse(city.equals(other));

        other.setContext(context);
        assertFalse(city.equals(other));
        other.setCityCode("TEST");
        assertTrue(city.equals(other));
    }

}
