package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;

import org.helianto.partner.PartnerAssociation;

/**
 * <code>PartnerAssociation</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationTests extends TestCase {
    
    /**
     * Test <code>PartnerAssociation</code> static factory method.
     */
    public void testPartnerAssociationFactory() {
        Entity entity = new Entity();
        String partnerAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PartnerAssociation partnerAssociation = PartnerAssociation.partnerAssociationFactory(entity, partnerAlias);
        
        assertSame(entity, partnerAssociation.getEntity());
        assertEquals(partnerAlias, partnerAssociation.getPartnerAlias());
        
    }
    
    /**
     * Test <code>PartnerAssociation</code> equals() method.
     */
    public void testPartnerAssociationEquals() {
        Entity entity = new Entity();
        String partnerAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PartnerAssociation partnerAssociation = PartnerAssociation.partnerAssociationFactory(entity, partnerAlias);
        PartnerAssociation copy = (PartnerAssociation) DomainTestSupport.minimalEqualsTest(partnerAssociation);
        
        copy.setEntity(null);
        copy.setPartnerAlias(partnerAlias);
        assertFalse(partnerAssociation.equals(copy));

        copy.setEntity(entity);
        copy.setPartnerAlias(null);
        assertFalse(partnerAssociation.equals(copy));

        copy.setEntity(entity);
        copy.setPartnerAlias(partnerAlias);

        assertTrue(partnerAssociation.equals(copy));
    }

}
    
    
