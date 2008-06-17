package org.helianto.partner.orm;

import java.util.List;

import org.helianto.partner.Customer;
import org.helianto.partner.dao.CustomerDao;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.test.CustomerTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>CustomerDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class CustomerDaoImplTests extends AbstractPartnerDaoImplConfig {

    private CustomerDao customerDao;
    private PartnerDao partnerDao;
    
    /*
     * Hook to persist one <code>Customer</code>.
     */  
    protected Customer writeCustomer() {
        Customer customer = CustomerTestSupport.createCustomer();
        partnerDao.persistPartner(customer);
        partnerDao.flush();
        partnerDao.clear();
        return customer;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneCustomer() {
        Customer customer = writeCustomer();

        assertEquals(customer,  customerDao.findCustomerByNaturalId(customer.getPartnerRegistry()));
    }
    
    /*
     * Hook to persist a <code>Customer</code> list.
     */  
    protected List<Customer> writeCustomerList() {
        int customerListSize = 10;
        List<Customer> customerList = CustomerTestSupport.createCustomerList(customerListSize);
        assertEquals(customerListSize, customerList.size());
        for (Customer customer: customerList) {
            partnerDao.persistPartner(customer);
        }
        partnerDao.flush();
        partnerDao.clear();
        return customerList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListCustomer() {
        List<Customer> customerList = writeCustomerList();

        Customer customer = customerList.get((int) (Math.random()*customerList.size()));
        assertEquals(customer,  customerDao.findCustomerByNaturalId(customer.getPartnerRegistry()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testCustomerDuplicate() {
        Customer customer =  writeCustomer();
        Customer customerCopy = CustomerTestSupport.createCustomer(customer.getPartnerRegistry());

        try {
            partnerDao.mergePartner(customerCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveCustomer() {
        List<Customer> customerList = writeCustomerList();
        Customer customer = customerList.get((int) (Math.random()*customerList.size()));
        partnerDao.removePartner(customer);

        assertNull(customerDao.findCustomerByNaturalId(customer.getPartnerRegistry()));
    }

    //- setters

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}

