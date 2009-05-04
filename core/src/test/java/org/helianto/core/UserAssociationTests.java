package org.helianto.core;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

/**
 * <code>UserAssociation</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserAssociationTests extends TestCase {
    
    /**
     * Test <code>UserAssociation</code> static factory method.
     */
    public void testUserAssociationFactory() {
        UserGroup parent = new UserGroup();
        UserGroup child = new UserGroup();
        
        UserAssociation userAssociation = UserAssociation.userAssociationFactory(parent, child);
        
        assertSame(parent, userAssociation.getParent());
        assertTrue(parent.getChildAssociations().contains(userAssociation));
        assertSame(child, userAssociation.getChild());
        assertTrue(child.getParentAssociations().contains(userAssociation));
        
    }
    
    /**
     * Test <code>UserAssociation</code> equals() method.
     */
    public void testUserAssociationEquals() {
        UserGroup parent = new UserGroup();
        UserGroup child = new UserGroup();
        
        UserAssociation userAssociation = UserAssociation.userAssociationFactory(parent, child);
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
    
    
