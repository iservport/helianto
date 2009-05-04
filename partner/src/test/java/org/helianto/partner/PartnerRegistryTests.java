package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;

import org.helianto.partner.PartnerRegistry;

/**
 * <code>PartnerRegistry</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerRegistryTests extends TestCase {
    
    /**
     * Test <code>PartnerRegistry</code> static factory method.
     */
    public void testPartnerRegistryFactory() {
        Entity entity = new Entity();
        String partnerAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PartnerRegistry partnerRegistry = PartnerRegistry.partnerRegistryFactory(entity, partnerAlias);
        
        assertSame(entity, partnerRegistry.getEntity());
        assertEquals(partnerAlias, partnerRegistry.getPartnerAlias());
        
    }
    
    /**
     * Test <code>PartnerRegistry</code> equals() method.
     */
    public void testPartnerRegistryEquals() {
        Entity entity = new Entity();
        String partnerAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PartnerRegistry partnerRegistry = PartnerRegistry.partnerRegistryFactory(entity, partnerAlias);
        PartnerRegistry copy = (PartnerRegistry) DomainTestSupport.minimalEqualsTest(partnerRegistry);
        
        copy.setEntity(null);
        copy.setPartnerAlias(partnerAlias);
        assertFalse(partnerRegistry.equals(copy));

        copy.setEntity(entity);
        copy.setPartnerAlias(null);
        assertFalse(partnerRegistry.equals(copy));

        copy.setEntity(entity);
        copy.setPartnerAlias(partnerAlias);

        assertTrue(partnerRegistry.equals(copy));
    }

}
    
    
