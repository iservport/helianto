package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Operator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class OperatorFilterAdapterTests {

    public static String C1 = "alias.operatorName = 'NAME' ";
    public static String C2 = "lower(alias.operatorName) like '%name%' ";
    
    @Test
    public void constructor() {
    	assertSame(target, filter.getForm());
    	assertTrue(filter.isUniqueName());
    	
    	filter = new OperatorFilterAdapter("DEFAULT");
    	assertEquals("DEFAULT", filter.getForm().getOperatorName());
    	assertTrue(filter.isUniqueName());
    	
    	filter = new OperatorFilterAdapter("DEFAULT", false);
    	assertEquals("DEFAULT", filter.getForm().getOperatorName());
    	assertFalse(filter.isUniqueName());
    }

    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setOperatorName("NAME");
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void filter() {
        target.setOperatorName("NAME");
        filter.setUniqueName(false);
        assertEquals(C2, filter.createCriteriaAsString());
    }
    
    private OperatorFilterAdapter filter;
    private Operator target;
    
    @Before
    public void setUp() {
    	target = new Operator();
    	filter = new OperatorFilterAdapter(target);
    }
    
}

