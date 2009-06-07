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

package org.helianto.core.service;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class CreateManagerTest extends AbstractTransactionalSpringContextTests {
    
    private ServerMgr serverMgr;
    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }
    
    private NamespaceMgr operatorMgr;
    
    public void setOperatorMgr(NamespaceMgr operatorMgr) {
        this.operatorMgr = operatorMgr;
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/support.xml",
                "deploy/core.xml",
                "deploy/org.helianto.core.xml"
                };
    }

    /*
     * 
     */
    
    public void testCreateManager() {
        String entityName = "TEST";
        String principal = "TEST";
        String optionalALias = "TEST";

        Operator operator = operatorMgr.findOperatorByName("DEFAULT");
        if (operator==null) {
            operator = Operator.operatorFactory("DEFAULT", null);
        }
        Entity entity = Entity.entityFactory(operator, entityName );
        
        Identity identity = Identity.identityFactory(principal, optionalALias);
        serverMgr.storeManager(entity, identity);

    }

}
