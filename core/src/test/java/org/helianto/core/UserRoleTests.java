package org.helianto.core;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.UserGroup;
import org.helianto.core.Service;

import org.helianto.core.UserRole;

/**
 * <code>UserRole</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleTests extends TestCase {
    
    /**
     * Test <code>UserRole</code> static factory method.
     */
    public void testUserRoleFactory() {
        UserGroup userGroup = new UserGroup();
        Service service = new Service();
        String serviceExtension = DomainTestSupport.STRING_TEST_VALUE;
        
        UserRole userRole = UserRole.userRoleFactory(userGroup, service, serviceExtension);
        
        assertSame(userGroup, userRole.getUserGroup());
        assertSame(service, userRole.getService());
        assertEquals(serviceExtension, userRole.getServiceExtension());
        
    }
    
    /**
     * Test <code>UserRole</code> equals() method.
     */
    public void testUserRoleEquals() {
        UserRole copy, userRole = new UserRole();
        userRole.setUserGroup(new UserGroup());
        userRole.setService(new Service());
        copy = (UserRole) DomainTestSupport.minimalEqualsTest(userRole);

        copy.setUserGroup(userRole.getUserGroup());
        assertFalse(userRole.equals(copy));

        copy.setUserGroup(null);
        copy.setService(userRole.getService());
        assertFalse(userRole.equals(copy));

        copy.setUserGroup(userRole.getUserGroup());
        copy.setService(userRole.getService());
        assertTrue(userRole.equals(copy));
    }

}
