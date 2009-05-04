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

package org.helianto.partner.orm;

import org.helianto.core.test.AbstractIntegrationTest;

/**
 * Common config attributes.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractPartnerDaoImplConfig extends
        AbstractIntegrationTest {

    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "META-INF/spring/dataSource.xml",
                "META-INF/spring/sessionFactory.xml",
                "META-INF/spring/transaction.xml",
                "META-INF/spring/support.xml",
                "META-INF/spring/core-context.xml",
                "META-INF/spring/partner-context.xml"
                };
    }
    
}