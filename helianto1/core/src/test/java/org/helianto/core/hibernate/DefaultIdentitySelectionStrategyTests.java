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

package org.helianto.core.hibernate;

import org.helianto.core.IdentityFilter;
import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.orm.DefaultIdentitySelectionStrategy;
import org.helianto.core.test.UserTestSupport;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentitySelectionStrategyTests extends TestCase {
    
    public static String USER_CRITERIA = "identity.id in (" +
        "select user.identity.id from User user where user.entity.id =  0 )  ";
    public static String USER_PRINCIPAL_CRITERIA = USER_CRITERIA +
        "AND lower(identity.principal) like '%principalsearch%' ";

    private IdentitySelectionStrategy identitySelectionStrategy;

    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        try {
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
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
        assertEquals(USER_CRITERIA, identitySelectionStrategy.createCriteriaAsString(filter, "identity"));
    }
    
    public void testCreateCriteriaAsStringPrincipal() {
        filter.setUser(UserTestSupport.createUser());
        filter.setNameOrAliasSearch("");
        filter.setPrincipal("principalSearch");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        
        assertEquals(USER_PRINCIPAL_CRITERIA, criteria);
    }
    
    // TODO activate test when corresponding method is ok
    public void pendingTestCreateCriteriaAsStringFull() {
        filter.setUser(UserTestSupport.createUser());
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        filter.setPrincipal("principalSearch");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        String expect = "where (" +
            "identity.id in (select user.identity.id from User user where user.entity.id = 0) and " +
            "lower(identity.principal) like '%principalsearch%' " +
                "or lower(identity.optionalAlias) like '%nameoraliassearch%' " +
                "or lower(identity.firstName) like '%nameoraliassearch%' " +
                "or lower(identity.lastName) like '%nameoraliassearch%' )";
        assertEquals(expect, criteria);
    }
    
    // TODO activate test when corresponding method is ok
    public void pendingTestCreateCriteriaAsStringNameOrAlias() {
        filter.setUser(UserTestSupport.createUser());
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        filter.setPrincipal("");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        String expect = "where (" +
            "identity.id in (select user.identity.id from User user where user.entity.id = 0) and " +
            "lower(identity.optionalAlias) like '%nameoraliassearch%' " +
            "or lower(identity.firstName) like '%nameoraliassearch%' " +
            "or lower(identity.lastName) like '%nameoraliassearch%' )";
        assertEquals(expect, criteria);
    }
    
    private IdentityFilter filter;
    
    @Override
    public void setUp() {
        identitySelectionStrategy = 
            new DefaultIdentitySelectionStrategy();
        filter = new IdentityFilter();
        filter.setUser(UserTestSupport.createUser());
    }
}
