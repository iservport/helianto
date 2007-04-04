package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;

import org.helianto.partner.PartnerAssociationFilter;

/**
 * <code>PartnerAssociationFilter</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationFilterTests extends TestCase {
    
    /**
     * Test <code>PartnerAssociationFilter</code> static factory method.
     */
    public void testPartnerAssociationFilterFactory() {
        Entity entity = new Entity();
        String partnerAssociationFilterAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PartnerAssociationFilter partnerAssociationFilter = PartnerAssociationFilter.partnerAssociationFilterFactory(entity, partnerAssociationFilterAlias);
        
        assertSame(entity, partnerAssociationFilter.getEntity());
        assertEquals(partnerAssociationFilterAlias, partnerAssociationFilter.getPartnerAssociationFilterAlias());
        
    }
    
    /**
     * Test <code>PartnerAssociationFilter</code> equals() method.
     */
    public void testPartnerAssociationFilterEquals() {
        Entity entity = new Entity();
        String partnerAssociationFilterAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PartnerAssociationFilter partnerAssociationFilter = PartnerAssociationFilter.partnerAssociationFilterFactory(entity, partnerAssociationFilterAlias);
        PartnerAssociationFilter copy = (PartnerAssociationFilter) DomainTestSupport.minimalEqualsTest(partnerAssociationFilter);
        
        copy.setEntity(null);
        copy.setPartnerAssociationFilterAlias(partnerAssociationFilterAlias);
        assertFalse(partnerAssociationFilter.equals(copy));

        copy.setEntity(entity);
        copy.setPartnerAssociationFilterAlias(null);
        assertFalse(partnerAssociationFilter.equals(copy));

        copy.setEntity(entity);
        copy.setPartnerAssociationFilterAlias(partnerAssociationFilterAlias);

        assertTrue(partnerAssociationFilter.equals(copy));
    }

}
    
    
