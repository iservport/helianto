package org.helianto.core.domain.type;



/**
 * Address interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AddressEntity extends LocationEntity {

    /**
     * The first segment of an address, usually representing a street, road, etc..
     */
    public String getAddress1();
    
    /**
     * Address classifier (or blank), like St. (street), Rd. (road), etc..
     */
    public String getAddressClassifier();

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
     * Any other address information, if any.
     */
    public String getAddress3();

    /**
     * Convenience to show the address as a short string.
     */
    public String getShortAddress();

}
