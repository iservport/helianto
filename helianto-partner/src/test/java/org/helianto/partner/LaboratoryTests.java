package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.partner.domain.Laboratory;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.junit.Test;

/**
 * <code>Laboratory</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LaboratoryTests{
    
    /**
     * Test <code>Laboratory</code> equals() method.
     */
	@Test
    public void laboratoryEquals() {
		Entity entity = new Entity(new Operator("DEFAULT"));
        PrivateEntity partnerRegistry = new PrivateEntity(entity, "TEST");
        
        Laboratory laboratory = new Laboratory();
        Laboratory other = new Laboratory();
        
        assertTrue(laboratory.equals(other));
        
        laboratory.setPrivateEntity(partnerRegistry);
        assertFalse(laboratory.equals(other));
        other.setPrivateEntity(partnerRegistry);
        assertTrue(laboratory.equals(other));
        assertEquals(laboratory.hashCode(), other.hashCode());
        assertFalse(laboratory.equals(new Partner(partnerRegistry)));
    }

}
    
    
