package org.helianto.inventory.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.inventory.form.TaxForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Mauricio Fernandes de Castro
 */
public class TaxFilterAdapterTests {

    public static String OB = "order by alias.keyType.keyCode ";
    public static String C0 = "alias.processAgreement.id = 1 ";
    public static String C1 = "alias.keyType.id = 2 ";
    public static String C2 = "alias.keyType.keyCode = 'CODE' ";

    @Test
    public void empty() {
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getProcessAgreementId()).thenReturn(1);
    	Mockito.when(form.getKeyTypeId()).thenReturn(2);
        assertEquals(C0+"AND "+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void processAgreement() {
    	Mockito.when(form.getProcessAgreementId()).thenReturn(1);
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void keyType() {
    	Mockito.when(form.getKeyTypeId()).thenReturn(2);
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void keyCode() {
    	Mockito.when(form.getKeyCode()).thenReturn("CODE");
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    private TaxFilterAdapter filter;
    private TaxForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(TaxForm.class);
    	filter = new TaxFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}

