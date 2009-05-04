package org.helianto.partner.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.partner.PartnerRegistryFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerRegistryFilterDaoTests {

    public static String C1 = "partnerregistry.entity.id = 0 ";
    public static String C2 = "AND partnerregistry.partnerAlias = 'ALIAS' ";
    public static String C3 = "AND lower(partnerregistry.partnerName) like '%name%' ";

    @Test
    public void testEmpty() {
        assertEquals(C1, partnerRegistryDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setPartnerAlias("ALIAS");
        assertEquals(C1+C2, partnerRegistryDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilter() {
        filter.setPartnerNameLike("NAME");
        assertEquals(C1+C3, partnerRegistryDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultPartnerRegistryDao partnerRegistryDao;
    private PartnerRegistryFilter filter;
    
    @Before
    public void setUp() {
    	filter = PartnerRegistryFilter.partnerRegistryFilterFactory(UserTestSupport.createUser());
    	partnerRegistryDao = new DefaultPartnerRegistryDao();
    }
}

