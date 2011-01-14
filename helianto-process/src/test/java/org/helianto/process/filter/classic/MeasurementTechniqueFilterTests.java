package org.helianto.process.filter.classic;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.test.UserTestSupport;
import org.helianto.process.filter.classic.MeasurementTechniqueFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueFilterTests {

    public static String OB = "order by alias.measurementTechniqueCode ";
    public static String C0 = "alias.entity.id = 0 ";
    public static String C1 = "AND alias.measurementTechniqueCode = 'CODE' ";
    public static String C2 = "AND alias.measurementTechniqueName = 'NAME' ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setMeasurementTechniqueCode("CODE");
        assertEquals(C0+C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterName() {
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
