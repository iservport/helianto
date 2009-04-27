package org.helianto.partner.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerState;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerFilterDaoTests {

    public static String OB = "order by partner.partnerRegistry.partnerAlias ";
    public static String C1 = "partner.partnerRegistry.entity.id = 1 ";
    public static String C2 = "AND partner.class=Partner ";
    public static String C3 = "AND partner.priority = '0' ";
    public static String C4 = "AND partner.partnerRegistry.partnerAlias = 'ALIAS' ";
    public static String C5 = "AND lower(partner.partnerRegistry.partnerName) like '%name%' ";
    public static String C6 = "AND partner.partnerState = 'A' ";

    @Test
    public void testEmpty() {
        assertEquals(C1+C2+C3+OB, partnerDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setPartnerAlias("ALIAS");
        assertEquals(C1+C2+C4, partnerDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterName() {
        filter.setPartnerNameLike("NAME");
        assertEquals(C1+C2+C5+C3+OB, partnerDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterState() {
        filter.setPartnerState(PartnerState.ACTIVE.getValue());
        assertEquals(C1+C2+C6+C3+OB, partnerDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultPartnerDao partnerDao;
    private PartnerFilter filter;
    
    @Before
    public void setUp() {
    	filter = PartnerFilter.partnerFilterFactory(UserTestSupport.createUser());
    	filter.getEntity().setId(1);
    	partnerDao = new DefaultPartnerDao();
    }
}

