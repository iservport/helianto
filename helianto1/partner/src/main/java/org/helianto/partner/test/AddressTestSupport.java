package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Address;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>AddressDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AddressTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Address</code>.
     * @param partnerRegistry optional PartnerRegistry 
     * @param sequence optional int 
     */
    public static Address createAddress(Object... args) {
        PartnerRegistry partnerAssociation;
        try {
            partnerAssociation = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Address address = Address.addressFactory(partnerAssociation, sequence);
        return address;
    }

    /**
     * Test support method to create a <code>Address</code> list.
     *
     * @param addressListSize
     */
    public static List<Address> createAddressList(int addressListSize) {
        return createAddressList(addressListSize, 1);
    }

    /**
     * Test support method to create a <code>Address</code> list.
     *
     * @param addressListSize
     * @param partnerAssociationListSize
     */
    public static List<Address> createAddressList(int addressListSize, int partnerAssociationListSize) {
        List<PartnerRegistry> partnerAssociationList = PartnerRegistryTestSupport.createPartnerRegistryList(partnerAssociationListSize);

        return createAddressList(addressListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Address</code> list.
     *
     * @param addressListSize
     * @param partnerAssociationList
     */
    public static List<Address> createAddressList(int addressListSize, List<PartnerRegistry> partnerAssociationList) {
        List<Address> addressList = new ArrayList<Address>();
        for (PartnerRegistry partnerRegistry: partnerAssociationList) {
	        for (int i=0;i<addressListSize;i++) {
    	        addressList.add(createAddress(partnerRegistry));
        	}
        }
        return addressList;
    }

}
