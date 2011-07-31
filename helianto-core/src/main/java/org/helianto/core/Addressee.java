package org.helianto.core;


/**
 * Classes implementing this interface represent an address.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Addressee {

    /**
     * The first segment of an address, usually representing a street, road, etc..
     */
    public String getAddress1();

    /**
     * Optional address street number.
     */
    public String getAddressNumber();

    /**
     * Optional address street number detail, like room number, etc..
     */
	public String getAddressDetail();

    /**
     * Address segment to show region, county, etc..
     */
    public String getAddress2();

    /**
     * Post office box, if any.
     */
    public String getPostOfficeBox();
    
    /**
     * Postal code.
     */
    public String getPostalCode();

    /**
     * Any other address information, if any.
     */
    public String getAddress3();

    /**
     * Province or a descendant class like City.
     */
    public Province getProvince();

    /**
     * City name.
     */
    public String getCityName();

    /**
     * Convenience to show the address as a short string.
     */
    public String getShortAddress();

}
