package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.Manufacturer;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>ManufacturerDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ManufacturerTestSupport {

    /**
     * Test support method to create a <code>Manufacturer</code>.
     * @param partnerRegistry optional PartnerRegistry 
     */
    public static Manufacturer createManufacturer(Object... args) {
        PartnerRegistry partnerRegistry;
        try {
            partnerRegistry = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        Manufacturer manufacturer = Manufacturer.manufacturerFactory(partnerRegistry);
        return manufacturer;
    }

    /**
     * Test support method to create a <code>Manufacturer</code> list.
     *
     * @param manufacturerListSize
     */
    public static List<Manufacturer> createManufacturerList(int manufacturerListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(manufacturerListSize);

        return createManufacturerList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Manufacturer</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Manufacturer> createManufacturerList(List<PartnerRegistry> partnerRegistryList) {
        List<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
    	    manufacturerList.add(createManufacturer(partnerRegistry));
        }
        return manufacturerList;
    }

}
