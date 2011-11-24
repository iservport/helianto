package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Operator;
import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.core.filter.form.EntityForm;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilterTests {

    public static String ORDER = "order by alias.alias ";
    public static String C1 = "alias.operator.id = 1 ";
    public static String C2 = "AND alias.alias = 'ENTITY' ";

    @Test
    public void empty() {
        assertEquals(C1+ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeEntityForm) form).setEntityAlias("ENTITY");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    private EntityFormFilterAdapter filter;
    private EntityForm form;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	form = new CompositeEntityForm(operator);
    	filter = new EntityFormFilterAdapter(form);
    }
    
}

