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

package org.helianto.core.filter;


import org.helianto.core.IdentityFilter;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFilterTests extends TestCase {

    public void testNameOrAliasSearch() {
        filter.setNameOrAliasSearch("nameOrAliasSearch");
        assertEquals("nameOrAliasSearch", filter.getNameOrAliasSearch());
    }

    public void testPrincipalSearch() {
        filter.setPrincipal("principalSearch");
        assertEquals("principalSearch", filter.getPrincipal());
    }
    
    private IdentityFilter filter;
    
    public void setUp() {
        filter = new IdentityFilter();
    }

}