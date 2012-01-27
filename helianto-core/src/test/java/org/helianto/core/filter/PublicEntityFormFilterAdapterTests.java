package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.core.filter.form.PublicEntityForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PublicEntityFormFilterAdapterTests {

    public static String OB = "order by alias.entity.alias ";
    public static String C1 = "alias.entity.operator.id = 1 ";
    public static String C2 = "alias.entity.id = 2 ";
    public static String C3 = "alias.entityAlias = 'ALIAS' ";
    public static String C4 = "alias.class = 'P' ";
    public static String C5 = "lower(alias.entityName) like '%name%' ";

    @Test
    public void operator() {
    	operator.setId(1); // just because a zero here will prevent the filter to fire!
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeEntityForm) form).setEntity(entity);
    	((CompositeEntityForm) form).setEntityAlias("ALIAS");
        assertEquals(C2+"AND "+C3, filter.createCriteriaAsString());
    }
    
    @Test
    public void entity() {
    	((CompositeEntityForm) form).setOperator(null);
    	((CompositeEntityForm) form).setEntity(entity);
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
        form.setType('P');
        assertEquals(C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
    	((CompositeEntityForm) form).setEntityName("NAME");
        assertEquals(C5+OB, filter.createCriteriaAsString());
    }
    
    private PublicEntityFormFilterAdapter filter;
    private PublicEntityForm form;
    
    private Operator operator;
    private Entity entity;
    
 	@Before
    public void setUp() {
    	entity = EntityTestSupport.createEntity();
    	entity.setId(2); // just because a zero here will prevent the filter to fire!
    	operator = entity.getOperator();
    	form = new CompositeEntityForm(operator);
    	filter = new PublicEntityFormFilterAdapter(form);
    }
}

