package org.helianto.inventory.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.inventory.CardType;
import org.helianto.inventory.filter.CardSetFilterAdapter;
import org.helianto.inventory.form.CardSetForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Mauricio Fernandes de Castro
 */
public class CardSetFilterAdapterTests {

    public static String OB = "order by alias.internalNumber ";
    public static String C0 = "alias.entity.id = 1 ";
    public static String C1 = "AND alias.internalNumber = 9223372036854775807 ";
    public static String C2 = "AND alias.cardType = 'D' ";
    public static String C3 = "AND alias.processDocument.id = 2 ";
    public static String C4 = "AND alias.processDocument.docCode = 'CODE' ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getInternalNumber()).thenReturn(Long.MAX_VALUE);
        assertEquals(C0+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterType() {
    	Mockito.when(form.getCardType()).thenReturn(CardType.DATA.getPrefix());
        assertEquals(C0+C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterProcessDocumentId() {
    	Mockito.when(form.getProcessDocumentId()).thenReturn(2);
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterProcessDocumentCode() {
    	Mockito.when(form.getProcessDocumentCode()).thenReturn("CODE");
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    private CardSetFilterAdapter filter;
    private CardSetForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(CardSetForm.class);
    	filter = new CardSetFilterAdapter(form);
    	Mockito.when(form.getEntityId()).thenReturn(1);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}

