package org.helianto.inventory.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.inventory.form.CardForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Mauricio Fernandes de Castro
 */
public class CardFilterAdapterTests {

    public static String OB = "order by alias.cardLabel ";
    public static String C1 = "alias.cardSet.id = 1 ";
    public static String C2 = "alias.cardLabel = 'LABEL' ";
    public static String C3 = "alias.cardState = 'X' ";

    @Test
    public void empty() {
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getCardSetId()).thenReturn(1);
    	Mockito.when(form.getCardLabel()).thenReturn("LABEL");
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void cardLabel() {
    	Mockito.when(form.getCardLabel()).thenReturn("LABEL");
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void cardState() {
    	Mockito.when(form.getCardState()).thenReturn('X');
        assertEquals(C3+OB, filter.createCriteriaAsString());
    }
    
    private CardFilterAdapter filter;
    private CardForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(CardForm.class);
    	filter = new CardFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}


