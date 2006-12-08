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

import org.helianto.core.dao.IdentityFilter;
import org.helianto.core.dao.IdentitySelectionStrategy;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentitySelectionStrategyTests extends TestCase {

    public void testCreateCriteriaAsStringFull() {
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        filter.setPrincipalSearch("principalSearch");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter );
        String expect = "where identity.identityType != 'G' and (" +
                "lower(identity.principal) like '%principalsearch%' " +
                "or lower(identity.optionalAlias) like '%nameoraliassearch%' " +
                "or lower(identity.firstName) like '%nameoraliassearch%' " +
                "or lower(identity.lastName) like '%nameoraliassearch%' )";
        assertEquals(expect, criteria);
    }
    
    public void testCreateCriteriaAsStringPrincipal() {
        filter.setNameOrAliasSearch("");
        filter.setPrincipalSearch("principalSearch");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter );
        String expect = "where identity.identityType != 'G' and (" +
                "lower(identity.principal) like '%principalsearch%' )";
        assertEquals(expect, criteria);
    }
    
    public void testCreateCriteriaAsStringNameOrAlias() {
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        filter.setPrincipalSearch("");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter );
        String expect = "where identity.identityType != 'G' and (" +
                "lower(identity.optionalAlias) like '%nameoraliassearch%' " +
                "or lower(identity.firstName) like '%nameoraliassearch%' " +
                "or lower(identity.lastName) like '%nameoraliassearch%' )";
        assertEquals(expect, criteria);
    }
    
    public void testCreateCriteriaAsStringEmpty() {
        filter.setNameOrAliasSearch("");
        filter.setPrincipalSearch("");
        String criteria = 
            identitySelectionStrategy.createCriteriaAsString(filter );
        assertEquals("", criteria);
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
