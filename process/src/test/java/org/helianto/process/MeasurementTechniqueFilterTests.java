package org.helianto.process;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.test.UserTestSupport;
import org.helianto.process.MeasurementTechniqueFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueFilterTests {

    public static String OB = "order by measurementtechnique.measurementTechniqueCode ";
    public static String C0 = "measurementtechnique.entity.id = 0 ";
    public static String C1 = "AND measurementtechnique.measurementTechniqueCode = 'CODE' ";
    public static String C2 = "AND measurementtechnique.measurementTechniqueName = 'NAME' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setMeasurementTechniqueCode("CODE");
        assertEquals(C0+C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterName() {
        filter.setMeasurementTechniqueName("NAME");
        assertEquals(C0+C2+OB, filter.createCriteriaAsString(false));
    }
    
    private MeasurementTechniqueFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = MeasurementTechniqueFilter.measurementTechniqueFilterFactory(user);
    }
}

