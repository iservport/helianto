package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>MeasurementTechnique</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueTests extends TestCase {
    
    /**
     * Test <code>MeasurementTechnique</code> static factory method.
     */
    public void testMeasurementTechniqueFactoryClass() {
    	Entity entity = new Entity();

    	MeasurementTechnique measurementTechnique = MeasurementTechnique.measurementTechniqueFactory(entity, "CODE");

        assertTrue(measurementTechnique instanceof MeasurementTechnique);
		assertSame(entity, measurementTechnique.getEntity());
        assertEquals("CODE", measurementTechnique.getMeasurementTechniqueCode());
        
    }
    
    /**
     * Test <code>MeasurementTechnique</code> equals() method.
     */
    public void testMeasurementTechniqueEquals() {
    	Entity entity = new Entity();

    	MeasurementTechnique measurementTechnique = MeasurementTechnique.measurementTechniqueFactory(entity, "CODE");

    	MeasurementTechnique copy = (MeasurementTechnique) DomainTestSupport.minimalEqualsTest(measurementTechnique);
        
        copy.setEntity(null);
        copy.setMeasurementTechniqueCode("CODE");
        assertFalse(measurementTechnique.equals(copy));

        copy.setEntity(entity);
        copy.setMeasurementTechniqueCode("");
        assertFalse(measurementTechnique.equals(copy));

        copy.setEntity(entity);
        copy.setMeasurementTechniqueCode("CODE");

        assertTrue(measurementTechnique.equals(copy));
    }
    
}
