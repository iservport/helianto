package org.helianto.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Service;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.junit.Test;

/**
 * <code>UserRole</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleTests  {
    
    /**
     * Test <code>UserRole</code> static factory method.
     */
	@Test
    public void userRoleFactory() {
        UserGroup userGroup = new UserGroup();
        Service service = new Service();
        String serviceExtension = DomainTestSupport.STRING_TEST_VALUE;
        
        UserRole userRole = new UserRole(userGroup, service, serviceExtension);
        
        assertSame(userGroup, userRole.getUserGroup());
        assertSame(service, userRole.getService());
        assertEquals(serviceExtension, userRole.getServiceExtension());
        
    }
    
    /**
     * Test <code>UserRole</code> equals() method.
     */
	@Test
    public void userRoleEquals() {
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
	
	@Test
	public void extension() {
		UserGroup userGroup = new UserGroup();
		Service service = new Service();
		UserRole userRole = new UserRole(userGroup, service, null);
		assertArrayEquals(userRole.getServiceExtensionAsArray(), new String[]{});
		
		userRole.setServiceExtension("");
		assertArrayEquals(userRole.getServiceExtensionAsArray(), new String[]{});
		
		userRole.setServiceExtension(" ");
		assertArrayEquals(userRole.getServiceExtensionAsArray(), new String[]{});
		
		userRole.setServiceExtension(" a");
		assertArrayEquals(userRole.getServiceExtensionAsArray(), new String[]{"a"});
		
		userRole.setServiceExtension(" a , b ");
		assertArrayEquals(userRole.getServiceExtensionAsArray(), new String[]{"a", "b"});
	}

}
