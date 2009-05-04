package org.helianto.sample;

import org.helianto.core.Entity;

import junit.framework.TestCase;

/**
 * Sample domain test case.
 * Add equals() and hashCode() tests too.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DomainSampleTests extends TestCase {

    public void test() {
        Entity entity = new Entity();
        DomainSample sampleDomain = new DomainSample();
        
        sampleDomain.setId(Integer.MAX_VALUE);
        sampleDomain.setId(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, sampleDomain.getId());

        sampleDomain.setEntity(entity);
        assertSame(entity, sampleDomain.getEntity());
        
        sampleDomain.setaKey("KEY");
        assertEquals("KEY", sampleDomain.getaKey());
        
        sampleDomain.setaProperty(Integer.MAX_VALUE);
        sampleDomain.setaProperty(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, sampleDomain.getaProperty());
    }
    
}
