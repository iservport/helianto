package org.helianto.core;


/**
 * Address interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Address 
	extends AddressLocation {

    /**
     * The first segment of an address, usually representing a street, road, etc..
     */
    String getAddress1();
    
    /**
     * Address classifier (or blank), like St. (street), Rd. (road), etc..
     */
    String getAddressClassifier();

    /**
     * Optional address street number.
     */
    String getAddressNumber();

    /**
     * Optional address street number detail, like room number, etc..
     */
	String getAddressDetail();

    /**
     * Address segment to show region, county, etc..
     */
    String getAddress2();

    /**
     * Any other address information, if any.
     */
    String getAddress3();

    /**
     * Convenience to show the address as a short string.
     */
    String getShortAddress();

}
