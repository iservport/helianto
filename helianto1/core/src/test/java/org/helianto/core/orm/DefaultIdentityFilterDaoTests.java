package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.filter.IdentityFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * <code>IdentityDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentityFilterDaoTests {

    public static String C1 = "identity.id in (select user.identity.id from User user where user.entity.id =  0 )  ";
    public static String C2 = "lower(identity.principal) like '%principal%' ";
    public static String C3 = "AND identity.optionalAlias like '%ALIAS%' ";

    @Test
    public void testEmpty() {
        assertEquals(C1, identityDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setPrincipal("PRINCIPAL");
    	filter.setEntity(null);
        assertEquals(C2, identityDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilter() {
        filter.setNameOrAliasSearch("ALIAS");
        assertEquals(C1+C3, identityDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultIdentityDao identityDao;
    private IdentityFilter filter;
    
    @Before
    public void setUp() {
    	filter = IdentityFilter.identityFilterFactory(UserTestSupport.createUser());
    	identityDao = new DefaultIdentityDao();
    }
}

