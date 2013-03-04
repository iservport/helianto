package org.helianto.process;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Unit;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.process.domain.MeasurementTechnique;
import org.junit.Test;


/**
 * <code>MeasurementTechnique</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueTests {
    
    /**
     * Test <code>MeasurementTechnique</code> equals() method.
     */
	@Test
    public void measurementTechniqueEquals() {
    	Unit unit = new Unit();

    	MeasurementTechnique measurementTechnique = new MeasurementTechnique(unit, "CODE");

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
