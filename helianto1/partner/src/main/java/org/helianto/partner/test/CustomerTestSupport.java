package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Customer;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.test.PartnerAssociationTestSupport;

/**
 * Class to support <code>CustomerDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CustomerTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Customer</code>.
     * @param partnerAssociation optional PartnerAssociation 
     * @param sequence optional int 
     */
    public static Customer createCustomer(Object... args) {
        PartnerRegistry partnerAssociation;
        try {
            partnerAssociation = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Customer customer = Customer.customerFactory(partnerAssociation, sequence);
        return customer;
    }

    /**
     * Test support method to create a <code>Customer</code> list.
     *
     * @param customerListSize
     */
    public static List<Customer> createCustomerList(int customerListSize) {
        return createCustomerList(customerListSize, 1);
    }

    /**
     * Test support method to create a <code>Customer</code> list.
     *
     * @param customerListSize
     * @param partnerAssociationListSize
     */
    public static List<Customer> createCustomerList(int customerListSize, int partnerAssociationListSize) {
        List<PartnerRegistry> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize);

        return createCustomerList(customerListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Customer</code> list.
     *
     * @param customerListSize
     * @param partnerAssociationList
     */
    public static List<Customer> createCustomerList(int customerListSize, List<PartnerRegistry> partnerAssociationList) {
        List<Customer> customerList = new ArrayList<Customer>();
        for (PartnerRegistry partnerRegistry: partnerAssociationList) {
	        for (int i=0;i<customerListSize;i++) {
    	        customerList.add(createCustomer(partnerRegistry));
        	}
        }
        return customerList;
    }

}
