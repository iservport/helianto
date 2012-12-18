package org.helianto.partner.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.junit.Test;

/**
 * <code>Partner</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerTests {
    
    /**
     * Test <code>Partner</code> equals() method.
     */
	@Test
    public void testPartnerEquals() {
		Entity entity = new Entity(new Operator("DEFAULT"));
        PrivateEntity2 partnerRegistry = new PrivateEntity2(entity, "TEST");
        
        Partner partner = new Partner();
        Partner other = new Partner();
        
        assertTrue(partner.equals(other));
        
        partner.setPrivateEntity(partnerRegistry);
        assertFalse(partner.equals(other));
        other.setPrivateEntity(partnerRegistry);
        assertTrue(partner.equals(other));
    }
	
	@Test
	public void privateEntity() {
		// private entity is never null
		Partner partner = new Partner();
		assertNotNull(partner.getPrivateEntity());
    }
	
	@Test
	public void newEntity() {
		// by default, a new privateEntity is NOT created
		Entity entity = new Entity();
		Partner partner = new Partner();
		assertFalse(partner.isPrivateEntityValid());
		assertFalse(partner.isNewPrivateEntityRequested(entity));
		// a transient newEntity not empty should trigger a new privateEntity creation
		partner.setNewEntityAlias("TEST");
		assertTrue(partner.isNewPrivateEntityRequested(entity));
		assertTrue(partner.isPrivateEntityValid());
		assertEquals(entity, partner.getEntity());
		assertEquals("TEST", partner.getEntityAlias());
	}

}
    
    
