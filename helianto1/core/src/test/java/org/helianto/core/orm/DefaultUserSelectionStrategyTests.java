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

package org.helianto.core.orm;

import junit.framework.TestCase;

import org.helianto.core.UserFilter;
import org.helianto.core.dao.UserSelectionStrategy;
import org.helianto.core.test.UserTestSupport;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserSelectionStrategyTests extends TestCase {
    
    public static String USER_CRITERIA = "user.entity.id = 0 AND user.userState = 'A' ";
    public static String USER_PRINCIPAL_CRITERIA = "user.entity.id = 0 AND lower(user.identityPrincipal) like '%principalsearch%' AND user.userState = 'A' ";

    private UserSelectionStrategy userSelectionStrategy;

    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        try {
            userSelectionStrategy.createCriteriaAsString(filter, "identity");
            fail("expected exception");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        catch (Exception e) {
            fail("expected previous exception");
        }
    }
    
    public void testCreateCriteriaAsStringUser() {
        assertEquals(USER_CRITERIA, userSelectionStrategy.createCriteriaAsString(filter, "user"));
    }
    
    public void testCreateCriteriaAsStringPrincipal() {
        filter.setUser(UserTestSupport.createUser());
        filter.setIdentityPrincipal("principalSearch");
        String criteria = 
            userSelectionStrategy.createCriteriaAsString(filter, "user");
        
        assertEquals(USER_PRINCIPAL_CRITERIA, criteria);
    }
    
    private UserFilter filter;
    
    @Override
    public void setUp() {
        userSelectionStrategy = 
            new DefaultUserSelectionStrategy();
        filter = new UserFilter();
        filter.reset();
        filter.setUser(UserTestSupport.createUser());
    }
}
