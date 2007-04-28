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

import org.helianto.core.dao.IdentitySelectionStrategy;
import org.helianto.core.filter.IdentityFilter;
import org.helianto.core.test.AuthorizationTestSupport;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentitySelectionStrategyTests extends TestCase {

    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        try {
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
            fail();
        } 
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    
    public void testCreateCriteriaAsStringFull() {
        filter.setUser(AuthorizationTestSupport.createUser());
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        filter.setPrincipalSearch("principalSearch");
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
    
    public void testCreateCriteriaAsStringPrincipal() {
        filter.setUser(AuthorizationTestSupport.createUser());
        filter.setNameOrAliasSearch("");
        filter.setPrincipalSearch("principalSearch");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        String expect = "where (" +
            "identity.id in (select user.identity.id from User user where user.entity.id = 0) and " +
            "lower(identity.principal) like '%principalsearch%' )";
        assertEquals(expect, criteria);
    }
    
    public void testCreateCriteriaAsStringNameOrAlias() {
        filter.setUser(AuthorizationTestSupport.createUser());
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        filter.setPrincipalSearch("");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        String expect = "where (" +
            "identity.id in (select user.identity.id from User user where user.entity.id = 0) and " +
            "lower(identity.optionalAlias) like '%nameoraliassearch%' " +
            "or lower(identity.firstName) like '%nameoraliassearch%' " +
            "or lower(identity.lastName) like '%nameoraliassearch%' )";
        assertEquals(expect, criteria);
    }
    
    public void testCreateCriteriaAsStringEmpty() {
        filter.setUser(AuthorizationTestSupport.createUser());
        filter.setNameOrAliasSearch("");
        filter.setPrincipalSearch("");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter, "identity");
        String expect = "where (" +
        "identity.id in (select user.identity.id from User user where user.entity.id = 0) )";
    assertEquals(expect, criteria);
    }
    
    private IdentitySelectionStrategy identitySelectionStrategy;
    private IdentityFilter filter;
    
    @Override
    public void setUp() {
        identitySelectionStrategy = 
            new DefaultIdentitySelectionStrategy();
        filter = new IdentityFilter();
    }
}
