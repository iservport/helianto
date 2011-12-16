package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.base.AbstractAddress;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;

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
    public static PartnerPhone createPhone(Object... args) {
        AbstractAddress address;
        try {
            address = (PrivateAddress) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            address = AddressTestSupport.createAddress();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        PartnerPhone phone = new PartnerPhone();
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
    public static List<PartnerPhone> createPhoneList(int phoneListSize) {
        return createPhoneList(phoneListSize, 1);
    }

    /**
     * Test support method to create a <code>Phone</code> list.
     *
     * @param phoneListSize
     * @param addressListSize
     */
    public static List<PartnerPhone> createPhoneList(int phoneListSize, int addressListSize) {
        List<PrivateAddress> addressList = AddressTestSupport.createAddressList(addressListSize);

        return createPhoneList(phoneListSize, addressList);
    }

    /**
     * Test support method to create a <code>Phone</code> list.
     *
     * @param phoneListSize
     * @param addressList
     */
    public static List<PartnerPhone> createPhoneList(int phoneListSize, List<PrivateAddress> addressList) {
        List<PartnerPhone> phoneList = new ArrayList<PartnerPhone>();
        for (PrivateAddress address: addressList) {
	        for (int i=0;i<phoneListSize;i++) {
    	        phoneList.add(createPhone(address));
        	}
        }
        return phoneList;
    }

}
