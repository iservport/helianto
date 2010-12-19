package org.helianto.inventory;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.filter.classic.DateFilterMode;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessRequirementFilterTests {

    public static String C1 = "alias.entity.id = 0 ";
    public static String C2 = "AND alias.internalNumber = 9223372036854775807 ";
    public static String C3 = "AND (alias.requirementDate >= '1969-12-31 21:00:01' ) ";
    public static String C4 = "AND (alias.requirementDate >= '1969-12-24 23:59:59' AND alias.requirementDate < '1969-12-31 21:00:02' ) ";
    public static String C5 = "AND (alias.requirementDate >= '1969-12-31 21:00:01' AND alias.requirementDate < '1969-12-31 21:00:02' ) ";
    public static String O1 = "order by alias.requirementDate ";

    @Test
    public void empty() {
    	filter.setToDate(null);
        assertEquals(C1+O1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setInternalNumber(Long.MAX_VALUE);
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterNextCheckDateFrom() {
        filter.setFromDate(new Date(1000));
        filter.setToDate(null);
        assertEquals(C1+C3+O1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterNextCheckDateTo() {
    	filter.setDateFilterMode(DateFilterMode.TO_DATE_MINUS_INTERVAL);
        filter.setToDate(new Date(2000));
        assertEquals(C1+C4+O1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterNextCheckDateFromTo() {
    	filter.setDateFilterMode(DateFilterMode.FROM_DATE_TO_DATE);
        filter.setFromDate(new Date(1000));
        filter.setToDate(new Date(2000));
        assertEquals(C1+C5+O1, filter.createCriteriaAsString(false));
    }
    
    private ProcessRequirementFilter filter;
    
    @Before
    public void setUp() {
    	filter = new ProcessRequirementFilter(UserTestSupport.createUser());
    }
}
