package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.Customer;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>CustomerDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CustomerTestSupport {

    /**
     * Test support method to create a <code>Customer</code>.
     * @param partnerAssociation optional PartnerAssociation 
     */
    public static Customer createCustomer(Object... args) {
        PartnerRegistry partnerAssociation;
        try {
            partnerAssociation = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        Customer customer = Customer.customerFactory(partnerAssociation);
        return customer;
    }

    /**
     * Test support method to create a <code>Customer</code> list.
     *
     * @param customerListSize
     */
    public static List<Customer> createCustomerList(int customerListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(customerListSize);

        return createCustomerList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Customer</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Customer> createCustomerList(List<PartnerRegistry> partnerRegistryList) {
        List<Customer> customerList = new ArrayList<Customer>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
   	        customerList.add(createCustomer(partnerRegistry));
        }
        return customerList;
    }

}
