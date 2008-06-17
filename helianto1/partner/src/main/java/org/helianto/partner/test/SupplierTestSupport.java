package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Supplier;

/**
 * Class to support <code>SupplierDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SupplierTestSupport {

    /**
     * Test support method to create a <code>Supplier</code>.
     * @param partnerRegistry optional PartnerRegistry 
     */
    public static Supplier createSupplier(Object... args) {
        PartnerRegistry partnerRegistry;
        try {
            partnerRegistry = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        Supplier supplier = Supplier.supplierFactory(partnerRegistry);
        return supplier;
    }

    /**
     * Test support method to create a <code>Supplier</code> list.
     *
     * @param supplierListSize
     */
    public static List<Supplier> createSupplierList(int supplierListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(supplierListSize);

        return createSupplierList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Supplier</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Supplier> createSupplierList(List<PartnerRegistry> partnerRegistryList) {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
    	    supplierList.add(createSupplier(partnerRegistry));
        }
        return supplierList;
    }

}
