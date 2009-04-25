package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.UserLogFilter;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserLogFilterDaoTests {

    public static String C1 = "userlog.entity.id = 0 ";
    public static String C2 = "AND userlog.user.identity.id = 1 ";
    public static String C3 = "AND (userlog.lastEvent >= '1969-12-31 21:00:01' ) ";
    public static String C4 = "AND (userlog.lastEvent < '1969-12-31 21:00:02' ) ";
    public static String C5 = "AND (userlog.lastEvent >= '1969-12-31 21:00:01' AND userlog.lastEvent < '1969-12-31 21:00:02' ) ";

    @Test
    public void testEmpty() {
        assertEquals(C1, userLogDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterIdentity() {
        filter.setIdentity(IdentityTestSupport.createIdentity());
        filter.getIdentity().setId(1);
        assertEquals(C1+C2, userLogDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterFromDate() {
        filter.setFromDate(new Date(1000));
        assertEquals(C1+C3, userLogDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterToDate() {
        filter.setToDate(new Date(2000));
        assertEquals(C1+C4, userLogDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterDateRange() {
        filter.setFromDate(new Date(1000));
        filter.setToDate(new Date(2000));
        assertEquals(C1+C5, userLogDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterAll() {
        filter.setIdentity(IdentityTestSupport.createIdentity());
        filter.getIdentity().setId(1);
        filter.setFromDate(new Date(1000));
        filter.setToDate(new Date(2000));
        assertEquals(C1+C5+C2, userLogDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultUserLogDao userLogDao;
    private UserLogFilter filter;
    
    @Before
    public void setUp() {
    	filter = UserLogFilter.userLogFilterFactory(UserTestSupport.createUser());
    	userLogDao = new DefaultUserLogDao();
    }
}

