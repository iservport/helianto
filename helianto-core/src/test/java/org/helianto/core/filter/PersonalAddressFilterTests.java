package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Identity;
import org.helianto.core.PersonalAddress;
import org.helianto.core.def.AddressType;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PersonalAddressFilterTests {

    public static String ORDER = "order by alias.addressType ";
    public static String C1 = "alias.identity.id = 1 ";
    public static String C2 = "AND alias.addressType = '1' ";

    @Test
    public void empty() {
        assertEquals(ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	identity.setId(1);
    	form.setAddressTypeAsEnum(AddressType.COLLECTION);
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    private PersonalAddressFilterAdapter filter;
    private Identity identity;
    private PersonalAddress form;
    
    @Before
    public void setUp() {
    	identity = new Identity("PRINCIPAL");
    	form = new PersonalAddress(identity, null);
    	filter = new PersonalAddressFilterAdapter(form);
    }
    
}

