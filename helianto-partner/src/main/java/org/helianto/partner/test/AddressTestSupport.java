package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Class to support <code>Address</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AddressTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Address</code>.
     * @param privateEntity optional PartnerRegistry 
     * @param sequence optional int 
     */
    public static PrivateAddress createAddress(Object... args) {
        PrivateEntity privateEntity;
        try {
            privateEntity = (PrivateEntity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            privateEntity = PrivateEntityTestSupport.createPartnerRegistry();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        PrivateAddress address = new PrivateAddress(privateEntity, sequence);
        return address;
    }
    
    public static void populateAddress(PrivateAddress address) {
    	address.setAddress1("Address1_"+testKey);
    	address.setAddressNumber("#_"+testKey);
    	address.setAddressDetail("##_"+testKey);
    	address.setAddress2("Address2_"+testKey);
    	address.setAddress3("Address3_"+testKey);
    	address.setCityName("cityName"+testKey);
    	address.setPostalCode("ZIP"+testKey);
    }

    /**
     * Test support method to create a <code>Address</code> list.
     *
     * @param addressListSize
     */
    public static List<PrivateAddress> createAddressList(int addressListSize) {
        return createAddressList(addressListSize, 1);
    }

    /**
     * Test support method to create a <code>Address</code> list.
     *
     * @param addressListSize
     * @param partnerAssociationListSize
     */
    public static List<PrivateAddress> createAddressList(int addressListSize, int partnerAssociationListSize) {
        List<PrivateEntity> partnerAssociationList = PrivateEntityTestSupport.createPartnerRegistryList(partnerAssociationListSize);

        return createAddressList(addressListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Address</code> list.
     *
     * @param addressListSize
     * @param partnerAssociationList
     */
    public static List<PrivateAddress> createAddressList(int addressListSize, List<PrivateEntity> partnerAssociationList) {
        List<PrivateAddress> addressList = new ArrayList<PrivateAddress>();
        for (PrivateEntity privateEntity: partnerAssociationList) {
	        for (int i=0;i<addressListSize;i++) {
    	        addressList.add(createAddress(privateEntity));
        	}
        }
        return addressList;
    }

}
