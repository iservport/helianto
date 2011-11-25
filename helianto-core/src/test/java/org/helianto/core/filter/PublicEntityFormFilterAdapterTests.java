package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.PublicEntity;
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
    public static String C3 = "AND alias.class=PublicEntity ";
    public static String C4 = "AND lower(alias.entityName) like '%name%' ";
    public static String C5 = "AND alias.publicEntityType = 'X' ";

    @Test
    public void operator() {
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeEntityForm) form).setOperator(operator);
    	((CompositeEntityForm) form).setEntity(entity);
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void entity() {
    	((CompositeEntityForm) form).setOperator(null);
    	((CompositeEntityForm) form).setEntity(entity);
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        form.setClazz(PublicEntity.class);
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
    	((CompositeEntityForm) form).setEntityName("NAME");
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
    	((CompositeEntityForm) form).setPublicEntityType('X');
        assertEquals(C1+C5+OB, filter.createCriteriaAsString());
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
    	operator.setId(1); // just because a zero here will prevent the filter to fire!
    	form = new CompositeEntityForm(operator);
    	filter = new PublicEntityFormFilterAdapter(form);
    }
}

