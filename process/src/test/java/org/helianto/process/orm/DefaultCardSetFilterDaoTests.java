package org.helianto.process.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.process.CardSetFilter;
import org.helianto.process.CardType;
import org.helianto.process.test.ProcessTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCardSetFilterDaoTests {

    public static String OB = "order by cardset.internalNumber ";
    public static String C0 = "cardset.entity.id = 0 ";
    public static String C1 = "AND cardset.internalNumber = 9223372036854775807 ";
    public static String C2 = "AND cardset.cardType = 'D' ";
    public static String C3 = "AND cardset.processDocument.id = 1 ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, cardSetDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setInternalNumber(Long.MAX_VALUE);
        assertEquals(C0+C1, cardSetDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterType() {
        filter.setCardType(CardType.DATA.getPrefix());
        assertEquals(C0+C2+OB, cardSetDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterProcess() {
        filter.setProcess(ProcessTestSupport.createProcess());
        filter.getProcess().setId(1);
        assertEquals(C0+C3+OB, cardSetDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultCardSetDao cardSetDao;
    private CardSetFilter filter;
    
    @Before
    public void setUp() {
    	filter = CardSetFilter.cardSetFilterFactory(UserTestSupport.createUser());
    	cardSetDao = new DefaultCardSetDao();
    }
}

