package org.helianto.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>UserAssociation</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserAssociationTests {
    
    /**
     * Test <code>UserAssociation</code> static factory method.
     */
	@Test
    public void constructor() {
        UserGroup parent = new UserGroup();
        UserGroup child = new UserGroup();
        
        UserAssociation userAssociation = new UserAssociation(parent, child);
        
        assertSame(parent, userAssociation.getParent());
        assertSame(child, userAssociation.getChild());
        
    }
    
    /**
     * Test <code>UserAssociation</code> equals() method.
     */
	@Test
    public void userAssociationEquals() {
        UserGroup parent = new UserGroup();
        UserGroup child = new UserGroup();
        
        UserAssociation userAssociation = new UserAssociation(parent, child);
        UserAssociation copy = (UserAssociation) DomainTestSupport.minimalEqualsTest(userAssociation);
        
        copy.setParent(null);
        copy.setChild(child);
        assertFalse(userAssociation.equals(copy));

        copy.setParent(parent);
        copy.setChild(null);
        assertFalse(userAssociation.equals(copy));

        copy.setParent(parent);
        copy.setChild(child);

        assertTrue(userAssociation.equals(copy));
    }

}
    
    
