package org.helianto.inventory.filter.classic;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.inventory.CardType;
import org.helianto.process.domain.Process;
import org.helianto.user.domain.User;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class CardSetFilterTests {

    public static String OB = "order by alias.internalNumber ";
    public static String C0 = "alias.entity.id = 0 ";
    public static String C1 = "AND alias.internalNumber = 9223372036854775807 ";
    public static String C2 = "AND alias.cardType = 'D' ";
    public static String C3 = "AND alias.processDocument.id = 1 ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setInternalNumber(Long.MAX_VALUE);
        assertEquals(C0+C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterType() {
        filter.setCardType(CardType.DATA.getPrefix());
        assertEquals(C0+C2+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterProcess() {
        filter.setProcess(new Process());
        filter.getProcess().setId(1);
        assertEquals(C0+C3+OB, filter.createCriteriaAsString(false));
    }
    
    private CardSetFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = CardSetFilter.cardSetFilterFactory(user);
    }
}

