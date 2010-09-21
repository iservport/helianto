package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.Address;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.Phone;

/**
 * Class to support <code>PhoneDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PhoneTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Phone</code>.
     * @param address optional Address 
     * @param sequence optional int 
     */
    public static Phone createPhone(Object... args) {
        AbstractAddress address;
        try {
            address = (Address) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            address = AddressTestSupport.createAddress();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Phone phone = new Phone();
        // TODO review phone
//        phone.setPartnerRegistry((PartnerRegistry) address);
        phone.setSequence(sequence);
        return phone;
    }

    /**
     * Test support method to create a <code>Phone</code> list.
     *
     * @param phoneListSize
     */
    public static List<Phone> createPhoneList(int phoneListSize) {
        return createPhoneList(phoneListSize, 1);
    }

    /**
     * Test support method to create a <code>Phone</code> list.
     *
     * @param phoneListSize
     * @param addressListSize
     */
    public static List<Phone> createPhoneList(int phoneListSize, int addressListSize) {
        List<Address> addressList = AddressTestSupport.createAddressList(addressListSize);

        return createPhoneList(phoneListSize, addressList);
    }

    /**
     * Test support method to create a <code>Phone</code> list.
     *
     * @param phoneListSize
     * @param addressList
     */
    public static List<Phone> createPhoneList(int phoneListSize, List<Address> addressList) {
        List<Phone> phoneList = new ArrayList<Phone>();
        for (Address address: addressList) {
	        for (int i=0;i<phoneListSize;i++) {
    	        phoneList.add(createPhone(address));
        	}
        }
        return phoneList;
    }

}
