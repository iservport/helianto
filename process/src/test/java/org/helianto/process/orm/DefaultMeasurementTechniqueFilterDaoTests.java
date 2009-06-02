package org.helianto.process.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.process.MeasurementTechniqueFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultMeasurementTechniqueFilterDaoTests {

    public static String OB = "order by measurementtechnique.measurementTechniqueCode ";
    public static String C0 = "measurementtechnique.entity.id = 0 ";
    public static String C1 = "AND measurementtechnique.measurementTechniqueCode = 'CODE' ";
    public static String C2 = "AND measurementtechnique.measurementTechniqueName = 'NAME' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, measurementTechniqueDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setMeasurementTechniqueCode("CODE");
        assertEquals(C0+C1, measurementTechniqueDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterName() {
        filter.setMeasurementTechniqueName("NAME");
        assertEquals(C0+C2+OB, measurementTechniqueDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultMeasurementTechniqueDao measurementTechniqueDao;
    private MeasurementTechniqueFilter filter;
    
    @Before
    public void setUp() {
    	filter = MeasurementTechniqueFilter.measurementTechniqueFilterFactory(UserTestSupport.createUser());
    	measurementTechniqueDao = new DefaultMeasurementTechniqueDao();
    }
}

