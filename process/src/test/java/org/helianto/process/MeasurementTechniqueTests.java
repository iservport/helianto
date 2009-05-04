package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Unit;
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
    	Unit unit = new Unit();

    	MeasurementTechnique measurementTechnique = MeasurementTechnique.measurementTechniqueFactory(unit, "CODE");

        assertTrue(measurementTechnique instanceof MeasurementTechnique);
		assertSame(unit, measurementTechnique.getUnit());
        assertEquals("CODE", measurementTechnique.getMeasurementTechniqueCode());
        
    }
    
    /**
     * Test <code>MeasurementTechnique</code> equals() method.
     */
    public void testMeasurementTechniqueEquals() {
    	Unit unit = new Unit();

    	MeasurementTechnique measurementTechnique = MeasurementTechnique.measurementTechniqueFactory(unit, "CODE");

    	MeasurementTechnique copy = (MeasurementTechnique) DomainTestSupport.minimalEqualsTest(measurementTechnique);
        
        copy.setUnit(null);
        copy.setMeasurementTechniqueCode("CODE");
        assertFalse(measurementTechnique.equals(copy));

        copy.setUnit(unit);
        copy.setMeasurementTechniqueCode("");
        assertFalse(measurementTechnique.equals(copy));

        copy.setUnit(unit);
        copy.setMeasurementTechniqueCode("CODE");

        assertTrue(measurementTechnique.equals(copy));
    }
    
}
