package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.PublicEntity;
import org.helianto.core.filter.form.AbstractPublicEntityForm;
import org.helianto.core.filter.form.PublicEntityForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PublicEntityFormFilterAdapterTests {

    public static String OB = "order by alias.entity.alias ";
    public static String C1 = "alias.operator.id = 1 ";
    public static String C2 = "AND alias.entity.id = 2 ";
    public static String C3 = "AND alias.class=PublicEntity ";
    public static String C4 = "AND lower(alias.entityName) like '%name%' ";
    public static String C5 = "AND alias.publicEntityType = 'X' ";

    @Test
    public void operator() {
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((AbstractPublicEntityForm) form).setEntity(entity);
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        form.setClazz(PublicEntity.class);
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
    	((AbstractPublicEntityForm) form).setEntityName("NAME");
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
    	((AbstractPublicEntityForm) form).setPublicEntityType('X');
        assertEquals(C1+C5+OB, filter.createCriteriaAsString());
    }
    
    private PublicEntityFormFilterAdapter filter;
    private PublicEntityForm form;
    
    private Operator operator;
    private Entity entity;
    
    @SuppressWarnings("serial")
	@Before
    public void setUp() {
    	entity = EntityTestSupport.createEntity();
    	entity.setId(2); // just because a zero here will prevent the filter to fire!
    	operator = entity.getOperator();
    	operator.setId(1); // just because a zero here will prevent the filter to fire!
    	form = new AbstractPublicEntityForm() {
    		@Override
    		public Operator getOperator() { return operator; }
    	};
    	filter = new PublicEntityFormFilterAdapter(form);
    }
}

