package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Customer</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CustomerTests {
    
	@Test
	public void constructor() {
		Customer customer = new Customer();
		assertTrue(customer instanceof Partner);
	}
    
	@Test
	public void constructorPartnerRegistry() {
		PartnerRegistry partnerRegistry = new PartnerRegistry();
		Customer customer = new Customer(partnerRegistry);
		assertTrue(customer instanceof Partner);
		assertEquals(customer.getPartnerRegistry(), partnerRegistry);
	}
    
	@Test
	public void constructorEntity() {
		Entity entity = new Entity();
		Customer customer = new Customer(entity);
		assertTrue(customer instanceof Partner);
		assertEquals(customer.getPartnerRegistry().getEntity(), entity);
	}
    
    /**
     * Test <code>Customer</code> equals() method.
     */
	@Test
    public void customerEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Customer customer = new Customer(partnerRegistry);
        Customer copy = (Customer) DomainTestSupport.minimalEqualsTest(customer);
        
        copy.setPartnerRegistry(null);
        assertFalse(customer.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(customer.equals(copy));
    }

}
    
    
