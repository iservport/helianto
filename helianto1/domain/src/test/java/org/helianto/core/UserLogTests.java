package org.helianto.core;

import java.util.Date;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.type.UserEventType;

import junit.framework.TestCase;

import org.helianto.core.User;

import org.helianto.core.UserLog;

/**
 * <code>UserLog</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserLogTests extends TestCase {
    
    /**
     * Test <code>UserLog</code> static factory method.
     */
    public void testUserLogFactory() {
        User user = new User();
        Identity identity = new Identity();
        user.setIdentity(identity);
        Date lastEvent = DomainTestSupport.DATE_TEST_VALUE;
        
        UserLog userLog = UserLog.userLogFactory(user, lastEvent);
        
        assertSame(user, userLog.getUser());
        assertEquals(lastEvent, userLog.getLastEvent());
        assertEquals(userLog.getUser().getIdentity().getLastLogin(), lastEvent);
        assertEquals(UserEventType.LOGIN_SUCCESS.getValue(), userLog.getEventType());
        
    }
    
    /**
     * Test <code>UserLog</code> equals() method.
     */
    public void testUserLogEquals() {
        User user = new User();
        Identity identity = new Identity();
        user.setIdentity(identity);
        Date lastEvent = DomainTestSupport.DATE_TEST_VALUE;
        
        UserLog userLog = UserLog.userLogFactory(user, lastEvent);
        UserLog copy = (UserLog) DomainTestSupport.minimalEqualsTest(userLog);
        
        copy.setUser(null);
        copy.setLastEvent(lastEvent);
        assertFalse(userLog.equals(copy));

        copy.setUser(user);
        copy.setLastEvent(null);
        assertFalse(userLog.equals(copy));

        copy.setUser(user);
        copy.setLastEvent(lastEvent);

        assertTrue(userLog.equals(copy));
    }

}
    
    
