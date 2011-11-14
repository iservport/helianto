package org.helianto.resource.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.filter.ResourceAssociationFormFilterAdapter;
import org.helianto.resource.form.CompositeResourceForm;
import org.helianto.resource.form.ResourceAssociationForm;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceAssociationFormFilterAdapterTests {

    @Test
    public void empty() {
    	form.setParent(null);
        assertEquals("", filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.setChild(child);
        assertEquals("alias.parent.id = 1 AND alias.child.id = 2 ", filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
        assertEquals("alias.parent.id = 1 order by alias.child.resourceCode ", filter.createCriteriaAsString());
    }
    
    @Test
    public void child() {
    	form.setParent(null);
    	form.setChild(child);
        assertEquals("alias.child.id = 2 order by alias.parent.resourceCode ", filter.createCriteriaAsString());
    }
    
    private ResourceAssociationFormFilterAdapter filter;
    private ResourceAssociationForm form;
    private ResourceGroup parent;
    private ResourceGroup child;
    
    @Before
    public void setUp() {
    	parent = new ResourceGroup();
    	parent.setId(1);
    	child = new ResourceGroup();
    	child.setId(2);
    	form = new CompositeResourceForm(parent);
    	filter = new ResourceAssociationFormFilterAdapter(form);
    }
}

