package org.helianto.partner.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.form.PrivateSegmentForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Mauricio Fernandes de Castro
 */
public class PrivateSegmentFilterAdapterTests {

	public static String OB = "order by alias.segmentAlias ";
    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.segmentAlias = 'CODE' ";
    public static String C3 = "AND lower(alias.segmentName) like '%name%' ";
    public static String C4 = "AND alias.segmentType = 'X' ";

    @Test
    public void empty() {
    	Mockito.when(form.getEntity()).thenReturn(null);
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getSegmentAlias()).thenReturn("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
    	Mockito.when(form.getSegmentName()).thenReturn("NAME");
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
    	Mockito.when(form.getSegmentType()).thenReturn('X');
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    private PrivateSegmentForm form;
    private PrivateSegmentFilterAdapter filter;
    private Entity entity;
    
    @Before
    public void setUp() {
    	entity = EntityTestSupport.createEntity(1);
    	form = Mockito.mock(PrivateSegmentForm.class);
    	filter = new PrivateSegmentFilterAdapter(form);
    	Mockito.when(form.getEntity()).thenReturn(entity);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
}

