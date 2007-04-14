package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.Supplier;
import org.helianto.partner.test.PartnerAssociationTestSupport;

/**
 * Class to support <code>SupplierDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SupplierTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Supplier</code>.
     * @param partnerAssociation optional PartnerAssociation 
     * @param sequence optional int 
     */
    public static Supplier createSupplier(Object... args) {
        PartnerAssociation partnerAssociation;
        try {
            partnerAssociation = (PartnerAssociation) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Supplier supplier = Supplier.supplierFactory(partnerAssociation, sequence);
        return supplier;
    }

    /**
     * Test support method to create a <code>Supplier</code> list.
     *
     * @param supplierListSize
     */
    public static List<Supplier> createSupplierList(int supplierListSize) {
        return createSupplierList(supplierListSize, 1);
    }

    /**
     * Test support method to create a <code>Supplier</code> list.
     *
     * @param supplierListSize
     * @param partnerAssociationListSize
     */
    public static List<Supplier> createSupplierList(int supplierListSize, int partnerAssociationListSize) {
        List<PartnerAssociation> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize);

        return createSupplierList(supplierListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Supplier</code> list.
     *
     * @param supplierListSize
     * @param partnerAssociationList
     */
    public static List<Supplier> createSupplierList(int supplierListSize, List<PartnerAssociation> partnerAssociationList) {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        for (PartnerAssociation partnerAssociation: partnerAssociationList) {
	        for (int i=0;i<supplierListSize;i++) {
    	        supplierList.add(createSupplier(partnerAssociation));
        	}
        }
        return supplierList;
    }

}
