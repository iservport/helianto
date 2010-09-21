package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
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
        PrivateEntity partnerRegistry = new PrivateEntity();
        
        Laboratory laboratory = new Laboratory(partnerRegistry);
        Laboratory copy = (Laboratory) DomainTestSupport.minimalEqualsTest(laboratory);
        
        copy.setPrivateEntity(null);
        assertFalse(laboratory.equals(copy));

        copy.setPrivateEntity(partnerRegistry);
        assertTrue(laboratory.equals(copy));
    }

}
    
    
