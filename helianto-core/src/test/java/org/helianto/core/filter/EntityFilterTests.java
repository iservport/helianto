package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Operator;
import org.helianto.core.form.CompositeEntityForm;
import org.helianto.core.form.EntityForm;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilterTests {

    public static String ORDER = "order by alias.alias ";
    public static String C1 = "alias.operator.id = 1 ";
    public static String C2 = "alias.alias = 'ENTITY' ";
    public static String C3 = "alias.activityState = 'A' ";
    public static String C4 = "lower(alias.nature) like '%x%' ";

    @Test
    public void empty() {
        assertEquals(C1+ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeEntityForm) form).setEntityAlias("ENTITY");
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void alias() {
    	((CompositeEntityForm) form).setEntityAlias("ENTITY");
    	((CompositeEntityForm) form).setOperator(null);
        assertEquals(C2+ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void activityState() {
    	((CompositeEntityForm) form).setActivityState(ActivityState.ACTIVE.getValue());
        assertEquals(C1+"AND "+C3+ORDER, filter.createCriteriaAsString());
    }
    
    @Test
    public void nature() {
    	((CompositeEntityForm) form).setNature('X');
        assertEquals(C1+"AND "+C4+ORDER, filter.createCriteriaAsString());
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

