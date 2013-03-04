package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
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
        Date beginDate = new Date();
        
        UserAssociation userAssociation = new UserAssociation(parent, child);
        Date endDate = new Date();
        
        assertSame(parent, userAssociation.getParent());
        assertSame(child, userAssociation.getChild());
        
        assertEquals('A', userAssociation.getResolution());
        assertNotNull(userAssociation.getAssociationDate());
        assertFalse(userAssociation.getAssociationDate().before(beginDate));
        assertFalse(userAssociation.getAssociationDate().after(endDate));
    }
	
	@Test
	public void reset() {
		UserAssociation userAssociation = new UserAssociation();
		userAssociation.reset();
		assertEquals(' ', userAssociation.getResolution());
		assertNull(userAssociation.getAssociationDate());
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
    
    
