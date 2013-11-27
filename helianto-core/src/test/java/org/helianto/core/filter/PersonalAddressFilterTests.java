package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.def.AddressType;
import org.helianto.core.form.PersonalAddressForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PersonalAddressFilterTests {

    public static String ORDER = "order by alias.addressType ";
    public static String C1 = "alias.identity.id = 1 ";
    public static String C2 = "AND alias.addressType = 'C' ";

    @Test
    public void empty() {
    	Mockito.when(form.getIdentityId()).thenReturn(0);
        assertEquals(ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getAddressType()).thenReturn(AddressType.COLLECTION.getValue());
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    private PersonalAddressFormFilterAdapter filter;
    private PersonalAddressForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(PersonalAddressForm.class);
    	filter = new PersonalAddressFormFilterAdapter(form);
    	Mockito.when(form.getIdentityId()).thenReturn(1);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}

