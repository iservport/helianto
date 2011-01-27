package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.resource.ResourceAssociation;
import org.helianto.resource.ResourceGroup;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceAssociationFilterAdapterTests {

    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setParent(parent);
    	target.setChild(child);
        assertEquals("alias.parent.id = 1 AND alias.child.id = 2 ", filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	target.setParent(parent);
        assertEquals("alias.parent.id = 1 order by alias.child.resourceCode ", filter.createCriteriaAsString());
    }
    
    @Test
    public void child() {
    	target.setChild(child);
        assertEquals("alias.child.id = 2 order by alias.parent.resourceCode ", filter.createCriteriaAsString());
    }
    
    private ResourceAssociationFilterAdapter filter;
    private ResourceAssociation target;
    private ResourceGroup parent;
    private ResourceGroup child;
    
    @Before
    public void setUp() {
    	parent = new ResourceGroup();
    	parent.setId(1);
    	child = new ResourceGroup();
    	child.setId(2);
    	target = new ResourceAssociation();
    	filter = new ResourceAssociationFilterAdapter(target);
    }
}

