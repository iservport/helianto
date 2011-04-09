package org.helianto.partner.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.PublicEntity;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PublicEntityFilterAdapterTests {

    @Test
    public void constructor() {
    	Entity entity = EntityTestSupport.createEntity();
    	filter = new PublicEntityFilterAdapter(entity);
		assertEquals(entity.getOperator(), filter.getForm().getOperator());
		assertEquals(entity, filter.getForm().getEntity());
	}
	
    public static String OB = "order by alias.entity.alias ";
    public static String C1 = "alias.operator.id = 1 ";
    public static String C2 = "AND alias.entity.alias = 'ALIAS' ";
    public static String C3 = "AND alias.class=PublicEntity ";
    public static String C4 = "AND lower(alias.entityName) like '%name%' ";
    public static String C5 = "AND alias.publicEntityType = 'X' ";

    @Test
    public void empty() {
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	sample.setEntityAlias("ALIAS");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(PublicEntity.class);
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
    	sample.setEntityName("NAME");
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
    	sample.setPublicEntityType('X');
        assertEquals(C1+C5+OB, filter.createCriteriaAsString());
    }
    
    private PublicEntityFilterAdapter filter;
    private PublicEntitySampler sample;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity();
    	entity.getOperator().setId(1); // just because a zero here will prevent the filter to fire!
    	sample = new PublicEntitySampler(entity);
    	filter = new PublicEntityFilterAdapter(sample);
    }
}

