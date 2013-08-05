/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.user.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.def.EventType;
import org.helianto.core.domain.Identity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>UserLog</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserLogTests {
    
    /**
     * Test <code>UserLog</code> static factory method.
     */
	@Test
    public void userLogFactory() {
        User user = new User();
        Identity identity = new Identity();
        user.setIdentity(identity);
        Date lastEvent = DomainTestSupport.DATE_TEST_VALUE;
        
        UserLog userLog = new UserLog(user, lastEvent, EventType.LOGIN_ATTEMPT);
        
        assertSame(user, userLog.getUser());
        assertEquals(lastEvent, userLog.getLastEvent());
        assertEquals(EventType.LOGIN_ATTEMPT.getValue(), userLog.getEventType());
        
    }
    
    /**
     * Test <code>UserLog</code> equals() method.
     */
	@Test
    public void userLogEquals() {
        User user = new User();
        Identity identity = new Identity();
        user.setIdentity(identity);
        Date lastEvent = DomainTestSupport.DATE_TEST_VALUE;
        
        UserLog userLog = new UserLog(user, lastEvent, EventType.LOGIN_ATTEMPT);
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
    
    
